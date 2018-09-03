package webchat_gd.entity;

import java.util.Date;

public class Contact {
    private String contactRecordId;

    private String userId;

    private String contactId;

    private String remark;

    private Date addTime;

    private Date lastContactTime;

    public String getContactRecordId() {
        return contactRecordId;
    }

    public void setContactRecordId(String contactRecordId) {
        this.contactRecordId = contactRecordId == null ? null : contactRecordId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId == null ? null : contactId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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