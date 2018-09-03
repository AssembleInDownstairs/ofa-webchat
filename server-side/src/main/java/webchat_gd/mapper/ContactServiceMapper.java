package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.dto.ContactInfoDto;

@Mapper
public interface ContactServiceMapper {

	List<ContactInfoDto> selectContactInfo(ContactInfoDto contactInfoDto);

	int agreeContactApplication(String userId, String contactId);

}
