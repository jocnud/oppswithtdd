package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LinkRepositoryTest {

	@InjectMocks
	private LinkRepository linkRepository;

	@Test
	public void findByFullURL_shouldResturnSavedURL() {

		Link link = new Link("shortURL", "fullURL");
		linkRepository.save(link);
		Link result = linkRepository.findByFullURL("fullURL");

		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(link);

	}

	@Test
	public void findByShortURL_shoulResturnSavedURL() {
		Link link = new Link("bt.ly", "somefull.url");
		linkRepository.save(link);
		Link returnedlink = linkRepository.findByShortURL("bt.ly");

		assertThat(returnedlink).isNotNull();
		assertThat(returnedlink).isEqualTo(link);
	}

}
