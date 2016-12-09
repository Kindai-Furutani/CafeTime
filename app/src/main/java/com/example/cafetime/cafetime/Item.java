package com.example.cafetime.cafetime;

/**
 * Created by c200 on 16/12/09.
 */

public class Item {
	private CharSequence mTitle;
	private CharSequence mDescription;

	public Item(){
		mTitle = "";
		mDescription="";
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

}
