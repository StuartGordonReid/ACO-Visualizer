package Cube;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Cube3D
 *
 * @author kenny cason http://www.ken-soft.com 2009 June
 */
public class Rectangle implements Polygon {

    public short x;  // some vars for id purposes. i.e. location in a 3D puzzle
    public short y;
    public short z;

    private Point[] points;

    private CubeFace[] faces;

    private double xWidth;

    private double yWidth;

    private double zWidth;

    private Point axisOffset;

    private Transformation matOps;

    public Rectangle() {
        init(0, 0, 0, new Point(0, 0, 0));
    }

    public Rectangle(double xWidth, double yWidth, double zWidth) {
        init(xWidth, yWidth, zWidth, new Point(0, 0, 0));
    }

    public Rectangle(double xWidth, double yWidth, double zWidth,
            Point axisOffset) {
        init(xWidth, yWidth, zWidth, axisOffset);
    }

    public Rectangle(double width, Point axisOffset) {
        init(width, width, width, axisOffset);
    }

    private void init(double xWidth, double yWidth, double zWidth, Point axisOffset) {
        matOps = new Transformation();
        this.xWidth = xWidth;
        this.yWidth = yWidth;
        this.zWidth = zWidth;
        this.axisOffset = axisOffset;
        constructCube();
    }

    private void constructCube() {
        points = new Point[8];

        double x = xWidth / 2;
        double y = yWidth / 2;
        double z = zWidth / 2;

        points[0] = new Point(-x, -y, -z);
        points[1] = new Point(-x, -y, z);
        points[2] = new Point(x, -y, z);
        points[3] = new Point(x, -y, -z);
        points[4] = new Point(-x, y, -z);
        points[5] = new Point(-x, y, z);
        points[6] = new Point(x, y, z);
        points[7] = new Point(x, y, -z);

        faces = new CubeFace[6];
        faces[0] = new CubeFace(new Point[]{points[0], points[1], points[2], points[3]});
        faces[1] = new CubeFace(new Point[]{points[0], points[1], points[5], points[4]});
        faces[2] = new CubeFace(new Point[]{points[1], points[2], points[6], points[5]});
        faces[3] = new CubeFace(new Point[]{points[2], points[3], points[7], points[6]});
        faces[4] = new CubeFace(new Point[]{points[3], points[0], points[4], points[7]});
        faces[5] = new CubeFace(new Point[]{points[4], points[5], points[6], points[7]});

    }

    public void renderColored(Graphics _g, Point axis) {
        Point totOffset = new Point(axis.getX() + axisOffset.getX(), axis.getY() + axisOffset.getY(), axis.getZ() + axisOffset.getZ());

        /*	
         for(int i = 0; i < faces.length; i++) {
         faces[i].drawWires(_g, totOffset);
         }*/
        int[] ordered = orderFacesByZDepth();
        // only draw the three with the highest z index
        for (int i = 0; i < 3; i++) {
            faces[ordered[i]].drawColoredCube(_g, totOffset);
        }
    }

    public void renderWired(Graphics _g, Point axis) {
        Point totOffset = new Point(axis.getX() + axisOffset.getX(), axis.getY() + axisOffset.getY(), axis.getZ() + axisOffset.getZ());

        Graphics2D g = (Graphics2D) _g;
        //	g.drawLine((int)axis.getX(), (int)axis.getY(), (int)totOffset.getX(), (int)totOffset.getY());
        for (int i = 0; i < faces.length; i++) {
            faces[i].drawWires(g, totOffset);
        }

    }

    public void setAxisOffset(double x, double y, double z) {
        axisOffset = new Point(x, y, z);
    }

    public void setXAxisOffset(double x) {
        axisOffset.setX(x);
    }

    @Override
    public void setYAxisOffset(double y) {
        axisOffset.setY(y);
    }

    public void setZAxisOffset(double z) {
        axisOffset.setZ(z);
    }

    public void setAxisOffset(Point p) {
        axisOffset.setX(p.getX());
        axisOffset.setY(p.getY());
        axisOffset.setZ(p.getZ());
    }

    public Point getAxisOffset() {
        return axisOffset;
    }

    public double getXAxisOffset() {
        return axisOffset.getX();
    }

    public double getYAxisOffset() {
        return axisOffset.getY();
    }

    public double getZAxisOffset() {
        return axisOffset.getZ();
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public void setYWidth(double yWidth) {
        this.yWidth = yWidth;
    }

    public void setZWidth(double zWidth) {
        this.zWidth = zWidth;
    }

    public double getXWidth() {
        return xWidth;
    }

    public void setXWidth(double xWidth) {
        this.xWidth = xWidth;
    }

    public double getYWidth() {
        return yWidth;
    }

    public double getZWidth() {
        return zWidth;
    }

    public CubeFace[] getFaces() {
        return faces;
    }

    public void setFaces(CubeFace[] faces) {
        this.faces = faces;
    }

    public void rotateX(double theta) {
        for (int i = 0; i < points.length; i++) {
            points[i] = matOps.rotateX(points[i], theta);
        }
    }

    public void rotateY(double theta) {
        for (int i = 0; i < points.length; i++) {
            points[i] = matOps.rotateY(points[i], theta);
        }
    }

    public void rotateZ(double theta) {
        for (int i = 0; i < points.length; i++) {
            points[i] = matOps.rotateY(points[i], theta);
        }
    }

    public void rotateXAroundAxis(double theta) {
        rotateX(theta);
        axisOffset = matOps.rotateX(axisOffset, theta);
    }

    public void rotateYAroundAxis(double theta) {
        rotateY(theta);
        axisOffset = matOps.rotateY(axisOffset, theta);
    }

    public void rotateZAroundAxis(double theta) {
        rotateZ(theta);
        axisOffset = matOps.rotateZ(axisOffset, theta);
    }

    public void rotateAroundAxisOffsetVector(double theta) {
    }

    public void rotateFacesX(int dir) {
        Color temp;
        temp = faces[0].faceColor;
        faces[0].faceColor = faces[5].faceColor;
        faces[5].faceColor = temp;

        if (dir > 0) {
            temp = faces[1].faceColor;
            for (int i = 1; i < 4; i++) {
                faces[i].faceColor = faces[i + 1].faceColor;
            }
            faces[4].faceColor = temp;
        } else {
            temp = faces[4].faceColor;
            for (int i = 4; i > 1; i--) {
                faces[i].faceColor = faces[i - 1].faceColor;
            }
            faces[1].faceColor = temp;
        }
    }

    public void rotateFacesY(int dir) {
    }

    public void rotateFacesZ(int dir) {
    }

    public void setFaceColors(Color[] colors) {
        for (int i = 0; i < colors.length && i < faces.length; i++) {
            faces[i].setFaceColor(colors[i]);
        }
    }

    public int[] orderFacesByZDepth() {
        int[] ordered = new int[faces.length];
        for (int i = 0; i < ordered.length; i++) {
            ordered[i] = i;
        }
        for (int c = 1; c < ordered.length + 1; c++) {
            for (int i = 0; i < ordered.length - c; i++) {
                if (faces[ordered[i]].getZDepth() < faces[ordered[i + 1]].getZDepth()) {
                    int temp = ordered[i];
                    ordered[i] = ordered[i + 1];
                    ordered[i + 1] = temp;
                }
            }
        }
        return ordered;
    }

}
