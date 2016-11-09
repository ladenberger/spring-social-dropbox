package org.springframework.social.dropbox.api.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.dropbox.api.Metadata;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RevisionMetadataMixin {
	@JsonCreator
	public RevisionMetadataMixin(@JsonProperty("entries") List<Metadata> matches,
			@JsonProperty("is_deleted") boolean isDeleted) {
	}
}
