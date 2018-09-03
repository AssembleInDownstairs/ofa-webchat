package webchat_gd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webchat_gd.dto.DictMessageDto;

@Mapper
public interface DictionaryServiceMapper {
	List<DictMessageDto> selectDictMessage(DictMessageDto dictMessageDto);
}
