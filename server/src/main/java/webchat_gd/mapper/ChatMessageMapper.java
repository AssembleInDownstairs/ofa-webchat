package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import webchat_gd.dto.ContactApplicationDto;
import webchat_gd.dto.ContactUnreadMessageDto;
import webchat_gd.entity.ChatMessage;

@Mapper
public interface ChatMessageMapper {
	int insert(ChatMessage record);

	int insertSelective(ChatMessage record);

	int deleteChatMessage(String chatMessageId);

	int deleteChatMessageByUC(@Param("userId") String userId, @Param("chatterId") String chatterId);

	List<ChatMessage> selectChatMessage(ChatMessage chatMessage);

	List<ContactUnreadMessageDto> selectContactUnreadMessage(ContactUnreadMessageDto contactUnreadMessageDto);

	List<ContactApplicationDto> selectContactApplications(String userId);

}