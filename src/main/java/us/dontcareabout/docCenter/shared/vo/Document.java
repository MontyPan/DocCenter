package us.dontcareabout.docCenter.shared.vo;

import javax.servlet.http.HttpServletRequest;

/**
 * 將 {@link HttpServletRequest#getPathInfo()} 轉換成系統需要資料。
 * <p>
 * 例如 <code>pathInfo</code> 是「/foo/folder1/folder2/WTF.md」，
 * 則實際對應的檔案會是「foo」這個 repo 下的「/docs/folder1/folder2/WTF.md」。
 * 系統也需要知道副檔名（{@link #getType()}）以便作 dispatch。
 */
public class Document {
	private final String repo;
	private final String path;

	public Document(String pathInfo) {
		//開頭會是「/」，所以跳過
		int firstSeparator = pathInfo.indexOf('/', 1);
		repo = pathInfo.substring(1, firstSeparator);
		path = pathInfo.substring(firstSeparator + 1);
	}

	public String getRepo() {
		return repo;
	}

	public String getPath() {
		return path;
	}

	public String getType() {
		return path.substring(path.indexOf(".") + 1);
	}
}