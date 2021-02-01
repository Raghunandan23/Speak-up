package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class ChangePassword extends JFrame{
    
    JLabel lbl_currentPass, lbl_newPass, lbl_confPass;
    JPasswordField txt_currentPass, txt_newPass, txt_confPass;
    JLabel err_current, err_new, err_conf;
    String currentPass, newPass, confPass;
    
    boolean flag;
    
    UIManager UI=new UIManager();
        
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 =new Color(255, 0, 0);
    Color c5 = new Color(19, 173, 173);
    
    ChangePassword()
    {
        setSize(400, 300);
        setBounds(400, 200, 400, 300);
        
        getContentPane().setBackground(c3);
        
        Font f1 = new Font("serif", Font.PLAIN, 18);
        Font err_font = new Font("serif", Font.PLAIN, 12);
        
        
        lbl_currentPass = new JLabel("Current Password :");
        lbl_currentPass.setForeground(c2);
        lbl_currentPass.setBounds(20, 20, 150, 30);
        lbl_currentPass.setFont(f1);
        add(lbl_currentPass);
        
        txt_currentPass = new JPasswordField();
        txt_currentPass.setBorder(new LineBorder(c2));
        txt_currentPass.setBounds(180, 20, 180, 30);
        txt_currentPass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String phone = new String(txt_currentPass.getPassword());
                if(phone.length() < 1){
                    err_current.setText("This Field cannot be empty.");
                    add(err_current);
                } else {
                    remove(err_current);
                }
                validate();
                repaint();
            }
        });
        add(txt_currentPass);
        
        err_current = new JLabel();
        err_current.setBounds(180, 5, 150, 15);
        err_current.setFont(err_font);
        err_current.setForeground(Color.red);
        
        JCheckBox check_current = new JCheckBox("Show Password");
        check_current.setBackground(c3);
        check_current.setForeground(c2);
        check_current.setBounds(180, 51, 150, 15);
        check_current.setFont(new Font("serif", Font.PLAIN, 13));
        check_current.addActionListener(e -> {
            showPassword(check_current, txt_currentPass);
        });
        add(check_current);
        
        
        lbl_newPass = new JLabel("New Password :");
        lbl_newPass.setForeground(c2);
        lbl_newPass.setBounds(20, 80, 150, 30);
        lbl_newPass.setFont(f1);
        add(lbl_newPass);
        
        txt_newPass = new JPasswordField();
        txt_newPass.setBorder(new LineBorder(c2));
        txt_newPass.setBounds(180, 80, 180, 30);
        txt_newPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(flag){
                    showMessage();
                    flag = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                flag = true;
            }
        });
        txt_newPass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String password = new String(txt_newPass.getPassword());
                if(password.length() < 1){
                    err_new.setText("This Field cannot be empty");
                    add(err_new);
                } else {
                    remove(err_new);
                }
                validate();
                repaint();
            }
        });
        add(txt_newPass);
        
        err_new = new JLabel();
        err_new.setBounds(180, 65, 150, 15);
        err_new.setFont(err_font);
        err_new.setForeground(Color.red);
        
        JCheckBox check_new = new JCheckBox("Show Password");
        check_new.setBackground(c3);
        check_new.setForeground(c2);
        check_new.setBounds(180, 111, 150, 15);
        check_new.setFont(new Font("serif", Font.PLAIN, 13));
        check_new.addActionListener(e -> {
            showPassword(check_new, txt_newPass);
        });
        add(check_new);
        
        lbl_confPass = new JLabel("Confirm Password :");
        lbl_confPass.setForeground(c2);
        lbl_confPass.setBounds(20,140, 150, 30);
        lbl_confPass.setFont(f1);
        add(lbl_confPass);
        
        txt_confPass = new JPasswordField();
        txt_confPass.setBorder(new LineBorder(c2));
        txt_confPass.setBounds(180, 140, 180, 30);
        txt_confPass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String phone = new String(txt_confPass.getPassword());
                if(phone.length() < 1){
                    err_conf.setText("This Field cannot be empty.");
                    add(err_conf);
                } else {
                    remove(err_conf);
                }
                validate();
                repaint();
            }
        });
        add(txt_confPass);
        
        err_conf = new JLabel();
        err_conf.setBounds(180, 125, 150, 15);
        err_conf.setFont(err_font);
        err_conf.setForeground(Color.red);
        
        JCheckBox check_conf = new JCheckBox("Show Password");
        check_conf.setBackground(c3);
        check_conf.setForeground(c2);
        check_conf.setBounds(180, 171, 150, 15);
        check_conf.setFont(new Font("serif", Font.PLAIN, 13));
        check_conf.addActionListener(e -> {
            showPassword(check_conf, txt_confPass);
        });
        add(check_conf);
        
        JButton btn_change = new JButton("Change");
        btn_change.setBorder(new LineBorder(c2));
        btn_change.setBackground(c1);
        btn_change.setForeground(c3);
        btn_change.setBounds(200, 200, 100, 30);
        btn_change.addActionListener(e -> {
            handleChange();
        });
        add(btn_change);
        
        JButton btn_cancel = new JButton("Cancel");
        btn_cancel.setBorder(new LineBorder(c2));
        btn_cancel.setBackground(c1);
        btn_cancel.setForeground(c3);
        btn_cancel.setBounds(80, 200, 100, 30);
        btn_cancel.addActionListener(e -> {
            dispose();
        });
        add(btn_cancel);
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
        
    }
    
    void showMessage(){
        UI.put("OptionPane.background", c3);
        UI.put("Panel.background", c3);
        JOptionPane.showMessageDialog(this, "Your Password must contain a lower case and uppercase alphabet , a number and must be minimum 8 digits long.");
    }
    
    void showPassword(JCheckBox check, JPasswordField pass) {
        if(check.isSelected()) 
            pass.setEchoChar('\u0000');
        else
            pass.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
        
    }
    void handleChange(){
        currentPass = new String(txt_currentPass.getPassword());
        newPass = new String(txt_newPass.getPassword());
        confPass = new String(txt_confPass.getPassword());
        boolean flag = true;
        
        if(currentPass.length() < 1){
            err_current.setText("This Field cannot be empty.");
            add(err_current);
            flag = false;
        } 
        else if(!checkCurrentPassword()){
            err_current.setText("Invalid current password.");
            add(err_current);
            flag = false;
        }
 
        if(newPass.length() < 1){
            err_new.setText("This Field cannot be empty.");
            add(err_new);
            flag = false;
        } else if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$", newPass)){
            txt_newPass.setText("");
            txt_confPass.setText("");
            err_new.setText("Password must follow the rules");
            add(err_new);
            flag = false;
        } else if(!newPass.equals(confPass)) {
            err_new.setText("The passwords must match.");
            add(err_new);
            err_conf.setText("The passwords must match.");
            add(err_conf);
            txt_newPass.setText("");
            txt_confPass.setText("");
            flag = false;
        }

        if(confPass.length() < 1){
            err_conf.setText("This Field cannot be empty.");
            add(err_conf);
            flag = false;
        }

        validate();
        repaint();
        
        if(flag) {
            changePassword();
        }
    }
    
    boolean checkCurrentPassword(){
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query;
            if(UserType.userType.equals("Public")){
                query = "select password from public where id="+PublicModel.id;
            }
            else {
                query = "select password from govt_servant where id=" + GovtModel.id;
            }
        
            ResultSet user = stmt.executeQuery(query);
            user.next();
            String pass = user.getString("password");
            String hashedPassword = HashPassword.toHexString(currentPass);
            return pass.equals(hashedPassword);
            
        } catch(SQLException ex) {
            System.out.println("Exception occurred while checking current password " + ex);
        }
        return false;
    }
    
    void changePassword() {
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String hashedPassword = HashPassword.toHexString(newPass);
            String query;
            if(UserType.userType.equals("Public")){
                query = "update public set password='"+hashedPassword+"' where id="+PublicModel.id;
            } 
            else{
                query = "update govt_servant set password='"+hashedPassword+"' where id="+GovtModel.id;
            }
            int result = stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Password succcessfully updated.");
            UI.put("OptionPane.background", c3);
            UI.put("Panel.background", c3);
            dispose();
            con.close();
        } catch(SQLException ex) {
            System.out.println("Excepton occurred while changing password  "+ex);
            
        }
    }
}
