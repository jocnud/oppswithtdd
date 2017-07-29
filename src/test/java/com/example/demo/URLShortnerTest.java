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

		Link link = urlShortner.shorten(FULL_URL);

		verify(linkRespository).findByFullURL(anyString());
		verify(linkRespository).save(link);

	}

	@Test
	public void returnShortenURL_whenFullURLFound() {
		when(linkRespository.findByFullURL(anyString())).thenReturn(link);

		Link link = urlShortner.shorten(FULL_URL);

		assertThat(link.getShortURL().equals(SHORT_URL));
		verify(linkRespository).findByFullURL(FULL_URL);

	}
	
	@Test
	public void returnFullURL_whenShortURLFound(){
		
		when(linkRespository.findByShortURL(SHORT_URL)).thenReturn(link);
	
		Link link = urlShortner.expandURL(SHORT_URL);
		assertThat(link.getFullURL().equals(FULL_URL));
		
		verify(linkRespository).findByShortURL(SHORT_URL);
		 
		
	}
	

}
