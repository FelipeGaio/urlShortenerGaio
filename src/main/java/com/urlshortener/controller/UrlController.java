package com.urlshortener.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.json.UrlJson;
import com.urlshortener.service.UrlService;
import com.urlshortener.util.UrlValidator;

@RestController
@RequestMapping("api")
public class UrlController {

	@Autowired
	private UrlService urlService;
	
    @PostMapping (path = "/shortener", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> urlShortener(@RequestBody UrlJson requestJson){
		String url = requestJson.getUrl();
		
		if(UrlValidator.validateURL(url)) {
			UrlJson urlSaved = new UrlJson();
			urlSaved.setUrl(urlService.generateAndSave(url));
			return ResponseEntity.ok(urlSaved);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
    @GetMapping("/{id}")
    public void redirectToUrl(@PathVariable String id, HttpServletResponse resp) throws Exception {
        final String url = urlService.findUrlByKey(id);
        if (url != null) {
            resp.addHeader("Location", url);
            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
