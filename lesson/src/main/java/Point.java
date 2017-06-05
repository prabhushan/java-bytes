

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

	private int x;
	private int y;

	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if (this.y <= o.y || (this.y == o.y && this.x < o.x)) {
			return -1;
		} else if (this.x == o.x && this.y == o.y) {
			return 0;
		}
		return 1;
	}

	/**
	 * Draws this point to standard draw.
	 */
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	/**
	 * Draws the line segment between this point and the specified point to
	 * standard draw.
	 *
	 * @param that
	 *            the other point
	 */
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
		// string representation
	}

	public double slopeTo(Point that) {
		double slope = 0.0;
		if ((this.y == that.y) && (this.x == that.x)) {
			return Double.NEGATIVE_INFINITY;
		} else if (this.y == that.y) {
			return 0;
		} else if (this.x == that.x) {
			return Double.POSITIVE_INFINITY;
		}
		slope = (double)(this.y - that.y) / (this.x - that.x);
		return slope;
	}

	public Comparator<Point> slopeOrder() {
		return new Comparator<Point>() {
			
			@Override
			public int compare(Point o1, Point o2) {
				double slope1 = Point.this.slopeTo(o1);
				double slope2 = Point.this.slopeTo(o2);
				
				if (slope1 < slope2) return -1;
				if (slope2 < slope1) return 1;
				return 0;
				
			}
		};

	}

}
