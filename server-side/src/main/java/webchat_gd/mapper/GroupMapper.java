package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.entity.Group;

@Mapper
public interface GroupMapper {
	int insert(Group group);

	int insertSelective(Group group);

	int deleteGroup(String groupId);

	int updateGroup(Group group);

	List<Group> selectGroup(Group group);
}