package speak_up;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AdminComplaints extends JFrame {
    
    ResultSet rs;
    AdminComplaints() {
        setSize(600, 600);
        setTitle("Speak Up");
        setBounds(300, 85, 550, 500);
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        
        getContentPane().setBackground(c3);
        
        try {
            Connection con = DbConnection.getConnection();
            String query1 = "select * from complaints";
            Statement state = con.createStatement();
            rs = state.executeQuery(query1);
            con.close();
        } catch(SQLException exp){
            JOptionPane.showMessageDialog(this,"Error Occured while connecting to database...");
        }
        
        JLabel main_lbl = new JLabel("PUBLIC COMPLAINTS");
        main_lbl.setForeground(c2);
        main_lbl.setFont(new Font("Verdana", Font.BOLD, 18));
        main_lbl.setBounds(150, 10, 300, 50);
        add(main_lbl);
        setLayout(new FlowLayout());
        try{
            
            if(rs.next()){
                JPanel panel_mainPanel = new JPanel();
                panel_mainPanel.setBackground(c3);
                panel_mainPanel.setLayout(new GridLayout(10, 1, 25, 2));
                panel_mainPanel.setBounds(25, 50, 500, 300);
                
                GridLayout grid = new GridLayout(1, 5, 20, 20);
                
                Font headingFont = new Font("serif", Font.BOLD, 17);
                
                JPanel panel_header = new JPanel(grid);
                panel_header.setBackground(c3);
                
                JLabel lbl_headName = new JLabel("Sector");
                lbl_headName.setForeground(c2);
                lbl_headName.setFont(headingFont);
                
                JLabel lbl_headPostedAt = new JLabel("Posted At");
                lbl_headPostedAt.setForeground(c2);
                lbl_headPostedAt.setFont(headingFont);
                
                JLabel lbl_headStatus = new JLabel("Status");
                lbl_headStatus.setForeground(c2);
                lbl_headStatus.setFont(headingFont);
                
                
                JLabel lbl_headArea = new JLabel("City");
                lbl_headArea.setForeground(c2);
                lbl_headArea.setFont(headingFont);
                
                JLabel lbl_headView = new JLabel("View");
                lbl_headArea.setForeground(c2);
                lbl_headArea.setFont(headingFont);
                
                panel_header.add(lbl_headName);
                panel_header.add(lbl_headPostedAt);
                panel_header.add(lbl_headStatus);
                panel_header.add(lbl_headArea);
                panel_header.add(lbl_headView);
                
                panel_mainPanel.add(panel_header);
                do {
                    JPanel panel = new JPanel(grid);
                    panel.setBackground(c3);

                    JLabel lbl_name = new JLabel(rs.getString("sector"));
                    JLabel lbl_sector = new JLabel(rs.getString("posted_at"));
                    JLabel lbl_status = new JLabel(rs.getString("status").toUpperCase());
                    JLabel lbl_area = new JLabel(rs.getString("city"));
                    
                    JButton btn_view = new JButton("View");
                    btn_view.setBorder(new LineBorder(c2));
                    btn_view.setBackground(c1);
                    btn_view.setForeground(c3);
                    int id = rs.getInt("Id");
                    btn_view.addActionListener(e -> {
                            showComplaint(id);
                    });
                    
                    panel.add(lbl_name);
                    panel.add(lbl_sector);
                    panel.add(lbl_status);
                    panel.add(lbl_area);
                    panel.add(btn_view);
                    panel_mainPanel.add(panel);
                } while(rs.next());
                 add(panel_mainPanel);
            
            }
            else {
                JLabel lbl_empty = new JLabel("There are no complaints.");
                lbl_empty.setFont(new Font("serif", Font.PLAIN, 17));
                lbl_empty.setBounds(180, 150, 200, 100);
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
    void showComplaint(int id) {
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select c.id, p.name, p.phone, g.name as gname, g.phone as gphone, c.city, c.sector, c.description, c.status, c.area, c.posted_at from complaints c join public p on c.person_id=p.id join govt_servant g on g.city=c.city where c.person_id=p.id and g.city=c.city and g.sector=c.sector and c.id="+id;
            ResultSet result = stmt.executeQuery(query);
            result.next();
            AdminShowComplaint a1 = new AdminShowComplaint(result);
        } catch(SQLException ex) {
            System.out.println("Exeption occurred while showing complaints..");
        }
    }

}
