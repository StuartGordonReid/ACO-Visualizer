package Window;

import Graphics.CubeGraphic;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Cube extends JPanel {

    public CubeGraphic cubeGraphic;
    private JPanel simulationPanel;
    private JPanel resultsPanel;
    public Color lightGrey = new Color(200, 200, 200);

    public Cube(int count, int height, int width) {
        this.setLayout(new Layout());
        this.setBackground(Color.white);
        this.initializeComponents(count, height, width);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void initializeComponents(int count, int height, int width) {
        cubeGraphic = new CubeGraphic(576, 576);
        this.add(cubeGraphic);

        resultsPanel = new JPanel();
        resultsPanel.setBackground(Color.white);
        this.add(resultsPanel);
    }
}
