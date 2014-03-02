package Listeners;

import Graphics.Polygon3D;
import Graphics.CubeGraphic;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Keyboard implements KeyListener {

    public CubeGraphic cube;
    private JFrame parent;

    public Keyboard(CubeGraphic c, JFrame p) {
        cube = c;
        parent = p;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            for (Polygon3D p : cube.polygons) {
                p.rotateXAroundAxis(-2);
            }
            cube.border.rotateXAroundAxis(-2);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            for (Polygon3D p : cube.polygons) {
                p.rotateXAroundAxis(2);
            }
            cube.border.rotateXAroundAxis(2);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            for (Polygon3D p : cube.polygons) {
                p.rotateYAroundAxis(2);
            }
            cube.border.rotateYAroundAxis(2);

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            for (Polygon3D p : cube.polygons) {
                p.rotateYAroundAxis(-2);
            }
            cube.border.rotateYAroundAxis(-2);

        }

        parent.repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

}
