package org.springframework.social.dropbox.api.impl;

import static junit.framework.Assert.assertEquals;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.social.test.client.RequestMatchers.method;
import static org.springframework.social.test.client.RequestMatchers.requestTo;
import static org.springframework.social.test.client.ResponseCreators.withResponse;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.dropbox.api.DropboxFile;
import org.springframework.social.dropbox.api.DropboxUserProfile;
import org.springframework.social.dropbox.api.Metadata;
import org.springframework.social.dropbox.api.TemporaryLink;

/**
 * @author Bryce Fischer
 * @author Robert Drysdale
 */
public class DropboxTemplateTest extends AbstractDropboxApiTest {
	@Test
	public void getUserProfileValid() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.ACCOUNT_INFO_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/profileValid"), responseHeaders));

		DropboxUserProfile profile = dropbox.getUserProfile();
		assertEquals("US", profile.getCountry());
		assertEquals("some email", profile.getEmail());
		assertEquals("dbid:AAH4f99T0taONIb-OurWxbNQ6ywGRopQngc", profile.getAccountId());
		assertEquals("referralLink", profile.getReferralLink());
	}

	@Test
	public void putFile() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.FILE_PUT_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/file_put_metadata"), responseHeaders));

		FileInputStream stream = new FileInputStream(jsonResource("metadata").getFile());
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		while (true) {
			int b = stream.read();
			if (b == -1) {
				break;
			}
			bytes.write(b);
		}

		Metadata metadata = dropbox.putFile("file.json", bytes.toByteArray());
		assertEquals("7702a9405f", metadata.getRev());
		assertEquals(false, metadata.isThumbExists());
		assertEquals(fromDropboxDate("Fri, 02 Dec 2011 11:27:27 +0000"), metadata.getModified());
		assertEquals("/file.json", metadata.getPathDisplay());
		assertEquals(12, metadata.getSize());

		stream.close();

	}

	@Test
	public void copyFile() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.COPY_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/copy"), responseHeaders));

		Metadata metadata = dropbox.copy("file.json", "file3.json");
		assertEquals("8e02a9405f", metadata.getRev());
		assertEquals(false, metadata.isThumbExists());
		assertEquals("folder", metadata.getTag());
		assertEquals(fromDropboxDate("Fri, 16 Dec 2011 12:24:07 +0000"), metadata.getModified());
		assertEquals("/file3.json", metadata.getPathDisplay());
		assertEquals(12265, metadata.getSize());
	}

	@Test
	public void moveFile() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.MOVE_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/file_moved"), responseHeaders));

		Metadata metadata = dropbox.move("file.json", "file_moved.json");
		assertEquals("9202a9405f", metadata.getRev());
		assertEquals(false, metadata.isThumbExists());
		assertEquals(fromDropboxDate("Fri, 16 Dec 2011 14:39:42 +0000"), metadata.getModified());
		assertEquals("/file_moved.json", metadata.getPathDisplay());
		assertEquals(12, metadata.getSize());
	}

	@Test
	public void restoreFile() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.RESTORE_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/restored"), responseHeaders));

		Metadata metadata = dropbox.restore("file3.json", "8e02a9405f");
		assertEquals("9302a9405f", metadata.getRev());
		assertEquals(false, metadata.isThumbExists());
		assertEquals(fromDropboxDate("Fri, 16 Dec 2011 14:48:58 +0000"), metadata.getModified());
		assertEquals("/file3.json", metadata.getPathDisplay());
		assertEquals(12, metadata.getSize());
	}

	@Test
	public void getTemporaryLink() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.TEMP_LINK_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/temporaryLink"), responseHeaders));

		TemporaryLink url = dropbox.getTemporaryLink("file3.json");
		assertEquals("https://dl.dropbox.com/0/view/6rcp09bdfz1kxfv/file3.json", url.getLink());

		Metadata metadata = url.getMetadata();
		assertEquals("8e02a9405f", metadata.getRev());

	}

	@Test
	public void getShare() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.SHARES_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/share"), responseHeaders));

		Metadata metaData = dropbox.getShare("file3.json");
		assertEquals("https://www.dropbox.com/s/2sn712vy1ovegw8/Prime_Numbers.txt?dl=0", metaData.getUrl());

	}

	@Test
	public void searchFile() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.SEARCH_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/search"), responseHeaders));

		List<Metadata> list = dropbox.search("", "json");
		assertEquals(3, list.size());
		Metadata metadata = list.get(0);

		assertEquals("8d02a9405f", metadata.getRev());
		assertEquals(false, metadata.isThumbExists());
		assertEquals(fromDropboxDate("Fri, 16 Dec 2011 12:23:30 +0000"), metadata.getModified());
		assertEquals("/file2.json", metadata.getPathDisplay());
		assertEquals(12, metadata.getSize());
	}

	@Test
	public void createFolder() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.CREATE_FOLDER_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/create_folder"), responseHeaders));

		Metadata metadata = dropbox.createFolder("test");
		assertEquals("8f02a9405f", metadata.getRev());
		assertEquals(false, metadata.isThumbExists());
		assertEquals(fromDropboxDate("Fri, 16 Dec 2011 12:35:37 +0000"), metadata.getModified());
		assertEquals("/test", metadata.getPathDisplay());
		assertEquals(0, metadata.getSize());
	}

	@Test
	public void delete() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.DELETE_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/delete"), responseHeaders));

		Metadata metadata = dropbox.delete("file3.json");
		assertEquals("9002a9405f", metadata.getRev());
		assertEquals(false, metadata.isThumbExists());
		assertEquals(fromDropboxDate("Fri, 16 Dec 2011 12:57:00 +0000"), metadata.getModified());
		assertEquals("/file3.json", metadata.getPathDisplay());
		assertEquals(0, metadata.getSize());
	}

	@Test
	public void getFile() throws Exception {

		HttpHeaders h = new HttpHeaders();
		h.setContentType(MediaType.APPLICATION_JSON);
		h.setContentLength(1234);

		mockServer.expect(requestTo(DropboxTemplate.FILE_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/metadata"), h));

		DropboxFile file = dropbox.getFile("Getting Started.pdf");
		byte[] bytes = file.getBytes();
		assertEquals(1234, bytes.length);
	}

	@Test
	public void getRevisions() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.REVISIONS_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/revisions"), responseHeaders));
		List<Metadata> revisions = dropbox.getRevisions("file.json");

		Metadata file = revisions.get(0);
		assertEquals("7702a9405f", file.getRev());
		assertEquals(false, file.isThumbExists());
		assertEquals(fromDropboxDate("Fri, 02 Dec 2011 11:27:27 +0000"), file.getModified());
		assertEquals("/file.json", file.getPathDisplay());
		assertEquals(12, file.getSize());

		file = revisions.get(1);
		assertEquals("7602a9405f", file.getRev());
		assertEquals(false, file.isThumbExists());
		assertEquals(fromDropboxDate("Fri, 02 Dec 2011 11:24:16 +0000"), file.getModified());
		assertEquals("/file.json", file.getPathDisplay());
		assertEquals(543, file.getSize());
	}

	@Test
	public void getMetadata() throws Exception {
		mockServer.expect(requestTo(DropboxTemplate.METADATA_URL)).andExpect(method(POST))
				.andRespond(withResponse(jsonResource("/metadata"), responseHeaders));
		Metadata metadata = dropbox.getItemMetadata("");
		assertEquals("8e02a9405f", metadata.getRev());
		assertEquals(false, metadata.isThumbExists());
		assertEquals("folder", metadata.getTag());
		assertEquals(fromDropboxDate("Fri, 16 Dec 2011 12:24:07 +0000"), metadata.getModified());
		assertEquals("/file3.json", metadata.getPathDisplay());
		assertEquals(12265, metadata.getSize());
	}
}
