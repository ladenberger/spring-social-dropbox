package org.springframework.social.dropbox.api.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.dropbox.api.Metadata;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListFolderMixin {
	@JsonCreator
	public ListFolderMixin(@JsonProperty("entries") List<Metadata> entries, @JsonProperty("has_more") boolean hasMore,
			@JsonProperty("cursor") String cursor) {
	}
}
