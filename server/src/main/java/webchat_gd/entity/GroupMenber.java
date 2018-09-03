package webchat_gd.entity;

import java.util.Date;

public class GroupMenber {
	private String groupMenberRecordId;

	private String groupId;

	private String userId;

	private String userGroupNickname;

	private Date addTime;

	private Date lastContactTime;

	public String getGroupMenberRecordId() {
		return groupMenberRecordId;
	}

	public void setGroupMenberRecordId(String groupMenberRecordId) {
		this.groupMenberRecordId = groupMenberRecordId == null ? null : groupMenberRecordId.trim();
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId == null ? null : groupId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserGroupNickname() {
		return userGroupNickname;
	}

	public void setUserGroupNickname(String userGroupNickname) {
		this.userGroupNickname = userGroupNickname == null ? null : userGroupNickname.trim();
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getLastContactTime() {
		return lastContactTime;
	}

	public void setLastContactTime(Date lastContactTime) {
		this.lastContactTime = lastContactTime;
	}
}