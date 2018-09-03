package webchat_gd.utils;

/**
 * 分页工具类.
 * 
 * @author Yishen
 *
 */
public class PageUtil {

	/**
	 * 获取limit的偏移量.
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public static int getOffset(Integer pageIndex, Integer pageSize) {

		if (pageIndex == null || pageIndex < 1) {
			try {
				throw new Exception("pageIndex不能为空或少于1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (pageSize == null || pageSize < 1) {
			try {
				throw new Exception("pageSize不能为空或少于1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		int offset = (pageIndex - 1) * pageSize;

		return offset;
	}

}
