package org.springframework.social.dropbox.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.dropbox.api.Metadata;
import org.springframework.social.dropbox.api.SearchMatchType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataMatchMixin {
	@JsonCreator
	public MetadataMatchMixin(@JsonProperty("match_type") SearchMatchType matchType,
			@JsonProperty("metadata") Metadata metadata) {
	}
}
