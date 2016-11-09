package org.springframework.social.dropbox.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Metadata which describes a directory or file in Dropbox
 * 
 * @author Robert Drysdale
 *
 */
public class Metadata implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String id;
	private final int size;
	private final String name;
	private final String tag;
	private final String rev;
	private final boolean thumbExists;
	private final Date modified;
	private final String pathDisplay;
	private final String url;

	public Metadata(String id, int size, String name, String tag, String rev, boolean thumbExists, Date modified,
			String pathDisplay, String url) {
		super();
		this.id = id;
		this.size = size;
		this.name = name;
		this.tag = tag;
		this.rev = rev;
		this.thumbExists = thumbExists;
		this.modified = modified;
		this.pathDisplay = pathDisplay;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public int getSize() {
		return size;
	}

	public String getTag() {
		return tag;
	}

	public String getName() {
		return name;
	}

	public String getRev() {
		return rev;
	}

	public boolean isThumbExists() {
		return thumbExists;
	}

	public Date getModified() {
		return modified;
	}

	public String getPathDisplay() {
		return pathDisplay;
	}

	public String getUrl() {
		return url;
	}

	public boolean isDir() {
		return tag == "folder";
	}

}
