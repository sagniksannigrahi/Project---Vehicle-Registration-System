import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/management";
        String username = "root";
        String password = "1234";

        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            if (connection != null) {
                System.out.println("Connected to the database");

                // Test a simple SQL query
                String sqlQuery = "SELECT * FROM vehicle_registration";
                PreparedStatement statement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = statement.executeQuery();

                // Print the results
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("ID: " + id + ", Name: " + name);
                }

                resultSet.close();
                statement.close();
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
