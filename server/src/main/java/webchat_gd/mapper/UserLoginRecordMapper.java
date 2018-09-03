package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.entity.UserLoginRecord;

@Mapper
public interface UserLoginRecordMapper {
	int insert(UserLoginRecord record);

	int insertSelective(UserLoginRecord record);

	List<UserLoginRecord> selectUserLoginRecord(UserLoginRecord userLoginRecord);
}