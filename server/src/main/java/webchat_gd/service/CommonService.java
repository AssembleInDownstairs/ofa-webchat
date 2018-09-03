package webchat_gd.service;

/**
 * 公共服务.
 * 
 * @author Yishen
 *
 */
public interface CommonService {

	// TODO Yishen 获取当前用户id

	/**
	 * 根据用户账号获取用户id.
	 * 
	 * @param userAccount
	 * @return
	 */
	String findUserIdByUserAccount(String userAccount);

}
