package Cube;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class CubeFace {

    private Color lineColor;

    public Color faceColor;

    private Point[] points;

    public CubeFace(Point[] points) {
        faceColor = Color.WHITE;
        lineColor = new Color(255, 255, 255, 20);
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i];
        }
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public void drawWires(Graphics _g, Point axisOffset) {
        Graphics2D g = (Graphics2D) _g;
        g.setColor(lineColor);
        for (int i = 0; i < points.length - 1; i++) {
            g.drawLine((int) (axisOffset.getX() + points[i].getX()), (int) (axisOffset.getY() + points[i].getY()),
                    (int) (axisOffset.getX() + points[i + 1].getX()), (int) (axisOffset.getY() + points[i + 1].getY()));
        }
        g.drawLine((int) (axisOffset.getX() + points[points.length - 1].getX()), (int) (axisOffset.getY() + points[points.length - 1].getY()),
                (int) (axisOffset.getX() + points[0].getX()), (int) (axisOffset.getY() + points[0].getY()));
    }

    public void drawColoredCube(Graphics _g, Point axisOffset) {
        Graphics2D g = (Graphics2D) _g;
        // I didn't feel like writing my own filly polygon method :P so I'm using JAVA's. sorry! 
        // The reason I didn't originally use Java polyon was because I wanted to experiment with making the algorithms myself
        // inlcuding managing z-indexes etc.
        Polygon p = new Polygon();
        for (int i = 0; i < points.length; i++) {
            p.addPoint((int) (axisOffset.getX() + points[i].getX()),
                    (int) (axisOffset.getY() + points[i].getY()));
        }
        g.setColor(faceColor);
        g.fillPolygon(p);
        drawWires(g, axisOffset);
    }

    public void setFaceColor(Color color) {
        faceColor = color;
    }

    public Color getFaceColor() {
        return faceColor;
    }

    public double getZDepth() {
        double val = 0;
        for (int i = 0; i < points.length; i++) {
            val += points[i].getZ();
        }
        return val /= points.length;
    }

}
