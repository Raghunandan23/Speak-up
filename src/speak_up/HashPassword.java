package speak_up;

import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  



public class HashPassword {  
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    }
    
    public static String toHexString(String password){
        try{
            byte[] hash = getSHA(password);

            BigInteger number = new BigInteger(1, hash);  
            StringBuilder hexString = new StringBuilder(number.toString(16));  
            while (hexString.length() < 32){  
                hexString.insert(0, '0');  
            }  
            return hexString.toString();
        } catch(NoSuchAlgorithmException ex) {
            System.out.println("Exception occurred while hashing password");
        }
        return null;
    } 
}  