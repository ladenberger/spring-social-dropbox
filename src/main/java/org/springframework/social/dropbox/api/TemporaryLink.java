package org.springframework.social.dropbox.api;

import java.io.Serializable;

public class TemporaryLink implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String link;
	private final Metadata metadata;

	public TemporaryLink(String link, Metadata metadata) {
		this.link = link;
		this.metadata = metadata;
	}

	public String getLink() {
		return link;
	}

	public Metadata getMetadata() {
		return metadata;
	}

}
