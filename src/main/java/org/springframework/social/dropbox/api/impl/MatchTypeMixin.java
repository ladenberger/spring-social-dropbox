package org.springframework.social.dropbox.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchTypeMixin {
	@JsonCreator
	public MatchTypeMixin(@JsonProperty(".tag") String tag) {
	}
}
