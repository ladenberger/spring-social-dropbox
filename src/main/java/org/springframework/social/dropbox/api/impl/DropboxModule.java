package org.springframework.social.dropbox.api.impl;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.social.dropbox.api.DropboxUserProfile;
import org.springframework.social.dropbox.api.ListFolder;
import org.springframework.social.dropbox.api.Metadata;
import org.springframework.social.dropbox.api.RevisionMetadata;
import org.springframework.social.dropbox.api.SearchMatchType;
import org.springframework.social.dropbox.api.SearchMetadata;
import org.springframework.social.dropbox.api.SearchMetadataMatch;
import org.springframework.social.dropbox.api.TemporaryLink;

/**
 * @author Bryce Fischer
 * @author Robert Drysdale
 */
public class DropboxModule extends SimpleModule {
    public DropboxModule() {
        super("DropboxModule", new Version(1, 0, 0, null));
    }

    @Override
    public void setupModule(SetupContext context) {
		context.setMixInAnnotations(DropboxUserProfile.class, DropboxUserProfileMixin.class);
		context.setMixInAnnotations(Metadata.class, MetadataMixin.class);
		context.setMixInAnnotations(TemporaryLink.class, TemporaryLinkMixin.class);
		context.setMixInAnnotations(SearchMetadata.class, SearchMetadataMixin.class);
		context.setMixInAnnotations(SearchMetadataMatch.class, MetadataMatchMixin.class);
		context.setMixInAnnotations(SearchMatchType.class, MatchTypeMixin.class);
		context.setMixInAnnotations(RevisionMetadata.class, RevisionMetadataMixin.class);
		context.setMixInAnnotations(ListFolder.class, ListFolderMixin.class);
    }
}
