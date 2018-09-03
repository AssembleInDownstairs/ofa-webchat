package webchat_gd.dto;

/**
 * 进群申请dto.
 * 
 * @author Yishen
 *
 */
public class GroupApplicationDto {

	private String groupId;
	private String userId;

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

}
