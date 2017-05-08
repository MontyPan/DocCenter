package us.dontcareabout.docCenter.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//因為 dtc-core 沒有上 public Maven repo
//為了可以獨立 build，所以直接幹過來用

/**
 * 包含兩個 {@link Properties}：{@code inner} 與 {@code outter}。
 * 若 {@code outter} 有指定的 property，則優先使用 {@code outter} 的設定值；
 * 若無則使用 {@code inner} 的設定值。
 * <p>
 * 在設計思維上，{@code inner} 是 developer 直接 hard code 在程式碼中，
 * 所以僅提供 class path 的取得方式（{@link ClassLoader#getResourceAsStream(String)}），
 * {@code outter} 則是可選擇以 class path 或是指定檔案路徑的方式取得。
 * <p>
 * <b>注意：設定檔必須是 XML 格式，參見 {@link Properties#loadFromXML(InputStream)}</b>
 */
public class DoubleProperties {
	private Properties inner = new Properties();
	private Properties outter = new Properties();
	private boolean outterFlag = false;

	public DoubleProperties(String innerName, String outterName) {
		try {
			inner.loadFromXML(getResourceStream(innerName));
		} catch (IOException e) {}

		try {
			InputStream outterIS = getResourceStream(outterName);

			if (outterIS == null) { return; }

			outter.loadFromXML(outterIS);
			outterFlag = true;
		} catch(IOException e) {}
	}

	public DoubleProperties(String innerName, File outterFile) {
		try {
			inner.loadFromXML(getResourceStream(innerName));
		} catch (IOException e) {}

		try {
			if (outterFile == null) { return; }

			outter.loadFromXML(new FileInputStream(outterFile));
			outterFlag = true;
		} catch(IOException e) {}
	}

	protected String getProperty(String key) {
		if (outterFlag && outter.getProperty(key) != null) {
			return outter.getProperty(key);
		} else {
			return inner.getProperty(key);
		}
	}

	protected String getProperty(String key, String defaultValue) {
		if (outterFlag && outter.getProperty(key) != null) {
			return outter.getProperty(key);
		} else {
			return inner.getProperty(key, defaultValue);
		}
	}

	/**
	 * 將指定的 property 以行為單位、過濾空白行、並刪除行首行尾的空白，
	 * 並轉為 String 的 List。
	 */
	protected List<String> getPropertyList(String key) {
		ArrayList<String> result = new ArrayList<>();
		String property = getProperty(key);

		if (property == null) { return result; }

		for (String line : property.split("\n")) {
			String clean = line.trim();

			if (clean.isEmpty()) { continue; }

			result.add(clean);
		}

		return result;
	}

	private InputStream getResourceStream(String name) {
		return this.getClass().getClassLoader().getResourceAsStream(name);
	}
}
