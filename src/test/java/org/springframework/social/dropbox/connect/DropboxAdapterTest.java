package org.springframework.social.dropbox.connect;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.dropbox.api.Dropbox;
import org.springframework.social.dropbox.api.DropboxUserProfile;

/**
 * @author Bryce Fischer
 * @author Robert Drysdale
 */
public class DropboxAdapterTest {
	private DropboxAdapter adapter = new DropboxAdapter();
	private Dropbox dropboxApi = Mockito.mock(Dropbox.class);

	@Test
	public void fetchProfile() throws Exception {
		final String country = "USA";
		final String displayName = "DisplayName";
		final String accountId = "dbid:AAH4f99T0taONIb-OurWxbNQ6ywGRopQngc";
		String email = "emailaddress";
		String referralLink = "referralLink";

		Mockito.when(dropboxApi.getUserProfile())
				.thenReturn(new DropboxUserProfile(accountId, displayName, email, country, referralLink));

		UserProfile profile = adapter.fetchUserProfile(dropboxApi);

		assertEquals(displayName, profile.getName());
	}
}
