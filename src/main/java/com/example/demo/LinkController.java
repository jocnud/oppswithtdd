package com.example.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Object shortenURL(@RequestParam("fullURL") String fullURL) {
		Link link = urlShortner.shorten(fullURL);
		// link.setShortURL("http:/localhost:8080/redirect/" +
		// link.getShortURL());
		return "http://shtddopp.herokuapp.com/foo/" + link.getShortURL();
	}

	@GetMapping("/expand")
	@ResponseStatus(value = HttpStatus.OK)
	public Link expandURL(@RequestParam("shortURL") String shortURL) {

		return urlShortner.expandURL(shortURL);
	}

	@GetMapping("/redirect/{shortURL}")
	@ResponseStatus(value = HttpStatus.OK)
	RedirectView redirectToFull(@PathVariable String shortURL) {
		return new RedirectView("http://google.com");
	}

	@RequestMapping("/foo/{shortURL}")
	void handleFoo(HttpServletResponse response ,@PathVariable String shortURL) throws IOException {
		response.sendRedirect(urlShortner.expandURL(shortURL).getFullURL());
	}

}
