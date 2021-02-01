package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;


public class RegisterAdmin extends JFrame{
    JLabel lbl_phone, lbl_password, lbl_passErr, lbl_phoneErr;
    JTextField txt_phone;
    JPasswordField txt_password;
    JTextArea lbl_knownUser = new JTextArea();
   
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 = new Color(93, 253, 146);

    RegisterAdmin() 
    {
        setSize(570, 500);
        setTitle("Speak Up");
        setBounds(300, 85, 570, 500);
        
        getContentPane().setBackground(c3);
        
        JLabel lbl_header = new JLabel("REGISTER ADMIN");
        lbl_header.setForeground(c2);
        lbl_header.setBounds(155, 80, 300, 40);
        Font headerFont = new Font("serif", Font.BOLD, 25);
        lbl_header.setFont(headerFont);
        add(lbl_header);
        
        Font lblFont = new Font("Itc New Baskerville",Font.BOLD,13);
        
        lbl_phone = new JLabel("Phone Number  :");
        lbl_header.setForeground(c2);
        lbl_phone.setBounds(100, 150, 200, 30);
        lbl_phone.setFont(lblFont);
        add(lbl_phone);
        
        lbl_phoneErr = new JLabel();
        lbl_phoneErr.setFont(new Font("serif", Font.PLAIN, 12));
        lbl_phoneErr.setBounds(250, 185, 150, 15);
        lbl_phoneErr.setForeground(Color.red);

        txt_phone = new JTextField();
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
        
        lbl_password = new JLabel("Password  :");
        lbl_header.setForeground(c2);
        lbl_password.setBounds(100, 210, 120, 30);
        lbl_password.setFont(lblFont);
        add(lbl_password);
        
        lbl_passErr = new JLabel();
        lbl_passErr.setFont(new Font("serif", Font.PLAIN, 12));
        lbl_passErr.setBounds(250, 260, 150, 15);
        lbl_passErr.setForeground(Color.red);

        txt_password = new JPasswordField();
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
        check_password.setBackground(c3);
        check_password.setForeground(c2);
        check_password.setBounds(250, 245, 150, 15);
        check_password.setFont(lblFont);
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
        });
        
        add(btn_goBack);
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    void verifyUser(String phone, char[] password){
        try{    
            Connection con = DbConnection.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement("select * from admin where phone=(?)");
            stmt.setString(1, phone);
            ResultSet user = stmt.executeQuery();
            if(user.next()){
                remove(lbl_knownUser);
                lbl_knownUser.setWrapStyleWord(true);
                lbl_knownUser.setLineWrap(true);
                lbl_knownUser.setOpaque(false);
                lbl_knownUser.setEditable(false);

                lbl_knownUser.setBounds(150, 570, 250, 70);
                lbl_knownUser.setFont(new Font("serif", Font.PLAIN, 15));
                lbl_knownUser.setForeground(Color.red);
                
                lbl_knownUser.setText("User with the given phone, email and unique id already exists.");
                add(lbl_knownUser);
                validate();
                repaint();
            } 
            else {
                Statement addStmt = con.createStatement();
                String hashedPassword = HashPassword.toHexString(new String(password));
                String query = "insert into admin(phone, password) values('"+phone+"','"+hashedPassword+"')";
                int result = addStmt.executeUpdate(query);
                System.out.println("Result :" + result);
                UIManager UI=new UIManager();
                UI.put("OptionPane.background", c3);
                UI.put("Panel.background", c3);
                JOptionPane.showMessageDialog(this, "Account sucessfully created!");
                dispose();
            }
            con.close();
            
        } catch(HeadlessException | SQLException ex) {
            System.out.println("exception occurred :" + ex);
        }    
    }
}
