package speak_up;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PublicModel {
    static ResultSet user;
    static int id;
    static String name;
    static String email;
    static String phone;
    static String gender;
    static String address;
    static String area;
    static String city;
    static String pincode;
    
    static void login(ResultSet logged_user){
        try{
            user = logged_user;
            id = logged_user.getInt("id");
            name = logged_user.getString("name");
            email = logged_user.getString("email");
            phone = logged_user.getString("phone");
            gender = logged_user.getString("gender");
            address = logged_user.getString("address");
            area = logged_user.getString("area");
            city = logged_user.getString("city");
            pincode = logged_user.getString("pincode");
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
        gender = "";
        address = "";
        area = "";
        city = "";
        pincode = "";
    }
}
