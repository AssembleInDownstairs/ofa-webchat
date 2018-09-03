package webchat_gd.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import webchat_gd.Application;
import webchat_gd.dto.GroupListInfoDto;
import webchat_gd.dto.GroupMemberDto;
import webchat_gd.entity.Group;
import webchat_gd.entity.GroupMenber;
import webchat_gd.mapper.GroupMenberMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Sql("classpath:testSQL/insert-groupServiceTest.sql")
public class GroupServiceTest {

	@Autowired
	private GroupService groupService;

	@Autowired
	private GroupMenberMapper groupMenberMapper;

	@Test
	public void testSelectGroupInfo() {
		GroupListInfoDto groupListInfoDto = new GroupListInfoDto();
		groupListInfoDto.setUserId("user_id111");
		List<GroupListInfoDto> groupInfos = groupService.selectGroupInfo(groupListInfoDto);
		Assert.assertEquals("group_name111", groupInfos.get(0).getGroupName());
	}

	@Test
	public void testSelectGroupMenber() {
		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setGroupId("group_id111");
		List<GroupMemberDto> result = groupService.selectGroupMenber(groupMenber);
		Assert.assertEquals("user_id111", result.get(0).getUserId());
	}

	@Test
	public void testCreateGroup() throws Exception {
		Group group = new Group();
		group.setGroupCode("group_code333");
		group.setGroupName("group_name333");
		group.setGroupImage("group_image333");
		group.setAdministratorId("user_id222");
		group.setCreateBy("user_id222");
		groupService.createGroup(group);

		Group group2 = new Group();
		group2.setGroupCode("group_code333");
		group2.setGroupName("group_name333");
		group2.setGroupImage("group_image333");
		group2.setAdministratorId("user_id222");
		group2.setCreateBy("user_id222");

		List<Group> selectResult = groupService.selectGroup(group2);
		Assert.assertEquals("group_name333", selectResult.get(0).getGroupName());
	}

	@Test
	public void testSelectGroup() {
		Group group = new Group();
		group.setGroupId("group_id222");
		group.setGroupCode("group_code222");
		group.setGroupName("group_name222");
		group.setGroupImage("group_image222");
		group.setAdministratorId("user_id222");
		group.setCreateBy("create_by222");
		List<Group> selectResult = groupService.selectGroup(group);
		Assert.assertEquals("group_name222", selectResult.get(0).getGroupName());
	}

	@Test
	public void testUpdateGroup() throws Exception {
		Group group = new Group();
		group.setGroupId("group_id222");
		group.setGroupCode("group_code222");
		group.setGroupName("Group_Yishen");
		group.setGroupImage("group_image222");
		group.setAdministratorId("user_id222");
		group.setCreateBy("create_by222");
		groupService.updateGroup(group);
		List<Group> selectResult = groupService.selectGroup(group);
		Assert.assertEquals("Group_Yishen", selectResult.get(0).getGroupName());
	}

	@Test
	public void testApplyGroup() {
		String userId = "user_id222";
		String groupId = "group_id222";
		boolean result = groupService.applyGroup(userId, groupId);
		Assert.assertEquals(true, result);
	}

	@Test
	public void testAgreeGroupApplication() {
		String userId = "user_id222";
		String groupId = "group_id222";
		groupService.applyGroup(userId, groupId);
		groupService.agreeGroupApplication(userId, groupId);
		GroupListInfoDto groupListInfoDto = new GroupListInfoDto();
		groupListInfoDto.setUserId(userId);
		List<GroupListInfoDto> groupInfos = groupService.selectGroupInfo(groupListInfoDto);
		Assert.assertEquals("group_name222", groupInfos.get(0).getGroupName());
	}

	@Test
	public void testRefuseGroupApplication() {
		String userId = "user_id222";
		String groupId = "group_id222";
		groupService.applyGroup(userId, groupId);
		boolean operateResult = groupService.refuseGroupApplication(userId, groupId);
		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setUserId(userId);
		groupMenber.setGroupId(groupId);
		List<GroupMemberDto> selectResult = groupMenberMapper.selectGroupMenber(groupMenber);
		Assert.assertEquals(true, operateResult);
		Assert.assertEquals(0, selectResult.size());
	}

	@Test
	public void testQuitGroup() {
		String userId = "user_id222";
		String groupId = "group_id222";
		groupService.applyGroup(userId, groupId);
		boolean operateResult = groupService.quitGroup(userId, groupId);
		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setUserId(userId);
		groupMenber.setGroupId(groupId);
		List<GroupMemberDto> selectResult = groupMenberMapper.selectGroupMenber(groupMenber);
		Assert.assertEquals(true, operateResult);
		Assert.assertEquals(0, selectResult.size());
	}

	@Test
	public void testDismissGroup() throws Exception {
		String userId = "user_id111";
		String groupId = "group_id111";
		groupService.applyGroup(userId, groupId);
		boolean operateResult = groupService.dismissGroup(groupId);

		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setUserId(userId);
		groupMenber.setGroupId(groupId);
		List<GroupMemberDto> selectResult = groupMenberMapper.selectGroupMenber(groupMenber);

		Group group = new Group();
		group.setGroupId(groupId);
		List<Group> selectResult2 = groupService.selectGroup(group);

		Assert.assertEquals(true, operateResult);
		Assert.assertEquals(0, selectResult.size());
		Assert.assertEquals(0, selectResult2.size());
	}

	@Test
	public void testUpdateUserGroupNickname() {
		String userId = "user_id111";
		String groupId = "group_id111";
		String userGroupNickname = "Yishen";
		groupService.updateUserGroupNickname(groupId, userId, userGroupNickname);
		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setUserId(userId);
		groupMenber.setGroupId(groupId);
		List<GroupMemberDto> selectResult = groupMenberMapper.selectGroupMenber(groupMenber);
		Assert.assertEquals("Yishen", selectResult.get(0).getUserGroupNickname());
	}

	@Test
	public void testUpdateMemberLastContactTime() throws Exception {
		String userId = "user_id111";
		String groupId = "group_id111";
		boolean result = groupService.updateMemberLastContactTime(groupId, userId);
		Assert.assertEquals(true, result);
	}

}
