package us.dontcareabout.docCenter.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocCenterSetting extends DoubleProperties {
	private static DocCenterSetting instance = new DocCenterSetting();

	public DocCenterSetting() {
		super("dtc-config.xml", "docCenter.properties");
	}

	public static String rootPath() {
		return instance.getProperty("root.path");
	}

	//TODO 改成可以紀錄 repo 其他資訊的 VO
	public static List<String> repoList() {
		ArrayList<String> result = new ArrayList<>();
		String value = instance.getProperty("repo.list");

		if (value == null) { return result; }

		for (String line : value.split("\n")) {
			if (line.trim().isEmpty()) { continue; }
			result.add(line.trim());
		}

		Collections.sort(result);
		return result;
	}
}
