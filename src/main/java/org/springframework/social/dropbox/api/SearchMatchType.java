package org.springframework.social.dropbox.api;

import java.io.Serializable;

public class SearchMatchType implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String tag;

	public SearchMatchType(String tag) {
		super();
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}
}
