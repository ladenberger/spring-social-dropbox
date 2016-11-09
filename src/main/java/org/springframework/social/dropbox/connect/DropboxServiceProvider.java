package org.springframework.social.dropbox.connect;

import org.springframework.social.dropbox.api.Dropbox;
import org.springframework.social.dropbox.api.impl.DropboxTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

/**
 * @author Bryce Fischer
 * @author Robert Drysdale
 */
public class DropboxServiceProvider extends AbstractOAuth2ServiceProvider<Dropbox> {

	private final boolean appFolder;

	public DropboxServiceProvider(OAuth2Operations oauth2Operations, boolean appFolder) {
		super(oauth2Operations);
		this.appFolder = appFolder;
	}

	@Override
	public Dropbox getApi(String accessToken) {
		return new DropboxTemplate(accessToken, appFolder);
	}

}
