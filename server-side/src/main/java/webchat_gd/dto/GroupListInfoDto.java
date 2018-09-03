package webchat_gd.dto;

/**
 * 群列表信息dto.
 * 
 * @author hys58
 *
 */
public class GroupListInfoDto {

	private String userId;// 用户id
	private String groupId;// 群id
	private String groupCode;// 群号
	private String groupName;// 群名称
	private String groupImage;// 群头像
	private String administratorId;// 管理员id
	private String userGroupNickname;// 用户群昵称
	private String groupMenberRecordId;// 用户群成员id

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}

	public String getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(String administratorId) {
		this.administratorId = administratorId;
	}

	public String getUserGroupNickname() {
		return userGroupNickname;
	}

	public void setUserGroupNickname(String userGroupNickname) {
		this.userGroupNickname = userGroupNickname;
	}

	public String getGroupMenberRecordId() {
		return groupMenberRecordId;
	}

	public void setGroupMenberRecordId(String groupMenberRecordId) {
		this.groupMenberRecordId = groupMenberRecordId;
	}

}
