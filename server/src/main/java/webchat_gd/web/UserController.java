package webchat_gd.web;

import java.io.File;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;

import webchat_gd.dto.UserInfoDto;
import webchat_gd.entity.User;
import webchat_gd.entity.UserLoginRecord;
import webchat_gd.service.UserService;
import webchat_gd.utils.FileUtil;

@RequestMapping("/webchat/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 在配置文件中配置的文件保存路径
	 */
	@Value("${files.location}")
	private String location;

	/**
	 * 静态文件保存路径，用于跳过shiro的过滤
	 */
	@Value("${files.imagePath}")
	private String imagePath;

	@GetMapping("/list/detail")
	public PageInfo<User> selectUser(User user, int pageNum, int pageSize) {
		return userService.selectUser(user, pageNum, pageSize);
	}

	@GetMapping("/list")
	public PageInfo<UserInfoDto> findUserInfo(UserInfoDto userInfoDto, int pageNum, int pageSize) {
		return userService.findUserInfo(userInfoDto, pageNum, pageSize);
	}

	@GetMapping("/info")
	public UserInfoDto getUserInfo() {
		String userId = (String) SecurityUtils.getSubject().getSession().getAttribute("userId");
		UserInfoDto userInfoDto = userService.findUserInfoById(userId);
		return userInfoDto;
	}

	@PutMapping("/info")
	public boolean updateUserInfo(UserInfoDto userInfoDto) throws Exception {

		if (userInfoDto.getUserId() == null && "".equals(userInfoDto.getUserId())) {
			throw new Exception("用户id不能为空");
		}

		boolean result = userService.updateUserInfo(userInfoDto);

		return result;
	}

	@GetMapping("/login/message")
	public PageInfo<UserLoginRecord> selectUserLoginRecord(UserLoginRecord userLoginRecord, int pageNum, int pageSize) {
		return userService.selectUserLoginRecord(userLoginRecord, pageNum, pageSize);
	}

	@PostMapping("/photo")
	public String uploadProfilePhoto(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new Exception("上传图片不能为空");
		}

		int lastIndexOfPoint = file.getOriginalFilename().lastIndexOf(".");
		if (lastIndexOfPoint == -1) {
			throw new Exception("文件格式名错误");
		}

		String fileType = file.getOriginalFilename().substring(lastIndexOfPoint);

		String newFileName = UUID.randomUUID().toString() + fileType;

		FileUtil.savePic(file.getInputStream(), newFileName, location + File.separator + imagePath);

		String imageUrl = "/" + imagePath + "/" + newFileName;

		return imageUrl;
	}

}
