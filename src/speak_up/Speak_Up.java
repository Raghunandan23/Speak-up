package speak_up;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Speak_Up {

    public static void main(String[] args) {
        Connection con;
        try {
            con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from logged_in_user";
            ResultSet logged_user = stmt.executeQuery(query);
            if(logged_user.next()){
                int user_id = logged_user.getInt("user_id");
                String user_type = logged_user.getString("user_type");
                if(user_type.equals("public")) {
                    String query2 = "select * from public where id='"+user_id+"'";
                    ResultSet user = stmt.executeQuery(query2);
                    user.next();
                    PublicModel.login(user);
                    UserType.setUserType("Public");
                    PublicHomePage p1 = new PublicHomePage();
                }
                else {
                    String query2 = "select * from govt_servant where id='"+user_id+"'";
                    ResultSet user = stmt.executeQuery(query2);
                    user.next();
                    GovtModel.login(user);
                    UserType.setUserType("Govt. Servant");
                    GovtHomePage g1 = new GovtHomePage();
                }
            }
            else {
                WelcomePage w1 = new WelcomePage();
            }
            con.close();
        }
        catch(SQLException ex) {
            System.out.println("Some error occurred while setting the user model " + ex);
        } 
    }
}

