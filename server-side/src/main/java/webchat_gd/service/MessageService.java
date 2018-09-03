package webchat_gd.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import webchat_gd.dto.ContactApplicationDto;
import webchat_gd.dto.ContactUnreadMessageDto;
import webchat_gd.dto.GroupApplicationDto;
import webchat_gd.dto.GroupUnreadMessageDto;
import webchat_gd.entity.ChatMessage;
import webchat_gd.entity.GroupChatMessage;

/**
 * 消息模块.
 * 
 * @author hys58
 *
 */
public interface MessageService {

	/**
	 * 好友聊天信息显示.
	 * 
	 * @param chatMessage
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<ChatMessage> selectChatMessage(ChatMessage chatMessage, Integer pageNum, Integer pageSize);

	/**
	 * 好友聊天信息发送.
	 * 
	 * @param chatMessage
	 * @return
	 */
	boolean sendChatMessage(ChatMessage chatMessage);

	/**
	 * 好友聊天信息撤销.
	 * 
	 * @param chatMessage
	 * @return
	 */
	boolean revokeChatMessage(ChatMessage chatMessage);

	/**
	 * 群聊天信息显示.
	 * 
	 * @param groupChatMessage
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<GroupChatMessage> selectGroupChatMessage(GroupChatMessage groupChatMessage, Integer pageNum,
			Integer pageSize);

	/**
	 * 群聊天信息发送.
	 * 
	 * @param groupChatMessage
	 * @return
	 */
	boolean sendGroupChatMessage(GroupChatMessage groupChatMessage);

	/**
	 * 群聊天信息撤销.
	 * 
	 * @param groupChatMessage
	 * @return
	 */
	boolean revokeGroupChatMessage(GroupChatMessage groupChatMessage);

	/**
	 * 获取用户好友未读信息.
	 * 
	 * @param contactUnreadMessage
	 * @return
	 */
	List<ContactUnreadMessageDto> selectContactUnreadMessage(ContactUnreadMessageDto contactUnreadMessageDto);

	/**
	 * 获取用户群未读消息.
	 * 
	 * @param groupUnreadMessageDto
	 * @return
	 */
	List<GroupUnreadMessageDto> selectGroupUnreadMessage(GroupUnreadMessageDto groupUnreadMessageDto);

	/**
	 * 获取好友申请信息.
	 * 
	 * @param userId
	 * @return
	 */
	List<ContactApplicationDto> selectContactApplications(String userId);

	/**
	 * 获取进群申请信息.
	 * 
	 * @param userId
	 * @return
	 */
	List<GroupApplicationDto> selectGrouptApplications(String userId);
}
