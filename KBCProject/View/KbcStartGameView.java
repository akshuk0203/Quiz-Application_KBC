import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KbcStartGameView extends JFrame {
    JButton startGameButton, historyButton;
    JLabel background;
    Font font;
    int userId;
    String name;

    public KbcStartGameView(int userId, String name) {
        this.userId = userId;
        this.name = name;
        font = new Font("Arial", Font.BOLD, 22);

        startGameButton = new JButton("Start New Game");
        historyButton = new JButton("History");

        Border border = BorderFactory.createLineBorder(Color.WHITE);
        startGameButton.setBorder(border);
        historyButton.setBorder(border);

        setSize(2000, 1100);
        setVisible(true);
        setLocation(10, 10);

        ImageIcon img = new ImageIcon("Resources/KBC.jpg");
        background = new JLabel(img);
        setContentPane(background);
        setLayout(null);
        setSize(1999, 999);
        setSize(2000, 1100);

        background.add(startGameButton);
        background.add(historyButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background.setBounds(0, 0, 2000, 1100);
        
        startGameButton.setBounds(800, 850, 250, 50);
        historyButton.setBounds(1600, 50, 250, 50);
        
        startGameButton.setFont(font);
        historyButton.setFont(font);
        
        startGameButton.setBackground(new Color(40, 25, 60));
        startGameButton.setForeground(Color.lightGray);
        
        historyButton.setBackground(new Color(40, 25, 60));
        historyButton.setForeground(Color.lightGray);
        
        resetQuestionFlags();
    }

    private void resetQuestionFlags() {
        Connection cn = null;
        PreparedStatement prstm = null;
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kbc", "root", "1234a");
            prstm = cn.prepareStatement("update questionbank set flag=0");
            prstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (prstm != null) prstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addStartGameButtonListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
    }

    public void addHistoryButtonListener(ActionListener listener) {
        historyButton.addActionListener(listener);
    }
}
