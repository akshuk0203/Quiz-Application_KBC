import java.sql.*;
import java.util.Random;

public class KbcGameModel {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/kbc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234a";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    /*public ResultSet getRandomQuestion(int questionIndex) {
        Connection cn = connect();
        Statement stm;
        ResultSet rs = null;
        int difficulty= (questionIndex >= 1 && questionIndex <= 7) ? 1 :
                        (questionIndex >= 8 && questionIndex <= 14) ? 2 :
                        (questionIndex >= 15 && questionIndex <= 17) ? 3 : 0;
        try {
            Random r = new Random();
            int rn;
            int flag;
            do {
                rn = r.nextInt(20);
                String query = "select SrNo, flag from questionbank where SrNo=" + rn;
                stm = cn.createStatement();
                ResultSet rst = stm.executeQuery(query);
                rst.next();
                flag = rst.getInt(2);
            } while (flag != 0);
            String query = "select * from questionbank where SrNo=" + rn;
            stm = cn.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }*/

    public ResultSet getRandomQuestion(int questionIndex) {
        Connection cn = connect();
        Statement stm;
        ResultSet rs = null;
    
        // Determine difficulty based on questionIndex
        int difficulty = (questionIndex >= 1 && questionIndex <= 7) ? 1 :
                         (questionIndex >= 8 && questionIndex <= 14) ? 2 :
                         (questionIndex >= 15 && questionIndex <= 17) ? 3 : 0;
    
        try {
            // Query to fetch a random question with matching difficulty and unused (flag=0)
            String query = "SELECT * FROM questionbank WHERE flag = 0 AND difficulty = " + difficulty + " ORDER BY RAND() LIMIT 1";
            stm = cn.createStatement();
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return rs;
    }    

    public void updateQuestionFlag(int SrNo) {
        String query = "update questionbank set flag=1 where SrNo=" + SrNo;
        try (Connection conn = connect();
             PreparedStatement prstm = conn.prepareStatement(query)) {
            prstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGameFlag() {
        String query = "update questionbank set flag=0";
        try (Connection conn = connect();
             PreparedStatement prstm = conn.prepareStatement(query)) {
            prstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveWinnings(int userId, String amount) {
        String query = "INSERT INTO winnings (user_id, amount_won) VALUES (?, ?)";
        try (Connection conn = connect();
             PreparedStatement prstm = conn.prepareStatement(query)) {
            prstm.setInt(1, userId);
            prstm.setString(2, amount);
            prstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
