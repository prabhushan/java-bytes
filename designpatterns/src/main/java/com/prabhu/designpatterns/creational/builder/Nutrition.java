package com.prabhu.designpatterns.creational.builder;

public class Nutrition {
	private int calories;
	private int carb;
	private int fat;
	// optional fields
	private int salt;
	private int sugar;

	private Nutrition(int calories, int carb, int fat, int salt, int sugar) {
		this.calories = calories;
		this.carb = carb;
		this.fat = fat;
		this.salt = salt;
		this.sugar = sugar;
	}

	public int getCalories() {
		return calories;
	}

	public int getCarb() {
		return carb;
	}

	public int getFat() {
		return fat;
	}

	public int getSalt() {
		return salt;
	}

	public int getSugar() {
		return sugar;
	}
	
	@Override
	public String toString() {
		
		return carb + "~"+calories+"~"+fat+"~"+salt+"~"+sugar;
	}

	static class NutritionBuilder {
		private int calories;
		private int carb;
		private int fat;
		private int salt;
		private int sugar;

		public NutritionBuilder(int cal, int carb, int fat) {
			this.calories = cal;
			this.carb = carb;
			this.fat = fat;
		}

		public NutritionBuilder addSalt(int salt) {
			this.salt = salt;
			return this;
		}

		public NutritionBuilder addSugar(int sugar) {
			this.sugar = sugar;
			return this;
		}

		public Nutrition build() {
			return new Nutrition(this.calories, this.carb, this.fat, this.salt, this.sugar);
		}
	}


}
