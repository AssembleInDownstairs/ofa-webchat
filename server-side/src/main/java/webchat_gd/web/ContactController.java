package webchat_gd.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webchat_gd.dto.ContactInfoDto;
import webchat_gd.service.ContactService;

@RestController
@RequestMapping("/webchat/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/list")
	public List<ContactInfoDto> selectContactInfo(ContactInfoDto contactInfoDto) {
		return contactService.selectContactInfo(contactInfoDto);
	}

	@PostMapping("/application")
	public boolean applyContact(String userId, String contactId) throws Exception {
		if (userId == null || "".equals(userId) || contactId == null || "".equals(contactId)) {
			throw new Exception("用户id和好友id都不能为空");
		}
		// 判断申请目标是否是用户本身
		if (userId.equals(contactId)) {
			throw new Exception("不能向自己提出好友申请");
		}
		return contactService.applyContact(userId, contactId);
	}

	@PostMapping("/agreement")
	public boolean agreeContactApplication(String userId, String contactId) {
		return contactService.agreeContactApplication(userId, contactId);
	}

	@DeleteMapping("/refuse")
	public boolean refuseContactApplication(String userId, String contactId) throws Exception {
		if (userId == null || "".equals(userId) || contactId == null || "".equals(contactId)) {
			throw new Exception("用户id和好友id都不能为空");
		}
		return contactService.refuseContactApplication(userId, contactId);
	}

	@DeleteMapping("/delete")
	public boolean deleteContact(String userId, String contactId) throws Exception {
		if (userId == null || "".equals(userId) || contactId == null || "".equals(contactId)) {
			throw new Exception("用户id和好友id都不能为空");
		}
		return contactService.deleteContact(userId, contactId);
	}

	@PutMapping("/remark")
	public boolean updateContactRemark(String userId, String contactId, String remark) throws Exception {
		if (userId == null || "".equals(userId) || contactId == null || "".equals(contactId)) {
			throw new Exception("用户id和好友id都不能为空");
		}
		return contactService.updateContactRemark(userId, contactId, remark);
	}

	@PutMapping("/lastContactTime")
	public boolean updateLastContactTime(String userId, String contactId) throws Exception {
		return contactService.updateLastContactTime(userId, contactId);
	}

}
