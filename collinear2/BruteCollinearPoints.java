import java.util.*;

import edu.princeton.cs.algs4.StdDraw;
public class BruteCollinearPoints {
    //private Point[] points;
    private int num;
    private LineSegment[] seg;
    /**
     * finds all line segments containing 4 points
     * @param points
     */
   public BruteCollinearPoints(Point[] points) throws NullPointerException, IllegalArgumentException {
       if (points == null) {
           throw new NullPointerException("Null in input!");
       }
       for (int i = 0; i < points.length; i++) {
           if (points[i] == null) {
               throw new NullPointerException("Null in input!");
           }
       }
       for (int i = 0; i < points.length - 1; i++) {
           for (int j = i + 1; j < points.length; j++) {
               if (points[i].compareTo(points[j]) == 0) {
                   throw new IllegalArgumentException("Repeated point exists!");
               }
           }
       }
       //this.points = points;
       Point zero = new Point(0,0);
       Arrays.sort(points, zero.slopeOrder());
       num = 0;
       for (int i = 0; i < points.length - 3; i++) {
           for (int j = i + 1; j < points.length - 2; j++) {
               for (int k = j + 1; k < points.length - 1; k++) {
                   for (int m = k + 1; m < points.length; m++) {
                       if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])
                               && points[j].slopeTo(points[k]) == points[k].slopeTo(points[m])) {
                           num++;
                       }
                       
                   }
               }
           }
       }
       seg = new LineSegment[num];
       int count = 0;
       for (int i = 0; i < points.length - 3; i++) {
           for (int j = i + 1; j < points.length - 2; j++) {
               for (int k = j + 1; k < points.length - 1; k++) {
                   for (int m = k + 1; m < points.length; m++) {
                       if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])
                               && points[j].slopeTo(points[k]) == points[k].slopeTo(points[m])) {
                           seg[count++] = points[m].compareTo(points[i]) < 0 ?new LineSegment(points[m], points[i]):
                               new LineSegment(points[i], points[m]);
                       }
                       
                   }
               }
           }
       }
   }
   /**
    * the number of line segments
    * @return
    */
   public int numberOfSegments() {
       return num;
   }
   /**
    * the line segments
    * @return
    */
   public LineSegment[] segments() {
       return seg;
   }
   
   /*
   public static void main(String[] args) {

       In in = new In(args[0]);
       int n = in.readInt();
       Point[] points = new Point[n];
       for (int i = 0; i < n; i++) {
           int x = in.readInt();
           int y = in.readInt();
           points[i] = new Point(x, y);
       }

       StdDraw.enableDoubleBuffering();
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       for (Point p : points) {
           p.draw();
       }
       StdDraw.show();

       BruteCollinearPoints collinear = new BruteCollinearPoints(points);
       for (LineSegment segment : collinear.segments()) {
           StdOut.println(segment);
           segment.draw();
       }
       StdDraw.show();
   }
   */
}
