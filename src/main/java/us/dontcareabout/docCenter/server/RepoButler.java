package us.dontcareabout.docCenter.server;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import us.dontcareabout.docCenter.shared.vo.Document;
import us.dontcareabout.docCenter.shared.vo.Folder;

public class RepoButler {
	public static final RepoButler instance = new RepoButler();

	private HashMap<String, Folder> repoMap = new HashMap<>();
	private List<String> repoList;

	private RepoButler() {
		rebuild();
	}

	public synchronized void rebuild() {
		repoMap.clear();
		//以現在的架構來說 repoList 不可能變
		//但是理論上連 repoList 都應該是可以不靠 restart service 而改變
		//所以就還是先塞到這裡面來... [遠目]
		repoList = DocCenterSetting.repoList();

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

	public List<String> getRepoList() {
		return repoList;
	}

	public Folder findFolder(String repo) {
		return repoMap.get(repo);
	}

	private String genRealPath(String repo) {
		return Paths.get(DocCenterSetting.rootPath(), repo, "docs").toString();
	}
}
