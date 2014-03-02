package Window;

import java.awt.*;
import javax.swing.*;

public class Matrix extends JPanel {

    private JPanel[][] matrixLocations;
    private JPanel simulationPanel;
    private JPanel resultsPanel;
    public Color lightGrey = new Color(200, 200, 200);

    public Matrix(int count, int height, int width) {
        this.setLayout(new Layout());
        this.setBackground(Color.white);
        this.initializeComponents(count, height, width);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void initializeComponents(int count, int height, int width) {
        simulationPanel = new JPanel();
        simulationPanel.setLayout(new GridLayout(0, count));
        simulationPanel.setBackground(Color.white);
        this.generateMatrix(count, height, width);
        this.add(simulationPanel);

        resultsPanel = new JPanel();
        resultsPanel.setBackground(Color.white);
        this.add(resultsPanel);
    }

    private void generateMatrix(int count, int height, int width) {
        matrixLocations = new JPanel[count][count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                matrixLocations[i][j] = new JPanel();
                matrixLocations[i][j].setBackground(new Color(0, 0, 0, 0));
                matrixLocations[i][j].setBorder(BorderFactory.createLineBorder(lightGrey));
                matrixLocations[i][j].setPreferredSize(new Dimension(height, width));
                simulationPanel.add(matrixLocations[i][j]);
            }
        }
    }
}
