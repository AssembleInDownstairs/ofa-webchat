package webchat_gd.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webchat_gd.dto.ContactInfoDto;
import webchat_gd.entity.Contact;
import webchat_gd.mapper.ContactMapper;
import webchat_gd.mapper.ContactServiceMapper;
import webchat_gd.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactServiceMapper contactServiceMapper;

	@Autowired
	ContactMapper contactMapper;

	@Override
	public List<ContactInfoDto> selectContactInfo(ContactInfoDto contactInfoDto) {
		List<ContactInfoDto> data = contactServiceMapper.selectContactInfo(contactInfoDto);
		return data;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean applyContact(String userId, String contactId) {
		// 校验是否已经申请过
		Contact validate = new Contact();
		validate.setUserId(contactId);
		validate.setContactId(userId);
		List<Contact> hasRecord = contactMapper.selectContact(validate);
		if (hasRecord != null && hasRecord.size() > 0) {
			return false;
		}
		Date date = new Date();
		Contact contact = new Contact();
		contact.setContactRecordId(UUID.randomUUID().toString());
		contact.setUserId(contactId);
		contact.setContactId(userId);
		contact.setAddTime(date);
		contactMapper.insert(contact);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean agreeContactApplication(String userId, String contactId) {
		Date lastContactTime = new Date();
		Contact contact1 = new Contact();
		contact1.setUserId(userId);
		contact1.setContactId(contactId);
		contact1.setLastContactTime(lastContactTime);

		Contact contact2 = new Contact();
		contact2.setContactRecordId(UUID.randomUUID().toString());
		contact2.setUserId(contactId);
		contact2.setContactId(userId);
		contact2.setAddTime(lastContactTime);
		contact2.setLastContactTime(lastContactTime);
		contactMapper.updateContact(contact1);
		contactMapper.insert(contact2);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean refuseContactApplication(String userId, String contactId) {
		Contact contact1 = new Contact();
		contact1.setUserId(userId);
		contact1.setContactId(contactId);
		Contact contact2 = new Contact();
		contact2.setUserId(contactId);
		contact2.setContactId(userId);
		contactMapper.deleteContact(contact1);
		contactMapper.deleteContact(contact2);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean deleteContact(String userId, String contactId) {
		Contact contact1 = new Contact();
		contact1.setUserId(userId);
		contact1.setContactId(contactId);
		Contact contact2 = new Contact();
		contact2.setUserId(contactId);
		contact2.setContactId(userId);
		contactMapper.deleteContact(contact1);
		contactMapper.deleteContact(contact2);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateContactRemark(String userId, String contactId, String remark) {
		Contact contact = new Contact();
		contact.setUserId(userId);
		contact.setContactId(contactId);
		contact.setRemark(remark);
		contactMapper.updateContact(contact);
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean updateLastContactTime(String userId, String contactId) throws Exception {
		Contact contact = new Contact();
		contact.setUserId(userId);
		contact.setContactId(contactId);
		contact.setLastContactTime(new Date());

		int result = contactMapper.updateContact(contact);
		if (result == 1) {
			return true;
		} else {
			throw new Exception("更新与联系人最后聊天时间时发生错误");
		}

	}

}
