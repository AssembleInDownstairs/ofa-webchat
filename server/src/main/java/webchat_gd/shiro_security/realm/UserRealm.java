package webchat_gd.shiro_security.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import webchat_gd.entity.User;
import webchat_gd.service.UserService;

@Component
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	// @Autowired
	// private AuthorizationService authorizationService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User selectParam = new User();
		selectParam.setUserAccount(username);
		List<User> userList = userService.selectUser(selectParam, 1, 1).getList();
		if (userList.size() < 1) {
			throw new UnknownAccountException();// 没找到帐号
		}
		User user = userList.get(0);
		String principal = user.getUserAccount();

		// 使用校验码作为盐值加密密码
		String verifyCode = (String) SecurityUtils.getSubject().getSession(false).getAttribute("verifyCode");
		String credentials = new Md5Hash(user.getPassword(), verifyCode).toString();

		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials,
				getName());

		return simpleAuthenticationInfo;
	}

}
