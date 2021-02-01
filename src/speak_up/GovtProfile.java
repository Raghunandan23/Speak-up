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

public class GovtProfile extends JFrame{
    
    JLabel lbl_header, lbl_name, lbl_phone, lbl_email, lbl_sector, lbl_city, lbl_unique_id; 
    JTextField txt_name, txt_phone, txt_email, txt_unique_id, txt_sector;
    JButton btn_edit, btn_save, btn_cancel, btn_back;
    String name, area, email, city, sector, phone;
    JLabel err_name, err_phone, err_email, err_city;
    JComboBox  cb_city;
    JTextArea err_phone2 = new JTextArea();
    JTextArea err_email2 = new JTextArea();
    
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 =new Color(255, 0, 0);
    Color c5 = new Color(19, 173, 173);
    
    public GovtProfile(){
        
        setSize(600, 620);
        setBounds(300, 85, 600, 540);
        setTitle("Speak Up");
        
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
        });
        
        add(btn_back);
        
        
        Font simpleFont= new Font("serif", Font.PLAIN, 20);
        
        lbl_name = new JLabel("Name :");
        lbl_name.setBounds(20, 100, 100, 25);
        lbl_name.setFont(simpleFont);
        add(lbl_name);
        
        txt_name = new JTextField(GovtModel.name);
        lbl_name.setForeground(c2);
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
        err_name.setBounds(110, 135, 150, 15);
        err_name.setFont(err_font);
        err_name.setForeground(Color.red);
        
        lbl_phone = new JLabel("Phone Number:");
        lbl_phone.setForeground(c2);
        lbl_phone.setBounds(290, 100, 200, 25);
        lbl_phone.setFont(simpleFont);
        add(lbl_phone);
        
        txt_phone = new JTextField(GovtModel.phone);
        txt_phone.setBorder(new LineBorder(c2));
        txt_phone.setBounds(420, 100, 100, 30);
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
                remove(err_phone2);
                String phone = txt_phone.getText();
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
        err_phone.setBounds(420, 135, 150, 15);
        err_phone.setFont(err_font);
        err_phone.setForeground(Color.red);
        
        
        lbl_email = new JLabel("Email-ID :");
        lbl_email.setForeground(c2);
        lbl_email.setBounds(20, 170, 100, 25);
        lbl_email.setFont(simpleFont);
        add(lbl_email);
        
        txt_email = new JTextField(GovtModel.email);
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
                remove(err_email2);
                String email = txt_email.getText();
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
                
        lbl_sector = new JLabel("Sector :");
        lbl_sector.setForeground(c2);
        lbl_sector.setBounds(290, 310, 100, 25);
        lbl_sector.setFont(simpleFont);
        add(lbl_sector);
        
        txt_sector = new JTextField(GovtModel.sector);
        txt_sector.setBorder(new LineBorder(c2));
        txt_sector.setBounds(400, 310, 150, 30);
        txt_sector.setEditable(false);
        add(txt_sector);
        
        lbl_city = new JLabel("City :");
        lbl_city.setForeground(c2);
        lbl_city.setBounds(20, 310, 150, 25);
        lbl_city.setFont(simpleFont);
        add(lbl_city);
        
        String cities[] = {GovtModel.city, "City 1", "City 2"};
        cb_city = new JComboBox(cities);
        cb_city.setBorder(new LineBorder(c2));
        cb_city.setBackground(c3);
        cb_city.setForeground(c2);
        cb_city.setBounds(110, 310, 150, 30);
        cb_city.setEnabled(false);
        add(cb_city);
        
        lbl_unique_id = new JLabel("Aadhar Number :");
        lbl_unique_id.setBounds(20, 240, 200, 25);
        lbl_unique_id.setFont(simpleFont);
        add(lbl_unique_id);
        
        txt_unique_id = new JTextField(GovtModel.unique_id);
        txt_unique_id.setBorder(new LineBorder(c2));
        txt_unique_id.setBounds(200, 240, 200, 30);
        txt_unique_id.setEditable(false);
        add(txt_unique_id);
        
        btn_save = new JButton("Save");
        btn_save.setBackground(c5);
        btn_save.setForeground(c2);
        btn_save.setBounds(185, 370, 100, 30);
        btn_save.addActionListener(e -> {
            handleSave();
        });
        
        btn_cancel = new JButton("Cancel");
        btn_cancel.setBorder(new LineBorder(c2));
        btn_cancel.setBackground(c5);
        btn_cancel.setForeground(c2);
        btn_cancel.setBounds(310, 370, 100, 30);
        btn_cancel.addActionListener(e -> {
            dispose();
            GovtProfile p1 = new GovtProfile();
        });
        
        btn_edit = new JButton("Edit");
        btn_edit.setBorder(new LineBorder(c2));
        btn_edit.setBackground(c5);
        btn_edit.setForeground(c2);
        btn_edit.setBounds(250, 370, 100, 30);
        btn_edit.addActionListener(e -> {
            txt_name.setEditable(true);
            txt_phone.setEditable(true);
            txt_email.setEditable(true);
            cb_city.setEnabled(true);
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
        btn_changePassword.setBounds(120, 420, 150, 30);
        btn_changePassword.addActionListener(e -> {
            ChangePassword c7 = new ChangePassword();
        });
        add(btn_changePassword);
        
        JButton btn_deleteAccount = new JButton("Delete Account");
        btn_deleteAccount.setBorder(new LineBorder(c2));
        btn_deleteAccount.setBounds(320, 420, 150, 30);
        btn_deleteAccount.setBackground(c4);
        btn_deleteAccount.setForeground(c3);
        btn_deleteAccount.addActionListener(e -> {
            handleDelete();
        });
        add(btn_deleteAccount);
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    void handleSave(){
        name = txt_name.getText();  
        email = txt_email.getText(); 
        city = cb_city.getSelectedItem().toString(); 
        phone = txt_phone.getText(); 
        
        boolean flag = CheckValidGovt.checkValid(this, name, email, city, phone, err_name, err_email, err_city, err_phone);
        
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
            String userQuery = "select * from govt_servant where id="+GovtModel.id;
            ResultSet current = stmt1.executeQuery(userQuery);
            current.next();
            if(!phone.equals(current.getString("phone"))){
                Statement stmt2 = con.createStatement();
                String query1 = "select * from govt_servant where phone='"+phone+"'";
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
                String query2 = "select * from govt_servant where email='"+email+"'";
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
            String query = "update govt_servant set name='"+name+"', phone='"+phone+"', email='"+email+"', city='"+city+"' where id="+GovtModel.id ;
            int result = stmt.executeUpdate(query);
            UIManager UI=new UIManager();
            UI.put("OptionPane.background", c3);
            UI.put("Panel.background", c3);
            JOptionPane.showMessageDialog(this, "Profile updated Successfully!");
            String query2 = "select * from govt_servant where id="+GovtModel.id;
            ResultSet user = stmt.executeQuery(query2);
            user.next();
            GovtModel.login(user);
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
        cb_city.setEnabled(false);
    }
    
    void handleDelete(){
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", c3);
        UI.put("Panel.background", c3);
        int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete your Account?");
        if(option == JOptionPane.YES_OPTION){
            try{
                Connection con = DbConnection.getConnection();
                Statement stmt = con.createStatement();
                String query = "delete from govt_servant where id="+GovtModel.id;
                int result = stmt.executeUpdate(query);
                UI.put("OptionPane.background", c3);
                UI.put("Panel.background", c3);
                JOptionPane.showMessageDialog(this, "Account deleted!");
                GovtModel.logout();
                dispose();
                WelcomePage w3 = new WelcomePage();
                con.close();
            }catch(SQLException ex) {
                System.out.println("Exception occurred while deletng account. "+ ex);
            }
        }
    }
}
