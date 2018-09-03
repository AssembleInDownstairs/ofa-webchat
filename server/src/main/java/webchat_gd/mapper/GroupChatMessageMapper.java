package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.dto.GroupApplicationDto;
import webchat_gd.dto.GroupUnreadMessageDto;
import webchat_gd.entity.GroupChatMessage;

@Mapper
public interface GroupChatMessageMapper {
	int insert(GroupChatMessage groupChatMessage);

	int insertSelective(GroupChatMessage groupChatMessage);

	int deleteGroupChatMessage(String groupChatMessageId);

	int deleteGroupChatMessageByGroupId(String groupId);

	List<GroupChatMessage> selectGroupChatMessage(GroupChatMessage groupChatMessage);

	List<GroupUnreadMessageDto> selectGroupUnreadMessage(GroupUnreadMessageDto groupUnreadMessageDto);

	List<GroupApplicationDto> selectGrouptApplications(String userId);

}