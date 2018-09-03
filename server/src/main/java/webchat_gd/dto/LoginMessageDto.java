package webchat_gd.dto;

/**
 * 登录信息dto.
 * 
 * @author Yishen
 *
 */
public class LoginMessageDto {

	private String username;// 用户账号
	private String password;// 密码
	private String verifyCode;// 校验码

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
