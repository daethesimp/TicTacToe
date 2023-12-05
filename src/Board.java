
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new Board();
    }

    private JButton[][] buttons;
    private JLabel statusLabel;
    private boolean isPlayerX;

    public Board() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buttons = new JButton[3][3];
        isPlayerX = true;

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font(null, Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Player X's turn");
        statusLabel.setFont(new Font(null, Font.PLAIN, 16));
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getText().equals("")) {
            if (isPlayerX) {
                clickedButton.setText("X");
                statusLabel.setText("Player O's turn");
            } else {
                clickedButton.setText("O");
                statusLabel.setText("Player X's turn");
            }

            isPlayerX = !isPlayerX;

            if (checkWin()) {
                statusLabel.setText(isPlayerX ? "Player O wins!" : "Player X wins!");
                disableButtons();
            } else if (isBoardFull()) {
                statusLabel.setText("It's a tie!");
            }
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][1].getText().equals(buttons[i][2].getText())
                    && !buttons[i][0].getText().equals("")) {
                return true; // Row win
            }

            if (buttons[0][i].getText().equals(buttons[1][i].getText())
                    && buttons[1][i].getText().equals(buttons[2][i].getText())
                    && !buttons[0][i].getText().equals("")) {
                return true; // Column win
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().equals("")) {
            return true; // Diagonal win (top-left to bottom-right)
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().equals("")) {
            return true; // Diagonal win (top-right to bottom-left)
        }

        return false;
    }

    private boolean isBoardFull() {
        // Check if the board is full (tie)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // Board is not full
                }
            }
        }
        return true; // Board is full
    }

    private void disableButtons() {
        // Disable all buttons after the game ends
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}
