package us.dontcareabout.docCenter.server;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;

import us.dontcareabout.docCenter.shared.vo.Document;
import us.dontcareabout.docCenter.shared.vo.Folder;

public class RepoButler {
	public static final RepoButler instance = new RepoButler();

	// ==== Refactory 抽去設定檔 ==== //
	private String repoRoot = "D:\\test\\docCenter";
	private String[] repoList = new String[]{"OrderStation", "PatientPortal"};
	// ======== //

	private HashMap<String, Folder> repoMap = new HashMap<>();

	private RepoButler() {
		for (String repo : repoList) {
			repoMap.put(repo, new Folder(genRealPath(repo)));
		}
	}

	public String getContext(Document doc) {
		File file = new File(
			genRealPath(doc.getRepo()), doc.getPath()
		);

		try {
			return new String(
				Files.readAllBytes(
					file.toPath()
				),
				StandardCharsets.UTF_8
			);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public Set<String> getRepoList() {
		return repoMap.keySet();
	}

	public Folder findFolder(String repo) {
		return repoMap.get(repo);
	}

	private String genRealPath(String repo) {
		return Paths.get(repoRoot, repo, "docs").toString();
	}
}
