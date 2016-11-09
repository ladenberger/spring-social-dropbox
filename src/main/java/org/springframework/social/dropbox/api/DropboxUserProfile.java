package org.springframework.social.dropbox.api;

/**
 * 
 * Dropbox User Profile
 * 
 * @author Bryce Fischer
 */
public class DropboxUserProfile {
	private String country;
	private final String referralLink;
	private final String email;
	private String accountId;
	private String displayName;

	public DropboxUserProfile(String accountId, String displayName, String email, String country, String referralLink) {
		this.accountId = accountId;
		this.displayName = displayName;
		this.email = email;
		this.country = country;
		this.referralLink = referralLink;
	}

	public String getCountry() {
		return country;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getReferralLink() {
		return referralLink;
	}

	public String getEmail() {
		return email;
	}

	public String getDisplayName() {
		return displayName;
	}
}
