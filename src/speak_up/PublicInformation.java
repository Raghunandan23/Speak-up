package speak_up;

import java.awt.Color;
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

public class PublicInformation extends JFrame{
    
    ResultSet e_rs, w_rs, c_rs;
    
    PublicInformation()
    {
        setBounds(300, 85, WIDTH, HEIGHT);
        setSize(570, 500);
        setTitle("Speak Up");
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        
        getContentPane().setBackground(c3);
        
        try 
        {
            Connection con = DbConnection.getConnection();
            String query1 = "select * from electricity_board where area='"+PublicModel.area+"' and city='"+PublicModel.city+"'";
            String query2 = "select * from water_board where area='"+PublicModel.area+"' and city='"+PublicModel.city+"'";
            String query3 = "select * from corporation_board where area='"+PublicModel.area+"' and city='"+PublicModel.city+"'";
            
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();
            
            e_rs = stmt1.executeQuery(query1);
            w_rs = stmt2.executeQuery(query2);
            c_rs = stmt3.executeQuery(query3);
            
            con.close();
            
        } catch(SQLException exp){
            JOptionPane.showMessageDialog(this,"Error Occured while connecting to database...");
        }
        
        JLabel main_lbl = new JLabel("ANNOUNCEMENTS");
        main_lbl.setForeground(c2);
        main_lbl.setFont(new Font("Verdana", Font.BOLD, 18));
        main_lbl.setBounds(200, 10, 200, 50);
        add(main_lbl);

        JPanel panel_mainPanel = new JPanel(); 
        panel_mainPanel.setBackground(c3);
        boolean flag = true;

        panel_mainPanel.setLayout(new GridLayout(10, 1, 25, 2));
        panel_mainPanel.setBounds(25, 50, 500, 300);

        GridLayout grid = new GridLayout(1, 5, 20, 20);

        Font headingFont = new Font("serif", Font.BOLD, 17);

        JPanel panel_header = new JPanel(grid);
        panel_header.setBackground(c3);

        JLabel lbl_headSector = new JLabel("Sector");
        lbl_headSector.setForeground(c2);
        lbl_headSector.setFont(headingFont);

        JLabel lbl_headPostedAt = new JLabel("Posted At");
        lbl_headPostedAt.setForeground(c2);
        lbl_headPostedAt.setFont(headingFont);


        JLabel lbl_headArea = new JLabel("Area");
        lbl_headArea.setForeground(c2);
        lbl_headArea.setFont(headingFont);

        JLabel lbl_headView = new JLabel("View");
        lbl_headView.setForeground(c2);
        lbl_headArea.setFont(headingFont);

        panel_header.add(lbl_headSector);
        panel_header.add(lbl_headPostedAt);
        panel_header.add(lbl_headArea);
        panel_header.add(lbl_headView);

        panel_mainPanel.add(panel_header);
        
        try {
            if(e_rs.next()){
                flag = false;
                do {
                    JPanel panel = new JPanel(grid);
                    panel.setBackground(c3);

                    JLabel lbl_sector = new JLabel(e_rs.getString("sector").toUpperCase());
                    JLabel lbl_postedAt = new JLabel(e_rs.getString("posted_at"));
                    JLabel lbl_area = new JLabel(e_rs.getString("area"));
                    
                    JButton btn_view = new JButton("View");
                    btn_view.setBorder(new LineBorder(c2));
                    btn_view.setBackground(c1);
                    btn_view.setForeground(c3);
                    int id = e_rs.getInt("Id");
                    btn_view.addActionListener(e -> {
                            showAnnouncement_E(id);
                    });
                    
                    panel.add(lbl_sector);
                    panel.add(lbl_postedAt);
                    panel.add(lbl_area);
                    panel.add(btn_view);
                    panel_mainPanel.add(panel);
                } while(e_rs.next());
            
            }
            if(w_rs.next()){
                flag = false;
                do {
                    JPanel panel = new JPanel(grid);
                    panel.setBackground(c3);

                    JLabel lbl_sector = new JLabel(w_rs.getString("sector").toUpperCase());
                    JLabel lbl_postedAt = new JLabel(w_rs.getString("posted_at"));
                    JLabel lbl_area = new JLabel(w_rs.getString("area"));
                    
                    JButton btn_view = new JButton("View");
                    btn_view.setBorder(new LineBorder(c2));
                    btn_view.setBackground(c1);
                    btn_view.setForeground(c3);
                    int id = w_rs.getInt("Id");
                    btn_view.addActionListener(e -> {
                            showAnnouncement_W(id);
                    });
                    
                    panel.add(lbl_sector);
                    panel.add(lbl_postedAt);
                    panel.add(lbl_area);
                    panel.add(btn_view);
                    panel_mainPanel.add(panel);
                } while(w_rs.next());
            
            }
            if(c_rs.next()){
                flag = false;
                do {
                    JPanel panel = new JPanel(grid);
                    panel.setBackground(c3);

                    JLabel lbl_sector = new JLabel(c_rs.getString("sector").toUpperCase());
                    JLabel lbl_postedAt = new JLabel(c_rs.getString("posted_at"));
                    JLabel lbl_area = new JLabel(c_rs.getString("area"));
                    
                    JButton btn_view = new JButton("View");
                    btn_view.setBorder(new LineBorder(c2));
                    btn_view.setBackground(c1);
                    btn_view.setForeground(c3);
                    int id = c_rs.getInt("Id");
                    btn_view.addActionListener(e -> {
                            showAnnouncement_C(id);
                    });
                    
                    panel.add(lbl_sector);
                    panel.add(lbl_postedAt);
                    panel.add(lbl_area);
                    panel.add(btn_view);
                    panel_mainPanel.add(panel);
                } while(c_rs.next());
            
            }
            if(flag) {
                JLabel lbl_empty = new JLabel("There are no announcements.");
                lbl_empty.setFont(new Font("serif", Font.PLAIN, 17));
                lbl_empty.setBounds(180, 150, 200, 100);
                add(lbl_empty);
            } 
        } catch(SQLException ex) {
            System.out.println("Exception while showing announcements. "+ex);
        }
        JButton btn_back = new JButton("Back");
        btn_back.setBorder(new LineBorder(c2));
        btn_back.setBounds(220, 400, 100, 30);
        btn_back.setBackground(c1);
        btn_back.setForeground(c3);
        btn_back.addActionListener(e -> {
            dispose();
        });
        add(btn_back);
        add(panel_mainPanel);
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
      
    }
    
