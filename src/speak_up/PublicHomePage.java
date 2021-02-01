package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PublicHomePage extends JFrame{
    
    public PublicHomePage()
    {
        setBounds(300, 85, WIDTH, HEIGHT);
        setSize(570,500);
        setTitle("Speak Up");
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        
        getContentPane().setBackground(c3);
        
        JPanel MainPanel = new JPanel();
        MainPanel.setBounds(400, 10, 150, 150);
        MainPanel.setBackground(c3);
        MainPanel.setLayout(null);
        
        JLabel lbl_more1 = new JLabel("___");
        JLabel lbl_more2 = new JLabel("___");
        JLabel lbl_more3 = new JLabel("___");
        lbl_more1.setBounds(80, 1, 30, 15);
        lbl_more2.setBounds(80, 6, 30, 15);
        lbl_more3.setBounds(80, 11, 30, 15);
        
        MainPanel.add(lbl_more1);
        MainPanel.add(lbl_more2);
        MainPanel.add(lbl_more3);
        
        JPanel panel = new JPanel();
        panel.setBackground(c3);
        panel.setBounds(30, 25, 120, 130);
        
        JButton btn_profile = new JButton("Profile");
        btn_profile.setBorder(new LineBorder(c2));
        btn_profile.setBackground(c1);
        btn_profile.setForeground(c3);
        btn_profile.setBounds(10, 10, 100, 20);
        btn_profile.addActionListener(e -> {
            dispose();
            PublicProfile p1 = new PublicProfile();
        });
        panel.add(btn_profile);
        
        JButton btn_mycomplaints = new JButton("My Complaints");
        btn_mycomplaints.setBorder(new LineBorder(c2));
        btn_mycomplaints.setBackground(c1);
        btn_mycomplaints.setForeground(c3);
        btn_mycomplaints.setBounds(10, 80, 100, 20);
        btn_mycomplaints.addActionListener(e -> {
            MyComplaints m1 = new MyComplaints();
        });
        panel.add(btn_mycomplaints);
        
        JButton btn_logout = new JButton("Logout");
        btn_logout.setBorder(new LineBorder(c2));
        btn_logout.setBackground(c1);
        btn_logout.setForeground(c3);
        btn_logout.setBounds(10, 150, 100, 20);
        btn_logout.addActionListener(e -> {
            PublicModel.logout();
            dispose();
            WelcomePage w1 = new WelcomePage();
        });
        panel.add(btn_logout);
        
        lbl_more2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(panel.isVisible()){
                    panel.setVisible(false);
                    MainPanel.remove(panel);
                    validate();
                    repaint();
                }
                else {
                    panel.setVisible(true);
                    MainPanel.add(panel);
                    validate();
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setVisible(true);
                MainPanel.add(panel);
                validate();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(MainPanel);
        
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panel.setVisible(false);
                MainPanel.remove(panel);
                validate();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        JLabel lbl_header = new JLabel("Hi, "+PublicModel.name+"!");
        lbl_header.setForeground(c2);
        lbl_header.setBounds(180, 40, 500, 80);
        Font headerfont = new Font("serif", Font.BOLD, 25);
        lbl_header.setFont(headerfont);
        add(lbl_header);
        
        JButton btn_ginfo = new JButton("Show Announcements");
        btn_ginfo.setBorder(new LineBorder(c2));
        btn_ginfo.setBackground(c1);
        btn_ginfo.setForeground(c3);
        btn_ginfo.setBounds(180, 180, 170, 40);
        add(btn_ginfo);
        
        JButton btn_complaint = new JButton("Make Complaint");
        btn_complaint.setBorder(new LineBorder(c2));
        btn_complaint.setBackground(c1);
        btn_complaint.setForeground(c3);
        btn_complaint.setBounds(190, 300, 150, 40);
        add(btn_complaint);
        
        btn_ginfo.addActionListener(e -> {
                PublicInformation l1 = new PublicInformation();
        });
        
        btn_complaint.addActionListener(e -> {
                PublicComplaints l2 = new PublicComplaints();
        });
        
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }
}
