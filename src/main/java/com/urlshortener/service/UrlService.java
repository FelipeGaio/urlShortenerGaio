package com.urlshortener.service;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.entity.UrlEntity;
import com.urlshortener.repository.UrlRepository;
import com.urlshortener.util.IDGenerator;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository urlRepository;
	
	public String generateAndSave(String longUrl) {
		Long id = IDGenerator.createUniqueId();
		String idBase62 = IDGenerator.createUniqueUrlID(id);
		String newUrlShortener = IDGenerator.formatNewUrlShortener(idBase62, longUrl);
		
		UrlEntity entity = new UrlEntity();
		entity.setId(id);
		entity.setKey(idBase62);
		entity.setDate(Calendar.getInstance().getTime());
		entity.setLongUrl(longUrl);
		entity.setShortenerUrl(newUrlShortener);
		
		urlRepository.save(entity);
		
		return newUrlShortener;
	}


	public String findUrlByKey(String key) throws Exception {
		
		Long keyDictionary = IDGenerator.getDictionaryKeyFromUniqueID(key);
		
		Optional<UrlEntity> optionalUrl = Optional.ofNullable(urlRepository.findOne(keyDictionary));
		
		if(optionalUrl.isPresent()) {
			return optionalUrl.get().getLongUrl();
		}else {
			return null;
		}
		
	}
	
}
