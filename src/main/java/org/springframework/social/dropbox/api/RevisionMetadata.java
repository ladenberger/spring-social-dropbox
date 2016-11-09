package org.springframework.social.dropbox.api;

import java.io.Serializable;
import java.util.List;

public class RevisionMetadata implements Serializable {
	private static final long serialVersionUID = 1L;

	private final List<Metadata> entries;
	private final boolean isDeleted;

	public RevisionMetadata(List<Metadata> entries, boolean isDeleted) {
		super();
		this.entries = entries;
		this.isDeleted = isDeleted;
	}

	public List<Metadata> getEntries() {
		return entries;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

}
