package webchat_gd.dto;

import java.util.Date;

/**
 * 好友信息dto.
 * 
 * @author Yishen
 *
 */
public class ContactInfoDto {

	private String userId;// 用户id
	private String contactId;// 好友id
	private String contactAccount;// 好友-账号
	private String contactNickname;// 好友-昵称
	private String contactPhone;// 好友-电话
	private String contactProvince;// 好友-省
	private String contactCity;// 好友-市
	private String contactGender;// 好友-性别
	private Date contactBirthday;// 好友-生日
	private String contactProfilePhoto;// 好友-头像
	private String contactPersonalProfile;// 好友-个人简介
	private String remark;// 好友备注

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getContactAccount() {
		return contactAccount;
	}

	public void setContactAccount(String contactAccount) {
		this.contactAccount = contactAccount;
	}

	public String getContactNickname() {
		return contactNickname;
	}

	public void setContactNickname(String contactNickname) {
		this.contactNickname = contactNickname;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactProvince() {
		return contactProvince;
	}

	public void setContactProvince(String contactProvince) {
		this.contactProvince = contactProvince;
	}

	public String getContactCity() {
		return contactCity;
	}

	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	public String getContactGender() {
		return contactGender;
	}

	public void setContactGender(String contactGender) {
		this.contactGender = contactGender;
	}

	public Date getContactBirthday() {
		return contactBirthday;
	}

	public void setContactBirthday(Date contactBirthday) {
		this.contactBirthday = contactBirthday;
	}

	public String getContactProfilePhoto() {
		return contactProfilePhoto;
	}

	public void setContactProfilePhoto(String contactProfilePhoto) {
		this.contactProfilePhoto = contactProfilePhoto;
	}

	public String getContactPersonalProfile() {
		return contactPersonalProfile;
	}

	public void setContactPersonalProfile(String contactPersonalProfile) {
		this.contactPersonalProfile = contactPersonalProfile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
