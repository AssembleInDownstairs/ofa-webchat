package webchat_gd.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import webchat_gd.dto.LoginMessageDto;
import webchat_gd.dto.UserInfoDto;
import webchat_gd.entity.User;
import webchat_gd.entity.UserLoginRecord;
import webchat_gd.service.UserService;
import webchat_gd.utils.VerifyCodeUtils;

@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public boolean register(User user) throws Exception {

		// 校验用户账号是否已存在
		User validate = new User();
		validate.setUserAccount(user.getUserAccount());
		PageInfo<User> selectResult = userService.selectUser(validate, 1, 1);
		if (selectResult.getList().size() > 0) {
			throw new Exception("该用户账号已存在");
		}

		return userService.register(user);
	}

	@PostMapping("/login")
	@ResponseBody
	public Map<String, String> login(LoginMessageDto loginMessageDto) throws Exception {

		System.out.println(new Gson().toJson(loginMessageDto).toString());

		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		// 判断当前session是否存在
		if (session == null) {
			throw new Exception("获取当前session不存在");
		}

		String username = loginMessageDto.getUsername();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,
				loginMessageDto.getPassword());
		Map<String, String> result = new HashMap<>();
		try {
			subject.login(usernamePasswordToken);

			// 把用户id存进session里面
			UserInfoDto userInfoDto = new UserInfoDto();
			userInfoDto.setUserAccount(username);
			PageInfo<UserInfoDto> selectUser = userService.findUserInfo(userInfoDto, 1, 1);
			UserInfoDto userInfo = selectUser.getList().get(0);
			session.setAttribute("userId", userInfo.getUserId());

			// 记录用户登录信息
			UserLoginRecord userLoginRecord = new UserLoginRecord();
			userLoginRecord.setLoginRecordId(UUID.randomUUID().toString());
			userLoginRecord.setRecordTime(new Date());
			userLoginRecord.setUserId(userInfo.getUserId());
			userService.recordLoginInformation(userLoginRecord);

			result.put("token", session.getId().toString());
			result.put("msg", "登录成功");
		} catch (IncorrectCredentialsException e) {
			result.put("msg", "密码错误");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 获取当前session的sessionId.
	 * 
	 * @return
	 */
	@GetMapping("/session")
	@ResponseBody
	public Serializable getSessionId() {
		Session session = SecurityUtils.getSubject().getSession(true);
		Serializable id = session.getId();
		return id;
	}

	/**
	 * 获取当前session的校验码.
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getVerifyCode")
	@ResponseBody
	public String getVerifyCode() throws Exception {
		Session session = SecurityUtils.getSubject().getSession(false);
		// 判断当前session是否存在
		if (session == null) {
			throw new Exception("获取当前session不存在");
		}
		String verifyCode = (String) session.getAttribute("verifyCode");
		return verifyCode;
	}

	/**
	 * 生成校验码,并以图片的格式返回.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("/generateVerifyCode")
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Session session = SecurityUtils.getSubject().getSession(false);
			// 判断当前session是否存在
			if (session == null) {
				throw new Exception("获取当前session不存在");
			}

			// 生成随机验证码，把验证码存到session里面并把验证码以图片的形式发给前端
			ServletOutputStream outputStream = response.getOutputStream();
			String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
			// 在session中保存验证码，用于登录校验时作为MD5加密的盐值
			session.setAttribute("verifyCode", verifyCode);

			VerifyCodeUtils.outputImage(80, 32, outputStream, verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
