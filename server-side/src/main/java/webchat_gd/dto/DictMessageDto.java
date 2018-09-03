package webchat_gd.dto;

/**
 * 字典信息dto.
 * 
 * @author Yishen
 *
 */
public class DictMessageDto {

	private String dictName;// 字典名称
	private String dictCode;// 字典编码
	private String itemName;// 字典条目名称
	private String itemValue;// 字典条目值
	private String language;// 语言

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
