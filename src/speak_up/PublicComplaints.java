package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
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

public class PublicComplaints extends JFrame{
    
    JLabel  l_complaints,l_sector,l_description,information,l_infoerr,l_descerr, l_area;
    JTextField tf_description, tf_area;
    JTextArea tf_info;
    JComboBox cb_sector;
    JButton btn_submit,btn_back;
    
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 = new Color (93, 253, 146);
    
    PublicComplaints(){
        
        setTitle("Speak Up");
        setBounds(300, 85, WIDTH, HEIGHT);
        setSize(570, 570);
        
        getContentPane().setBackground(c3);
        
        l_complaints = new JLabel("POST YOUR COMPLAINTS");
        l_complaints.setForeground(c2);
        l_complaints.setBounds(115, 10, 400, 80);
        Font headerfont = new Font("serif", Font.BOLD, 25);
        l_complaints.setFont(headerfont);
        add(l_complaints);
        
        JLabel lbl_sect = new JLabel("Sectors :");
        lbl_sect.setForeground(c2);
        lbl_sect.setBounds(70, 80, 300, 30);
        add(lbl_sect);
        
        String sectors[]={"Corporation","Water","Electricity"};
        cb_sector = new JComboBox(sectors);
        cb_sector.setBorder(new LineBorder(c2));
        cb_sector.setBackground(c3);
        cb_sector.setBounds(170, 80, 300, 30);
        add(cb_sector);
        
        l_area = new JLabel("Area :");
        l_area.setForeground(c2);
        l_area.setBounds(70, 130, 150, 30);
        add(l_area);
        
        tf_area = new JTextField(PublicModel.area);
        tf_area.setBorder(new LineBorder(c2));
        tf_area.setBounds(170, 130, 150, 30);
        tf_area.setEditable(false);
        add(tf_area);
        
        
        l_description = new JLabel("Description :");
        l_description.setForeground(c2);
        l_description.setBounds(70, 180, 150, 30);
        add(l_description);
        l_descerr = new JLabel();
        
        tf_description = new JTextField();
        tf_description.setBorder(new LineBorder(c2));
        tf_description.setBounds(170, 180, 300, 30);
        tf_description.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    String description = tf_description.getText();
                    if(description.length() < 1){
                        l_descerr.setText("This Field cannot be empty");
                        add(l_descerr);
                    } 
                    else{
                        remove(l_descerr);
                    }
                    validate();
                    repaint();
                }
            });
        add(tf_description);
        
        l_descerr.setBounds(170, 210, 150, 15);
        l_descerr.setFont(new Font("serif", Font.PLAIN, 11));
        l_descerr.setForeground(Color.red);
        
        information = new JLabel("Information :");
        information.setForeground(c2);
        information.setBounds(70, 230, 100, 30);
        add(information);
        
        
        tf_info=new JTextArea();
        tf_info.setBorder(new LineBorder(c2));
        tf_info.setBounds(170,230,300,200);
        tf_info.setWrapStyleWord(true);
        tf_info.setLineWrap(true);
        tf_info.addKeyListener(new KeyListener() {
           @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                String information = tf_info.getText();
                if(information.length() < 1){
                    l_infoerr.setText("This Field cannot be empty");
                    add(l_infoerr);
                } 
                else{
                    remove(l_infoerr);
                }
                validate();
                repaint();
            }
        });
        add(tf_info);
        l_infoerr = new JLabel();
        l_infoerr.setBounds(170, 430, 150, 15);
        l_infoerr.setFont(new Font("serif", Font.PLAIN, 11));
        l_infoerr.setForeground(Color.red);
        
        btn_back = new JButton("Back");
        btn_back.setBackground(c1);
        btn_back.setForeground(c3);
        btn_back.setBorder(new LineBorder(c2));
        btn_back.setBounds(150, 470, 100, 30);
        add(btn_back);
        
        btn_submit = new JButton("Post");
        btn_submit.setBackground(c4);
        btn_submit.setForeground(c2);
        btn_submit.setBorder(new LineBorder(c2));
        btn_submit.setBounds(300, 470, 100, 30);
        btn_submit.addActionListener((ActionEvent e) -> {
            handelsubmit();
        });
        add(btn_submit);
        
        btn_back.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    public void handelsubmit()
    {
        String sector=cb_sector.getSelectedItem().toString();
        String description = tf_description.getText();  
        String informations = tf_info.getText();
        
        boolean flag=true;
        if(description.length() < 1){
            l_descerr.setText("This Field cannot be empty.");
            add(l_descerr);
            flag = false;
        }
        if(informations.length() < 1){
            l_infoerr.setText("This Field cannot be empty.");
            add(l_infoerr);
            flag = false;
        }
        if(flag){
            addComplaint(sector,description,informations);
        }
        validate();
        repaint();
    }
    
    void addComplaint(String sector ,String description, String information)
    {
         try {   
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query="insert into complaints(sector,description,city, person_id, information, status, area) values('"+sector+"','"+description+"','"+PublicModel.city+"', "+PublicModel.id+", '"+information+"', 'posted', '"+PublicModel.area+"');";                      
            int result = stmt.executeUpdate(query);
            System.out.println("Result :" + result);
            UIManager UI=new UIManager();
            UI.put("OptionPane.background", c3);
            UI.put("Panel.background", c3);
            JOptionPane.showMessageDialog(this, "Your Complaint has been posted Sucessfully.");
            dispose();
            con.close();
         }
         catch(SQLException ex){
            System.out.println("exception occurred :" + ex);
        } 
    }
}
