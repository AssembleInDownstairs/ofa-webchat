package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.dto.UserInfoDto;
import webchat_gd.entity.UserLoginRecord;

@Mapper
public interface UserServiceMapper {

	UserInfoDto findUserInfoById(String userId);

	List<UserInfoDto> findUserInfo(UserInfoDto userInfoDto);

	int updateUserInfo(UserInfoDto userInfoDto);

	int recordLoginInformation(UserLoginRecord userLoginRecord);
}
