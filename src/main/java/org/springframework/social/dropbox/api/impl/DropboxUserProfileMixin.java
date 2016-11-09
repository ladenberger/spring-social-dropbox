package org.springframework.social.dropbox.api.impl;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.social.dropbox.api.DropboxUserProfile;

/**
 * @author Bryce Fischer
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = DropboxUserProfileMixin.DropboxUserProfileDeserializer.class)
public class DropboxUserProfileMixin {
	static class DropboxUserProfileDeserializer extends JsonDeserializer<DropboxUserProfile> {
		@Override
		public DropboxUserProfile deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

			JsonNode tree = jp.readValueAsTree();

			String referralLink = tree.get("referral_link").asText();
			String accountID = tree.get("account_id").asText();
			String country = tree.get("country").asText();
			String email = tree.get("email").asText();

			JsonNode nameNode = tree.get("name");
			String displayName = nameNode.get("display_name").asText();

			/*
			 * JsonNode quotaNode = tree.get("quota_info"); BigInteger
			 * sharedQuota = quotaNode.get("shared").bigIntegerValue();
			 * BigInteger quota = quotaNode.get("quota").bigIntegerValue();
			 * BigInteger normalQuota =
			 * quotaNode.get("normal").bigIntegerValue();
			 */

			return new DropboxUserProfile(accountID, displayName, email, country, referralLink);

		}
	}
}
