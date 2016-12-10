package com.prabhu.designpatterns.structural.bridge;

public class SocialMedia implements BaseSite {

	private Theme theme;

	public SocialMedia(Theme theme) {
		this.theme = theme;
	}
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public void login() {
		System.out.println("social login "+ theme.getColor());


	}

	public void save() {
		System.out.println("social save "+ theme.getColor());


	}

}
