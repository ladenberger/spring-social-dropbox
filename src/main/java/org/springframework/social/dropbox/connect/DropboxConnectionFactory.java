package org.springframework.social.dropbox.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.dropbox.api.Dropbox;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Dropbox Connection Factory
 * 
 * @author Bryce Fischer
 * @author Robert Drysdale
 */
public class DropboxConnectionFactory extends OAuth2ConnectionFactory<Dropbox> {

	public DropboxConnectionFactory(String appKey, String appSecret, boolean appFolder) {
		super("dropbox",
				new DropboxServiceProvider(new OAuth2Template(appKey, appSecret,
						"https://www.dropbox.com/oauth2/authorize", "https://api.dropbox.com/oauth2/token"), appFolder),
				new DropboxAdapter());
	}

}
