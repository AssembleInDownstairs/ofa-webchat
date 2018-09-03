package webchat_gd.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

import webchat_gd.Application;
import webchat_gd.dto.ContactApplicationDto;
import webchat_gd.dto.ContactUnreadMessageDto;
import webchat_gd.dto.GroupApplicationDto;
import webchat_gd.dto.GroupUnreadMessageDto;
import webchat_gd.entity.ChatMessage;
import webchat_gd.entity.GroupChatMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Sql("classpath:testSQL/insert-messageServiceTest.sql")
public class MessageServiceTest {

	@Autowired
	private MessageService messageService;

	@Test
	public void testSelectChatMessage() {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setUserId("user_id111");
		chatMessage.setChatterId("user_id222");
		PageInfo<ChatMessage> selectResult = messageService.selectChatMessage(chatMessage, 1, 10);
		Assert.assertEquals("hello2", selectResult.getList().get(0).getMessageContent());
	}

	@Test
	public void testSendChatMessage() {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setChatMessageId(UUID.randomUUID().toString());
		chatMessage.setUserId("user_id111");
		chatMessage.setChatterId("user_id222");
		chatMessage.setMessageType("01");
		chatMessage.setMessageContent("hi");
		chatMessage.setTimeOfSend(new Date());
		messageService.sendChatMessage(chatMessage);

		ChatMessage chatMessage2 = new ChatMessage();
		chatMessage2.setUserId("user_id111");
		chatMessage2.setChatterId("user_id222");
		PageInfo<ChatMessage> selectResult = messageService.selectChatMessage(chatMessage2, 1, 10);
		Assert.assertEquals("hi", selectResult.getList().get(0).getMessageContent());
	}

	@Test
	public void testRevokeChatMessage() {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setChatMessageId(UUID.randomUUID().toString());
		chatMessage.setUserId("user_id111");
		chatMessage.setChatterId("user_id222");
		chatMessage.setMessageType("01");
		chatMessage.setMessageContent("hi");
		chatMessage.setTimeOfSend(new Date());
		messageService.sendChatMessage(chatMessage);

		messageService.revokeChatMessage(chatMessage);

		ChatMessage chatMessage2 = new ChatMessage();
		chatMessage2.setUserId("user_id111");
		chatMessage2.setChatterId("user_id222");
		PageInfo<ChatMessage> selectResult = messageService.selectChatMessage(chatMessage2, 1, 10);
		Assert.assertEquals("hello2", selectResult.getList().get(0).getMessageContent());
	}

	@Test
	public void testSelectGroupChatMessage() {
		GroupChatMessage groupChatMessage = new GroupChatMessage();
		groupChatMessage.setGroupId("group_id111");

		PageInfo<GroupChatMessage> selectResult = messageService.selectGroupChatMessage(groupChatMessage, 1, 10);
		Assert.assertEquals("hello2", selectResult.getList().get(0).getMessageContent());
	}

	@Test
	public void testSendGroupChatMessage() {
		GroupChatMessage groupChatMessage = new GroupChatMessage();
		groupChatMessage.setGroupChatMessageId(UUID.randomUUID().toString());
		groupChatMessage.setGroupId("group_id222");
		groupChatMessage.setUserId("user_id222");
		groupChatMessage.setMessageType("01");
		groupChatMessage.setMessageContent("hi");
		groupChatMessage.setTimeOfSend(new Date());

		messageService.sendGroupChatMessage(groupChatMessage);

		GroupChatMessage groupChatMessage2 = new GroupChatMessage();
		groupChatMessage2.setGroupId("group_id222");
		PageInfo<GroupChatMessage> selectResult = messageService.selectGroupChatMessage(groupChatMessage2, 1, 10);
		Assert.assertEquals("hi", selectResult.getList().get(0).getMessageContent());
	}

	@Test
	public void testRevokeGroupChatMessage() {
		GroupChatMessage groupChatMessage = new GroupChatMessage();
		groupChatMessage.setGroupChatMessageId(UUID.randomUUID().toString());
		groupChatMessage.setGroupId("group_id222");
		groupChatMessage.setUserId("user_id222");
		groupChatMessage.setMessageType("01");
		groupChatMessage.setMessageContent("hi");
		groupChatMessage.setTimeOfSend(new Date());

		messageService.sendGroupChatMessage(groupChatMessage);

		messageService.revokeGroupChatMessage(groupChatMessage);

		GroupChatMessage groupChatMessage2 = new GroupChatMessage();
		groupChatMessage2.setGroupId("group_id222");
		PageInfo<GroupChatMessage> selectResult = messageService.selectGroupChatMessage(groupChatMessage2, 1, 10);
		Assert.assertEquals(0, selectResult.getList().size());
	}

	@Test
	public void testSelectContactUnreadMessage() {
		ContactUnreadMessageDto contactUnreadMessageDto = new ContactUnreadMessageDto();
		contactUnreadMessageDto.setUserId("user_id222");
		List<ContactUnreadMessageDto> result = messageService.selectContactUnreadMessage(contactUnreadMessageDto);

		Assert.assertEquals("hello2", result.get(0).getLastChatMessage());
	}

	@Test
	public void testSelectGroupUnreadMessage() {
		GroupUnreadMessageDto groupUnreadMessageDto = new GroupUnreadMessageDto();
		groupUnreadMessageDto.setUserId("user_id111");
		List<GroupUnreadMessageDto> result = messageService.selectGroupUnreadMessage(groupUnreadMessageDto);
		Assert.assertEquals("hello2", result.get(0).getLastChatMessage());
	}

	@Test
	public void testSelectContactApplications() {
		String userId = "user_id111";

		List<ContactApplicationDto> result = messageService.selectContactApplications(userId);

		Assert.assertEquals("user_id333", result.get(0).getContactId());
	}

	@Test
	public void testSelectGrouptApplications() {
		String userId = "user_id111";

		List<GroupApplicationDto> result = messageService.selectGrouptApplications(userId);

		Assert.assertEquals("user_id333", result.get(0).getUserId());
	}

}
