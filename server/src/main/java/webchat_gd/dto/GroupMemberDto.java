package webchat_gd.dto;

import java.util.Date;

/**
 * 群成员dto.
 * 
 * @author hys58
 *
 */
public class GroupMemberDto {

	private String groupMenberRecordId;
	private String groupId;// 群id
	private String userId;// 用户id
	private String userAccount;// 用户账号
	private String nickname;// 用户昵称
	private String userGroupNickname;// 用户群名称
	private Date addTime;
	private Date lastContactTime;
	private String profilePhoto;// 用户头像

	public String getGroupMenberRecordId() {
		return groupMenberRecordId;
	}

	public void setGroupMenberRecordId(String groupMenberRecordId) {
		this.groupMenberRecordId = groupMenberRecordId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserGroupNickname() {
		return userGroupNickname;
	}

	public void setUserGroupNickname(String userGroupNickname) {
		this.userGroupNickname = userGroupNickname;
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

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

}
