package speak_up;

import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheckValidPublic {
    
    static boolean checkValid(JFrame f, String name, String email, String address, String area, String city, String pincode, String phone,
            JLabel l_nameerr, JLabel l_emailerror, JLabel l_adderr, JLabel l_areaerr, JLabel l_cityerr, JLabel l_pinerror, JLabel l_phperror  ){
        
        boolean flag = true;
        if(name.length() < 1){
            l_nameerr.setText("This Field cannot be empty.");
            f.add(l_nameerr);
            flag = false;
        }
        if(email.length() < 1){
            l_emailerror.setText("This Field cannot be empty.");
            f.add(l_emailerror);
            flag = false;
        } else if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)){
            l_emailerror.setText("Enter a valid email ID.");
            f.add(l_emailerror);
            flag = false;
        }
        
        
        if(address.length() < 1){
            l_adderr.setText("This Field cannot be empty.");
            f.add(l_adderr);
            flag = false;
        }
        
        if(area.length() < 1){
            l_areaerr.setText("This Field cannot be empty.");
            f.add(l_areaerr);
            flag = false;
        }
        
        if(city.length() < 1){
            l_cityerr.setText("This Field cannot be empty.");
            f.add(l_cityerr);
            flag = false;
        }
        
        if(pincode.length() < 1){
            l_pinerror.setText("This Field cannot be empty.");
            f.add(l_pinerror);
            flag = false;
        } else if (!Pattern.matches("\\d{0}\\d{6}", pincode)) {
            l_pinerror.setText("Enter a valid pincode");
            f.add(l_pinerror);
            flag = false;
        }
        
        if(phone.length() < 1){
            l_phperror.setText("This Field cannot be empty.");
            f.add(l_phperror);
            flag = false;
        } else if (!Pattern.matches("^[6-9]{1}[0-9]{9}$", phone)) {
            l_phperror.setText("Enter a valid phone number");
            f.add(l_phperror);
            flag = false;
        }
        return flag;
    }
}
