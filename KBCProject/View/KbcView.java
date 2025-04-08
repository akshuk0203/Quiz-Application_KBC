import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KbcView extends JFrame {
    JLabel l1, l2;
    JTextField t1;
    JButton b1;
    Font f, f1;

    public KbcView() {
        setVisible(true);
        setLocation(600, 300);
        setLayout(null);
        setSize(500, 500);
        f = new Font("Arial", Font.BOLD, 18);
        f1 = new Font("Monotonic", Font.ITALIC, 15);
        l1 = new JLabel("Name");
        l2 = new JLabel("All The Best!");
        t1 = new JTextField();
        b1 = new JButton("Submit");
        l1.setFont(f);
        b1.setFont(f);
        t1.setFont(f1);
        l2.setFont(f);
        add(t1);
        add(l1);
        add(b1);
        add(l2);
        l1.setBounds(100, 100, 100, 50);
        t1.setBounds(210, 100, 220, 40);
        l2.setBounds(170, 220, 200, 50);
        b1.setBounds(180, 280, 150, 40);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getUserName() {
        return t1.getText();
    }

    public void addSubmitButtonListener(ActionListener listener) {
        b1.addActionListener(listener);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
