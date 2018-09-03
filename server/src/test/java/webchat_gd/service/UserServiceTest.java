package webchat_gd.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import webchat_gd.Application;
import webchat_gd.dto.UserInfoDto;
import webchat_gd.entity.User;
import webchat_gd.entity.UserLoginRecord;
import webchat_gd.utils.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Sql("classpath:testSQL/insert-userServiceTest.sql")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	private Gson gson = new Gson();

	@Test
	public void testSelectUser() {
		User selectParam = new User();
		selectParam.setUserAccount("user_account111");
		selectParam.setPassword("password111");
		PageInfo<User> selectResult = userService.selectUser(selectParam, 1, 1);
		Assert.assertEquals("user_id111", selectResult.getList().get(0).getUserId());
	}

	@Test
	public void testFindUserInfoById() {
		String userId = "user_id111";
		UserInfoDto userInfo = userService.findUserInfoById(userId);
		Assert.assertEquals("nickname111", userInfo.getNickname());
	}

	@Test
	public void testUpdateUserInfo() throws Exception {
		String userId = "user_id111";
		UserInfoDto userInfo = userService.findUserInfoById(userId);
		userInfo.setNickname("Yishen");
		userService.updateUserInfo(userInfo);
		userInfo = userService.findUserInfoById(userId);
		Assert.assertEquals("Yishen", userInfo.getNickname());
	}

	@Test
	public void testRecordLoginInformation() {
		UserLoginRecord userLoginRecord = new UserLoginRecord();
		userLoginRecord.setLoginRecordId("login_record_id222");
		userLoginRecord.setUserId("user_id222");
		userLoginRecord.setRecordTime(DateUtil.stringToDate("2018-2-14 23:47:21", DateUtil.COMMON_TIME_FORMAT));
		userService.recordLoginInformation(userLoginRecord);
		PageInfo<UserLoginRecord> result = userService.selectUserLoginRecord(userLoginRecord, 1, 10);
		System.out.println(gson.toJson(result));
		Assert.assertEquals("login_record_id222", result.getList().get(0).getLoginRecordId());
	}

	@Test
	public void testSelectUserLoginRecord() {
		UserLoginRecord userLoginRecord = new UserLoginRecord();
		userLoginRecord.setLoginRecordId("login_record_id111");
		PageInfo<UserLoginRecord> result = userService.selectUserLoginRecord(userLoginRecord, 1, 10);
		Assert.assertEquals("user_id111", result.getList().get(0).getUserId());
	}

}
