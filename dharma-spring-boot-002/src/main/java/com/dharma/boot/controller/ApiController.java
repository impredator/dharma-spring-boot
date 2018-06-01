package com.dharma.boot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ApiController {

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public HashMap<String, Object> get(@RequestParam String name) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("action", "hello");
		map.put("name", name);
		return map;
	}
}
