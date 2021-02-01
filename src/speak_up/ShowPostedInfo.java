package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class ShowPostedInfo extends JFrame{
    
    
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 =new Color(255, 0, 0);
    
    UIManager UI=new UIManager();
    
    ShowPostedInfo(ResultSet rs) 
    {
        setTitle("Speak Up");
        setSize(520, 500);
        setBounds(300, 85, 520, 500);
        
        getContentPane().setBackground(c3);
        
        try {
            int id = rs.getInt("id");
        
            Font keyFont = new Font("serif", Font.BOLD, 18);
            Font valueFont = new Font("serif", Font.PLAIN, 18);
            
            JLabel lbl_head = new JLabel("INFORMATION");
            lbl_head.setForeground(c2);
            lbl_head.setBounds(180, 20, 200, 30);
            lbl_head.setFont(new Font("serif", Font.BOLD, 23));
            add(lbl_head);
            
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(c3);
            mainPanel.setBounds(20, 50, 500, 550);
            mainPanel.setLayout(null);
            
            JLabel lbl_sector = new JLabel("Sector:");
            lbl_sector.setFont(keyFont);
            lbl_sector.setBounds(40, 20, 150, 30);
            mainPanel.add(lbl_sector);
            
            JLabel lbl_sector_state = new JLabel(rs.getString("sector"));
            lbl_sector_state.setFont(valueFont);
            lbl_sector_state.setBounds(220, 20, 250, 30);
            mainPanel.add(lbl_sector_state);
            
            JLabel lbl_area = new JLabel("Area:");
            lbl_area.setBounds(40, 100, 150, 30);
            lbl_area.setFont(keyFont);
            mainPanel.add(lbl_area);
            
            JLabel lbl_area_state = new JLabel(rs.getString("area"));
            lbl_area_state.setBounds(220, 100, 100, 30);
            lbl_area_state.setFont(valueFont);
            mainPanel.add(lbl_area_state);
            
            JLabel lbl_city = new JLabel("City:");
            lbl_city.setBounds(40, 60, 150, 30);
            lbl_city.setFont(keyFont);
            mainPanel.add(lbl_city);    
            
            JLabel lbl_city_state = new JLabel(rs.getString("city"));
            lbl_city_state.setBounds(220, 60, 100, 30);
            lbl_city_state.setFont(valueFont);
            mainPanel.add(lbl_city_state);
            
            JLabel lbl_info = new JLabel("Information:");
            lbl_info.setBounds(40, 140, 150, 30);
            lbl_info.setFont(keyFont);
            mainPanel.add(lbl_info);
            
            JTextArea lbl_info_state = new JTextArea(rs.getString("info"));
            lbl_info_state.setBounds(220, 140, 300, 60);
            lbl_info_state.setWrapStyleWord(true);
            lbl_info_state.setLineWrap(true);
            lbl_info_state.setOpaque(false);
            lbl_info_state.setEditable(false);
            lbl_info_state.setFont(valueFont);
            mainPanel.add(lbl_info_state);
           
            JButton btn_delete = new JButton("Delete");
            btn_delete.setBorder(new LineBorder(c2));
            btn_delete.setBounds(100, 350, 100, 30);
            btn_delete.addActionListener(e -> {
                handleDelete(id);
            });
            btn_delete.setBackground(c4);
            btn_delete.setForeground(c3);
            mainPanel.add(btn_delete);
            
            JButton btn_back = new JButton("Back");
            btn_back.setBorder(new LineBorder(c2));
            btn_back.setBackground(c1);
            btn_back.setForeground(c3);
            btn_back.setBounds(250, 350, 100, 30);
            btn_back.addActionListener(e -> {
                dispose();
                MyInformations m1 = new MyInformations();
            });
            mainPanel.add(btn_back);
            
            add(mainPanel);
        } catch(SQLException ex) {
            System.out.println("exception occurred while showing my complaint" + ex);
        }
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    void handleDelete(int id) {
        UI.put("OptionPane.background", c3);
        UI.put("Panel.background", c3);
        int option = JOptionPane.showConfirmDialog(this, "Are you sure do you want to delete this Information?");
        if(option == JOptionPane.YES_NO_OPTION){
            try {
                Connection con = DbConnection.getConnection();
                Statement stmt = con.createStatement();
                String query = "delete from "+GovtModel.sector+"_board where id="+id;
                int result = stmt.executeUpdate(query);
                UI.put("OptionPane.background", c3);
                UI.put("Panel.background", c3);
                JOptionPane.showMessageDialog(this, "Complaint deleted successfully.");
                dispose();
                MyInformations m1 = new MyInformations();
                con.close();
            } catch(SQLException ex) {
                System.out.println("Exception occurred while deleteing complaint.  "+ex);
            }
        }
    }
    
}
