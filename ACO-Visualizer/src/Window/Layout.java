package Window;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

class Layout implements LayoutManager {

    public Layout() {
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
        dim.width = 1280 + insets.left + insets.right - 20;
        dim.height = (int) (720 * 0.8) + insets.top + insets.bottom - 20;

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

        int w = parent.getSize().width - 20;
        int h = parent.getSize().height - 20;
        int num1 = 0;
        Component c;

        //Heading and Info
        c = parent.getComponent(0);
        if (c.isVisible()) {
            c.setBounds(insets.left, insets.top + num1, (int) (h * 1.0), (int) (h * 1.0));
            num1 += c.getSize().width;
        }

        //Simulation and Results
        c = parent.getComponent(1);
        if (c.isVisible()) {
            c.setBounds(insets.left + num1, insets.top, (int) (w - num1), (int) (h * 1.0));
            num1 += c.getSize().width;
        }
    }
}
