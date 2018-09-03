package webchat_gd.service;

import com.github.pagehelper.PageInfo;

import webchat_gd.dto.DictMessageDto;

/**
 * 字典模块.
 * 
 * @author Yishen
 *
 */
public interface DictionaryService {
	/**
	 * 查询字典信息.
	 * 
	 * @param dictMessageDto
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<DictMessageDto> selectDictMessage(DictMessageDto dictMessageDto, int pageNum, int pageSize);
}
