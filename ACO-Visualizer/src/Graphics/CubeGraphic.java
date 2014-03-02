package Graphics;

import Graphics.Polygon3D;
import Graphics.Point3D;
import Graphics.Rectangle3D;
import Listeners.*;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.JPanel;

public class CubeGraphic extends JPanel {

    private static final long serialVersionUID = -514363092944040524L;
    public LinkedList<Polygon3D> polygons;

    public Rectangle3D border;

    private int parentWidth, parentHeight;
    public int xOrig, yOrig, zOrig;

    private final int puzzleDim = 10;
    private final int pieceSize = 35;
    private final int spacing = 35;
    private final boolean solid = false;

    public CubeGraphic(int pH, int pW) {
        polygons = makePuzzle(puzzleDim, pieceSize, spacing, solid);
        parentHeight = pH;
        parentWidth = pW;

        xOrig = (int) (parentWidth / 2);
        yOrig = (int) (parentHeight / 2);
        zOrig = 0;

        setSize(parentHeight, parentWidth);
        setLocation(100, 100);
        validate();
        setVisible(true);
        this.setBackground(Color.black);
    }

    private LinkedList<Polygon3D> makePuzzle(int dim, int width, int space, boolean solid) {
        polygons = new LinkedList();
        int startPos;
        startPos = -(((dim - 1) * space) / 2);

        for (int z = 0, zOff = startPos; z < dim; z++, zOff += space) {
            for (int y = 0, yOff = startPos; y < dim; y++, yOff += space) {
                for (int x = 0, xOff = startPos; x < dim; x++, xOff += space) {
                    if (solid) {
                        polygons.add(new Rectangle3D(width, new Point3D(xOff, yOff, zOff)));
                    } else {
                        if (dim > 2) {
                            if (x == 0 || y == 0 || z == 0 || x == dim - 1
                                    || y == dim - 1 || z == dim - 1) {
                                Rectangle3D rect = new Rectangle3D(width, new Point3D(xOff, yOff, zOff));
                                rect.x = (short) x;
                                rect.y = (short) y;
                                rect.z = (short) z;
                                polygons.add(rect);
                            }
                        }
                    }
                }
            }

        }

        border = new Rectangle3D(Math.abs(2 * startPos) + width, new Point3D(0, 0, 0));

        for (Polygon3D polygon : polygons) {
            polygon.rotateXAroundAxis(-12);
            polygon.rotateYAroundAxis(15);
        }

        border.rotateXAroundAxis(-12);
        border.rotateYAroundAxis(15);

        return polygons;
    }

    public void setColors(int i) {
        Color transparent = new Color(255, 0, 0, 0);

        for (Polygon3D polygon : polygons) {
            polygon.setFaceColors(new Color[]{transparent, transparent, transparent, transparent, transparent, transparent});
        }
    }

    @Override
    public void paintComponent(Graphics _g) {
        Graphics2D g = (Graphics2D) _g;

        int[] ordered = orderPolygonsByZDepth();
        for (int i = ordered.length - 1; i >= 0; i--) {
            polygons.get(ordered[i]).renderColored(g, new Point3D(xOrig, yOrig, zOrig));
        }
    }

    public int[] orderPolygonsByZDepth() {
        int[] ordered = new int[polygons.size()];
        for (int i = 0; i < ordered.length; i++) {
            ordered[i] = i;
        }
        for (int c = 1; c < ordered.length + 1; c++) {
            for (int i = 0; i < ordered.length - c; i++) {
                if (polygons.get(ordered[i]).getZAxisOffset() < polygons.get(ordered[i + 1]).getZAxisOffset()) {
                    int temp = ordered[i];
                    ordered[i] = ordered[i + 1];
                    ordered[i + 1] = temp;
                }
            }
        }
        return ordered;
    }
}
