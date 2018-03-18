/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PipeLineStages;

/**
 *
 * @author pooja
 */
class Point {
 protected int x;
 protected int y;
 public Point (int x, int y) { this.x = x; this.y = y; }
}
class ColorPoint extends Point {
 protected int color;
 public ColorPoint(int x, int y, int color) { super(x,y); this.color = color;     System.out.println(x);
 }
}
public class test {
 public static void main (String[] args){
 ColorPoint pa = new ColorPoint(1,2,3); //Line 0
 Point pb = new Point(4,5);
 // pa = pb; // Line 1
 // pb = pa; // Line 2
 // pa = (ColorPoint)pb; // Line 3
  pb = (Point)pa; // Line 4
 }
}
