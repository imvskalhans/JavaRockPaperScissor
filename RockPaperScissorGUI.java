import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// frontend
public class RockPaperScissorGUI extends JFrame implements ActionListener {
    private JButton rockButton, paperButton, scissorButton, quitButton;
    private JLabel computerChoiceLabel, computerScoreLabel, playerScoreLabel;
    private RockPaperScissor rockPaperScissor;

    public RockPaperScissorGUI() {
        super("Rock Paper Scissor");
        setSize(450, 574);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        rockPaperScissor = new RockPaperScissor();

        // Set a background color or image (you can customize this)
        getContentPane().setBackground(new Color(240, 240, 240));

        addGuiComponents();
    }

    private void addGuiComponents() {
        // create computer score label
        computerScoreLabel = new JLabel("Computer: 0");
        computerScoreLabel.setBounds(0, 43, 450, 30);
        computerScoreLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        computerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(computerScoreLabel);

        // create computer choice
        computerChoiceLabel = new JLabel("?");
        computerChoiceLabel.setBounds(164, 118, 120, 81);
        computerChoiceLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        computerChoiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        computerChoiceLabel.setBackground(Color.decode("#B5C0D0")); 
        computerChoiceLabel.setOpaque(true);
        computerChoiceLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(computerChoiceLabel);

        // create player score label
        playerScoreLabel = new JLabel("Player: 0");
        playerScoreLabel.setBounds(0, 317, 450, 30);
        playerScoreLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        playerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(playerScoreLabel);

        // Create a panel for better organization
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0)); // GridLayout with spacing

        // Player buttons
        rockButton = createButton("Rock", Color.decode("#5F5D9C"));
        paperButton = createButton("Paper", Color.decode("#6196A6"));
        scissorButton = createButton("Scissors", Color.decode("#A4CE95"));

        // Add buttons to the panel
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorButton);

        // Set panel bounds and add to the frame
        buttonPanel.setBounds(40, 387, 355, 81);
        add(buttonPanel);

        // Quit button
        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        quitButton.addActionListener(e -> System.exit(0)); 
        quitButton.setBackground(Color.decode("#F4EDCC")); 
        quitButton.setFocusPainted(false);
        quitButton.setBounds(180, 490, 80, 30);
        add(quitButton);
    }

    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Dialog", Font.PLAIN, 18));
        button.addActionListener(this);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        return button;
    }

    private void showDialog(String message) {
        JDialog resultDialog = new JDialog(this, "Result", true);
        resultDialog.setSize(227, 124);
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        JLabel resultLabel = new JLabel(message);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultDialog.add(resultLabel, BorderLayout.CENTER);

        JButton tryAgainButton = new JButton("Play Again?");
        tryAgainButton.addActionListener(e -> {
            computerChoiceLabel.setText("?");
            resultDialog.dispose();
        });
        resultDialog.add(tryAgainButton, BorderLayout.SOUTH);

        resultDialog.setLocationRelativeTo(this);
        resultDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerChoice = e.getActionCommand().toString();
        String result = rockPaperScissor.playRockPaperScissor(playerChoice);
        computerChoiceLabel.setText(rockPaperScissor.getComputerChoice());
        computerScoreLabel.setText("Computer: " + rockPaperScissor.getComputerScore());
        playerScoreLabel.setText("Player: " + rockPaperScissor.getPlayerScore());
        showDialog(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            RockPaperScissorGUI rockPaperScissorGUI = new RockPaperScissorGUI();
            rockPaperScissorGUI.setVisible(true);
        });
    }
}
