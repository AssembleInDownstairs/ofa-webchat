package webchat_gd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import webchat_gd.dto.ContactApplicationDto;
import webchat_gd.dto.ContactUnreadMessageDto;
import webchat_gd.dto.GroupApplicationDto;
import webchat_gd.dto.GroupUnreadMessageDto;
import webchat_gd.entity.ChatMessage;
import webchat_gd.entity.GroupChatMessage;
import webchat_gd.mapper.ChatMessageMapper;
import webchat_gd.mapper.GroupChatMessageMapper;
import webchat_gd.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	ChatMessageMapper chatMessageMapper;

	@Autowired
	GroupChatMessageMapper groupChatMessageMapper;

	@Override
	public PageInfo<ChatMessage> selectChatMessage(ChatMessage chatMessage, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChatMessage> data = chatMessageMapper.selectChatMessage(chatMessage);
		PageInfo<ChatMessage> result = new PageInfo<>(data);

		if ((pageNum - 1) * pageSize >= result.getTotal()) {
			result.setList(new ArrayList<ChatMessage>());
		}

		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean sendChatMessage(ChatMessage chatMessage) {
		chatMessage.setChatMessageId(UUID.randomUUID().toString());
		chatMessage.setTimeOfSend(new Date());
		chatMessageMapper.insert(chatMessage);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean revokeChatMessage(ChatMessage chatMessage) {
		String chatMessageId = chatMessage.getChatMessageId();
		chatMessageMapper.deleteChatMessage(chatMessageId);
		return true;
	}

	@Override
	public PageInfo<GroupChatMessage> selectGroupChatMessage(GroupChatMessage groupChatMessage, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<GroupChatMessage> data = groupChatMessageMapper.selectGroupChatMessage(groupChatMessage);
		PageInfo<GroupChatMessage> result = new PageInfo<>(data);

		if ((pageNum - 1) * pageSize >= result.getTotal()) {
			result.setList(new ArrayList<GroupChatMessage>());
		}
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean sendGroupChatMessage(GroupChatMessage groupChatMessage) {
		groupChatMessage.setGroupChatMessageId(UUID.randomUUID().toString());
		groupChatMessage.setTimeOfSend(new Date());
		groupChatMessageMapper.insert(groupChatMessage);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean revokeGroupChatMessage(GroupChatMessage groupChatMessage) {
		String groupChatMessageId = groupChatMessage.getGroupChatMessageId();
		groupChatMessageMapper.deleteGroupChatMessage(groupChatMessageId);
		return true;
	}

	@Override
	public List<ContactUnreadMessageDto> selectContactUnreadMessage(ContactUnreadMessageDto contactUnreadMessageDto) {
		List<ContactUnreadMessageDto> contactUnreadMessages = chatMessageMapper
				.selectContactUnreadMessage(contactUnreadMessageDto);

		// 解决ConcurrentModificationException
		Iterator<ContactUnreadMessageDto> iterator = contactUnreadMessages.iterator();
		while (iterator.hasNext()) {
			ContactUnreadMessageDto next = iterator.next();
			if (next.getUnreadMessageNumber() <= 0) {
				iterator.remove();
			}
		}
		return contactUnreadMessages;
	}

	@Override
	public List<GroupUnreadMessageDto> selectGroupUnreadMessage(GroupUnreadMessageDto groupUnreadMessageDto) {
		List<GroupUnreadMessageDto> groupUnreadMessages = groupChatMessageMapper
				.selectGroupUnreadMessage(groupUnreadMessageDto);
		// 解决ConcurrentModificationException
		Iterator<GroupUnreadMessageDto> iterator = groupUnreadMessages.iterator();
		while (iterator.hasNext()) {
			GroupUnreadMessageDto next = iterator.next();
			if (next.getUnreadMessageNumber() <= 0) {
				iterator.remove();
			}
		}
		return groupUnreadMessages;
	}

	@Override
	public List<ContactApplicationDto> selectContactApplications(String userId) {
		return chatMessageMapper.selectContactApplications(userId);
	}

	@Override
	public List<GroupApplicationDto> selectGrouptApplications(String userId) {
		return groupChatMessageMapper.selectGrouptApplications(userId);
	}
}
