package speak_up;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    
    static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/speak_up_1", "postgres", "23@2000rAGHU");
        } catch(SQLException ex) {
            System.out.println("Error establishing conection to DB "+ ex);
        }
        return null;
    }
}
