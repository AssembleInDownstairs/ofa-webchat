package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.dto.GroupListInfoDto;

@Mapper
public interface GroupServiceMapper {
	List<GroupListInfoDto> selectGroupInfo(GroupListInfoDto groupListInfoDto);
}
