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
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class RegistrationGovtServant extends JFrame{
    
    JTextField txt_name,txt_phn_num,txt_email,txt_uqu_id;
    JPasswordField pas_pass,pas_repass;
    JLabel lbl_name_err, lbl_email_err, lbl_phn_err, lbl_uqid_err, lbl_city_err, lbl_pass_err, lbl_repass_err;
    JComboBox cb_sector, cb_city;
    JLabel l_title,  lbl_name,lbl_phn_num,lbl_sector,lbl_email,lbl_uqu_id,lbl_pass,lbl_repass,lbl_city;
    JButton btn_reg,btn_back;
    JTextArea lbl_knownUser = new JTextArea();
    boolean flag;
    
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 = new Color (93, 253, 146);
    
    public RegistrationGovtServant() 
    {
        setTitle("Speak Up");
        setBounds(300, 85, 600, 500);
        
        getContentPane().setBackground(c3);
        
        Font headerfont = new Font("Itc New Baskerville",Font.BOLD,13);
        
        l_title = new JLabel("GOVERNMENT OFFICIAL REGISTRATION");  
        l_title.setForeground(c2);
        l_title.setFont(new Font("Serif", Font.BOLD, 20));
        l_title.setBounds(80, 30, 500, 30);
        add(l_title);        

        lbl_name = new JLabel("Name  : ");
        lbl_name.setForeground(c2);
        lbl_name.setFont(headerfont);
        lbl_name.setBounds(100,83,150,20);
        add(lbl_name);
        
        txt_name = new JTextField();
        txt_name.setBorder(new LineBorder(c2));
        txt_name.setBounds(280,80,200,30);
        txt_name.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String name = txt_name.getText();
                if(name.length() < 1){
                    lbl_name_err.setText("This Field cannot be empty.");
                    add(lbl_name_err);
                } else {
                    remove(lbl_name_err);
                }
                validate();
                repaint();
            }
        });
        add(txt_name);
        
        lbl_name_err = new JLabel();
        lbl_name_err.setBounds(280, 111, 150, 15);
        lbl_name_err.setFont(new Font("serif", Font.PLAIN, 12));
        lbl_name_err.setForeground(Color.red);
        
        lbl_phn_num = new JLabel("Phone Number  : ");
        lbl_phn_num.setFont(headerfont);
        lbl_phn_num.setBounds(100,133,150,20);
        add(lbl_phn_num);
        
        txt_phn_num = new JTextField(10);
        txt_phn_num.setBorder(new LineBorder(c2));
        txt_phn_num.setBounds(280,130,200,30);
        txt_phn_num.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String phone = txt_phn_num.getText();
                lbl_phn_err.setText("Enter a valid phone number.");
                if(!Pattern.matches("^[6-9]{1}[0-9]{0,9}$", phone)){
                    add(lbl_phn_err);
                    validate();
                    repaint();
                }
                else{
                    remove(lbl_phn_err);
                    validate();
                    repaint();
                }
            }
        });
        
        add(txt_phn_num);

        lbl_phn_err = new JLabel();
        lbl_phn_err.setBounds(280, 161,150, 15);
        lbl_phn_err.setFont(new Font("serif", Font.PLAIN, 12));
        lbl_phn_err.setForeground(Color.red);
        
        lbl_email = new JLabel("Email-ID  : ");
        lbl_email.setForeground(c2);
        lbl_email.setFont(headerfont);
        lbl_email.setBounds(100,183,100,20);
        add(lbl_email);
        
        txt_email = new JTextField();
        txt_email.setBorder(new LineBorder(c2));
        txt_email.setBounds(280,180,200,30);
        txt_email.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String email = txt_email.getText();
                if(email.length() < 1){
                    lbl_email_err.setText("This Field cannot be empty.");
                    add(lbl_email_err);
                } else {
                    remove(lbl_email_err);
                }
                validate();
                repaint();
            }
        });
        add(txt_email);
        
        lbl_email_err = new JLabel();
        lbl_email_err.setBounds(280, 211, 150, 15);
        lbl_email_err.setFont(new Font("serif", Font.PLAIN, 12));
        lbl_email_err.setForeground(Color.red);
        
        
        lbl_sector = new JLabel("Sector  : ");
        lbl_sector.setForeground(c2);
        lbl_sector.setFont(headerfont);
        lbl_sector.setBounds(100,233,100,20);
        add(lbl_sector);
        
        String sector[]={"Corporation" ,"Electricity", "Water"};
        cb_sector = new JComboBox(sector);
        cb_sector.setBorder(new LineBorder(c2));
        cb_sector.setBackground(c3);
        cb_sector.setBounds(280,230,200,30);
        add(cb_sector);
        
        lbl_city = new JLabel("City  : ");
        lbl_city.setForeground(c2);
        lbl_city.setFont(headerfont);
        lbl_city.setBounds(100,283,100,20);
        add(lbl_city);
        
        String cities[] = {"City 1", "City 2"};
        cb_city = new JComboBox(cities);
        cb_city.setBorder(new LineBorder(c2));
        cb_city.setBackground(c3);
        cb_city.setBounds(280,280,200,30);
        add(cb_city);
        
        lbl_uqu_id = new JLabel("Aadhar Number  : ");
        lbl_sector.setForeground(c2);
        lbl_uqu_id.setFont(headerfont);
        lbl_uqu_id.setBounds(100,333,200,20);
        add(lbl_uqu_id);
        
        txt_uqu_id = new JTextField();
        txt_uqu_id.setBorder(new LineBorder(c2));
        txt_uqu_id.setBounds(280,330,200,30);
        txt_uqu_id.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String uqid = txt_uqu_id.getText();
                if(uqid.length() < 1){
                    lbl_uqid_err.setText("This Field cannot be empty.");
                    add(lbl_uqid_err);
                } else if(uqid.length() > 12) {
                    lbl_uqid_err.setText("Enter a valid Aadhar.");
                    add(lbl_uqid_err);
                } else {
                    remove(lbl_uqid_err);
                }
                validate();
                repaint();
            }
        });
        add(txt_uqu_id);
        
        lbl_uqid_err = new JLabel();
        lbl_uqid_err.setBounds(280, 361, 150, 15);
        lbl_uqid_err.setFont(new Font("serif", Font.PLAIN, 12));
        lbl_uqid_err.setForeground(Color.red);
        
        lbl_pass = new JLabel("Password  : ");
        lbl_pass.setForeground(c2);
        lbl_pass.setFont(headerfont);
        lbl_pass.setBounds(100,383,170,20);
        add(lbl_pass);
        
        pas_pass = new JPasswordField();
        pas_pass.setBorder(new LineBorder(c2));
        pas_pass.setBounds(280,380,200,30);
        pas_pass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String pass = new String(pas_pass.getPassword());
                if(pass.length() < 1){
                    lbl_pass_err.setText("This Field cannot be empty.");
                    add(lbl_pass_err);
                } else {
                    remove(lbl_pass_err);
                }
                validate();
                repaint();
            }
        });
        add(pas_pass);
        
        lbl_pass_err = new JLabel();
        lbl_pass_err.setBounds(280,431, 200, 15);
        lbl_pass_err.setFont(new Font("serif", Font.PLAIN, 11));
        lbl_pass_err.setForeground(Color.red);
        
        JCheckBox check_password = new JCheckBox("Show Password");
        check_password.setBackground(c3);
        check_password.setForeground(c2);
        check_password.setBounds(280, 415, 150, 15);
        check_password.setFont(new Font("serif", Font.PLAIN, 13));
        check_password.addActionListener((ActionEvent e) -> {
            if(check_password.isSelected())
                pas_pass.setEchoChar('\u0000');
            else
                pas_pass.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
        });
        add(check_password);
        
        lbl_repass = new JLabel("Confirm Password  : ");
        lbl_repass.setForeground(c2);
        lbl_repass.setFont(headerfont);
        lbl_repass.setBounds(100,453,170,20);
        add(lbl_repass);
        
        pas_repass = new JPasswordField();
        pas_repass.setBorder(new LineBorder(c2));
        pas_repass.setBounds(280,450,200,30);
        pas_repass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String repass = new String(pas_repass.getPassword());
                if(repass.length() < 1){
                    lbl_repass_err.setText("This Field cannot be empty.");
                    add(lbl_repass_err);
                } else {
                    remove(lbl_repass_err);
                }
                validate();
                repaint();
            }
        });
        add(pas_repass);
        
        lbl_repass_err = new JLabel();
        lbl_repass_err.setBounds(280, 501, 150, 15);
        lbl_repass_err.setFont(new Font("serif", Font.PLAIN, 11));
        lbl_repass_err.setForeground(Color.red);
        
        JCheckBox check_re_password = new JCheckBox("Show Password");
        check_re_password.setBackground(c3);
        check_re_password.setForeground(c2);
        check_re_password.setBounds(280, 485, 150, 15);
        check_re_password.setFont(new Font("serif", Font.PLAIN, 13));
        check_re_password.addActionListener((ActionEvent e) -> {
            if(check_re_password.isSelected())
                pas_repass.setEchoChar('\u0000');
            else
                pas_repass.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
        });
        add(check_re_password);
        
        btn_reg = new JButton("Register");
        btn_reg.setBackground(c4);
        btn_reg.setForeground(c2);
        btn_reg.setBorder(new LineBorder(c2));
        btn_reg.setBounds(320,520,100,30);
        btn_reg.addActionListener((ActionEvent e) -> {
            handleSubmit();
        });
        
        add(btn_reg);
        
        btn_back = new JButton("Back");
        btn_back.setBackground(c1);
        btn_back.setForeground(c3);
        btn_back.setBounds(150,520,100,30);
        btn_back.addActionListener(e -> {
            dispose();
        });
        
        add(btn_back);
        
        setSize(600, 650);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    void handleSubmit() {
        String name = txt_name.getText();
        String phone = txt_phn_num.getText();
        String email = txt_email.getText();
        char[] password = pas_pass.getPassword();
        char[] confirmPassword = pas_repass.getPassword();
        String unique_id = txt_uqu_id.getText();
        String city = cb_city.getSelectedItem().toString();
        
        boolean flag = CheckValidGovt.checkValid(this, name, email, city, phone, lbl_name_err, lbl_email_err, lbl_city_err, lbl_phn_err);
        
        if(password.length < 1){
            lbl_pass_err.setText("This Field cannot be empty.");
            add(lbl_pass_err);
            flag = false;
        } else if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$", new String(password))){
            pas_pass.setText("");
            pas_repass.setText("");
            lbl_pass_err.setText("Password must follow the provided rules");
            add(lbl_pass_err);
            flag = false;
        } else if (!Arrays.equals(password,confirmPassword)) {
            lbl_pass_err.setText("The passwords must match.");
            add(lbl_pass_err);
            lbl_repass_err.setText("The passwords must match.");
            add(lbl_repass_err);
            pas_pass.setText("");
            pas_repass.setText("");
            flag = false;
        }

        if(confirmPassword.length < 1){
            lbl_repass_err.setText("This Field cannot be empty.");
            add(lbl_repass_err);
            flag = false;
        }
        if(unique_id.length() < 1){
            lbl_uqid_err.setText("This Field cannot be empty.");
            add(lbl_uqid_err);
            flag = false;
        } else if(unique_id.length() != 12) {
            lbl_uqid_err.setText("Enter a valid Aadhar.");
            add(lbl_uqid_err);
            flag = false;
        }
        
        if(flag){
            addUser(name, phone, email, password, unique_id, city);
        }
        validate();
        repaint();
    }
    void addUser(String name, String phone, String email, char[] password, String unique_id, String city ){
        String sector = cb_sector.getSelectedItem().toString();
        try{
            Connection con = DbConnection.getConnection();
            PreparedStatement stmt;
            stmt = con.prepareStatement("select * from govt_servant where phone=(?) or email=(?) or unique_id=(?)");
            stmt.setString(1, phone);
            stmt.setString(2, email);
            stmt.setString(3, unique_id);
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
                
                if(user.getString("phone").equals(phone) && user.getString("email").equals(email) && user.getString("unique_id").equals(unique_id)) {
                    lbl_knownUser.setText("User with the given phone, email and unique id already exists.");
                } else if (user.getString("phone").equals(phone)){
                    lbl_knownUser.setText("User with the given phone already exists.");
                } else if (user.getString("email").equals(email)) {
                    lbl_knownUser.setText("User with the given email already exists.");
                } else if (user.getString("unique_id").equals(unique_id)) {
                    lbl_knownUser.setText("User with the given unique id already exists.");
                }
                add(lbl_knownUser);
                validate();
                repaint();
            } 
            else {
                Statement addStmt = con.createStatement();
                String hashedPassword = HashPassword.toHexString(new String(password));
                String query = "insert into govt_servant(name, phone, email, sector, password, city, unique_id) values('"+name+"', '"+phone+"', '"+email+"', '"+sector+"', '"+hashedPassword+"', '"+city+"', '"+unique_id+"')";
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


