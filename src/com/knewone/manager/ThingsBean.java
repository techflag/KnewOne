package com.knewone.manager;

import java.io.Serializable;
import com.google.gson.annotations.Expose;

public class ThingsBean implements Serializable {

	private static final long serialVersionUID = 1564151153015859299L;

	@Expose
	private String title;

	@Expose
	private String subtitle;

	@Expose
	private String cover_url;

	@Expose
	private String id;

	@Expose
	private String fanciers_count;
	
	@Expose
	private Author author; 


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFanciers_count() {
		return fanciers_count;
	}

	public void setFanciers_count(String fanciers_count) {
		this.fanciers_count = fanciers_count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getCover_url() {
		return cover_url;
	}

	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}

	
	
	
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}





	public static class Author {
		@Expose
		public String id;
		
		@Expose
		public String avatar_url;
		
		@Expose
		public String name;
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getAvatar_url() {
			return avatar_url;
		}
		public void setAvatar_url(String avatar_url) {
			this.avatar_url = avatar_url;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
	}

}
