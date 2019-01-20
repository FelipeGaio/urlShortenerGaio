package com.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlshortener.entity.UrlEntity;

@Repository
public interface UrlRepository extends CrudRepository<UrlEntity, Long> {

	UrlEntity findByKey(String key);
	
}
