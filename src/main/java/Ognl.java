/**
 * 
*      
* 类名称：Ognl   
* 类描述：Ognl类 
* 创建人：dengyan  
* 创建时间：2016-3-14 下午5:20:02    
* 修改备注：   
* @version    
*
 */
public class Ognl {
	
	public static boolean isEmpty(Object o) {
		if(o == null)
			return true;
		if(o instanceof String) {
			String str = (String)o;
			if(str.length() == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
	
	public static boolean isNotBlank(Object o) {
		return !isBlank(o);
	}
	
	public static boolean isBlank(Object o) {		
		if(o == null)
			return true;
		if(o instanceof String) {
			System.out.println(o);
			String str = (String)o;
			return isBlank(str);
		}
		return false;
	}

	public static boolean isBlank(String str) {
		if(str == null || str.length() == 0) {
			return true;
		}
		
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
}
