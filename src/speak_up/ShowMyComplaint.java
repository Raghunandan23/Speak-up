package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class ShowMyComplaint extends JFrame{
    
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    Color c4 =new Color(255, 0, 0);
    Color c5 = new Color(19, 173, 173);

    public ShowMyComplaint(ResultSet rs) 
    {
        setSize(520, 600);
        setBounds(300, 85, 520, 600);
        setTitle("Speak Up");
        
        getContentPane().setBackground(c3);
        
        try {
            int id = rs.getInt("id");
        
            Font keyFont = new Font("serif", Font.BOLD, 18);
            Font valueFont = new Font("serif", Font.PLAIN, 18);
            
            JLabel lbl_head = new JLabel("COMPLAINT");
            lbl_head.setForeground(c2);
            lbl_head.setBounds(170, 20, 150, 30);
            lbl_head.setFont(new Font("serif", Font.BOLD, 23));
            add(lbl_head);
            
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(c3);
            mainPanel.setBounds(20, 50, 500, 550);
            mainPanel.setLayout(null);
            
            JLabel lbl_postedAt = new JLabel("Posted At :");
            lbl_postedAt.setForeground(c2);
            lbl_postedAt.setBounds(40, 20, 150, 30);
            lbl_postedAt.setFont(keyFont);
            mainPanel.add(lbl_postedAt);    
            
            JLabel lbl_postedAt_state = new JLabel(rs.getString("posted_at"));
            lbl_postedAt_state.setBounds(220, 20, 100, 30);
            lbl_postedAt_state.setFont(valueFont);
            mainPanel.add(lbl_postedAt_state);
            
            JLabel lbl_sector = new JLabel("Sector :");
            lbl_sector.setForeground(c2);
            lbl_sector.setBounds(40, 60, 150, 30);
            lbl_sector.setFont(keyFont);
            mainPanel.add(lbl_sector);
            
            JLabel lbl_sector_state = new JLabel(rs.getString("sector"));
            lbl_sector_state.setBounds(220, 60, 100, 30);
            lbl_sector_state.setFont(valueFont);
            mainPanel.add(lbl_sector_state);
            
            JLabel lbl_description = new JLabel("Description :");
            lbl_description.setForeground(c2);
            lbl_description.setBounds(40, 100, 150, 30);
            lbl_description.setFont(keyFont);
            mainPanel.add(lbl_description);
            
            JTextArea lbl_desc_state = new JTextArea(rs.getString("description"));
            lbl_desc_state.setBounds(220, 100, 300, 60);
            lbl_desc_state.setWrapStyleWord(true);
            lbl_desc_state.setLineWrap(true);
            lbl_desc_state.setOpaque(false);
            lbl_desc_state.setEditable(false);
            lbl_desc_state.setFont(valueFont);
            mainPanel.add(lbl_desc_state);
           
            JLabel lbl_info = new JLabel("Information :");
            lbl_info.setForeground(c2);
            lbl_info.setBounds(40, 140, 150, 30);
            lbl_info.setFont(keyFont);
            mainPanel.add(lbl_info);
            
            JTextArea lbl_info_state = new JTextArea(rs.getString("information"));
            lbl_info_state.setBounds(220, 140, 300, 130);
            lbl_info_state.setWrapStyleWord(true);
            lbl_info_state.setLineWrap(true);
            lbl_info_state.setOpaque(false);
            lbl_info_state.setEditable(false);
            lbl_info_state.setFont(valueFont);
            mainPanel.add(lbl_info_state);
            
            JLabel lbl_status = new JLabel("Status :");
            lbl_status.setForeground(c2);
            lbl_status.setFont(keyFont);
            lbl_status.setBounds(40, 270, 150, 30);
            mainPanel.add(lbl_status);
            
            String status = rs.getString("status");
            JLabel lbl_status_state = new JLabel();
            lbl_status_state.setFont(valueFont);
            lbl_status_state.setBounds(220, 270, 240, 30);
            
            
            if(status.equals("pending")) {
                lbl_status_state.setText("Marked as solved by Official");
            } else {
                lbl_status_state.setText(status.toUpperCase());
            }
            mainPanel.add(lbl_status_state);
            
            JLabel lbl_yourstatus = new JLabel("Conformation Status:");
            lbl_yourstatus.setForeground(c2);
            lbl_yourstatus.setFont(keyFont);
            lbl_yourstatus.setBounds(40, 320, 300, 30);
            mainPanel.add(lbl_yourstatus);
            
            JCheckBox check_solved = new JCheckBox("Solved");
            check_solved.setBackground(c3);
            check_solved.setBounds(240, 375, 80, 20);
            check_solved.setFont(new Font("serif", Font.PLAIN, 17));
            if(status.equals("solved")) {
                check_solved.setSelected(true);
                check_solved.setEnabled(false);
            }
            
            check_solved.addActionListener(e -> {
                if(check_solved.isSelected()){
                    try {
                        Connection con = DbConnection.getConnection();
                        Statement stmt = con.createStatement();
                        String query = "update complaints set status='solved' where id="+id;
                        int result = stmt.executeUpdate(query);
                        lbl_status_state.setText("SOLVED");
                        check_solved.setEnabled(false);
                        validate();
                        repaint();
                    } catch(SQLException ex){
                        System.out.println("Exception occurred while setting status "+ex);
                    }
                }
            });
            add(check_solved);
            
            JButton btn_delete = new JButton("Delete");
            btn_delete.setBorder(new LineBorder(c2));
            btn_delete.setBackground(c4);
            btn_delete.setForeground(c3);
            btn_delete.setBounds(100, 430, 100, 30);
            btn_delete.addActionListener(e -> {
                handleDelete(id);
            });
            mainPanel.add(btn_delete);
            
            JButton btn_back = new JButton("Back");
            btn_back.setBorder(new LineBorder(c2));
            btn_back.setBackground(c1);
            btn_back.setForeground(c3);
            btn_back.setBounds(250, 430, 100, 30);
            btn_back.addActionListener(e -> {
                dispose();
                MyComplaints c6 = new MyComplaints();
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
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", c3);
        UI.put("Panel.background", c3);
        int option = JOptionPane.showConfirmDialog(this, "Are you sure do you want to delete this complaint?");
        if(option == JOptionPane.YES_NO_OPTION){
            try {
                Connection con = DbConnection.getConnection();
                Statement stmt = con.createStatement();
                String query = "delete from complaints where id="+id;
                int result = stmt.executeUpdate(query);
                UI.put("OptionPane.background", c3);
                UI.put("Panel.background", c3);
                JOptionPane.showMessageDialog(this, "Complaint deleted successfully.");
                dispose();
                MyComplaints c1 = new MyComplaints();
            } catch(SQLException ex) {
                System.out.println("Exception occurred while deleteing complaint.  "+ex);
            }
        }
    }
}
