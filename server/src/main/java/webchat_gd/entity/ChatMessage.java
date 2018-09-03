package webchat_gd.entity;

import java.util.Date;

public class ChatMessage {
    private String chatMessageId;

    private String userId;

    private String chatterId;

    private String messageType;

    private String messageContent;

    private Date timeOfSend;

    public String getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(String chatMessageId) {
        this.chatMessageId = chatMessageId == null ? null : chatMessageId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getChatterId() {
        return chatterId;
    }

    public void setChatterId(String chatterId) {
        this.chatterId = chatterId == null ? null : chatterId.trim();
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