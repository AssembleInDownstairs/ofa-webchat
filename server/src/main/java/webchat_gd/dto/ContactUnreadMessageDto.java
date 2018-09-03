package webchat_gd.dto;

/**
 * 好友未读消息dto.
 * 
 * @author Yishen
 *
 */
public class ContactUnreadMessageDto {

	private String userId;// 用户id
	private String contactId;// 好友id
	private Integer unreadMessageNumber;// 未读消息数量
	private String lastChatMessage;// 好友最后一次聊天信息内容
	private String lastContactTime;// 用户最后一次关注聊天信息时间

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

	public Integer getUnreadMessageNumber() {
		return unreadMessageNumber;
	}

	public void setUnreadMessageNumber(Integer unreadMessageNumber) {
		this.unreadMessageNumber = unreadMessageNumber;
	}

	public String getLastChatMessage() {
		return lastChatMessage;
	}

	public void setLastChatMessage(String lastChatMessage) {
		this.lastChatMessage = lastChatMessage;
	}

	public String getLastContactTime() {
		return lastContactTime;
	}

	public void setLastContactTime(String lastContactTime) {
		this.lastContactTime = lastContactTime;
	}

}
