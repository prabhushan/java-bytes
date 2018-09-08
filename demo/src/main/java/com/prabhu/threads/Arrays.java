package com.prabhu.threads;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

public class Arrays {

	static TemporalAdjuster friday13Adjuster = (temporal) -> {
		temporal = temporal.with(ChronoField.DAY_OF_MONTH, 13);
		while (temporal.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.getValue()) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS);
		}
		return temporal;
	};

	static LocalDate today = LocalDate.now();

	public static void main(String[] args) {
		System.out.println(today.with(friday13Adjuster));
	}
}
