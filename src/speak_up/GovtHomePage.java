package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GovtHomePage extends JFrame{
    
    public GovtHomePage()
    {
        setBounds(300, 85, WIDTH, HEIGHT);
        setSize(570, 500);
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
        panel.setBounds(40, 25, 100, 110);
        
        JButton btn_profile = new JButton("Profile");
        btn_profile.setBorder(new LineBorder(c2));
        btn_profile.setBackground(c1);
        btn_profile.setForeground(c3);
        btn_profile.setBounds(10, 10, 100, 20);
        btn_profile.addActionListener(e -> {
            GovtProfile p1 = new GovtProfile();
        });
        panel.add(btn_profile);
        
        JButton btn_mycomplaints = new JButton("View Info(s)");
        btn_mycomplaints.setBorder(new LineBorder(c2));
        btn_mycomplaints.setBackground(c1);
        btn_mycomplaints.setForeground(c3);
        btn_mycomplaints.setBounds(10, 80, 100, 20);
        btn_mycomplaints.addActionListener(e -> {
            MyInformations m1 = new MyInformations();
        });
        panel.add(btn_mycomplaints);
        
        JButton btn_logout = new JButton("Logout");
        btn_logout.setBorder(new LineBorder(c2));
        btn_logout.setBackground(c1);
        btn_logout.setForeground(c3);
        btn_logout.setBounds(10, 150, 100, 20);
        btn_logout.addActionListener(e -> {
            GovtModel.logout();
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
       
        JLabel lbl_header = new JLabel("Hi, "+GovtModel.name+"!");
        lbl_header.setBounds(200, 40, 500, 80);
        Font headerfont = new Font("serif", Font.BOLD,25);
        lbl_header.setFont(headerfont);
        add(lbl_header);
        
        JButton btn_pinfo = new JButton("Post Information");
        btn_pinfo.setBorder(new LineBorder(c2));
        btn_pinfo.setBackground(c1);
        btn_pinfo.setForeground(c3);
        btn_pinfo.setBounds(200, 180, 150, 50);
        btn_pinfo.addActionListener(e-> {
            GovtInformation g1 = new GovtInformation();
        });
        add(btn_pinfo);
        
        JButton btn_vcomplaints = new JButton("View Complaints");
        btn_vcomplaints.setBorder(new LineBorder(c2));
        btn_vcomplaints.setBackground(c1);
        btn_vcomplaints.setForeground(c3);
        btn_vcomplaints.setBounds(200, 300, 150, 50);
        add(btn_vcomplaints);
        
        btn_vcomplaints.addActionListener(e -> {
            GovtComplaints l2 = new GovtComplaints();
        });
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
       
    }
}
