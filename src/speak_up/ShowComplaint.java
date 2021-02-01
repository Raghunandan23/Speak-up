package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.border.LineBorder;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ShowComplaint extends JFrame{

    public ShowComplaint(ResultSet rs) throws SQLException 
    {
        setTitle("Speak Up");
        setSize(500, 600);
        setBounds(300, 85, 500, 600);
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        Color c4 =new Color(255, 0, 0);
        
        getContentPane().setBackground(c3);
        
        try {
            int id = rs.getInt("id");
            //String email = rs.getString("email");
            Font keyFont = new Font("serif", Font.BOLD, 18);
            Font valueFont = new Font("serif", Font.PLAIN, 18);
            
            JLabel lbl_head = new JLabel("COMPLAINT");
            lbl_head.setForeground(c2);
            lbl_head.setBounds(170, 20, 200, 30);
            lbl_head.setFont(new Font("serif", Font.BOLD, 23));
            add(lbl_head);
            
            JPanel mainPanel = new JPanel(new GridLayout(8, 2));
            mainPanel.setBackground(c3);
            mainPanel.setBounds(20, 50, 440, 550);
            mainPanel.setLayout(null);
            
            JLabel lbl_name = new JLabel("Name :");
            lbl_name.setForeground(c2);
            lbl_name.setFont(keyFont);
            lbl_name.setBounds(40, 20, 150, 30);
            mainPanel.add(lbl_name);
            
            JLabel lbl_name_state = new JLabel(rs.getString("name"));
            lbl_name_state.setBounds(220, 20, 100, 30);
            lbl_name_state.setFont(valueFont);
            mainPanel.add(lbl_name_state);
            
            JLabel lbl_phone = new JLabel("Phone Number :");
            lbl_phone.setForeground(c2);
            lbl_phone.setBounds(40, 60, 200, 30);
            lbl_phone.setFont(keyFont);
            mainPanel.add(lbl_phone);    
            
            JLabel lbl_phone_state = new JLabel(rs.getString("phone"));
            lbl_phone_state.setBounds(220, 60, 100, 30);
            lbl_phone_state.setFont(valueFont);
            mainPanel.add(lbl_phone_state);
                      
            JLabel lbl_city = new JLabel("City :");
            lbl_city.setForeground(c2);
            lbl_city.setBounds(40, 100, 150, 30);
            lbl_city.setFont(keyFont);
            mainPanel.add(lbl_city);
            
            JLabel lbl_city_state = new JLabel(rs.getString("city"));
            lbl_city_state.setBounds(220, 100, 100, 30);
            lbl_city_state.setFont(valueFont);
            mainPanel.add(lbl_city_state);
            
            JLabel lbl_area = new JLabel("Area :");
            lbl_area.setForeground(c2);
            lbl_area.setBounds(40, 140, 150, 30);
            lbl_area.setFont(keyFont);
            mainPanel.add(lbl_area);
            
            JLabel lbl_area_state = new JLabel(rs.getString("area"));
            lbl_area_state.setBounds(220, 140, 100, 30);
            lbl_area_state.setFont(valueFont);
            mainPanel.add(lbl_area_state);
            
            JLabel lbl_description = new JLabel("Description :");
            lbl_description.setForeground(c2);
            lbl_description.setBounds(40, 180, 150, 30);
            lbl_description.setFont(keyFont);
            mainPanel.add(lbl_description);
            
            JTextArea lbl_desc_state = new JTextArea(rs.getString("description"));
            lbl_desc_state.setBounds(220, 180, 200, 60);
            lbl_desc_state.setWrapStyleWord(true);
            lbl_desc_state.setLineWrap(true);
            lbl_desc_state.setOpaque(false);
            lbl_desc_state.setEditable(false);
            lbl_desc_state.setFont(valueFont);
            mainPanel.add(lbl_desc_state);
           
            JLabel lbl_info = new JLabel("Information :");
            lbl_info.setForeground(c2);
            lbl_info.setBounds(40, 220, 150, 30);
            lbl_info.setFont(keyFont);
            mainPanel.add(lbl_info);
            
            JTextArea lbl_info_state = new JTextArea(rs.getString("information"));
            lbl_info_state.setBounds(220, 220, 200, 150);
            lbl_info_state.setWrapStyleWord(true);
            lbl_info_state.setLineWrap(true);
            lbl_info_state.setOpaque(false);
            lbl_info_state.setEditable(false);
            lbl_info_state.setFont(valueFont);
            mainPanel.add(lbl_info_state);
            
            JLabel lbl_status = new JLabel("Status :");
            lbl_status.setForeground(c2);
            lbl_status.setBounds(40, 320, 150, 30);
            lbl_status.setFont(keyFont);
            mainPanel.add(lbl_status);
            
            String status = rs.getString("status");
            
            JLabel lbl_status_state = new JLabel(status.toUpperCase());
            lbl_status_state.setBounds(220, 320, 100, 30);
            mainPanel.add(lbl_status_state);
            
            JLabel lbl_cstatus = new JLabel("Conformation Status:");
            lbl_cstatus.setForeground(c2);
            lbl_cstatus.setBounds(40, 360, 300, 30);
            lbl_cstatus.setFont(keyFont);
            mainPanel.add(lbl_cstatus);
            
            JCheckBox check_ack = new JCheckBox("Acknowledge");
            check_ack.setBackground(c3);
            check_ack.setFont(valueFont);
            check_ack.setBounds(220, 360, 150, 20);
            if(status.equals("acknowledged") || status.equals("solved") || status.equals("pending") ){
                check_ack.setSelected(true);
                check_ack.setEnabled(false);
            }
            check_ack.addActionListener(e -> {
                if(check_ack.isSelected()) {
                    try {
                        Connection con = DbConnection.getConnection();
                        Statement stmt = con.createStatement();
                        String query = "update complaints set status='acknowledged' where id=" + id;
                        int result = stmt.executeUpdate(query);
                        check_ack.setEnabled(false);
                        lbl_status_state.setText("Acknowledged");
                        validate();
                        repaint();
                        con.close();
                    } catch(SQLException ex) {
                        System.out.println("Exception while changing status " + ex);
                    }
                }
            });
            mainPanel.add(check_ack);
            
            JCheckBox check_solved = new JCheckBox("Solved");
            check_solved.setBackground(c3);
            check_solved.setFont(valueFont);
            check_solved.setBounds(220, 380, 100, 20);
            if(status.equals("solved") || status.equals("pending")) {
                check_solved.setSelected(true);
                check_solved.setEnabled(false);
            }
            check_solved.addActionListener(e -> {
                if(check_solved.isSelected()){
                    try {
                        Connection con = DbConnection.getConnection();
                        Statement stmt = con.createStatement();
                        String query = "update complaints set status='pending' where id=" + rs.getInt("id");
                        sendEmail(id);
                        int result = stmt.executeUpdate(query);
                        check_solved.setEnabled(false);
                        lbl_status_state.setText("Pending");
                        validate();
                        repaint();
                        con.close();
                    } catch(SQLException ex) {
                        System.out.println("Exception while changing status " + ex);
                    }
                }
            });
            mainPanel.add(check_solved);
            
            JButton btn_delete = new JButton("Delete");
            btn_delete.setBorder(new LineBorder(c2));
            btn_delete.setBackground(c4);
            btn_delete.setForeground(c3);
            btn_delete.setBounds(100, 450, 100, 30);
            if(!status.equals("solved")) {
                btn_delete.setEnabled(false);
            }
            btn_delete.addActionListener(e -> {
                handleDelete(id);
            });
            mainPanel.add(btn_delete);
            
            JButton btn_back = new JButton("Back");
            btn_back.setBorder(new LineBorder(c2));
            btn_back.setBackground(c1);
            btn_back.setForeground(c3);
            btn_back.setBounds(250, 450, 100, 30);
            btn_back.addActionListener(e -> {
                dispose();
                GovtComplaints g3 =new GovtComplaints();
            });
            mainPanel.add(btn_back);
            
            add(mainPanel);
        } catch(SQLException ex) {
            System.out.println("exception occurred while showing complaint" + ex);
        }
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
    
    void sendEmail(int id)
    {
        try{
            Connection con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select email,name from complaints c join public p on c.person_id=p.id where c.id = "+id;
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            // Recipient's email ID needs to be mentioned.
            String to = rs.getString("email");

            // Sender's email ID needs to be mentioned
            String from = GovtModel.email;

            // Assuming you are sending email from through gmails smtp
            String host = "smtp.gmail.com";

            // Get system properties
            Properties properties = System.getProperties();

            // Setup mail server
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            // Get the Session object.// and pass username and password
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(from, "23@2000Raghu");

                }

            });

            // Used to debug SMTP issues
            session.setDebug(true);

            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("To verify that your Complaint has been Solved");

                // Now set the actual message
                message.setText("Hi, "+rs.getString("name")+" your complaint has been Acknowledged and Solved by "+GovtModel.name+" of "+GovtModel.sector+" sector. Please check that your Complaint has been Solved and set your Conformation status as Solved.");

                System.out.println("sending...");
                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
       catch(SQLException ex) {
           System.out.println("Exception occurred while setting user model "+ex);
       }

    }
    void handleDelete(int id)
    {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure do you want to delete this Info?");
        if(option == JOptionPane.YES_NO_OPTION){
            try {
                Connection con = DbConnection.getConnection();
                Statement stmt = con.createStatement();
                String query = "delete from complaints where id="+id;
                int result = stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Complaint deleted successfully.");
                dispose();
                //GovtHomePage m1 = new GovtHomePage();
                con.close();
            } catch(SQLException ex) {
                System.out.println("Exception occurred while deleteing complaint.  "+ex);
            }
        }
    }
    
    
    
}
