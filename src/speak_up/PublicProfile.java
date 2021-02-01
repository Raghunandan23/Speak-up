package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class PublicProfile extends JFrame{
    
    JLabel lbl_header, lbl_name, lbl_phone, lbl_email, lbl_gender, lbl_address, lbl_area, lbl_city, lbl_pincode; 
    JTextField txt_name, txt_phone, txt_email, txt_gender,txt_address, txt_pincode;
    JButton btn_edit, btn_save, btn_cancel, btn_back;
    String name, area, email, city, address, pincode, phone;
    JLabel err_name, err_phone, err_email, err_city, err_address, err_area, err_pincode;
    JComboBox cb_area, cb_city;
    JTextArea err_phone2 = new JTextArea(); 
    JTextArea err_email2 = new JTextArea();
    
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 =new Color(255, 0, 0);
    Color c5 = new Color(19, 173, 173);
    
    UIManager UI=new UIManager();
    
    public PublicProfile()
    {
        setTitle("Speak Up");
        setBounds(300, 85, 600, 500);
        setSize(600, 600);
        
        getContentPane().setBackground(c3);
        
        lbl_header = new JLabel("Profile");
        lbl_header.setForeground(c2);
        lbl_header.setFont(new Font("serif", Font.BOLD, 35));
        lbl_header.setBounds(250, 30, 150, 50);
        add(lbl_header);
        
        btn_back = new JButton("Back");
        btn_back.setBorder(new LineBorder(c2));
        btn_back.setBackground(c1);
        btn_back.setForeground(c3);
        btn_back.setBounds(20, 10, 80, 30);
        btn_back.addActionListener(e -> {
            dispose();
            PublicHomePage p1 = new PublicHomePage();
        });
        
        add(btn_back);
        
        Font simpleFont= new Font("serif", Font.PLAIN, 20);
        
        lbl_name = new JLabel("Name  :");
        lbl_name.setForeground(c2);
        lbl_name.setBounds(20, 100, 100, 25);
        lbl_name.setFont(simpleFont);
        add(lbl_name);
        
        txt_name = new JTextField(PublicModel.name);
        txt_name.setBorder(new LineBorder(c2));
        txt_name.setBounds(110, 100, 150, 30);
        txt_name.setEditable(false);
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
                    err_name.setText("This Field cannot be empty.");
                    add(err_name);
                } else {
                    remove(err_name);
                }
                validate();
                repaint();
            }
        });
        
        add(txt_name);
        
        Font err_font = new Font("serif", Font.PLAIN, 12);
        
        err_name = new JLabel();
        err_name.setBounds(110, 140, 150, 15);
        err_name.setFont(err_font);
        err_name.setForeground(Color.red);
        
        lbl_phone = new JLabel("Phone Number :");
        lbl_phone.setForeground(c2);
        lbl_phone.setBounds(300, 100, 150, 25);
        lbl_phone.setFont(simpleFont);
        add(lbl_phone);
        
        txt_phone = new JTextField(PublicModel.phone);
        txt_phone.setBorder(new LineBorder(c2));
        txt_phone.setBounds(440, 100, 100, 30);
        txt_phone.setEditable(false);
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
                remove(err_phone2);
                if(phone.length() < 1){
                    err_phone.setText("This Field cannot be empty.");
                    add(err_phone);
                } else {
                    remove(err_phone);
                }
                validate();
                repaint();
            }
        });
        add(txt_phone);
        
        err_phone = new JLabel();
        err_phone.setBounds(440, 135, 150, 15);
        err_phone.setFont(err_font);
        err_phone.setForeground(Color.red);
        
        lbl_email = new JLabel("Email-ID :");
        lbl_email.setForeground(c2);
        lbl_email.setBounds(20, 170, 100, 25);
        lbl_email.setFont(simpleFont);
        add(lbl_email);
        
        txt_email = new JTextField(PublicModel.email);
        txt_email.setBorder(new LineBorder(c2));
        txt_email.setBounds(110, 170, 150, 30);
        txt_email.setEditable(false);
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
                remove(err_email2);
                if(email.length() < 1){
                    err_email.setText("This Field cannot be empty.");
                    add(err_email);
                } else {
                    remove(err_email);
                }
                validate();
                repaint();
            }
        });
        add(txt_email);
       
        err_email = new JLabel();
        err_email.setBounds(110, 205, 150, 15);
        err_email.setFont(err_font);
        err_email.setForeground(Color.red);
                
        lbl_gender = new JLabel("Gender :");
        lbl_gender.setForeground(c2);
        lbl_gender.setBounds(300, 170, 100, 25);
        lbl_gender.setFont(simpleFont);
        add(lbl_gender);
        
        txt_gender = new JTextField(PublicModel.gender);
        txt_gender.setBorder(new LineBorder(c2));
        txt_gender.setBounds(380, 170, 100, 30);
        txt_gender.setEditable(false);
        add(txt_gender);
        
        lbl_address = new JLabel("Address :");
        lbl_address.setForeground(c2);
        lbl_address.setBounds(20, 240, 100, 25);
        lbl_address.setFont(simpleFont);
        add(lbl_address);
        
        txt_address = new JTextField(PublicModel.address);
        txt_address.setBorder(new LineBorder(c2));
        txt_address.setBounds(110, 240, 300, 30);
        txt_address.setEditable(false);
        txt_address.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String address = txt_address.getText();
                if(address.length() < 1){
                    err_address.setText("This Field cannot be empty.");
                    add(err_address);
                } else {
                    remove(err_address);
                }
                validate();
                repaint();
            }
        });
        add(txt_address);
        
        err_address = new JLabel();
        err_address.setBounds(110, 275, 150, 15);
        err_address.setFont(err_font);
        err_address.setForeground(Color.red);
        
        lbl_area = new JLabel("Area :");
        lbl_area.setForeground(c2);
        lbl_area.setBounds(20, 310, 150, 25);
        lbl_area.setFont(simpleFont);
        add(lbl_area);
        
        String areas[] = {PublicModel.area, "Area 1", "Area 2"}; 
        cb_area = new JComboBox(areas);
        cb_area.setBorder(new LineBorder(c2));
        cb_area.setBackground(c3);
        cb_area.setForeground(c2);
        cb_area.setBounds(110, 310, 150, 30);
        cb_area.setEnabled(false);
        add(cb_area);
        
        err_area = new JLabel();
        err_area.setBounds(110, 345, 150, 15);
        err_area.setFont(err_font);
        err_area.setForeground(Color.red);
        
        
        lbl_city = new JLabel("City :");
        lbl_city.setForeground(c2);
        lbl_city.setBounds(300, 310, 150, 25);
        lbl_city.setFont(simpleFont);
        add(lbl_city);
        
        String cities[] = {PublicModel.city, "City 1", "City 2"};
        cb_city = new JComboBox(cities);
        cb_city.setBorder(new LineBorder(c2));
        cb_city.setBackground(c3);
        cb_city.setForeground(c2);
        cb_city.setBounds(350, 310, 150, 30);
        cb_city.setEnabled(false);
        add(cb_city);
        
        lbl_pincode = new JLabel("Pincode :");
        lbl_pincode.setForeground(c2);
        lbl_pincode.setBounds(20, 380, 150, 25);
        lbl_pincode.setFont(simpleFont);
        add(lbl_pincode);
        
        txt_pincode = new JTextField(PublicModel.pincode);
        txt_pincode.setBorder(new LineBorder(c2));
        txt_pincode.setBounds(110, 380, 150, 30);
        txt_pincode.setEditable(false);
        txt_pincode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String pincode = txt_pincode.getText();
                if(pincode.length() < 1){
                    err_pincode.setText("This Field cannot be empty.");
                    add(err_pincode);
                } else {
                    remove(err_pincode);
                }
                validate();
                repaint();
            }
        });
        add(txt_pincode);
        
        err_pincode = new JLabel();
        err_pincode.setBounds(110, 415, 150, 15);
        err_pincode.setFont(err_font);
        err_pincode.setForeground(Color.red);
        
        btn_save = new JButton("Save");
        btn_save.setBorder(new LineBorder(c2));
        btn_save.setBackground(c5);
        btn_save.setForeground(c2);
        btn_save.setBounds(185, 450, 100, 30);
        btn_save.addActionListener(e -> {
            handleSave();
        });
        
        btn_cancel = new JButton("Cancel");
        btn_cancel.setBorder(new LineBorder(c2));
        btn_cancel.setBackground(c5);
        btn_cancel.setForeground(c2);
        btn_cancel.setBounds(310, 450, 100, 30);
        btn_cancel.addActionListener(e -> {
            dispose();
            PublicProfile p1 = new PublicProfile();
        });
        
        btn_edit = new JButton("Edit");
        btn_edit.setBorder(new LineBorder(c2));
        btn_edit.setBackground(c5);
        btn_edit.setForeground(c2);
        btn_edit.setBounds(250, 450, 100, 30);
        btn_edit.addActionListener(e -> {
            txt_name.setEditable(true);
            txt_phone.setEditable(true);
            txt_email.setEditable(true);
            txt_address.setEditable(true);
            cb_area.setEnabled(true);
            cb_city.setEnabled(true);
            txt_pincode.setEditable(true);
            remove(btn_edit);
            add(btn_save);
            add(btn_cancel);
            validate();
            repaint();
        });
        add(btn_edit);
        
        JButton btn_changePassword = new JButton("Change Password");
        btn_changePassword.setBorder(new LineBorder(c2));
        btn_changePassword.setBackground(c1);
        btn_changePassword.setForeground(c3);
        btn_changePassword.setBounds(120, 500, 150, 30);
        btn_changePassword.addActionListener(e -> {
            ChangePassword c9 = new ChangePassword();
        });
        add(btn_changePassword);
        
        JButton btn_deleteAccount = new JButton("Delete Account");
        btn_deleteAccount.setBorder(new LineBorder(c2));
        btn_deleteAccount.setBounds(320, 500, 150, 30);
        btn_deleteAccount.setBackground(c4);
        btn_deleteAccount.setForeground(c3);
        btn_deleteAccount.addActionListener(e -> {
            handleDelete();
        });
        add(btn_deleteAccount);
        
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }
    void handleSave(){
        name = txt_name.getText();  
        email = txt_email.getText(); 
        address = txt_address.getText();  
        area = cb_area.getSelectedItem().toString();  
        city = cb_city.getSelectedItem().toString(); 
        pincode = txt_pincode.getText(); 
        phone = txt_phone.getText(); 
        
        boolean flag = CheckValidPublic.checkValid(this, name, email, address, area, city, pincode, phone, err_name, err_email, err_address, err_area, err_city, err_pincode, err_phone);
        validate();
        repaint();
        if(flag && checkNotExisting()) {
            updateProfile();
        }
    }
    
    boolean checkNotExisting() {
        boolean f = true;
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt1 = con.createStatement();
            String userQuery = "select * from public where id="+PublicModel.id;
            ResultSet current = stmt1.executeQuery(userQuery);
            current.next();
            if(!phone.equals(current.getString("phone"))){
                Statement stmt2 = con.createStatement();
                String query1 = "select * from public where phone='"+phone+"'";
                ResultSet result1 = stmt2.executeQuery(query1);
                if(result1.next()) {
                    f = false;
                    err_phone2 = new JTextArea("User with the given phone already exists.");
                    err_phone2.setBounds(420, 135, 150, 30);
                    err_phone2.setWrapStyleWord(true);
                    err_phone2.setLineWrap(true);
                    err_phone2.setOpaque(false);
                    err_phone2.setEditable(false);
                    err_phone2.setForeground(Color.red);
                    add(err_phone2);
                    validate();
                    repaint();
                }
            }
            if(!email.equals(current.getString("email"))){
                Statement stmt3 = con.createStatement();
                String query2 = "select * from public where email='"+email+"'";
                ResultSet result2 = stmt3.executeQuery(query2);
                if(result2.next()) {
                    f = false;
                    err_email2 = new JTextArea("User with the given email already exists.");
                    err_email2.setBounds(110, 205, 150, 30);
                    err_email2.setWrapStyleWord(true);
                    err_email2.setLineWrap(true);
                    err_email2.setOpaque(false);
                    err_email2.setEditable(false);
                    err_email2.setForeground(Color.red);
                    add(err_email2);
                    validate();
                    repaint();
                }
            }
            return f;
        } catch(SQLException ex) {
            System.out.println("Exception occurred  "+ex);
        }
        return false;
    }
    
    
    void updateProfile() {
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "update public set name='"+name+"', phone='"+phone+"', email='"+email+"', address='"+address+"', area='"+area+"', city='"+city+"', pincode='"+pincode+"' where id="+PublicModel.id+" ";
            int result = stmt.executeUpdate(query);
            UI.put("OptionPane.background", c3);
            UI.put("Panel.background", c3);
            JOptionPane.showMessageDialog(this, "Profile Updated Successfully!");
            String query2 = "select * from public where id="+PublicModel.id+" ";
            ResultSet user = stmt.executeQuery(query2);
            user.next();
            PublicModel.login(user);
            disableEdit();
            remove(btn_save);
            remove(btn_cancel);
            add(btn_edit);
            validate();
            repaint();
            con.close();
        } catch(Exception ex) {
            System.out.println("Exception in updating profile " + ex);
        }
    }
    
    void disableEdit(){
        txt_name.setEditable(false);
        txt_phone.setEditable(false);
        txt_email.setEditable(false);
        txt_address.setEditable(false);
        cb_area.setEnabled(false);
        cb_city.setEnabled(false);
        txt_pincode.setEditable(false);

    }
    
    void handleDelete(){
        UI.put("OptionPane.background", c3);
        UI.put("Panel.background", c3);
        int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete your Account?");
        if(option == JOptionPane.YES_OPTION){
            try{
               Connection con = DbConnection.getConnection();
                Statement stmt = con.createStatement();
                String query = "delete from public where id="+PublicModel.id;
                int result = stmt.executeUpdate(query);
                UI.put("OptionPane.background", c3);
                UI.put("Panel.background", c3);
                JOptionPane.showMessageDialog(this, "Account deleted!");
                PublicModel.logout();
                dispose();
                WelcomePage w3 = new WelcomePage();
                con.close();
            }catch(SQLException ex) {
                System.out.println("Exception occurred while deletng account. "+ ex);
            }
        }
    }
}