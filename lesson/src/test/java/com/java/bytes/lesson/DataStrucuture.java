package com.java.bytes.lesson;

import java.util.Arrays;

import org.junit.Test;

import com.prabhu.algorithms.week3.Point;

import junit.framework.TestCase;

public class DataStrucuture extends TestCase {

	@Test
	public void testSort() throws Exception {
		Point pointA = new Point(1, 2);
		Point pointB = new Point(2, 5);
		Point pointC = new Point(1, 4);
		Point pointD = new Point(2, 7);
		Point pointE = new Point(3, 8);
		Point pointF = new Point(1, 0);
		Point pointG = new Point(1, 2);
		Point[] ArrayPoints = new Point[7];
		ArrayPoints[0] = pointA;
		ArrayPoints[1] = pointB;
		ArrayPoints[2] = pointC;
		ArrayPoints[3] = pointD;
		ArrayPoints[4] = pointE;
		ArrayPoints[5] = pointF;
		ArrayPoints[6] = pointG;
		Arrays.sort(ArrayPoints);
	}

	@Test
	public void testZeroSlope() {
		Point pointA = new Point(1, 2);
		Point pointB = new Point(2, 2); 
		assertEquals(0.0,pointA.slopeTo(pointB));
	}

	public void testPositiveInfinitySlope() {
		Point pointA = new Point(1, 2);
		Point pointB = new Point(1, 100); 
		assertEquals(Double.POSITIVE_INFINITY,pointA.slopeTo(pointB));
	}

	public void testNegavtiveInfinitySlope() {
		Point pointA = new Point(1, 2);
		Point pointB = new Point(1, 2); 
		assertEquals(Double.NEGATIVE_INFINITY,pointA.slopeTo(pointB));
	}

	public void testSlope() {
		Point pointA = new Point(1, 2);
		Point pointB = new Point(4, 400); 
		double d = ((double)398/3);
		assertEquals(d,(pointA.slopeTo(pointB)));
	}
	
	public void testSlopeOrder(){
		Point pointA = new Point(1, 2);
		Point pointB = new Point(4, 400);
		Point pointC = new Point(1, 200);
		Point[] ArrayPoints = new Point[3];
		ArrayPoints[0] = pointA;
		ArrayPoints[1] = pointB;
		ArrayPoints[2] = pointC;
		Arrays.sort(ArrayPoints, pointC.slopeOrder());
		System.out.println(Arrays.toString(ArrayPoints));
	}

}
