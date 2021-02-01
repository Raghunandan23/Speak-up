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

public class GovtInformation extends JFrame{
    
    JTextField txt_sector;
    JTextArea tf_info;
    JLabel l_infoerr, lbl_area ;
    JComboBox cb_area;
    
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 = new Color (93, 253, 146);
    
    UIManager UI=new UIManager();
    
    public GovtInformation(){
        
        setTitle("Speak Up");
        setBounds(300, 85, WIDTH, HEIGHT);
        setSize(570, 550);
        
        getContentPane().setBackground(c3);
        
        JLabel lbl_ginfotab = new JLabel("POST INFORMATION");
        lbl_ginfotab.setBounds(150, 10, 400, 80);
        Font headerfont = new Font("serif", Font.BOLD, 25);
        lbl_ginfotab.setFont(headerfont);
        add(lbl_ginfotab);
        
        JLabel lbl_sector = new JLabel("Sector : ");
        lbl_sector.setForeground(c2);
        lbl_sector.setBounds(70, 100, 150, 30);
        add(lbl_sector);
        
        txt_sector = new JTextField(GovtModel.sector);
        txt_sector.setBorder(new LineBorder(c2));
        txt_sector.setForeground(c2);
        txt_sector.setBounds(170, 100, 300, 30);
        txt_sector.setBackground(c3);
        txt_sector.setEditable(false);
        add(txt_sector);
        
        lbl_area = new JLabel("Area :"); 
        lbl_area.setForeground(c2);
        lbl_area.setBounds(70, 150, 150, 30);  
        add(lbl_area); 
        
        String areas[] = {"Area 1", "Area 2"}; 
        cb_area = new JComboBox(areas); 
        cb_area.setBorder(new LineBorder(c2));
        cb_area.setBackground(c3);
        cb_area.setBounds(170, 150, 200, 30);  
        add(cb_area);
        
        
        JLabel information = new JLabel("Information :");
        lbl_area.setForeground(c2);
        information.setBounds(70, 200, 100, 30);
        add(information);
        
        tf_info=new JTextArea();
        tf_info.setBorder(new LineBorder(c2));
        tf_info.setBounds(170,190,300,200);
        tf_info.setWrapStyleWord(true);
        tf_info.setLineWrap(true);
        
        l_infoerr = new JLabel();
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
        
        l_infoerr.setBounds(170, 400, 150, 15);
        l_infoerr.setFont(new Font("serif", Font.PLAIN, 11));
        l_infoerr.setForeground(Color.red);
        
        JButton btn_post = new JButton("Post");
        btn_post.setBackground(c4);
        btn_post.setForeground(c2);
        btn_post.setBorder(new LineBorder(c2));
        btn_post.setBounds(320, 430, 100, 30);
        btn_post.addActionListener((ActionEvent e) -> {
            handelsubmit();
        });
        add(btn_post);
        
        JButton btn_back = new JButton("Back");
        btn_back.setBorder(new LineBorder(c2));
        btn_back.setBackground(c1);
        btn_back.setForeground(c3);
        btn_back.setBounds(120, 430, 100, 30);
        btn_back.addActionListener((ActionEvent e) -> {
            dispose();
        });
        add(btn_back);
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    void handelsubmit()
    {
        String informations = tf_info.getText();
        String area = cb_area.getSelectedItem().toString();
        boolean flag=true;
        
        if(informations.length() < 1){
            l_infoerr.setText("This Field cannot be empty.");
            add(l_infoerr);
            flag = false;
        }
        if(flag){
            addInformation(informations, area);
        }
        validate();
        repaint();
    }
    
    void addInformation(String information, String area)
    {
         try {   
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query="insert into "+GovtModel.sector+"_board(sector,info, govt_official_id, area, city) values('"+GovtModel.sector+"','"+information+"',"+GovtModel.id+", '"+area+"', '"+GovtModel.city+"')";
            int result = stmt.executeUpdate(query);
            System.out.println("Result :" + result);
            UI.put("OptionPane.background", c3);
            UI.put("Panel.background", c3);
            JOptionPane.showMessageDialog(this, "Your Information has been posted Sucessfully.");
            dispose();
            con.close();
        }
         catch(SQLException ex){
            System.out.println("exception occurred :" + ex);
        } 
    }
    
    
}