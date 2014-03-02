package Window;

import Listeners.Keyboard;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame {

    public Color lightGrey = new Color(200, 200, 200);
    public JPanel heading, controller;
    public Matrix matrix;
    public Cube cube;
    public int state = 1;

    public static void main(String args[]) {
        Window window = new Window();
        window.pack();
        window.show();
    }

    Window() {
        this.setLayout(new WindowLayout());
        this.testLayout();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void testLayout() {
        JPanel jp1 = new JPanel();
        jp1.setBackground(lightGrey);
        this.getContentPane().add(jp1);

        if (state == 2) {
            cube = new Cube(32, 2, 2);
            this.getContentPane().add(cube);
            Keyboard keyListener = new Keyboard(cube.cubeGraphic, this);
            this.addKeyListener(keyListener);
        } else {
            matrix = new Matrix(32, 2, 2);
            this.getContentPane().add(matrix);
        }

        JPanel jp3 = new JPanel();
        jp3.setBackground(lightGrey);
        this.getContentPane().add(jp3);
    }
}

class WindowLayout implements LayoutManager {

    public WindowLayout() {
    }

    @Override
    public void addLayoutComponent(String string, Component parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeLayoutComponent(Component parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 1280 + insets.left + insets.right + 20;
        dim.height = 720 + insets.top + insets.bottom + 20;

        return dim;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        return dim;
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();

        int w = parent.getSize().width;
        int h = parent.getSize().height;
        int num1 = 0;
        Component c;

        //Heading and Info
        c = parent.getComponent(0);
        if (c.isVisible()) {
            c.setBounds(insets.left, insets.top + num1, (int) (w * 1.0), (int) (h * 0.1));
            num1 += c.getSize().height;
        }

        //Simulation and Results
        c = parent.getComponent(1);
        if (c.isVisible()) {
            c.setBounds(insets.left, insets.top + num1, (int) (w * 1.0), (int) (h * 0.8));
            num1 += c.getSize().height;
        }

        //Control and Summary
        c = parent.getComponent(2);
        if (c.isVisible()) {
            c.setBounds(insets.left, insets.top + num1, (int) (w * 1.0), (int) (h * 0.1));
            num1 += c.getSize().height;
        }
    }
}