    void showAnnouncement_E(int id){
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select e.sector, g.name, info, posted_at, e.area, e.city from electricity_board e join govt_servant g on g.id=e.govt_official_id where e.id="+id;  
            ResultSet result = stmt.executeQuery(query);
            result.next();
            dispose();
            ShowAnnouncement s1 = new ShowAnnouncement(result);
            con.close();        
        } catch(SQLException ex) {
            System.out.println("Exception in view announcement "+ ex);
        }
    }
    
    void showAnnouncement_W(int id){
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select e.sector, g.name, info, posted_at, e.area, e.city from water_board e join govt_servant g on g.id=e.govt_official_id where e.id="+id;
            ResultSet result = stmt.executeQuery(query);
            result.next();
            dispose();
            ShowAnnouncement s1 = new ShowAnnouncement(result);
            con.close();        
        } catch(SQLException ex) {
            System.out.println("Exception in view announcement "+ ex);
        }
    }
    
    void showAnnouncement_C(int id){
        try {
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select e.sector, g.name, info, posted_at, e.area, e.city from corporation_board e join govt_servant g on g.id=e.govt_official_id where e.id="+id;
            ResultSet result = stmt.executeQuery(query);
            result.next();
            dispose();
            ShowAnnouncement s1 = new ShowAnnouncement(result);
            con.close();        
        } catch(SQLException ex) {
            System.out.println("Exception in view announcement "+ ex);
        }
    }
}

