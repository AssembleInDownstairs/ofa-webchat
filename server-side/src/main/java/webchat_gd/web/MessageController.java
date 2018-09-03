package webchat_gd.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import webchat_gd.dto.ContactApplicationDto;
import webchat_gd.dto.ContactUnreadMessageDto;
import webchat_gd.dto.GroupApplicationDto;
import webchat_gd.dto.GroupUnreadMessageDto;
import webchat_gd.entity.ChatMessage;
import webchat_gd.entity.GroupChatMessage;
import webchat_gd.service.MessageService;

@RestController
@RequestMapping("/webchat/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping("/list/contact")
	public PageInfo<ChatMessage> selectChatMessage(ChatMessage chatMessage, Integer pageNum, Integer pageSize) {
		return messageService.selectChatMessage(chatMessage, pageNum, pageSize);
	}

	@PostMapping("/send/contact")
	public boolean sendChatMessage(ChatMessage chatMessage) {
		return messageService.sendChatMessage(chatMessage);
	}

	@DeleteMapping("/revoke/contact")
	public boolean revokeChatMessage(ChatMessage chatMessage) {
		return messageService.revokeChatMessage(chatMessage);
	}

	@GetMapping("/list/group")
	public PageInfo<GroupChatMessage> selectGroupChatMessage(GroupChatMessage groupChatMessage, Integer pageNum,
			Integer pageSize) {
		return messageService.selectGroupChatMessage(groupChatMessage, pageNum, pageSize);
	}

	@PostMapping("/send/group")
	public boolean sendGroupChatMessage(GroupChatMessage groupChatMessage) {
		return messageService.sendGroupChatMessage(groupChatMessage);
	}

	@DeleteMapping("/revoke/group")
	public boolean revokeGroupChatMessage(GroupChatMessage groupChatMessage) {
		return messageService.revokeGroupChatMessage(groupChatMessage);
	}

	@GetMapping("/unread/contact")
	public List<ContactUnreadMessageDto> selectContactUnreadMessage(ContactUnreadMessageDto contactUnreadMessageDto) {
		return messageService.selectContactUnreadMessage(contactUnreadMessageDto);
	}

	@GetMapping("/unread/group")
	public List<GroupUnreadMessageDto> selectGroupUnreadMessage(GroupUnreadMessageDto groupUnreadMessageDto) {
		return messageService.selectGroupUnreadMessage(groupUnreadMessageDto);
	}

	@GetMapping("/application/contact")
	public List<ContactApplicationDto> selectContactApplications(String userId) {
		return messageService.selectContactApplications(userId);
	}

	@GetMapping("/application/group")
	public List<GroupApplicationDto> selectGrouptApplications(String userId) {
		return messageService.selectGrouptApplications(userId);
	}

	@GetMapping("/prompt/all")
	public Map<String, Object> selectAllMessage(String userId) {

		ContactUnreadMessageDto contactUnreadMessageDto = new ContactUnreadMessageDto();
		contactUnreadMessageDto.setUserId(userId);

		GroupUnreadMessageDto groupUnreadMessageDto = new GroupUnreadMessageDto();
		groupUnreadMessageDto.setUserId(userId);

		Map<String, Object> map = new HashMap<>();

		List<ContactUnreadMessageDto> contactUnreadMessage = messageService
				.selectContactUnreadMessage(contactUnreadMessageDto);
		List<GroupUnreadMessageDto> groupUnreadMessage = messageService.selectGroupUnreadMessage(groupUnreadMessageDto);
		List<ContactApplicationDto> contactApplications = messageService.selectContactApplications(userId);
		List<GroupApplicationDto> grouptApplications = messageService.selectGrouptApplications(userId);

		map.put("contactUnreadMessage", contactUnreadMessage);
		map.put("groupUnreadMessage", groupUnreadMessage);
		map.put("contactApplications", contactApplications);
		map.put("grouptApplications", grouptApplications);

		return map;
	}
}
