package de.thephoenixpixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame {
    private JPanel mainPanel;
    private JButton button7;
    private JButton button4;
    private JButton button1;
    private JButton button2;
    private JButton button5;
    private JButton button8;
    private JButton button3;
    private JButton button6;
    private JButton button9;
    private boolean player1IsPlay = true;
    private JButton[][] buttons;
    public MainPanel() {
        setContentPane(mainPanel);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(650, 500);

        setDefaultButtons();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayer(button1);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayer(button2);
                checkWinner();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayer(button3);
                checkWinner();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayer(button4);
                checkWinner();
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayer(button5);
                checkWinner();
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayer(button6);
                checkWinner();
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayer(button7);
                checkWinner();
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayer(button8);
                checkWinner();
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayer(button9);
                checkWinner();
            }
        });

        buttons = new JButton[][]{
                {button1, button2, button3},
                {button4, button5, button6},
                {button7, button8, button9}
        };

    }

    public void checkWinner() {
        // Check Rows
        for (int i = 0; i < 3; i++) {
            if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2])) {
                highlightWinnerButtons(buttons[i][0], buttons[i][1], buttons[i][2]);
                announceWinner(buttons[i][0].getText());
                return;
            }
        }

        // Check Columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) {
                highlightWinnerButtons(buttons[0][i], buttons[1][i], buttons[2][i]);
                announceWinner(buttons[0][i].getText());
                return;
            }
        }

        // Check Diagonals
        if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2])) {
            highlightWinnerButtons(buttons[0][0], buttons[1][1], buttons[2][2]);
            announceWinner(buttons[0][0].getText());
            return;
        }

        if (checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) {
            highlightWinnerButtons(buttons[0][2], buttons[1][1], buttons[2][0]);
            announceWinner(buttons[0][2].getText());
            return;
        }

        // Check for a tie
        if (isBoardFull()) {
            noOneWins();
        }
    }

    private boolean checkLine(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("-") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private void highlightWinnerButtons(JButton b1, JButton b2, JButton b3) {
        b1.setBackground(Color.RED);
        b2.setBackground(Color.RED);
        b3.setBackground(Color.RED);
    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(null, "Player " + winner + " wins!");
        setDefaultButtons();
    }

    private void noOneWins() {
        JOptionPane.showMessageDialog(null, "No one wins");
        setDefaultButtons();
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("-")) {
                    // Es gibt mindestens ein leeres Feld, das bedeutet, das Brett ist nicht voll
                    return false;
                }
            }
        }
        // Alle Felder sind belegt, das bedeutet, das Brett ist voll
        return true;
    }


    private void setDefaultButtons() {
        button1.setText("-");
        button2.setText("-");
        button3.setText("-");
        button4.setText("-");
        button5.setText("-");
        button6.setText("-");
        button7.setText("-");
        button8.setText("-");
        button9.setText("-");
        setDefaultColor();
    }

    public void setDefaultColor() {
        button1.setBackground(Color.GREEN);
        button2.setBackground(Color.GREEN);
        button3.setBackground(Color.GREEN);
        button4.setBackground(Color.GREEN);
        button5.setBackground(Color.GREEN);
        button6.setBackground(Color.GREEN);
        button7.setBackground(Color.GREEN);
        button8.setBackground(Color.GREEN);
        button9.setBackground(Color.GREEN);
    }

    private void setPlayer(JButton button) {
        if (!button.getText().equals("-")) {
            return;
        }
        if (player1IsPlay) {
            button.setText("X");
            button.setBackground(Color.MAGENTA);
        } else {
            button.setText("O");
            button.setBackground(Color.ORANGE);
        }
        player1IsPlay = !player1IsPlay;
    }

    public JPanel getPanel() {
        return this.mainPanel;
    }
}
