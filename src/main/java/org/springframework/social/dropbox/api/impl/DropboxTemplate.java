package org.springframework.social.dropbox.api.impl;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.dropbox.api.Dropbox;
import org.springframework.social.dropbox.api.DropboxFile;
import org.springframework.social.dropbox.api.DropboxUserProfile;
import org.springframework.social.dropbox.api.ListFolder;
import org.springframework.social.dropbox.api.Metadata;
import org.springframework.social.dropbox.api.RevisionMetadata;
import org.springframework.social.dropbox.api.SearchMetadata;
import org.springframework.social.dropbox.api.SearchMetadataMatch;
import org.springframework.social.dropbox.api.TemporaryLink;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bryce Fischer
 * @author Robert Drysdale
 */
public class DropboxTemplate extends AbstractOAuth2ApiBinding implements Dropbox {
	// private final String appFolderUrl;
	private ObjectMapper objectMapper;

	public DropboxTemplate(String accessToken, boolean appFolder) {
		super(accessToken);
		// appFolderUrl = appFolder ? "sandbox" : "dropbox";
		registerDropboxJsonModule(getRestTemplate());
	}

	public DropboxTemplate() {
		super();
		// appFolderUrl = null;
	}

	public DropboxUserProfile getUserProfile() {
		return getRestTemplate().postForObject(ACCOUNT_INFO_URL, null, DropboxUserProfile.class);
	}

