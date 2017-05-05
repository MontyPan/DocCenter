package us.dontcareabout.docCenter.shared.vo;

import java.io.File;
import java.util.ArrayList;

public class Folder {
	private final String relativePath;
	private final String name;
	private final ArrayList<Folder> folder = new ArrayList<>();
	private final ArrayList<String> file = new ArrayList<>();

	public Folder(String path) {
		this(path, path);
	}

	private Folder(String repoPath, String path) {
		this.relativePath = path.substring(repoPath.length());

		File root = new File(path);
		name = root.getName();

		for (File file : root.listFiles()) {
			if (file.isDirectory()) {
				folder.add(new Folder(repoPath, file.getAbsolutePath()));
				continue;
			}

			if (file.isFile()) {
				this.file.add(file.getName());
			}
		}
	}

	public String getName() {
		return name;
	}

	public ArrayList<Folder> getFolder() {
		return folder;
	}

	public ArrayList<String> getFile() {
		return file;
	}

	/**
	 * 注意：不會檢查 file 是否真的存在 {@link #getFile()} 當中
	 * @return 產生 {@link Document} 需要的 pathInfo
	 */
	public String genPathInfo(String file) {
		return (relativePath + '/' + file).replace('\\', '/');
	}
}