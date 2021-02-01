package speak_up;

import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheckValidGovt {
    
    static boolean checkValid(JFrame f, String name, String email, String city, String phone,
            JLabel l_nameerr, JLabel l_emailerr, JLabel l_cityerr, JLabel l_phoneerr  ){
        
        boolean flag = true;
        
        if(name.length() < 1){
            l_nameerr.setText("This Field cannot be empty.");
            f.add(l_nameerr);
            flag = false;
        }
        if(email.length() < 1){
            l_emailerr.setText("This Field cannot be empty.");
            f.add(l_emailerr);
            flag = false;
        } else if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)){
            l_emailerr.setText("Enter a valid email ID.");
            f.add(l_emailerr);
            flag = false;
        }
        if(phone.length() < 1){
            l_phoneerr.setText("This Field cannot be empty.");
            f.add(l_phoneerr);
            flag = false;
        } else if (!Pattern.matches("^[6-9]{1}[0-9]{9}$", phone)) {
            l_phoneerr.setText("Enter a valid phone number");
            f.add(l_phoneerr);
            flag = false;
        }
        if(city.length() < 1){
            l_cityerr.setText("This Field cannot be empty.");
            f.add(l_cityerr);
            flag = false;
        }
        return flag;
    }
}
