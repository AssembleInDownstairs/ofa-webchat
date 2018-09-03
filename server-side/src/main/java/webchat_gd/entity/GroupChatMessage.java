package webchat_gd.entity;

import java.util.Date;

public class GroupChatMessage {
    private String groupChatMessageId;

    private String groupId;

    private String userId;

    private String messageType;

    private String messageContent;

    private Date timeOfSend;

    public String getGroupChatMessageId() {
        return groupChatMessageId;
    }

    public void setGroupChatMessageId(String groupChatMessageId) {
        this.groupChatMessageId = groupChatMessageId == null ? null : groupChatMessageId.trim();
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

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Date getTimeOfSend() {
        return timeOfSend;
    }

    public void setTimeOfSend(Date timeOfSend) {
        this.timeOfSend = timeOfSend;
    }
}