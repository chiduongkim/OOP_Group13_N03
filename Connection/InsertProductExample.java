import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlExample {
  public static void main(String[] args) throws ClassNotFoundException {
    String host = "mysql-e4643ae-group13.i.aivencloud.com";
    String port = "13698";
    String databaseName = "defaultdb";
    String userName = "avnadmin";
    String password = "AVNS_fm4xvgmqE69OhX8l1GY";

    Class.forName("com.mysql.cj.jdbc.Driver");
    try (final Connection connection =
                DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", userName, password);
         final Statement statement = connection.createStatement();
         final ResultSet resultSet = statement.executeQuery("SELECT version() AS version")) {

      while (resultSet.next()) {
        System.out.println("Version: " + resultSet.getString("version"));
      }
    } catch (SQLException e) {
      System.out.println("Connection failure.");
      e.printStackTrace();
    }
  }
}
