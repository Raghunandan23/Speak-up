package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class LoginPage extends JFrame
{
    String userType;
    JLabel lbl_passErr;
    LoginPage(String type) 
    {
        this.userType = type;
        setSize(570, 500);
        setTitle("Speak Up");
        setBounds(300, 85, 570, 500);
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        Color c4=new Color(93, 253, 146);
        
        getContentPane().setBackground(c3);
        
        Font headerfont = new Font("Itc New Baskerville",Font.BOLD,13);
        
        JLabel lbl_header = new JLabel(type + " Login");
        lbl_header.setForeground(c2);
        if(type.equals("Public")){
            lbl_header.setBounds(190, 80, 300, 40);
        }
        else{
            lbl_header.setBounds(120, 80, 300, 40);
        }
        Font headerFont = new Font("serif", Font.BOLD, 25);
        lbl_header.setFont(headerFont);
        add(lbl_header);
        
        Font lblFont = new Font("Serif", Font.PLAIN, 18);
        
        JLabel lbl_phone = new JLabel("Phone Number  :");
        lbl_phone.setForeground(c2);
        lbl_phone.setBounds(120, 150, 200, 30);
        lbl_phone.setFont(headerfont);
        add(lbl_phone);
        
        JLabel lbl_phoneErr = new JLabel();
        lbl_phoneErr.setFont(new Font("serif", Font.PLAIN, 12));
        lbl_phoneErr.setBounds(250, 185, 150, 15);
        lbl_phoneErr.setForeground(Color.red);

        
        JTextField txt_phone = new JTextField();
        txt_phone.setBorder(new LineBorder(c2));
        txt_phone.setBounds(250, 150, 150, 30);
        txt_phone.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String phone = txt_phone.getText();
                lbl_phoneErr.setText("Enter a valid phone number.");
                if(!Pattern.matches("^[6-9]{1}[0-9]{0,9}$", phone)){
                    add(lbl_phoneErr);
                    validate();
                    repaint();
                }
                else{
                    remove(lbl_phoneErr);
                    validate();
                    repaint();
                }
            }
        });
        add(txt_phone);
        
        JLabel lbl_password = new JLabel("Password  :");
        lbl_password.setForeground(c2);
        lbl_password.setBounds(120, 210, 120, 30);
        lbl_password.setFont(headerfont);
        add(lbl_password);
        
        lbl_passErr = new JLabel();
        lbl_passErr.setFont(new Font("serif", Font.PLAIN, 12));
        lbl_passErr.setBounds(250, 260, 150, 15);
        lbl_passErr.setForeground(Color.red);

        
        JPasswordField txt_password = new JPasswordField();
        txt_password.setBorder(new LineBorder(c2));
        txt_password.setBounds(250, 210, 150, 30);
        
        txt_password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                char[] password = txt_password.getPassword();
                if(password.length > 0){
                    remove(lbl_passErr);
                    validate();
                    repaint();
                }
            }
        });
        add(txt_password);
        
        JCheckBox check_password = new JCheckBox("Show Password");
        check_password.setBounds(250, 245, 150, 15);
        check_password.setBackground(c3);
        check_password.setForeground(c2);
        check_password.setFont(headerfont);
        check_password.addActionListener((ActionEvent e) -> {
            if(check_password.isSelected())
                txt_password.setEchoChar('\u0000');
            else
                txt_password.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
        });
        add(check_password);
        
        JButton btn_submit = new JButton("Submit");
        btn_submit.setBackground(c4);
        btn_submit.setForeground(c2);
        btn_submit.setBorder(new LineBorder(c2));
        btn_submit.setBounds(280, 290, 100, 30);
        btn_submit.addActionListener((ActionEvent e) -> {
            boolean flag = true;
            String phone = txt_phone.getText();
            char password[] = txt_password.getPassword();
            if(phone.length() < 1){
                lbl_phoneErr.setText("This field cannot be empty");
                add(lbl_phoneErr);
                validate();
                repaint();
                flag = false;
            } else if (!Pattern.matches("^[6-9]{1}[0-9]{9}$", phone)){
                lbl_phoneErr.setText("Enter a valid phone number");
                add(lbl_phoneErr);
                validate();
                repaint();
                flag = false;
            }
            if(password.length < 1){
                lbl_passErr.setText("This field cannot be empty");
                add(lbl_passErr);
                validate();
                repaint();
                flag = false;
            }
            if(flag) {
                verifyUser(phone, password);
            }
        });
        add(btn_submit);
        
        JButton btn_goBack = new JButton("Back");
        btn_goBack.setBorder(new LineBorder(c2));
        btn_goBack.setBackground(c1);
        btn_goBack.setForeground(c3);
        btn_goBack.setBounds(160, 290, 100, 30);
        
        btn_goBack.addActionListener((ActionEvent e) -> {
            dispose();
            WelcomePage w1 = new WelcomePage();
        });
        
        add(btn_goBack);
        if(type.equals("Public")){
            JLabel lbl_newUser = new JLabel("Don't have an Account?");
            lbl_newUser.setBounds(100, 393, 160, 20);
            lbl_newUser.setForeground(c2);
            lbl_newUser.setFont(headerfont);
            add(lbl_newUser);

            JButton btn_newUser = new JButton("Sign Up As " + type);
            btn_newUser.setBorder(new LineBorder(c2));
            btn_newUser.setBackground(c1);
            btn_newUser.setForeground(c3);
            btn_newUser.setBounds(260, 390, 180, 30);
            btn_newUser.addActionListener(e -> {
                if(type.equals("Public")){
                    dispose();
                    RegistrationPublic r1 = new RegistrationPublic();
                }
                else {
                    dispose();
                    RegistrationGovtServant r2 = new RegistrationGovtServant();
                }
            });

            add(btn_newUser);
        }
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    void verifyUser(String phone, char[] password){
        try{
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query, query2;
            if(userType.equals("Public")){
                query = "select * from public where phone='"+phone+"' ";
            } else {
                query = "select * from govt_servant where phone='"+phone+"'";
            }
            ResultSet user = stmt.executeQuery(query);
            
            String hashedPassword = HashPassword.toHexString(new String(password));
            if(user.next()){
                String actualPassword = user.getString("password");
                if(actualPassword.equals(hashedPassword)){
                    
                    if(userType.equals("Public")){
                        PublicModel.login(user);
                        UserType.setUserType("Public");
                        query2 = "insert into logged_in_user(user_id, user_type) values('"+user.getInt("id")+"', 'public') ";
                        int result = stmt.executeUpdate(query2);
                        PublicHomePage p1 = new PublicHomePage();
                        dispose();
                    }
                    else {
                        GovtModel.login(user);
                        UserType.setUserType("Govt. Servant");
                        query2 = "insert into logged_in_user(user_id, user_type) values('"+user.getInt("id")+"', 'govt_servant') ";
                        int result = stmt.executeUpdate(query2);
                        dispose();
                        GovtHomePage g1 = new GovtHomePage();
                    }
                }
                else{
                    lbl_passErr.setText("Invalid Password");
                    add(lbl_passErr);
                    validate();
                    repaint();
                }
            }
            else{
                JTextArea lbl_noUser = new JTextArea("Account with given Phone Number does not exist");
                lbl_noUser.setWrapStyleWord(true);
                lbl_noUser.setLineWrap(true);
                lbl_noUser.setOpaque(false);
                lbl_noUser.setEditable(false);

                lbl_noUser.setBounds(210, 320, 180, 70);
                lbl_noUser.setFont(new Font("serif", Font.PLAIN, 15));
                lbl_noUser.setForeground(Color.red);
                add(lbl_noUser);
                validate();
                repaint();
            }
            con.close();
        } catch(SQLException ex) {
            System.out.println("exception occurred :" + ex);
        }
    }
}
