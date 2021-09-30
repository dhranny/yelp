package com.yelp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping
public class homeController {

	@GetMapping()
	public String getHome() {
		return "Welcome Home";
	}
}
