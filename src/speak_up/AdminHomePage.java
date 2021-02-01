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

public class AdminHomePage extends JFrame{
    
    AdminHomePage() 
    {
        setTitle("Speak Up");
        setSize(500, 500);
        setBounds(300, 85, 500, 500);
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        
        getContentPane().setBackground(c3);
        
        JPanel MainPanel = new JPanel();
        MainPanel.setBackground(c3);
        MainPanel.setBounds(330, 30, 150, 150);
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
        panel.setBounds(50, 25, 80, 200);
        
        JButton btn_addAdmin = new JButton("Add Admin");
        btn_addAdmin.setBorder(new LineBorder(c2));
        btn_addAdmin.setBackground(c1);
        btn_addAdmin.setForeground(c3);
        btn_addAdmin.setBounds(190, 250, 100, 30);
        btn_addAdmin.addActionListener(e -> {
            RegisterAdmin r1 = new RegisterAdmin();
        });
        panel.add(btn_addAdmin);
        
        
        JButton btn_logout = new JButton("Logout");
        btn_logout.setBorder(new LineBorder(c2));
        btn_logout.setBackground(c1);
        btn_logout.setForeground(c3);
        btn_logout.setBounds(10, 150, 100, 20);
        btn_logout.addActionListener(e -> {
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
        
        JLabel lbl_header = new JLabel("HI! ADMIN");
        lbl_header.setBounds(180,100, 150, 30);
        lbl_header.setForeground(c2);
        lbl_header.setFont(new Font("serif", Font.BOLD, 25));
        add(lbl_header);
        
        JButton btn_registerGovt = new JButton("Register Government Official");
        btn_registerGovt.setBorder(new LineBorder(c2));
        btn_registerGovt.setBackground(c1);
        btn_registerGovt.setForeground(c3);
        btn_registerGovt.setBounds(120, 180, 250, 30);
        btn_registerGovt.addActionListener(e -> {
            RegistrationGovtServant g1 = new RegistrationGovtServant();
            
        });
        add(btn_registerGovt);
        
        JButton btn_viewOfficials = new JButton("View Governtment Officials");
        btn_viewOfficials.setBorder(new LineBorder(c2));
        btn_viewOfficials.setBackground(c1);
        btn_viewOfficials.setForeground(c3);
        btn_viewOfficials.setBounds(120, 230, 250, 30);
        btn_viewOfficials.addActionListener(e -> {
            AdminShowOfficials a3 = new AdminShowOfficials();
        });
        add(btn_viewOfficials);
        
        JButton btn_viewComplaints = new JButton("View Complaints");
        btn_viewComplaints.setBorder(new LineBorder(c2));
        btn_viewComplaints.setBackground(c1);
        btn_viewComplaints.setForeground(c3);
        btn_viewComplaints.setBounds(120, 280, 250, 30);
        btn_viewComplaints.addActionListener(e -> {
            AdminComplaints a2 = new AdminComplaints();
        });
        add(btn_viewComplaints);
        
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
}