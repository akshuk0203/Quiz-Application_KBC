import java.sql.*;
import java.util.ArrayList;

public class KbcModel {
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

    public int insertUser(String name) {
        String selectQuery = "SELECT id FROM user WHERE name = ?";
        String insertQuery = "INSERT INTO user(name) VALUES(?)";
    
        try (Connection conn = connect();
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
    
            // Check if user already exists
            selectStmt.setString(1, name);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
    
            // If not exists, insert new user
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, name);
                insertStmt.executeUpdate();
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
    
        } catch (SQLException e) {
            System.out.println("Error in insertUser: " + e.getMessage());
        }
        return 0;
    }    

    public void updateUserFlag() {
        String query = "update questionbank set flag=0";
        try (Connection conn = connect();
             PreparedStatement prstm = conn.prepareStatement(query)) {
            prstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object[][] getUserWinningHistory(int userId) {
         ArrayList<Object[]> historyData = new ArrayList<>();
        try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kbc", "root", "1234a")) {
            String query = "SELECT * FROM winnings WHERE user_id = ?";
            PreparedStatement stmt = cn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] row = { rs.getDate("win_date"), rs.getString("amount_won") };
                historyData.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return historyData.toArray(new Object[0][0]);
    }
    
    public void insertWinnings(int userId, String amount) {
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
