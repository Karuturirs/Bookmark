package com.bookmark.restservices.model;



public class BookMark 
{
	
	private int id;
	private String name;
	private String type;
	private String path;
	private String url;
	private String description;
	private String parent;
	private int parentid;
	public BookMark(){
		
	}
	

	public BookMark(int id, String name, String type, String path, String url,
			String description, String parent) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.path = path;
		this.url = url;
		this.description = description;
		this.parent =parent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public String getParent() {
		return parent;
	}


	public void setParent(String parent) {
		this.parent = parent;
	}


	public int getParentid() {
		return parentid;
	}


	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	
}
