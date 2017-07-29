package com.example.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {

	private URLShortner urlShortner;

	@Value("${prepend.url:http://localhost:8080/}")
	private String prepedUrl;

	@Autowired
	public LinkController(URLShortner urlShortner) {
		this.urlShortner = urlShortner;
	}

	@GetMapping("/shorten")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Link shortenURL(@RequestParam("fullURL") String fullURL) {
		Link link = new Link(prepedUrl + urlShortner.shorten(fullURL).getShortURL(), fullURL);
		return link;
	}

	@GetMapping("/expand")
	@ResponseStatus(value = HttpStatus.OK)
	public Link expandURL(@RequestParam("shortURL") String shortURL) {
		Link link = new Link(prepedUrl + shortURL, urlShortner.expandURL(shortURL).getFullURL());
		return link;
	}

	@RequestMapping("/{shortURL}")
	void handleFoo(HttpServletResponse response, @PathVariable String shortURL) throws IOException {
		response.sendRedirect(urlShortner.expandURL(shortURL).getFullURL());
	}

}
