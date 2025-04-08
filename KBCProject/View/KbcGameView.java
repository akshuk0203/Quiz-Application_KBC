import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class KbcGameView extends JFrame {
    JButton endButton, nextButton;
    JLabel background, questionLabel, timerLabel, lifelineLabel;
    JButton optionA, optionB, optionC, optionD;
    JButton lifeline50_50, lifelineExpert, lifelineDoubleDip;
    JLabel[] amountLabels;
    Font font1, font2;

    public KbcGameView() {
        font1 = new Font("Arial", Font.BOLD, 30);
        font2 = new Font("Arial", Font.BOLD, 18);
        amountLabels = new JLabel[17];

        ImageIcon img = new ImageIcon("Resources/kbcFinal1.png");
        background = new JLabel(img);
        setContentPane(background);
        setLayout(null);
        setSize(2000, 1100);
        endButton = new JButton("End Game");
        nextButton = new JButton("Next");

        questionLabel = new JLabel();
        timerLabel = new JLabel();
        lifelineLabel = new JLabel("Lifelines");

        optionA = new JButton();
        optionB = new JButton();
        optionC = new JButton();
        optionD = new JButton();

        lifeline50_50 = new JButton("50-50");
        lifelineExpert = new JButton("Expert");
        lifelineDoubleDip = new JButton("Double Dip");

        Border border = BorderFactory.createLineBorder(Color.WHITE);
        endButton.setBorder(border);
        nextButton.setBorder(border);

        addComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComponents() {
        background.setBounds(0, 0, 2000, 1100);

        endButton.setFont(font2);
        endButton.setBounds(20, 30, 130, 40);
        endButton.setBackground(new Color(150, 30, 20));
        endButton.setForeground(Color.lightGray);
        background.add(endButton);

        nextButton.setFont(font1);
        nextButton.setBounds(1650, 900, 200, 60);
        background.add(nextButton);

        questionLabel.setFont(font1);
        questionLabel.setBounds(300, 400, 1200, 150);
        questionLabel.setOpaque(true);
        questionLabel.setBackground(new Color(125, 50, 255));
        questionLabel.setForeground(Color.lightGray);
        background.add(questionLabel);

        optionA.setFont(font1);
        optionA.setBounds(300, 600, 500, 80);
        optionA.setOpaque(true);
        optionA.setBackground(new Color(140, 50, 255));
        optionA.setForeground(Color.lightGray);
        background.add(optionA);

        optionB.setFont(font1);
        optionB.setBounds(1000, 600, 500, 80);
        optionB.setOpaque(true);
        optionB.setBackground(new Color(140, 50, 255));
        optionB.setForeground(Color.lightGray);
        background.add(optionB);

        optionC.setFont(font1);
        optionC.setBounds(300, 730, 500, 80);
        optionC.setOpaque(true);
        optionC.setBackground(new Color(140, 50, 255));
        optionC.setForeground(Color.lightGray);
        background.add(optionC);

        optionD.setFont(font1);
        optionD.setBounds(1000, 730, 500, 80);
        optionD.setOpaque(true);
        optionD.setBackground(new Color(140, 50, 255));
        optionD.setForeground(Color.lightGray);
        background.add(optionD);

        lifelineLabel.setFont(font1);
        lifelineLabel.setBounds(1400, 30, 300, 25);
        lifelineLabel.setForeground(Color.lightGray);
        background.add(lifelineLabel);

        lifeline50_50.setFont(font2);
        lifeline50_50.setBounds(1250, 70, 100, 25);
        lifeline50_50.setBackground(Color.PINK);
        background.add(lifeline50_50);

        lifelineExpert.setFont(font2);
        lifelineExpert.setBounds(1380, 70, 120, 25);
        lifelineExpert.setBackground(Color.PINK);
        background.add(lifelineExpert);

        lifelineDoubleDip.setFont(font2);
        lifelineDoubleDip.setBounds(1520, 70, 150, 25);
        lifelineDoubleDip.setBackground(Color.PINK);
        background.add(lifelineDoubleDip);

        timerLabel.setFont(font1);
        timerLabel.setBounds(850, 650, 100, 100);
        background.add(timerLabel);

        for (int i = 0; i <= 16; i++) {
            amountLabels[i] = new JLabel("" + (i + 1) + "    " + (50 * (int) Math.pow(2, i)));
            amountLabels[i].setFont(font2);
            amountLabels[i].setOpaque(false);
            amountLabels[i].setForeground(Color.lightGray);
            background.add(amountLabels[i]);
        }
        amountLabels[0].setBounds(1700, 510, 150, 20);
        amountLabels[1].setBounds(1700, 480, 150, 20);
        amountLabels[2].setBounds(1700, 450, 150, 20);
        amountLabels[3].setBounds(1700, 420, 150, 20);
        amountLabels[4].setBounds(1700, 390, 150, 20);
        amountLabels[5].setBounds(1700, 360, 150, 20);
        amountLabels[6].setBounds(1700, 330, 150, 20);
        amountLabels[7].setBounds(1700, 300, 150, 20);
        amountLabels[8].setBounds(1700, 270, 150, 20);
        amountLabels[9].setBounds(1700, 240, 150, 20);
        amountLabels[10].setBounds(1700, 210, 150, 20);
        amountLabels[11].setBounds(1700, 180, 150, 20);
        amountLabels[12].setBounds(1700, 150, 150, 20);
        amountLabels[13].setBounds(1700, 120, 150, 20);
        amountLabels[14].setBounds(1700, 90, 150, 20);
        amountLabels[15].setBounds(1700, 60, 150, 20);
        amountLabels[16].setBounds(1700, 30, 150, 20);
    }

    public void addEndButtonListener(ActionListener listener) {
        endButton.addActionListener(listener);
    }

    public void addNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    public void addOptionButtonListener(ActionListener listener) {
        optionA.addActionListener(listener);
        optionB.addActionListener(listener);
        optionC.addActionListener(listener);
        optionD.addActionListener(listener);
    }

    public void addLifelineButtonListener(ActionListener listener) {
        lifeline50_50.addActionListener(listener);
        lifelineExpert.addActionListener(listener);
        lifelineDoubleDip.addActionListener(listener);
    }

    public void setQuestion(String question, String option1, String option2, String option3, String option4) {
        questionLabel.setText(question);
        optionA.setText("A.  " + option1);
        optionB.setText("B.  " + option2);
        optionC.setText("C.  " + option3);
        optionD.setText("D.  " + option4);
		optionA.setEnabled(true);
        optionB.setEnabled(true);
        optionC.setEnabled(true);
        optionD.setEnabled(true);
		nextButton.setEnabled(false);
    }

    public void setTimer(String time) {
        timerLabel.setText(time);
    }

    public void markCorrectOption(String correctOption) {
        if (optionA.getText().contains(correctOption)) {
            optionA.setBackground(new Color(40, 150, 55));
        } else if (optionB.getText().contains(correctOption)) {
            optionB.setBackground(new Color(40, 150, 55));
        } else if (optionC.getText().contains(correctOption)) {
            optionC.setBackground(new Color(40, 150, 55));
        } else if (optionD.getText().contains(correctOption)) {
            optionD.setBackground(new Color(40, 150, 55));
        }
		optionA.setEnabled(false);
        optionB.setEnabled(false);
        optionC.setEnabled(false);
        optionD.setEnabled(false);
		nextButton.setEnabled(true);
    }

    public void markIncorrectOption(JButton selectedOption) {
        selectedOption.setBackground(new Color(150, 30, 20));
    }

    public void resetOptions() {
        optionA.setBackground(new Color(140, 50, 255));
        optionB.setBackground(new Color(140, 50, 255));
        optionC.setBackground(new Color(140, 50, 255));
        optionD.setBackground(new Color(140, 50, 255));
    }

    public void setAmountLabel(int index) {
        amountLabels[index].setOpaque(true);
        amountLabels[index].setBackground(new Color(170, 50, 10));
    }

    public void resetAmountLabels() {
        for (int i = 0; i <= 16; i++) {
            amountLabels[i].setOpaque(false);
            amountLabels[i].setBackground(null);
        }
    }

	 public void displayWinningHistory(Object[][] historyData) {
        String[] columnNames = { "Date","Amount"};
        JTable table = new JTable(historyData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("Winning History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
