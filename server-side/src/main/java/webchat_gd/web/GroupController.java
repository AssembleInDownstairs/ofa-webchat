package webchat_gd.web;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webchat_gd.dto.GroupListInfoDto;
import webchat_gd.dto.GroupMemberDto;
import webchat_gd.entity.Group;
import webchat_gd.entity.GroupMenber;
import webchat_gd.service.GroupService;

@RestController
@RequestMapping("/webchat/group")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@GetMapping("/list")
	public List<GroupListInfoDto> selectGroupInfo(GroupListInfoDto groupListInfoDto) {
		return groupService.selectGroupInfo(groupListInfoDto);
	}

	@GetMapping("/member")
	public List<GroupMemberDto> selectGroupMenber(GroupMenber groupMenber) {
		return groupService.selectGroupMenber(groupMenber);
	}

	@PostMapping("/create")
	public boolean createGroup(Group group) throws Exception {
		// 校验群号是否已存在
		Group validate = new Group();
		validate.setGroupCode(group.getGroupCode());
		List<Group> selectResult = groupService.selectGroup(validate);
		if (selectResult.size() > 0) {
			throw new Exception("该群号已存在");
		}

		return groupService.createGroup(group);
	}

	@GetMapping("/list/detail")
	public List<Group> selectGroup(Group group) {
		return groupService.selectGroup(group);
	}

	@PutMapping("/update")
	public boolean updateGroup(Group group) throws Exception {
		return groupService.updateGroup(group);
	}

	@PostMapping("/application")
	public boolean applyGroup(String userId, String groupId) throws Exception {
		if (userId == null || "".equals(userId) || groupId == null || "".equals(groupId)) {
			throw new Exception("用户id和群id都不能为空");
		}

		GroupMenber groupMenber = new GroupMenber();
		groupMenber.setGroupId(groupId);
		List<GroupMemberDto> groupMenbers = groupService.selectGroupMenber(groupMenber);
		for (GroupMemberDto groupMemberDto : groupMenbers) {
			String menberId = groupMemberDto.getUserId();
			if (menberId.equals(userId)) {
				return false;
			}
		}

		return groupService.applyGroup(userId, groupId);
	}

	@PostMapping("/agreement")
	public boolean agreeGroupApplication(String userId, String groupId) throws Exception {

		// 判断执行该操作的人是否是群主
		String administratorId = (String) SecurityUtils.getSubject().getSession().getAttribute("userId");
		Group validate = new Group();
		validate.setGroupId(groupId);
		validate.setAdministratorId(administratorId);
		List<Group> result = selectGroup(validate);
		if (result.size() == 0) {
			throw new Exception("当前用户不是该群的群主");
		}

		return groupService.agreeGroupApplication(userId, groupId);
	}

	@DeleteMapping("/refuse")
	public boolean refuseGroupApplication(String userId, String groupId) throws Exception {
		if (userId == null || "".equals(userId) || groupId == null || "".equals(groupId)) {
			throw new Exception("群id和好友id都不能为空");
		}

		return groupService.refuseGroupApplication(userId, groupId);
	}

	@DeleteMapping("/quit")
	public boolean quitGroup(String userId, String groupId) throws Exception {
		if (userId == null || "".equals(userId) || groupId == null || "".equals(groupId)) {
			throw new Exception("群id和好友id都不能为空");
		}

		Group group = new Group();
		group.setGroupId(groupId);
		List<Group> groups = groupService.selectGroup(group);
		if (groups.size() > 0) {
			String administratorId = groups.get(0).getAdministratorId();
			if (administratorId.equals(userId)) {
				// 群主不能退群
				return false;
			}
		} else {
			throw new Exception("不存在该群");
		}

		return groupService.quitGroup(userId, groupId);
	}

	@DeleteMapping("/kickMember")
	public boolean kickMember(String userId, String groupId) throws Exception {
		if (userId == null || "".equals(userId) || groupId == null || "".equals(groupId)) {
			throw new Exception("群id和好友id都不能为空");
		}

		Group group = new Group();
		group.setGroupId(groupId);
		List<Group> groups = groupService.selectGroup(group);
		if (groups.size() > 0) {
			String currentUserId = (String) SecurityUtils.getSubject().getSession().getAttribute("userId");
			String administratorId = groups.get(0).getAdministratorId();
			if (administratorId.equals(currentUserId)) {
				return groupService.quitGroup(userId, groupId);
			} else {
				throw new Exception("只有群主可以踢人");
			}
		} else {
			throw new Exception("不存在该群");
		}
	}

	@PostMapping("/invite")
	public boolean inviteUserInGroup(GroupMenber groupMenber) {
		return groupService.inviteUserInGroup(groupMenber);
	}

	@DeleteMapping("/dismission")
	public boolean dismissGroup(String groupId) throws Exception {
		// 判断执行该操作的人是否是群主
		String userId = (String) SecurityUtils.getSubject().getSession().getAttribute("userId");
		Group validate = new Group();
		validate.setGroupId(groupId);
		validate.setAdministratorId(userId);
		List<Group> result = selectGroup(validate);
		if (result.size() == 0) {
			throw new Exception("当前用户不是该群的群主");
		}

		return groupService.dismissGroup(groupId);
	}

	@PutMapping("/update/nickname")
	public boolean updateUserGroupNickname(String groupId, String userId, String userGroupNickname) {
		return groupService.updateUserGroupNickname(groupId, userId, userGroupNickname);
	}

	@PutMapping("/lastContactTime")
	public boolean updateMemberLastContactTime(String groupId, String userId) throws Exception {
		return groupService.updateMemberLastContactTime(groupId, userId);
	}
}
