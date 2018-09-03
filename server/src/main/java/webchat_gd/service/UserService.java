package webchat_gd.service;

import com.github.pagehelper.PageInfo;

import webchat_gd.dto.UserInfoDto;
import webchat_gd.entity.User;
import webchat_gd.entity.UserLoginRecord;

/**
 * 用户模块.
 * 
 * @author Yishen
 *
 */
public interface UserService {

	/**
	 * 注册用户.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean register(User user);

	/**
	 * 
	 * 查询用户表信息.
	 * 
	 * @param user
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<User> selectUser(User user, int pageNum, int pageSize);

	/**
	 * 
	 * 根据用户id获取查询用户信息.
	 * 
	 * @param userId
	 * @return
	 */
	UserInfoDto findUserInfoById(String userId);

	/**
	 * 
	 * 查询用户基本信息.
	 * 
	 * @param userInfoDto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<UserInfoDto> findUserInfo(UserInfoDto userInfoDto, int pageNum, int pageSize);

	/**
	 * 修改用户信息.
	 * 
	 * @param userInfoDto
	 * @return
	 */
	boolean updateUserInfo(UserInfoDto userInfoDto);

	/**
	 * 记录登录信息.
	 * 
	 * @param userLoginRecord
	 * @return
	 */
	boolean recordLoginInformation(UserLoginRecord userLoginRecord);

	/**
	 * 查询用户登录记录.
	 * 
	 * @param userLoginRecord
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<UserLoginRecord> selectUserLoginRecord(UserLoginRecord userLoginRecord, int pageNum, int pageSize);
}
