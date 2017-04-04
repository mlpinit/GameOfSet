package com.mlpinit.set;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class InfoPanel extends JPanel {
    private JLabel possibleSetsLabel;
    private JLabel setsCountLabel;
    private JButton threeMoreButton;
    private MainFrame mainFrame;

    public InfoPanel(MainFrame frame) {
        this.mainFrame = frame;

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        GridBagLayout gridBagLayout = new GridBagLayout(); 
        this.setLayout(gridBagLayout);
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(600, 120));
        this.possibleSetsLabel = createPossibleSetsJLabel();
        this.setsCountLabel = createSetsCountLabel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        gridBagLayout.setConstraints(setsCountLabel, constraints);
        this.add(possibleSetsLabel);
        constraints.gridx = 0;
        constraints.gridy = 1;
        gridBagLayout.setConstraints(possibleSetsLabel, constraints);
        this.add(setsCountLabel);
        constraints.weighty = 50;
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.threeMoreButton = new JButton("ADD");
        gridBagLayout.setConstraints(threeMoreButton, constraints);
        threeMoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.addThreeMoreCards();
            }
        });
        this.add(threeMoreButton);
    }

    public void updatePossibleSetsLabel(int possibleSets) {
        String start = possibleSets == 1 ? "There is " : "There are ";
        String end = possibleSets == 1 ? " possible set." : " possible sets";
        possibleSetsLabel.setText(start + possibleSets + end);
        possibleSetsLabel.validate();
        possibleSetsLabel.repaint();
    }

    public void updateSetsCount(int setsCount) {
        setsCountLabel.setText("Sets found: " + setsCount + ".");
        setsCountLabel.validate();
        setsCountLabel.repaint();
    }

    private JLabel createPossibleSetsJLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.gray);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        return label;
    }
    
    private JLabel createSetsCountLabel() {
        JLabel label = new JLabel("Sets found: 0.");
        label.setOpaque(true);
        label.setBackground(Color.gray);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        return label;
    }

}
