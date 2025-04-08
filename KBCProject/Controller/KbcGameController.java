import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KbcGameController {
    private KbcGameModel model;
    private KbcGameView view;
    private int userId;
    private String name;
    private String correctAnswer;
    private int questionIndex = 0;
    private int doubleDip = 0;
    private Timer timer;

    public KbcGameController(KbcGameModel model, KbcGameView view, int userId, String name) {
        this.model = model;
        this.view = view;
        this.userId = userId;
        this.name = name;

        this.view.addEndButtonListener(new EndButtonListener());
        this.view.addNextButtonListener(new NextButtonListener());
        this.view.addOptionButtonListener(new OptionButtonListener());
        this.view.addLifelineButtonListener(new LifelineButtonListener());

        model.updateGameFlag();
		loadQuestion();
    }


    class EndButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int ans = JOptionPane.showConfirmDialog(null, "Do you really want to exit?");
            if (ans == 0) {
                String amount = view.amountLabels[questionIndex - 1].getText().substring(3);
                JOptionPane.showMessageDialog(null, "You have won Rs " + amount);
                model.saveWinnings(userId, amount);
                System.exit(0);
            }
            if (timer != null) {
                timer.stop();
            }
        }
    }

    class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            loadQuestion();
            view.resetOptions();
        }
    }

    class OptionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton selectedOption = (JButton) e.getSource();
            String selectedAnswer = selectedOption.getText();
			System.out.println(selectedAnswer);
			System.out.println(correctAnswer);
            if (correctAnswer.equals(selectedAnswer)) {
                view.markCorrectOption(correctAnswer);
                view.setAmountLabel(questionIndex);
                questionIndex++;
                if(doubleDip==1)                  // correct answer at first attempt but selected under double dip lifeline
                    view.lifelineDoubleDip.setEnabled(false);
                if (questionIndex > 16) {
                    timer.stop();
                    String amount = view.amountLabels[questionIndex - 1].getText().substring(3);
                    JOptionPane.showMessageDialog(null, "Congratulations!\nYou have won Rs " + amount);
                    model.saveWinnings(userId, amount);
                    System.exit(0);
                }
            } else {
                if (doubleDip == 0) {
                    selectedOption.setBackground(new Color(150, 30, 20));
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Better luck next time", "Error", JOptionPane.ERROR_MESSAGE);
                    if (questionIndex > 0) {
                        String amount = view.amountLabels[questionIndex - 1].getText().substring(3);
                        JOptionPane.showMessageDialog(null, "You have won Rs " + amount);
                        model.saveWinnings(userId, amount);
                    }
                    System.exit(0);
                } else {
                    selectedOption.setBackground(new Color(150, 30, 20));
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "One more chance to select correct answer");
                    startTimer();
                    selectedOption.setBackground(new Color(140, 50, 255));
                    view.lifelineDoubleDip.setEnabled(false);
                    doubleDip = 0;
                }
            }
            if (timer != null) {
                timer.stop();
            }
			
        }
    }

    class LifelineButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton lifelineButton = (JButton) e.getSource();
            if (lifelineButton == view.lifeline50_50) {
                try {
                    String option1 = correctAnswer;
                    String option2 = "";
                    if (option1.equals(view.optionA.getText())) {
                        option2 = view.optionD.getText();
                        view.optionB.setText("");
                        view.optionC.setText("");
                    } else if (option1.equals(view.optionB.getText())) {
                        option2 = view.optionC.getText();
                        view.optionA.setText("");
                        view.optionD.setText("");
                    } else if (option1.equals(view.optionC.getText())) {
                        option2 = view.optionB.getText();
                        view.optionA.setText("");
                        view.optionD.setText("");
                    } else if (option1.equals(view.optionD.getText())) {
                        option2 = view.optionA.getText();
                        view.optionB.setText("");
                        view.optionC.setText("");
                    }
                    view.lifeline50_50.setEnabled(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (lifelineButton == view.lifelineExpert) {
                try {
                    view.markCorrectOption(correctAnswer);
                    view.setAmountLabel(questionIndex);
                    questionIndex++;
                    view.lifelineExpert.setEnabled(false);
					 if (timer != null) {
                			timer.stop();
            		}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (lifelineButton == view.lifelineDoubleDip) {
                doubleDip = 1;
            }
        }
    }

    private void loadQuestion() {
        ResultSet rs = model.getRandomQuestion(questionIndex+1);
        try {
            if (rs.next()) {
                String question = rs.getString(2);
                String option1 = rs.getString(4);
                String option2 = rs.getString(5);
                String option3 = rs.getString(6);
                String option4 = rs.getString(7);
                correctAnswer = rs.getString(3);
                view.setQuestion(question, option1, option2, option3, option4);
                model.updateQuestionFlag(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.resetAmountLabels();
        view.setAmountLabel(questionIndex);
        startTimer();
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            int timeRemaining = 60;

            public void actionPerformed(ActionEvent e) {
                view.setTimer(" " + timeRemaining);
                timeRemaining--;
                if (timeRemaining < 0) {
                    timer.stop();
                    view.optionA.setEnabled(false);
                    view.optionB.setEnabled(false);
                    view.optionC.setEnabled(false);
                    view.optionD.setEnabled(false);
                    view.nextButton.setEnabled(false);
                    String amount = view.amountLabels[questionIndex - 1].getText().substring(3);
                    JOptionPane.showMessageDialog(null, "Time UP\n You have won Rs " + amount);
                    model.saveWinnings(userId, amount);
                    System.exit(0);
                }
            }
        });
        timer.start();
    }
}
