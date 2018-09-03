package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.entity.Contact;

@Mapper
public interface ContactMapper {
	int insert(Contact record);

	int insertSelective(Contact record);

	int deleteContact(Contact record);

	int updateContact(Contact record);

	List<Contact> selectContact(Contact record);
}