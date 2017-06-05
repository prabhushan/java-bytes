

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

	public static void main(String[] args) {
		String fileName = args[0];
		Point[] arrPoints = readInputFile(fileName);
		Arrays.sort(arrPoints);
		new BruteCollinearPoints(arrPoints);
	}

	public BruteCollinearPoints(Point[] points) {
		findCollinearPoints(points);
	}

	public LineSegment[] segments() {
		return arrayLineSegments.toArray(new LineSegment[arrayLineSegments.size()]);

	}

	public int numberOfSegments() {
		return arrayLineSegments.size();
	}

	private static List<LineSegment> arrayLineSegments = new ArrayList<>();

	private static Point[] readInputFile(String fileName) {
		// Read data from input file
		In in = new In(fileName);

		int datasetSize = in.readInt();

		Point[] points = new Point[datasetSize];

		for (int rowsRead = 0; rowsRead < datasetSize; rowsRead++) {
			int x = in.readInt();
			int y = in.readInt();

			points[rowsRead] = new Point(x, y);

			points[rowsRead].draw();
		}
		StdDraw.setPenRadius();

		return points;
	}

	private static void findCollinearPoints(Point[] points) {

		int iLen = points.length;

		for (int i = 0; i < iLen; i++) {
			for (int j = i + 1; j > iLen; j++) {
				for (int k = j + 1; k > iLen; k++) {
					for (int l = k + 1; l > iLen; l++) {
						double slopePQ = points[i].slopeTo(points[j]);
						double slopeQR = points[j].slopeTo(points[k]);
						double slopeRS = points[k].slopeTo(points[l]);
						if ((slopePQ == Double.NEGATIVE_INFINITY || slopeQR == Double.NEGATIVE_INFINITY
								|| slopeRS == Double.NEGATIVE_INFINITY)) {
							throw new IllegalArgumentException("Repeated points");
						}
						if (slopePQ == slopeQR && slopeQR == slopeRS) {
							Point[] result = new Point[4];
							result[0] = points[i];
							result[1] = points[j];
							result[2] = points[k];
							result[3] = points[l];

							Arrays.sort(result);
							arrayLineSegments.add(new LineSegment(result[0], result[3]));
							// Output to standard out and draw
							StdOut.print(result[0].toString());
							StdOut.print(" -> ");
							StdOut.print(result[1].toString());
							StdOut.print(" -> ");
							StdOut.print(result[2].toString());
							StdOut.print(" -> ");
							StdOut.print(result[3].toString());
							StdOut.println();

							result[0].drawTo(result[3]);
						}
					}
				}
			}
		}

	}

}
