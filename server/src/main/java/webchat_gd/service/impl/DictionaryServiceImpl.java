package webchat_gd.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import webchat_gd.dto.DictMessageDto;
import webchat_gd.entity.User;
import webchat_gd.mapper.DictionaryServiceMapper;
import webchat_gd.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	private Logger log = LoggerFactory.getLogger(DictionaryServiceImpl.class);

	@Autowired
	private DictionaryServiceMapper dictionaryServiceMapper;

	@Override
	public PageInfo<DictMessageDto> selectDictMessage(DictMessageDto dictMessageDto, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<DictMessageDto> data = dictionaryServiceMapper.selectDictMessage(dictMessageDto);
		PageInfo<DictMessageDto> result = new PageInfo<>(data);
		return result;
	}

}
