package com.prabhu.designpatterns.structural.bridge;

public class Blog implements BaseSite {

	private Theme theme;

	public Blog(Theme theme) {
		this.theme = theme;
	}
	
	public void login() {
		System.out.println("blog login "+ theme.getColor());

	}

	public void save() {
		System.out.println("blog save "+ theme.getColor());

	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

}
