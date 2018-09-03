package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import webchat_gd.dto.GroupMemberDto;
import webchat_gd.entity.GroupMenber;

@Mapper
public interface GroupMenberMapper {
	int insert(GroupMenber groupMenber);

	int insertSelective(GroupMenber groupMenber);

	int deleteGroupMenber(String groupMenberRecordId);

	int deleteGroupMenberByGroupId(String groupId);

	int deleteGroupMenberByUG(@Param("userId") String userId, @Param("groupId") String groupId);

	int updateGroupMenber(GroupMenber groupMenber);

	int updateGroupMenberByUG(GroupMenber groupMenber);

	List<GroupMemberDto> selectGroupMenber(GroupMenber groupMenber);
}