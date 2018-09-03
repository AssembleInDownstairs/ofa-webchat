package webchat_gd.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import webchat_gd.Application;
import webchat_gd.dto.ContactInfoDto;
import webchat_gd.entity.Contact;
import webchat_gd.mapper.ContactMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Sql("classpath:testSQL/insert-contactServiceTest.sql")
public class ContactServiceTest {

	@Autowired
	ContactService contactService;

	@Autowired
	ContactMapper contactMapper;

	@Test
	public void testSelectContactInfo() {
		String userId = "user_id111";
		ContactInfoDto param = new ContactInfoDto();
		param.setUserId(userId);
		List<ContactInfoDto> contactInfos = contactService.selectContactInfo(param);
		ContactInfoDto contactInfoDto = contactInfos.get(0);
		Assert.assertEquals("remark111", contactInfoDto.getRemark());
	}

	@Test
	public void testApplyContact() {
		String userId = "user_id333";
		String contactId = "user_id444";
		contactService.applyContact(userId, contactId);
		Contact contact = new Contact();
		contact.setUserId(contactId);
		contact.setContactId(userId);
		List<Contact> validateResult = contactMapper.selectContact(contact);
		Assert.assertEquals("user_id444", validateResult.get(0).getUserId());
	}

	@Test
	public void testAgreeContactApplication() {
		String userId = "user_id333";
		String contactId = "user_id444";
		contactService.applyContact(userId, contactId);
		contactService.agreeContactApplication(contactId, userId);

		ContactInfoDto param = new ContactInfoDto();
		param.setUserId(userId);
		List<ContactInfoDto> contactInfos = contactService.selectContactInfo(param);
		ContactInfoDto contactInfoDto = contactInfos.get(0);
		Assert.assertEquals("user_id444", contactInfoDto.getContactId());
	}

	@Test
	public void testRefuseContactApplication() {
		String userId = "user_id333";
		String contactId = "user_id444";
		contactService.applyContact(userId, contactId);
		contactService.refuseContactApplication(contactId, userId);
		Contact contact = new Contact();
		contact.setUserId(userId);
		contact.setContactId(contactId);
		List<Contact> validateResult = contactMapper.selectContact(contact);
		Assert.assertEquals(0, validateResult.size());

	}

	@Test
	public void testDeleteContact() {
		String userId = "user_id111";
		String contactId = "user_id222";
		contactService.deleteContact(contactId, userId);
		Contact contact = new Contact();
		contact.setUserId(userId);
		contact.setContactId(contactId);
		List<Contact> validateResult = contactMapper.selectContact(contact);
		Assert.assertEquals(0, validateResult.size());
	}

	@Test
	public void testUpdateContactRemark() {
		String userId = "user_id111";
		String contactId = "user_id222";
		String remark = "Yishen";
		contactService.updateContactRemark(userId, contactId, remark);
		ContactInfoDto param = new ContactInfoDto();
		param.setUserId(userId);
		List<ContactInfoDto> contactInfos = contactService.selectContactInfo(param);
		ContactInfoDto contactInfoDto = contactInfos.get(0);
		Assert.assertEquals("Yishen", contactInfoDto.getRemark());
	}

	@Test
	public void testUpdateLastContactTime() throws Exception {
		String userId = "user_id111";
		String contactId = "user_id222";
		boolean result = contactService.updateLastContactTime(userId, contactId);
		Assert.assertEquals(true, result);
	}

}
