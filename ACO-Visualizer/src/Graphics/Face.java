package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Face {

    private Color lineColor;

    public Color faceColor;

    private Point3D[] points;

    public Face(Point3D[] points) {
        faceColor = new Color(255,255,255,0);
        lineColor = new Color(200, 200, 200, 255);
        this.points = new Point3D[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = points[i];
        }
    }

    public Point3D[] getPoints() {
        return points;
    }

    public void setPoints(Point3D[] points) {
        this.points = points;
    }

    public void drawWires(Graphics _g, Point3D axisOffset) {
        Graphics2D g = (Graphics2D) _g;
        g.setColor(lineColor);
        for (int i = 0; i < points.length - 1; i++) {
            g.drawLine((int) (axisOffset.getX() + points[i].getX()), (int) (axisOffset.getY() + points[i].getY()),
                    (int) (axisOffset.getX() + points[i + 1].getX()), (int) (axisOffset.getY() + points[i + 1].getY()));
        }
        g.drawLine((int) (axisOffset.getX() + points[points.length - 1].getX()), (int) (axisOffset.getY() + points[points.length - 1].getY()),
                (int) (axisOffset.getX() + points[0].getX()), (int) (axisOffset.getY() + points[0].getY()));
    }

    public void drawColoredCube(Graphics _g, Point3D axisOffset) {
        Graphics2D g = (Graphics2D) _g;
        Polygon p = new Polygon();
        for (int i = 0; i < points.length; i++) {
            p.addPoint((int) (axisOffset.getX() + points[i].getX()),
                    (int) (axisOffset.getY() + points[i].getY()));
        }
        g.setColor(faceColor);
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
