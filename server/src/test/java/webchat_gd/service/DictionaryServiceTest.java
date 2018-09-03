package webchat_gd.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

import webchat_gd.Application;
import webchat_gd.dto.DictMessageDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class DictionaryServiceTest {

	@Autowired
	private DictionaryService dictionaryService;

	@Test
	public void testSelectDictMessage() {

		DictMessageDto param = new DictMessageDto();
		param.setDictCode("CITY");
		param.setItemValue("440600");

		PageInfo<DictMessageDto> result = dictionaryService.selectDictMessage(param, 1, 1);
		DictMessageDto dictMessage = result.getList().get(0);

		Assert.assertEquals("佛山市", dictMessage.getItemName());

	}
}
