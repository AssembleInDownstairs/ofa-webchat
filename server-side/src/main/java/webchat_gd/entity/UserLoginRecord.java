package webchat_gd.entity;

import java.util.Date;

public class UserLoginRecord {
    private String loginRecordId;

    private String userId;

    private Date recordTime;

    public String getLoginRecordId() {
        return loginRecordId;
    }

    public void setLoginRecordId(String loginRecordId) {
        this.loginRecordId = loginRecordId == null ? null : loginRecordId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}