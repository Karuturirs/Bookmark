package com.bookmark.restservices.utils;

import java.util.Collection;
import java.util.HashSet;

import com.bookmark.restservices.model.BookMark;

public class BookMarkmaster 
{
	private HashSet<BookMark> dataList;
	private String path;
	public HashSet<BookMark> getDataList() {
		return dataList;
	}
	public void setDataList(HashSet<BookMark> dataList) {
		this.dataList = dataList;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
