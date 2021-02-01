package speak_up;

import java.util.*;
import javax.swing.*;  
import java.awt.*;  
import java.awt.Font;
import java.awt.event.*; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class RegistrationPublic extends JFrame    
{  
    JLabel l_title,l_name, l_email,l_gender, l_password, l_confpassword, l_address, l_area, l_city, l_pincode,l_phone; 
    JLabel l_nameerr,l_emailerror,l_passerr,l_confpasserr,l_adderr,l_areaerr,l_cityerr,l_pinerror,l_phperror;
    JTextField tf_name,tf_email,tf_address,tf_pincode,tf_phone;  
    JButton btn_register, btn_back;  
    JPasswordField p_password, p_confpassword;
    JComboBox cb_gender, cb_city, cb_area;
    JTextArea lbl_knownUser = new JTextArea();
    String name, phone, password, confpassword, email, gender, address, area, city, pincode;
    boolean flag = true;
    
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 = new Color (93, 253, 146);
    
    RegistrationPublic()
    {
        setTitle("Speak Up");
        setSize(600, 700);
        setBounds(300, 85, 600, 700);
        setTitle("Speakp Up"); 
        
        Font headerfont = new Font("Itc New Baskerville",Font.BOLD,13);
        
        getContentPane().setBackground(c3);
        
        l_title = new JLabel("PUBLIC REGISTRATION");  
        l_title.setForeground(c2);
        l_title.setFont(new Font("Serif", Font.BOLD, 20));
        l_title.setBounds(200, 30, 500, 30);
        add(l_title);  
        
        String gender_combobox[]={"Male","Female","Others"};
        
        l_name = new JLabel("Name  :");
        l_name.setForeground(c2);
        l_name.setFont(headerfont);
        l_name.setBounds(80, 70, 200, 30); 
        add(l_name);
        
        tf_name = new JTextField();
        tf_name.setBorder(new LineBorder(c2));
        tf_name.setBounds(300, 70, 200, 30);
        tf_name.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name = tf_name.getText();
                if(name.length() < 1){
                    l_nameerr.setText("This Field cannot be empty");
                    add(l_nameerr);
                } else {
                    remove(l_nameerr);
                }
                validate();
                repaint();
            }
        });
        add(tf_name);
        
        l_nameerr = new JLabel();
        l_nameerr.setBounds(300, 100, 150, 15);
        l_nameerr.setFont(new Font("serif", Font.PLAIN, 11));
        l_nameerr.setForeground(Color.red);
        
        l_email = new JLabel("Email-ID  :");
        l_email.setForeground(c2);
        l_email.setFont(headerfont);
        l_email.setBounds(80, 120, 200, 30); 
        add(l_email);
        
        tf_email = new JTextField();
        tf_email.setBorder(new LineBorder(c2));
        tf_email.setBounds(300, 120, 200, 30);
        tf_email.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    String email = tf_email.getText();
                    if(email.length() < 1){
                        l_emailerror.setText("This Field cannot be empty");
                        add(l_emailerror);
                    } 
                    else{
                        remove(l_emailerror);
                    }
                    validate();
                    repaint();
                }
            });
        add(tf_email);
        
        l_emailerror = new JLabel();
        l_emailerror.setBounds(300, 150, 150, 15);
        l_emailerror.setFont(new Font("serif", Font.PLAIN, 11));
        l_emailerror.setForeground(Color.red);
        
        l_gender = new JLabel("Gender  :");
        l_gender.setFont(headerfont);
        l_gender.setBounds(80,170,200,30);
        add(l_gender);
        
        cb_gender=new JComboBox(gender_combobox);
        cb_gender.setBorder(new LineBorder(c2));
        cb_gender.setBackground(c3);
        cb_gender.setBounds(300, 170, 200, 30);
        add(cb_gender);
        
        l_password = new JLabel("Password  :"); 
        l_password.setForeground(c2);
        l_password.setFont(headerfont);
        l_password.setBounds(80, 220, 200, 30);
        add(l_password);
        
        p_password = new JPasswordField();
        p_password.setBorder(new LineBorder(c2));
        p_password.setBounds(300,220,200,30);
        p_password.addFocusListener(new FocusListener() {
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
        p_password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String password = new String(p_password.getPassword());
                if(password.length() < 1){
                    l_passerr.setText("This Field cannot be empty");
                    add(l_passerr);
                } else {
                    remove(l_passerr);
                }
                validate();
                repaint();
            }
        });
        add(p_password);
        
        l_passerr = new JLabel();
        l_passerr.setBounds(420, 250, 150, 15);
        l_passerr.setFont(new Font("serif", Font.PLAIN, 11));
        l_passerr.setForeground(Color.red);
        
        JCheckBox c_password = new JCheckBox("Show Password");
        c_password.setBackground(c3);
        c_password.setForeground(c2);
        c_password.setBounds(300,250, 120, 15);
        c_password.setFont(new Font("serif", Font.PLAIN, 13));
        c_password.addActionListener((ActionEvent e) -> {
            if(c_password.isSelected())
                p_password.setEchoChar('\u0000');
            else
                p_password.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
        });
        add(c_password);
        
        l_confpassword = new JLabel("Confirm Password  :");
        l_confpassword.setForeground(c2);
        l_confpassword.setFont(headerfont);
        l_confpassword.setBounds(80, 270, 200, 30); 
        add(l_confpassword);
        
        p_confpassword = new JPasswordField();
        p_confpassword.setBorder(new LineBorder(c2));
        p_confpassword.setBounds(300, 270, 200, 30);
        p_confpassword .addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String confpassword = new String(p_confpassword .getPassword());
                if(confpassword .length() < 1){
                    l_confpasserr .setText("This Field cannot be empty");
                    add(l_confpasserr);
                } else {
                    remove(l_confpasserr);
                }
                validate();
                repaint();
            }
        });
        add(p_confpassword);
        
        l_confpasserr = new JLabel();
        l_confpasserr.setBounds(420, 300, 150, 15);
        l_confpasserr.setFont(new Font("serif", Font.PLAIN, 11));
        l_confpasserr.setForeground(Color.red);
        
        JCheckBox c_confpassword = new JCheckBox("Show Password");
        c_confpassword.setBackground(c3);
        c_confpassword.setForeground(c2);
        c_confpassword.setBounds(300, 300, 120, 15);
        c_confpassword.setFont(new Font("serif", Font.PLAIN, 13));
        c_confpassword.addActionListener((ActionEvent e) -> {
            if(c_confpassword.isSelected())
                p_confpassword.setEchoChar('\u0000');
            else
                p_confpassword.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
        });
        add(c_confpassword);
        
        l_address = new JLabel("Address  :");
        l_address.setForeground(c2);
        l_address.setFont(headerfont);
        l_address.setBounds(80, 320, 200, 30);
        add(l_address);
        
        tf_address = new JTextField(); 
        tf_address.setBorder(new LineBorder(c2));
        tf_address.setBounds(300, 320, 200, 30);  
        tf_address.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String address = tf_address.getText();
                if(address.length() < 1){
                    l_adderr.setText("This Field cannot be empty");
                    add(l_adderr);
                } else {
                    remove(l_adderr);
                }
                validate();
                repaint();
            }
        });
        add(tf_address);
        
        l_adderr = new JLabel();
        l_adderr.setBounds(300, 350, 150, 15);
        l_adderr.setFont(new Font("serif", Font.PLAIN, 11));
        l_adderr.setForeground(Color.red);
        
        l_area = new JLabel("Area  :");
        l_area.setForeground(c2);
        l_area.setFont(headerfont);
        l_area.setBounds(80, 370, 200, 30);  
        add(l_area); 
        
        String areas[] = {"Area 1", "Area 2"}; 
        cb_area = new JComboBox(areas); 
        cb_area.setBorder(new LineBorder(c2));
        cb_area.setBackground(c3);
        cb_area.setBounds(300, 370, 200, 30);  
        add(cb_area);
        
        l_city = new JLabel("City  :");
        l_city.setForeground(c2);
        l_city.setFont(headerfont);
        l_city.setBounds(80, 420, 200, 30); 
        add(l_city);
        
        String cities[] = {"City 1", "City 2"};
        
        cb_city = new JComboBox(cities); 
        cb_city.setBorder(new LineBorder(c2));
        cb_city.setBackground(c3);
        cb_city.setBounds(300, 420, 200, 30);
        add(cb_city);
        
        l_pincode = new JLabel("Pincode  :");
        l_pincode.setForeground(c2);
        l_pincode.setFont(headerfont);
        l_pincode.setBounds(80,470,200,40);
        add(l_pincode);
        
        tf_pincode=new JTextField();
        tf_pincode.setBorder(new LineBorder(c2));
        l_pinerror=new JLabel();
        tf_pincode.setBounds(300, 470, 200, 30);
        tf_pincode.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    String pincode = tf_pincode.getText();
                    
                    if(pincode.length() < 1){
                        l_pinerror.setText("This Field cannot be empty");
                        add(l_pinerror);
                        validate();
                        repaint();
                    } 
                    else if (pincode.length() > 6) {
                        l_pinerror.setText("Enter a valid pincode.");
                        add(l_pinerror);
                        validate();
                        repaint();
                        
                    }
                    else{
                        remove(l_pinerror);
                        validate();
                        repaint();
                    }
                }
            });
        add(tf_pincode);
        
        l_pinerror = new JLabel();
        l_pinerror.setBounds(300, 500, 150, 15);
        l_pinerror.setFont(new Font("serif", Font.PLAIN, 11));
        l_pinerror.setForeground(Color.red);

        l_phone =new JLabel("Phone Number  :");
        l_phone.setForeground(c2);
        l_phone.setFont(headerfont);
        l_phone.setBounds(80,520,200,40);
        add(l_phone);
        
        tf_phone=new JTextField();
        tf_phone.setBorder(new LineBorder(c2));
        tf_phone.setBounds(300, 520, 200, 30);
        tf_phone.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    String phone = tf_phone.getText();
                    
                    if(phone.length() <1){
                        l_phperror.setText("This Field cannot be empty");
                        add(l_phperror);
                    } 
                    else if (!Pattern.matches("^[6-9]{1}[0-9]{0,9}$", phone)) {
                        l_phperror.setText("Enter a valid phone number");
                        add(l_phperror);
                    }
                    else{
                        remove(l_phperror);
                    }
                    validate();
                    repaint();
                }
        });
        add(tf_phone);
        
        l_phperror = new JLabel();
        l_phperror.setBounds(300, 550, 150, 15);
        l_phperror.setFont(new Font("serif", Font.PLAIN, 11));
        l_phperror.setForeground(Color.red);
        
        btn_register = new JButton("Register"); 
        btn_register.setBackground(c4);
        btn_register.setForeground(c2);
        btn_register.setBorder(new LineBorder(c2));
        btn_register.setFont(headerfont);
        btn_register.setBounds(320, 570, 100, 30); 
        btn_register.addActionListener(e -> {
           handleSubmit();
        });
        add(btn_register); 
        
        btn_back = new JButton("Back"); 
        btn_back.setBorder(new LineBorder(c2));
        btn_back.setBackground(c1);
        btn_back.setForeground(c3);
        btn_back.setFont(headerfont);
        btn_back.setBounds(150, 570, 100, 30);  
        btn_back.addActionListener( e -> {
            dispose();
            WelcomePage w3 = new WelcomePage();
            
        });
        add(btn_back);
        
        setResizable(false);
        setLayout(null);
        setVisible(true); 
    }  
    
    void showMessage(){
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", c3);
        UI.put("Panel.background", c3);
        JOptionPane.showMessageDialog(this, "Your Password must contain a lower case and uppercase alphabet , a number and must be minimum 8 digits long.");
    }
    
    
    void handleSubmit() 
    {
        name = tf_name.getText();  
        email = tf_email.getText(); 
        gender=cb_gender.getSelectedItem().toString();
        char[] c_password = p_password.getPassword();  
        char[] c_confpassword = p_confpassword.getPassword();   
        password = new String(c_password);  
        confpassword = new String(c_confpassword);  
        address = tf_address.getText();  
        area = cb_area.getSelectedItem().toString();  
        city = cb_city.getSelectedItem().toString(); 
        pincode = tf_pincode.getText(); 
        phone = tf_phone.getText(); 
        
        boolean flag = CheckValidPublic.checkValid(this, name, email, address, area, city, pincode, phone, l_nameerr, l_emailerror, l_adderr, l_areaerr, l_cityerr, l_pinerror, l_phperror);
        
        if(password.length() < 1){
            l_passerr.setText("This Field cannot be empty.");
            add(l_passerr);
            flag = false;
        } else if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$", password)){
            p_password.setText("");
            p_confpassword.setText("");
            l_passerr.setText("Password must follow the provided rules");
            add(l_passerr);
            flag = false;
        } else if(!Arrays.equals(c_password, c_confpassword)) {
            l_passerr.setText("The passwords must match.");
            add(l_passerr);
            l_confpasserr.setText("The passwords must match.");
            add(l_confpasserr);
            p_password.setText("");
            p_confpassword.setText("");
            flag = false;
        }
        if(confpassword.length() < 1){
            l_confpasserr.setText("This Field cannot be empty.");
            add(l_confpasserr);
            flag = false;
        }
        if(flag){
            addUser();
        }
        validate();
        repaint();
    }
    
    void addUser()
    {
        try  
        {  
            Connection con = DbConnection.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement("select * from public where phone=(?) or email=(?)");
            stmt.setString(1, phone);
            stmt.setString(2, email);
            ResultSet user = stmt.executeQuery();
            if(user.next()){
                remove(lbl_knownUser);
                lbl_knownUser.setWrapStyleWord(true);
                lbl_knownUser.setLineWrap(true);
                lbl_knownUser.setOpaque(false);
                lbl_knownUser.setEditable(false);

                lbl_knownUser.setBounds(170, 600, 250, 70);
                lbl_knownUser.setFont(new Font("serif", Font.PLAIN, 15));
                lbl_knownUser.setForeground(Color.red);
                
                if(user.getString("phone").equals(phone) && user.getString("email").equals(email)) {
                    lbl_knownUser.setText("User with the given phone and email already exists.");
                } else if (user.getString("phone").equals(phone)){
                    lbl_knownUser.setText("User with the given phone already exists.");
                } else if (user.getString("email").equals(email)) {
                    lbl_knownUser.setText("User with the given email already exists.");
                }
                add(lbl_knownUser);
                validate();
                repaint();
            } 
            else {
                Statement addStmt = con.createStatement();
                String hashedPassword = HashPassword.toHexString(password);
                String query = "insert into public(name, phone, email, gender, password, address, pincode, area, city) values('"+name+"', '"+phone+"', '"+email+"', '"+gender+"', '"+hashedPassword+"', '"+address+"','"+pincode+"', '"+area+"', '"+city+"')";
                int result = addStmt.executeUpdate(query);
                System.out.println("Result :" + result);
                UIManager UI=new UIManager();
                UI.put("OptionPane.background", c3);
                UI.put("Panel.background", c3);
                JOptionPane.showMessageDialog(this, "Account sucessfully created!");
                dispose();
                LoginPage l2 = new LoginPage("Public");
            }
            con.close();
            
        } catch(HeadlessException | SQLException ex) {
            System.out.println("exception occurred :" + ex);
        }  
    }
    
}  
