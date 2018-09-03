package webchat_gd.service;

import java.util.List;

import webchat_gd.dto.GroupListInfoDto;
import webchat_gd.dto.GroupMemberDto;
import webchat_gd.entity.Group;
import webchat_gd.entity.GroupMenber;

/**
 * 群模块.
 * 
 * @author hys58
 *
 */
public interface GroupService {

	/**
	 * 群列表查询.
	 * 
	 * @param groupInfoDto
	 * @return
	 */
	List<GroupListInfoDto> selectGroupInfo(GroupListInfoDto groupListInfoDto);

	/**
	 * 群成员查询.
	 * 
	 * @param groupMenber
	 * @return
	 */
	List<GroupMemberDto> selectGroupMenber(GroupMenber groupMenber);

	/**
	 * 创建群.
	 * 
	 * @param group
	 * @return
	 */
	boolean createGroup(Group group);

	/**
	 * 群信息查询.
	 * 
	 * @param group
	 * @return
	 */
	List<Group> selectGroup(Group group);

	/**
	 * 群信息修改.
	 * 
	 * @param group
	 * @return
	 * @throws Exception
	 */
	boolean updateGroup(Group group) throws Exception;

	/**
	 * 进群申请.
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	boolean applyGroup(String userId, String groupId);

	/**
	 * 同意进群.
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	boolean agreeGroupApplication(String userId, String groupId);

	/**
	 * 拒绝进群.
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	boolean refuseGroupApplication(String userId, String groupId);

	/**
	 * 退出群.
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	boolean quitGroup(String userId, String groupId);

	/**
	 * 拉用户进群.
	 * 
	 * @param groupMenber
	 * @return
	 */
	boolean inviteUserInGroup(GroupMenber groupMenber);

	/**
	 * 解散群.
	 * 
	 * @param groupId
	 * @return
	 */
	boolean dismissGroup(String groupId);

	/**
	 * 修改用户群昵称.
	 * 
	 * @param groupId
	 * @param userId
	 * @param userGroupNickname
	 * @return
	 */
	boolean updateUserGroupNickname(String groupId, String userId, String userGroupNickname);

	/**
	 * 更新群成员最后关注聊天时间.
	 * 
	 * @param groupId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean updateMemberLastContactTime(String groupId, String userId) throws Exception;
}
