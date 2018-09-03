package webchat_gd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import webchat_gd.dto.DictMessageDto;
import webchat_gd.service.DictionaryService;

@RestController
@RequestMapping("/webchat/dict")
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;

	@GetMapping("/list")
	public PageInfo<DictMessageDto> selectDictMessage(DictMessageDto dictMessageDto, int pageNum, int pageSize) {
		return dictionaryService.selectDictMessage(dictMessageDto, pageNum, pageSize);
	}

}
