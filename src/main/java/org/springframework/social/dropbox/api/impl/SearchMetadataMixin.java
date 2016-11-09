package org.springframework.social.dropbox.api.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.dropbox.api.SearchMetadataMatch;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchMetadataMixin {
	@JsonCreator
	public SearchMetadataMixin(@JsonProperty("matches") List<SearchMetadataMatch> matches, @JsonProperty("more") boolean more,
			@JsonProperty("start") int start) {
	}
}
