package us.dontcareabout.docCenter.server;

public class JspUtil {
	/**
	 * @return 把字串轉成 JS 字串
	 */
	public static String toJsString(String string) {
		return string.replace("\r", "")
			.replace("\\", "\\\\")
			.replace("\"", "\\\"")
			.replace("\'", "\\\'");
	}
}
