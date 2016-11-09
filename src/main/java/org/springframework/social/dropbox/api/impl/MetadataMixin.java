package org.springframework.social.dropbox.api.impl;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Robert Drysdale
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataMixin {
	@JsonCreator
	public MetadataMixin(@JsonProperty("id") String id, @JsonProperty("size") int size,
			@JsonProperty("name") String name, @JsonProperty(".tag") String tag, @JsonProperty("rev") String rev,
			@JsonProperty("thumb_exists") boolean thumbExists, @JsonProperty("modified") Date modified,
			@JsonProperty("path_display") String pathDisplay, @JsonProperty("url") String url) {
	}
}
