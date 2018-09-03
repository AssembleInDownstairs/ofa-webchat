package webchat_gd.dto;

/**
 * 好友申请dto.
 * 
 * @author Yishen
 *
 */
public class ContactApplicationDto {

	private String userId;
	private String contactId;

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

}
