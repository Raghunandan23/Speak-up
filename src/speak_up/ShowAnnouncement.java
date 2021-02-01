package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class ShowAnnouncement extends JFrame {
    
    ShowAnnouncement(ResultSet rs) 
    {
        setSize(520, 500);
        setBounds(300, 85, 520, 500);
        setTitle("Speak Up");
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        
        getContentPane().setBackground(c3);
        
        try {
          
            Font keyFont = new Font("serif", Font.BOLD, 18);
            Font valueFont = new Font("serif", Font.PLAIN, 18);
            
            JLabel lbl_head = new JLabel(rs.getString("sector").toUpperCase()+" SECTOR");
            lbl_head.setBounds(120, 20, 300, 30);
            lbl_head.setFont(new Font("serif", Font.BOLD, 23));
            add(lbl_head);
            
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(c3);
            mainPanel.setBounds(20, 50, 500, 550);
            mainPanel.setLayout(null);
            
            JLabel lbl_name = new JLabel("Posted By:");
            lbl_name.setFont(keyFont);
            lbl_name.setBounds(40, 20, 150, 30);
            mainPanel.add(lbl_name);
            
            JLabel lbl_name_state = new JLabel(rs.getString("name"));
            lbl_name_state.setFont(valueFont);
            lbl_name_state.setBounds(220, 20, 240, 30);
            mainPanel.add(lbl_name_state);
            
            JLabel lbl_area = new JLabel("Area:");
            lbl_area.setBounds(40, 60, 150, 30);
            lbl_area.setFont(keyFont);
            mainPanel.add(lbl_area);    
            
            JLabel lbl_area_state = new JLabel(rs.getString("area"));
            lbl_area_state.setBounds(220, 60, 100, 30);
            lbl_area_state.setFont(valueFont);
            mainPanel.add(lbl_area_state);
            
            JLabel lbl_city = new JLabel("City:");
            lbl_city.setBounds(40, 100, 150, 30);
            lbl_city.setFont(keyFont);
            mainPanel.add(lbl_city);    
            
            JLabel lbl_city_state = new JLabel(rs.getString("city"));
            lbl_city_state.setBounds(220, 100, 100, 30);
            lbl_city_state.setFont(valueFont);
            mainPanel.add(lbl_city_state);
            
            JLabel lbl_postedAt = new JLabel("Posted At:");
            lbl_postedAt.setBounds(40, 140, 150, 30);
            lbl_postedAt.setFont(keyFont);
            mainPanel.add(lbl_postedAt);    
            
            JLabel lbl_postedAt_state = new JLabel(rs.getString("posted_at"));
            lbl_postedAt_state.setBounds(220, 140, 100, 30);
            lbl_postedAt_state.setFont(valueFont);
            mainPanel.add(lbl_postedAt_state);
            
            JLabel lbl_info = new JLabel("Information:");
            lbl_info.setBounds(40, 180, 150, 30);
            lbl_info.setFont(keyFont);
            mainPanel.add(lbl_info);
            
            JTextArea lbl_info_state = new JTextArea(rs.getString("info"));
            lbl_info_state.setBounds(220, 180, 300, 130);
            lbl_info_state.setWrapStyleWord(true);
            lbl_info_state.setLineWrap(true);
            lbl_info_state.setOpaque(false);
            lbl_info_state.setEditable(false);
            lbl_info_state.setFont(valueFont);
            mainPanel.add(lbl_info_state);
            
            JButton btn_back = new JButton("Back");
            btn_back.setBorder(new LineBorder(c2));
            btn_back.setBackground(c1);
            btn_back.setForeground(c3);
            btn_back.setBounds(175, 340, 100, 30);
            btn_back.addActionListener(e -> {
                PublicInformation p1 = new PublicInformation();
                dispose();
            });
            mainPanel.add(btn_back);
            add(mainPanel);
            
        } catch(SQLException ex) {
            System.out.println("exception occurred while showing announcement" + ex);
        }
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
}
