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

		assertThat(link.equals(result));

	}

}
