package com.urlshortener.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_url")
public class UrlEntity {
	
	@Id
	private Long id;
	
	private String key;
	
	private String longUrl;
	
	private String shortenerUrl;
	
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortenerUrl() {
		return shortenerUrl;
	}

	public void setShortenerUrl(String shortenerUrl) {
		this.shortenerUrl = shortenerUrl;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
