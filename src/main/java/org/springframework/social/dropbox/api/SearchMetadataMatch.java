package org.springframework.social.dropbox.api;

import java.io.Serializable;

public class SearchMetadataMatch implements Serializable {
	private static final long serialVersionUID = 1L;

	private final SearchMatchType matchType;
	private final Metadata metadata;

	public SearchMetadataMatch(SearchMatchType matchType, Metadata metadata) {
		super();
		this.matchType = matchType;
		this.metadata = metadata;
	}

	public SearchMatchType getMatchType() {
		return matchType;
	}

	public Metadata getMetadata() {
		return metadata;
	}

}
