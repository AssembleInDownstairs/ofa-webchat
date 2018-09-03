package webchat_gd.dto;

/**
 * 群未读消息dto.
 * 
 * @author Yishen
 *
 */
public class GroupUnreadMessageDto {

	private String groupId;// 群id
	private String userId;// 用户id
	private Integer unreadMessageNumber;// 未读消息数量
	private String lastMenberId;// 最后发送信息的成员id
	private String lastChatMessage;// 成员最后一次聊天信息内容
	private String lastContactTime;// 用户最后一次关注聊天信息时间

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

	public Integer getUnreadMessageNumber() {
		return unreadMessageNumber;
	}

	public void setUnreadMessageNumber(Integer unreadMessageNumber) {
		this.unreadMessageNumber = unreadMessageNumber;
	}

	public String getLastMenberId() {
		return lastMenberId;
	}

	public void setLastMenberId(String lastMenberId) {
		this.lastMenberId = lastMenberId;
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
