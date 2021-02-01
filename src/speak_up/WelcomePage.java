package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class WelcomePage extends JFrame {

    public WelcomePage() 
    {
        setTitle("Speak Up");
        setSize(570, 500);
        setBounds(300, 85, 570, 500);
        
        Font headerfont = new Font("Itc New Baskerville",Font.BOLD,13);
        
        Color c1=new Color(50, 105, 168);
        Color c2=new Color(0,0,0);
        Color c3=new Color(255, 255, 255);
        
        JPanel panel_one = new JPanel();
        panel_one.setBackground(c3);
        panel_one.setBounds(0, 0, 570, 500);
        panel_one.setLayout(null);
        
        JPanel MainPanel = new JPanel();
        MainPanel.setBackground(c3);
        MainPanel.setBounds(400, 10, 150, 150);
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
        
        JButton btn_admin = new JButton("Admin Login");
        btn_admin.setBounds(10, 10, 100, 30);
        btn_admin.setBorder(new LineBorder(c2));
        btn_admin.setBackground(c1);
        btn_admin.setForeground(c3);
        btn_admin.addActionListener((ActionEvent e) -> {
            dispose();
            AdminLogin gp1 = new AdminLogin();
        });
        panel.add(btn_admin);
        
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
        panel_one.add(MainPanel);
        
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
        
        JLabel lbl_header = new JLabel("Speak Up");
        lbl_header.setFont (new Font ("TimesRoman", Font.BOLD, 35));
        lbl_header.setForeground(c2);
        lbl_header.setBounds(220, 70, 200, 60);
        panel_one.add(lbl_header);
        
        JLabel lbl_subHeader = new JLabel("Where everyone can speak!");
        lbl_subHeader.setFont(new Font("TimesRoman", Font.ITALIC, 18));
        lbl_subHeader.setForeground(c2);
        lbl_subHeader.setBounds(180, 130, 300, 25);
        panel_one.add(lbl_subHeader);
        
        JButton btn_loginAsUser = new JButton("Login As Public");
        btn_loginAsUser.setBorder(new LineBorder(c2));
        btn_loginAsUser.setBackground(c1);
        btn_loginAsUser.setForeground(c3);
        btn_loginAsUser.setBounds(215, 200, 150, 30);
        btn_loginAsUser.addActionListener((ActionEvent e) -> {
            dispose();
            LoginPage l1 = new LoginPage("Public");
        });
        panel_one.add(btn_loginAsUser);
        
        JButton btn_loginAsGovtServant = new JButton("Login As Government");
        btn_loginAsGovtServant.setBorder(new LineBorder(c2));
        btn_loginAsGovtServant.setBounds(190, 250, 200, 30);
        btn_loginAsGovtServant.setBackground(c1);
        btn_loginAsGovtServant.setForeground(c3);
        btn_loginAsGovtServant.addActionListener((ActionEvent e) -> {
            dispose();
            LoginPage l1 = new LoginPage("Government Official");
        });
        panel_one.add(btn_loginAsGovtServant);
        
        JLabel lbl_newUser = new JLabel("Don't have an account?");
        lbl_newUser.setBounds(220, 350, 200, 20);
        lbl_newUser.setFont(headerfont);
        panel_one.add(lbl_newUser);
        
        JButton btn_signUpAsPublic = new JButton("Sign Up As Public");
        btn_signUpAsPublic.setBorder(new LineBorder(c2));
        btn_signUpAsPublic.setBounds(200, 380, 170, 30);
        btn_signUpAsPublic.setBackground(c1);
        btn_signUpAsPublic.setForeground(c3);
        btn_signUpAsPublic.addActionListener((ActionEvent e) -> {
            dispose();
            RegistrationPublic p1 = new RegistrationPublic();
        });
        panel_one.add(btn_signUpAsPublic);
        
        add(panel_one);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
