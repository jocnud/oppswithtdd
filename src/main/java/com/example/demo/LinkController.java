package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {

	private URLShortner urlShortner;

	@Autowired
	public LinkController(URLShortner urlShortner) {
		this.urlShortner = urlShortner;
	}

	@GetMapping("/shorten")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Link shortenURL(@RequestParam("fullURL") String fullURL) {
		return urlShortner.shorten(fullURL);
	}

}
