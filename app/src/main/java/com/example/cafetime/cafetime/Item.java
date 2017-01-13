package com.example.cafetime.cafetime;


public class Item {
	private CharSequence mTitle;
	private CharSequence mDescription;
	private CharSequence mLink;

	public Item(){
		mTitle = "";
		mDescription="";
		mLink = "";
	}

	public CharSequence getDescription(){
		return mDescription;
	}

	public void setDescription(CharSequence description){
		mDescription = description;
	}

	public CharSequence getTitle(){
		return mTitle;
	}

	public void setTitle(CharSequence title){
		mTitle = title;
	}

//ここからxmlパーサで取得された内容から、Link(URL)に関する部分を抽出
	public CharSequence getLink(){
		return mLink;
	}

	public void setLink(CharSequence link){
		mLink = link;
	}

}
