package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.entity.User;

@Mapper
public interface UserMapper {
	int insert(User record);

	int insertSelective(User record);

	List<User> selectUser(User user);
}