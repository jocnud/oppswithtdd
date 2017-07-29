package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class URLShortnerTest {

	private static final String SHORT_URL = "http://bit.ly/123";

	private static final String FULL_URL = "http://someveryveylongurl.com";

	Link link = new Link(SHORT_URL, FULL_URL);

	private URLShortner urlShortner;

	@Mock
	private LinkRepository linkRespository;

	@Before
	public void setup() {
		urlShortner = new URLShortner(linkRespository);
	}

	@Test
	public void shortenURL_whenLookUPFails() {

		when(linkRespository.findByFullURL(anyString())).thenReturn(null);

		Link link = urlShortner.shorten("http://someverylongurl.com");

		verify(linkRespository).findByFullURL(anyString());
		verify(linkRespository).save(link);

	}

	@Test
	public void returnShortenURL_whenURLFound() {
		when(linkRespository.findByFullURL(anyString())).thenReturn(link);

		Link link = urlShortner.shorten(FULL_URL);

		assertThat(link.getShortURL().equals(SHORT_URL));
		verify(linkRespository).findByFullURL(FULL_URL);

	}

}
