package com.mlpinit.set;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class InfoPanel extends JPanel {
    private JLabel possibleSetsLabel;
    private JLabel setsCountLabel;
    private JButton threeMoreButton;

    public InfoPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(600, 120));
        this.possibleSetsLabel = createPossibleSetsJLabel();
        this.setsCountLabel = createSetsCountLabel();
        this.add(possibleSetsLabel, BorderLayout.NORTH);
        this.add(setsCountLabel, BorderLayout.CENTER);
        this.threeMoreButton = new JButton("Add Three More");
        threeMoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame().addThreeMoreCards();
            }
        });
        this.add(threeMoreButton, BorderLayout.SOUTH);
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
        return label;
    }
    
    private JLabel createSetsCountLabel() {
        JLabel label = new JLabel("Sets found: 0.");
        label.setOpaque(true);
        label.setBackground(Color.gray);
        return label;
    }

    private MainFrame frame() {
        return (MainFrame) SwingUtilities.getWindowAncestor(this);
    }
}
