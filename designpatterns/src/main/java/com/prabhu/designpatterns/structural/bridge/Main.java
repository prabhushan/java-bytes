package com.prabhu.designpatterns.structural.bridge;

public class Main {

	public static void main(String[] args) {
		BaseSite site1 = new Blog(new BlueTheme());
		site1.login();
		site1.save();
		
		BaseSite site2 = new Blog(new RedTheme());
		site2.login();
		site2.save();
		
		BaseSite site3 = new SocialMedia(new BlueTheme());
		site3.login();
		site3.save();
	
		BaseSite site4 = new SocialMedia(new RedTheme());
		site4.login();
		site4.save();
		

	}

}
