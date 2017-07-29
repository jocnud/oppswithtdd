package com.example.demo;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

@Service
public class URLShortner {

	private LinkRepository linkRepository;

	@Autowired
	public URLShortner(LinkRepository linkRespository) {
		this.linkRepository = linkRespository;
	}

	public Link shorten(String fullURL) {

		Link link = linkRepository.findByFullURL(fullURL);

		if (link == null) {
			String shortenURL = Hashing.murmur3_32().hashString(fullURL, StandardCharsets.UTF_8).toString();
			link = new Link("http://my.ly/" + shortenURL, fullURL);
			linkRepository.save(link);
		}
		return link;
	}

}
