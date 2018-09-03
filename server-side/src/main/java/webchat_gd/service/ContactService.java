package webchat_gd.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import webchat_gd.dto.ContactInfoDto;

/**
 * 好友模块.
 * 
 * @author Yishen
 *
 */
public interface ContactService {

	/**
	 * 好友列表查询.
	 * 
	 * @param contactInfoDto
	 * @return
	 */
	List<ContactInfoDto> selectContactInfo(ContactInfoDto contactInfoDto);

	/**
	 * 添加好友申请.
	 * 
	 * @param userId
	 * @param contactId
	 * @return
	 */
	boolean applyContact(String userId, String contactId);

	/**
	 * 同意好友申请.
	 * 
	 * @param userId
	 * @param contactId
	 * @return
	 */
	boolean agreeContactApplication(String userId, String contactId);

	/**
	 * 拒绝好友申请.
	 * 
	 * @param userId
	 * @param contactId
	 * @return
	 */
	boolean refuseContactApplication(String userId, String contactId);

	/**
	 * 删除好友.
	 * 
	 * @param userId
	 * @param contactId
	 * @return
	 */
	boolean deleteContact(String userId, String contactId);

	/**
	 * 修改好友备注.
	 * 
	 * @param userId
	 * @param contactId
	 * @param remark
	 * @return
	 */
	boolean updateContactRemark(String userId, String contactId, String remark);

	/**
	 * 更新最后关注聊天时间.
	 * 
	 * @param userId
	 * @param contactId
	 * @return
	 * @throws Exception
	 */
	boolean updateLastContactTime(String userId, String contactId) throws Exception;
}
