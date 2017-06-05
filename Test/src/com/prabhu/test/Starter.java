package com.prabhu.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Starter {

	public static void main(String[] args) throws ClassNotFoundException {
		Class t = Class.forName("com.ozthra.common.consumer.ConsumerApplication");
		// Annotation[]
		// Annotation[] =
		Annotation[] annotations = t.getAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			System.out.println(annotations[i]);
		}
		Field[] field = t.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			System.out.println(field[0].getType().getCanonicalName());
		}
	}

}
