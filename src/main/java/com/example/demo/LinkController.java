package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LinkController {

	private URLShortner urlShortner;

	@Autowired
	public LinkController(URLShortner urlShortner) {
		this.urlShortner = urlShortner;
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Link shortenURL(@RequestParam("fullURL") String fullURL) {
		Link link = urlShortner.shorten(fullURL);
		link.setShortURL("http://shtddopp.herokuapp.com/redirect/"+link.getShortURL());
		return link;
	}

	@GetMapping("/expand")
	@ResponseStatus(value = HttpStatus.OK)
	public Link expandURL(@RequestParam("shortURL") String shortURL) {

		return urlShortner.expandURL(shortURL);
	}

	@GetMapping("/redirect/{shortURL}")
	@ResponseStatus(value = HttpStatus.OK)
	RedirectView redirectToFull(@PathVariable String shortURL) {
		
		return new RedirectView(urlShortner.expandURL(shortURL).getFullURL());
	}

}
