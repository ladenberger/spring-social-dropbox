package org.springframework.social.dropbox.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.dropbox.api.Metadata;

/**
 * @author Robert Drysdale
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemporaryLinkMixin {
	@JsonCreator
	public TemporaryLinkMixin(@JsonProperty("link") String link,
			@JsonProperty("metadata") Metadata metadata) {
	}
}
