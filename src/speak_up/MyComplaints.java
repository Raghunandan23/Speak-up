package speak_up;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MyComplaints extends JFrame{
    
    ResultSet rs;
    MyComplaints()
    {
        setSize(550, 500);
        setBounds(300, 85, 550, 500);
        setTitle("Speak Up");
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        
        getContentPane().setBackground(c3);
        
        try {
            Connection con = DbConnection.getConnection();
            String query1 = "select * from complaints where person_id=(?) ";
            PreparedStatement state = con.prepareStatement(query1);
            state.setInt(1, PublicModel.id);
            System.out.println(PublicModel.id);
            rs = state.executeQuery();
            con.close();
        } catch(SQLException exp){
            JOptionPane.showMessageDialog(this,"Error Occured while connecting to database...");
        }
        
        JLabel main_lbl = new JLabel("MY COMPLAINTS");
        main_lbl.setForeground(c2);
        main_lbl.setFont(new Font("Verdana", Font.BOLD, 18));
        main_lbl.setBounds(180, 10, 200, 50);
        add(main_lbl);
        setLayout(new FlowLayout());
        try{
            
            if(rs.next())
            {
                JPanel panel_mainPanel = new JPanel();
                panel_mainPanel.setBackground(c3);
                GridLayout grid = new GridLayout(10, 1);
                panel_mainPanel.setLayout(grid);
                GridLayout g = new GridLayout(1, 4, 20, 20);
                panel_mainPanel.setBounds(30, 80, 500, 300);
                
                Font headingFont = new Font("serif", Font.BOLD, 17);
                
                JPanel panel_header = new JPanel(g);
                panel_header.setBackground(c3);
                JLabel lbl_headSector = new JLabel("  Sector");
                lbl_headSector.setForeground(c2);
                lbl_headSector.setFont(headingFont);
                
                JLabel lbl_headDescription = new JLabel("Description");
                lbl_headDescription.setForeground(c2);
                lbl_headDescription.setFont(headingFont);
                
                JLabel lbl_headStatus = new JLabel(" Status");
                lbl_headStatus.setForeground(c2);
                lbl_headStatus.setFont(headingFont);
                
                JLabel lbl_headView = new JLabel("     View");
                lbl_headView.setForeground(c2);
                lbl_headView.setFont(headingFont);
               
                panel_header.add(lbl_headSector);
                panel_header.add(lbl_headDescription);
                panel_header.add(lbl_headStatus);
                panel_header.add(lbl_headView);
                
                panel_mainPanel.add(panel_header);
                do 
                {
                    JPanel panel = new JPanel(g);
                    panel.setBackground(c3);
                    panel.setSize(300, 100);
                    JLabel lbl_sector = new JLabel(rs.getString("sector").toUpperCase());
                    JLabel lbl_status = new JLabel(rs.getString("status").toUpperCase());
                    JLabel lbl_description = new JLabel(rs.getString("description"));
                   
                    JButton btn_view = new JButton("View");
                    btn_view.setBorder(new LineBorder(c2));
                    btn_view.setBackground(c1);
                    btn_view.setForeground(c3);
                    int id = rs.getInt("Id");
                    btn_view.addActionListener(e -> {
                            showStatus(id);
                    });
                    
                    panel.add(lbl_sector);
                    panel.add(lbl_description);
                    panel.add(lbl_status);
                    panel.add(btn_view);
                    panel_mainPanel.add(panel);
                    
                } while(rs.next());
                panel_mainPanel.setBackground(c3);
                add(panel_mainPanel);
            }
            else {
                JLabel lbl_empty = new JLabel("You haven't made any complaints.");
                lbl_empty.setForeground(c2);
                lbl_empty.setFont(new Font("serif", Font.PLAIN, 16));
                lbl_empty.setBounds(170, 150, 220, 100);
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
    
    void showStatus(int id){
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from complaints where id="+id;
            ResultSet result = stmt.executeQuery(query);
            result.next();
            dispose();
            ShowMyComplaint s1 = new ShowMyComplaint(result);
                    
        } catch(SQLException ex) {
            System.out.println("Exception in view status "+ ex);
        }
    } 
}