	public Metadata getItemMetadata(String path) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		// vars.add("root", appFolderUrl);
		vars.add("path", path);
		return getRestTemplate().postForObject(METADATA_URL, vars, Metadata.class);
	}

	public Metadata restore(String path, String rev) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		vars.add("path", path);
		vars.add("rev", rev);
		return getRestTemplate().postForObject(RESTORE_URL, vars, Metadata.class);
	}

	public Metadata copy(String fromPath, String toPath) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		// vars.add("root", appFolderUrl);
		vars.add("from_path", fromPath);
		vars.add("to_path", toPath);
		vars.add("allow_shared_folder", "false"); // New in v2
		vars.add("autorename", "false"); // New in v2
		return getRestTemplate().postForObject(COPY_URL, vars, Metadata.class);
	}

	public Metadata createFolder(String folder) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		// vars.add("root", appFolderUrl);
		vars.add("autorename", "false"); // New in v2
		vars.add("path", folder);
		return getRestTemplate().postForObject(CREATE_FOLDER_URL, vars, Metadata.class);
	}

	public Metadata delete(String path) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		// vars.add("root", appFolderUrl);
		vars.add("path", path);
		return getRestTemplate().postForObject(DELETE_URL, vars, Metadata.class);
	}

	public Metadata move(String fromPath, String toPath) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		// vars.add("root", appFolderUrl);
		vars.add("from_path", fromPath);
		vars.add("to_path", toPath);
		vars.add("allow_shared_folder", "false"); // New in v2
		vars.add("autorename", "false"); // New in v2
		return getRestTemplate().postForObject(MOVE_URL, vars, Metadata.class);
	}

	public List<Metadata> getRevisions(String path) {

		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		// vars.add("root", appFolderUrl);
		vars.add("path", path);
		RevisionMetadata revisionMetadata = getRestTemplate().postForObject(REVISIONS_URL, vars,
				RevisionMetadata.class);

		return revisionMetadata.getEntries();

	}

	public List<Metadata> search(String path, String query) {

		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		// vars.add("root", appFolderUrl);
		vars.add("path", path);
		vars.add("query", query);
		SearchMetadata listMetadata = getRestTemplate().postForObject(SEARCH_URL, vars, SearchMetadata.class);

		List<Metadata> metadataList = new ArrayList<Metadata>();
		for (SearchMetadataMatch metadataMatch : listMetadata.getMatches()) {
			metadataList.add(metadataMatch.getMetadata());
		}

		return metadataList;

	}

	public DropboxFile getThumbnail(String path, String format, String size) {
		try {
			URI uri = new URI(THUMBNAILS_URL);
			ClientHttpRequest request = getRestTemplate().getRequestFactory().createRequest(uri, HttpMethod.POST);
			request.getHeaders().add("path", path);
			request.getHeaders().add("format", format);
			request.getHeaders().add("size", size);
			ClientHttpResponse response = request.execute();
			HttpHeaders headers = response.getHeaders();
			return new DropboxFile(headers.getContentType().toString(), headers.getContentLength(), response.getBody());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public DropboxFile getFile(String path) {
		try {
			URI uri = new URI(FILE_URL);
			ClientHttpRequest request = getRestTemplate().getRequestFactory().createRequest(uri, HttpMethod.POST);
			request.getHeaders().add("path", path);
			ClientHttpResponse response = request.execute();
			HttpHeaders headers = response.getHeaders();
			return new DropboxFile(headers.getContentType().toString(), headers.getContentLength(), response.getBody());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public TemporaryLink getTemporaryLink(String path) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		// vars.add("root", appFolderUrl);
		vars.add("path", path);
		return getRestTemplate().postForObject(TEMP_LINK_URL, vars, TemporaryLink.class);
	}

	public Metadata getShare(String path) {
		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		// vars.add("root", appFolderUrl);
		vars.add("path", path);
		return getRestTemplate().postForObject(SHARES_URL, vars, Metadata.class);
	}

	public ListFolder getListFolder(String path, boolean recursive, boolean includeMediaInfo, boolean includeDeleted,
			boolean includeHasExplicitSharedMembers) {

		MultiValueMap<String, String> vars = new LinkedMultiValueMap<String, String>();
		vars.add("path", path);
		vars.add("recursive", String.valueOf(recursive));
		vars.add("include_media_info", String.valueOf(includeMediaInfo));
		vars.add("include_deleted", String.valueOf(includeDeleted));
		vars.add("include_has_explicit_shared_members", String.valueOf(includeHasExplicitSharedMembers));
		return getRestTemplate().postForObject(LIST_FOLDER_URL, vars, ListFolder.class);

	}

	public ListFolder getListFolder(String path) {
		return getListFolder(path, true, false, false, false);
	}

	public Metadata putFile(String path, byte[] file) {
		try {
			URI uri = new URI(FILE_PUT_URL);
			ClientHttpRequest request = getRestTemplate().getRequestFactory().createRequest(uri, HttpMethod.POST);
			request.getBody().write(file);

			ClientHttpResponse response = request.execute();
			ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();
			if (errorHandler.hasError(response)) {
				errorHandler.handleError(response);
				return null;
			} else {
				InputStream stream = response.getBody();
				return objectMapper.readValue(stream, Metadata.class);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

    private void registerDropboxJsonModule(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();

        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJacksonHttpMessageConverter) {
                MappingJacksonHttpMessageConverter jsonConverter = (MappingJacksonHttpMessageConverter) converter;

                objectMapper = new ObjectMapper();
                objectMapper.registerModule(new DropboxModule());
                jsonConverter.setObjectMapper(objectMapper);
            }
        }
    }

	public static final String BASE_URL = "https://api.dropbox.com/2/";
	public static final String BASE_CONTENT_URL = "https://content.dropboxapi.com/2/";

	public static final String ACCOUNT_INFO_URL = BASE_URL + "users/get_current_account";
	public static final String COPY_URL = BASE_URL + "files/copy";
	public static final String CREATE_FOLDER_URL = BASE_URL + "files/create_folder";
	public static final String DELETE_URL = BASE_URL + "files/delete";
	public static final String MOVE_URL = BASE_URL + "files/move";
	public static final String RESTORE_URL = BASE_URL + "files/restore";
	public static final String SEARCH_URL = BASE_URL + "files/search";
	public static final String REVISIONS_URL = BASE_URL + "files/list_revisions";
	public static final String METADATA_URL = BASE_URL + "files/get_metadata";
	public static final String TEMP_LINK_URL = BASE_URL + "files/get_temporary_link";
	public static final String LIST_FOLDER_URL = BASE_URL + "files/list_folder";

	public static final String FILE_URL = BASE_CONTENT_URL + "files/download";
	public static final String FILE_PUT_URL = BASE_CONTENT_URL + "files/upload";
	public static final String THUMBNAILS_URL = BASE_CONTENT_URL + "files/get_thumbnail";

	public static final String SHARES_URL = BASE_URL + "sharing/create_shared_link_with_settings";

}
