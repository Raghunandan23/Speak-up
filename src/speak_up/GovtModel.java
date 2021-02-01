package speak_up;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GovtModel {
    static ResultSet user;
    static int id;
    static String name;
    static String email;
    static String phone;
    static String sector;
    static String unique_id;
    static String city;
    
    static void login(ResultSet logged_user){
        try{
            user = logged_user;
            id = logged_user.getInt("id");
            name = logged_user.getString("name");
            email = logged_user.getString("email");
            phone = logged_user.getString("phone");
            sector = logged_user.getString("sector");
            unique_id = logged_user.getString("unique_id");
            city = logged_user.getString("city");
        } catch(SQLException ex) {
            System.out.println("Exception occurred while setting user model "+ex);
        }
    }
    
    static void logout(){
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "delete from logged_in_user";
            int result = stmt.executeUpdate(query);
            con.close();
        } catch(SQLException ex){
            System.out.println("Exception occurred while logging out user");
        }
        name = "";
        email = "";
        phone = "";
        sector = "";
        unique_id = "";
        city = "";
    }}
