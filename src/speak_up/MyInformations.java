package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MyInformations extends JFrame{
    
    ResultSet rs;
    
    MyInformations() 
    {
        setTitle("Speak Up");
        setSize(550, 500);
        setBounds(300, 85, 550, 500);
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        
        getContentPane().setBackground(c3);
        
        try {
            Connection con = DbConnection.getConnection();
            String query1 = "select * from "+GovtModel.sector+"_board where govt_official_id=(?) ";
            PreparedStatement state = con.prepareStatement(query1);
            state.setInt(1, GovtModel.id);
            System.out.println(GovtModel.id);
            rs = state.executeQuery();
            con.close();
        } catch(SQLException exp){
            JOptionPane.showMessageDialog(this,"Error Occured while connecting to database...");
        }
        
        JLabel main_lbl = new JLabel("POSTED INFORMATIONS");
        main_lbl.setBackground(c3);
        main_lbl.setFont(new Font("Verdana", Font.BOLD, 18));
        main_lbl.setBounds(150, 10, 250, 50);
        add(main_lbl);
        
        try{
            
            if(rs.next()){
                JPanel panel_mainPanel = new JPanel(new GridLayout(10, 1));
                panel_mainPanel.setBackground(c3);
                panel_mainPanel.setBounds(30, 80, 500, 300);
                
                GridLayout g = new GridLayout(1, 3, 20, 20);
                
                Font headingFont = new Font("serif", Font.BOLD, 17);
                
                JPanel panel_header = new JPanel(g);
                panel_header.setBackground(c3);
                
                JLabel lbl_headPosted = new JLabel("Posted At");
                lbl_headPosted.setForeground(c2);
                lbl_headPosted.setFont(headingFont);
                
                JLabel lbl_headArea = new JLabel("Area");
                lbl_headArea.setForeground(c2);
                lbl_headArea.setFont(headingFont);
                
                JLabel lbl_headView = new JLabel("View");
                lbl_headView.setForeground(c2);
               
                panel_header.add(lbl_headPosted);
                panel_header.add(lbl_headArea);
                panel_header.add(lbl_headView);
                
                panel_mainPanel.add(panel_header);
                do {
                    JPanel panel = new JPanel(g);
                    panel.setBackground(c3);
                    panel.setSize(300, 100);
                    
                    JLabel lbl_posted = new JLabel(rs.getString("posted_at"));
                    JLabel lbl_area = new JLabel(rs.getString("area"));
                    
                    JButton btn_view = new JButton("View");
                    btn_view.setBorder(new LineBorder(c2));
                    btn_view.setBackground(c1);
                    btn_view.setForeground(c3);
                    int id = rs.getInt("Id");
                    btn_view.addActionListener(e -> {
                            showInfo(id);
                    });
                    
                    panel.add(lbl_posted);
                    panel.add(lbl_area);
                    panel.add(btn_view);
                    panel_mainPanel.add(panel);
                } while(rs.next());
                
                add(panel_mainPanel);
            }
            else {
                JLabel lbl_empty = new JLabel("You haven't posted any information");
                lbl_empty.setBounds(160, 150, 250, 100);
                lbl_empty.setFont(new Font("serif", Font.PLAIN, 17));
                add(lbl_empty);
            } 
        } catch(SQLException ex) {
            System.out.println("Exception while showing complaints. "+ex);
        }
        JButton btn_back = new JButton("Back");
        btn_back.setBorder(new LineBorder(c2));
        btn_back.setBackground(c1);
        btn_back.setForeground(c3);
        btn_back.setBounds(220, 400, 100, 30);
        btn_back.addActionListener(e -> {
            dispose();
        });
        add(btn_back);
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
      
    }
    
    void showInfo(int id){
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from "+GovtModel.sector+"_board where id="+id;
            ResultSet result = stmt.executeQuery(query);
            result.next();
            dispose();
            ShowPostedInfo s1 = new ShowPostedInfo(result);
            con.close(); 
        } catch(SQLException ex) {
            System.out.println("Exception in view status "+ ex);
        }
    } 
}
