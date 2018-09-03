package webchat_gd.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webchat_gd.dto.GroupListInfoDto;
import webchat_gd.dto.GroupMemberDto;
import webchat_gd.entity.Group;
import webchat_gd.entity.GroupMenber;
import webchat_gd.mapper.GroupChatMessageMapper;
import webchat_gd.mapper.GroupMapper;
import webchat_gd.mapper.GroupMenberMapper;
import webchat_gd.mapper.GroupServiceMapper;
import webchat_gd.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupServiceMapper groupServiceMapper;

	@Autowired
	GroupMapper groupMapper;

	@Autowired
	GroupMenberMapper groupMenberMapper;

	@Autowired
	GroupChatMessageMapper groupChatMessageMapper;

	@Override
	public List<GroupListInfoDto> selectGroupInfo(GroupListInfoDto groupListInfoDto) {
		List<GroupListInfoDto> data = groupServiceMapper.selectGroupInfo(groupListInfoDto);
		return data;
	}

	@Override
	public List<GroupMemberDto> selectGroupMenber(GroupMenber groupMenber) {
		List<GroupMemberDto> groupMenbers = groupMenberMapper.selectGroupMenber(groupMenber);
		return groupMenbers;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean createGroup(Group group) {
		String groupId = UUID.randomUUID().toString();
		String userId = group.getAdministratorId();

		group.setGroupId(groupId);
		group.setCreateTime(new Date());
		group.setCreateBy(userId);
		groupMapper.insert(group);

		// 把创建者拉进群
		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setGroupMenberRecordId(UUID.randomUUID().toString());
		groupMenber.setUserId(userId);
		groupMenber.setGroupId(groupId);
		groupMenber.setAddTime(new Date());
		groupMenber.setLastContactTime(new Date());
		groupMenberMapper.insert(groupMenber);
		return true;
	}

	@Override
	public List<Group> selectGroup(Group group) {
		List<Group> data = groupMapper.selectGroup(group);
		return data;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateGroup(Group group) throws Exception {

		groupMapper.updateGroup(group);

		// 校验群号是否已存在
		Group validate = new Group();
		validate.setGroupCode(group.getGroupCode());
		List<Group> selectResult = groupMapper.selectGroup(validate);
		if (selectResult.size() > 1) {
			throw new Exception("该群号已存在");
		}
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean applyGroup(String userId, String groupId) {
		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setGroupMenberRecordId(UUID.randomUUID().toString());
		groupMenber.setUserId(userId);
		groupMenber.setGroupId(groupId);
		groupMenber.setAddTime(new Date());
		groupMenberMapper.insert(groupMenber);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean agreeGroupApplication(String userId, String groupId) {
		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setUserId(userId);
		groupMenber.setGroupId(groupId);
		groupMenber.setLastContactTime(new Date());
		groupMenberMapper.updateGroupMenberByUG(groupMenber);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean refuseGroupApplication(String userId, String groupId) {
		groupMenberMapper.deleteGroupMenberByUG(userId, groupId);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean quitGroup(String userId, String groupId) {
		groupMenberMapper.deleteGroupMenberByUG(userId, groupId);
		return true;
	}

	@Override
	public boolean inviteUserInGroup(GroupMenber groupMenber) {
		groupMenber.setGroupMenberRecordId(UUID.randomUUID().toString());
		groupMenber.setAddTime(new Date());
		groupMenber.setLastContactTime(new Date());
		groupMenberMapper.insert(groupMenber);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean dismissGroup(String groupId) {
		groupMenberMapper.deleteGroupMenberByGroupId(groupId);
		groupMapper.deleteGroup(groupId);
		groupChatMessageMapper.deleteGroupChatMessageByGroupId(groupId);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateUserGroupNickname(String groupId, String userId, String userGroupNickname) {
		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setGroupId(groupId);
		groupMenber.setUserId(userId);
		groupMenber.setUserGroupNickname(userGroupNickname);
		groupMenberMapper.updateGroupMenberByUG(groupMenber);
		return true;
	}

	@Override
	public boolean updateMemberLastContactTime(String groupId, String userId) throws Exception {
		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setGroupId(groupId);
		groupMenber.setUserId(userId);
		groupMenber.setLastContactTime(new Date());
		int result = groupMenberMapper.updateGroupMenberByUG(groupMenber);
		if (result == 1) {
			return true;
		} else {
			throw new Exception("更新群成员最后聊天时间时发生错误");
		}

	}

}
