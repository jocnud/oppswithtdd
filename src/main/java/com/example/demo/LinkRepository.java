package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class LinkRepository {

	private static List<Link> links = new ArrayList<>();

	public Link findByFullURL(String fullURL) {

		return links.stream().filter(l -> l.getFullURL().equals(fullURL)).findAny().orElse(null);

	}

	public void save(Link link) {
		links.add(link);
	}

}
