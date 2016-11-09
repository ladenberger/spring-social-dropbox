package org.springframework.social.dropbox.api;

import java.io.Serializable;
import java.util.List;

public class ListFolder implements Serializable {
	private static final long serialVersionUID = 1L;

	private final List<Metadata> entries;
	private final boolean hasMore;
	private final String cursor;

	public ListFolder(List<Metadata> entries, boolean hasMore, String cursor) {
		super();
		this.entries = entries;
		this.hasMore = hasMore;
		this.cursor = cursor;
	}

	public List<Metadata> getEntries() {
		return entries;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public String getCursor() {
		return cursor;
	}

}
