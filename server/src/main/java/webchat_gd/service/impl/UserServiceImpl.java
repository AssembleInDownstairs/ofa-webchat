package webchat_gd.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import webchat_gd.dto.UserInfoDto;
import webchat_gd.entity.User;
import webchat_gd.entity.UserLoginRecord;
import webchat_gd.mapper.UserLoginRecordMapper;
import webchat_gd.mapper.UserMapper;
import webchat_gd.mapper.UserServiceMapper;
import webchat_gd.service.UserService;

/**
 * 用户模块实现类.
 * 
 * @author hys58
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserServiceMapper userServiceMapper;

	@Autowired
	private UserLoginRecordMapper userLoginRecordMapper;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean register(User user) {
		// 插入用户数据
		user.setUserId(UUID.randomUUID().toString());
		user.setCreateTime(new Date());
		userMapper.insert(user);
		return true;
	}

	@Override
	public PageInfo<User> selectUser(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> data = userMapper.selectUser(user);
		PageInfo<User> result = new PageInfo<>(data);
		return result;
	}

	@Override
	public UserInfoDto findUserInfoById(String userId) {
		UserInfoDto result = userServiceMapper.findUserInfoById(userId);
		return result;
	}

	@Override
	public PageInfo<UserInfoDto> findUserInfo(UserInfoDto userInfoDto, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserInfoDto> data = userServiceMapper.findUserInfo(userInfoDto);
		PageInfo<UserInfoDto> result = new PageInfo<>(data);
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateUserInfo(UserInfoDto userInfoDto) {
		try {
			int result = userServiceMapper.updateUserInfo(userInfoDto);

			// 校验用户账号是否已存在
			User validate = new User();
			validate.setUserAccount(userInfoDto.getUserAccount());
			PageInfo<User> selectResult = selectUser(validate, 1, 1);
			if (selectResult.getList().size() > 1) {
				throw new Exception("该用户账号已存在");
			}

			if (result == 1) {
				return true;
			} else {
				throw new Exception("修改用户信息时插入异常");
			}
		} catch (Exception e) {
			log.error("修改用户信息时插入异常", e);
			e.printStackTrace();
		}
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean recordLoginInformation(UserLoginRecord userLoginRecord) {
		userServiceMapper.recordLoginInformation(userLoginRecord);
		return true;
	}

	@Override
	public PageInfo<UserLoginRecord> selectUserLoginRecord(UserLoginRecord userLoginRecord, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserLoginRecord> data = userLoginRecordMapper.selectUserLoginRecord(userLoginRecord);
		PageInfo<UserLoginRecord> result = new PageInfo<>(data);
		return result;
	}

}
