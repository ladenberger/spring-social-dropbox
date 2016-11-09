package org.springframework.social.dropbox.api;

import java.io.Serializable;
import java.util.List;

public class SearchMetadata implements Serializable {
	private static final long serialVersionUID = 1L;

	private final List<SearchMetadataMatch> matches;
	private final boolean more;
	private final int start;

	public SearchMetadata(List<SearchMetadataMatch> matches, boolean more, int start) {
		super();
		this.matches = matches;
		this.more = more;
		this.start = start;
	}

	public List<SearchMetadataMatch> getMatches() {
		return matches;
	}

	public boolean isMore() {
		return more;
	}

	public int getStart() {
		return start;
	}

}
