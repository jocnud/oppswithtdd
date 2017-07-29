package com.example.demo;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@WebMvcTest(LinkController.class)
public class LinkControllerTest {

	private static final String SHORT_URL = "abc123";
	private static final String FULL_URL = "http://www.averylongurl.com";
	private Link mockLink = new Link(SHORT_URL, FULL_URL);

	@Autowired
	private LinkController linkController;

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private URLShortner urlShortner;

	@Test
	public void shorted_createShortURL() throws Exception {

		// Arrange
		when(urlShortner.shorten(anyString())).thenReturn(mockLink);

		// Action
		ResultActions result = mockmvc.perform(get("/").param("fullURL", FULL_URL));

		// Assert
		result.andExpect(status().isCreated()).andExpect(jsonPath("short_url").value(SHORT_URL))
				.andExpect(jsonPath("full_url").value(FULL_URL));

	}
}
