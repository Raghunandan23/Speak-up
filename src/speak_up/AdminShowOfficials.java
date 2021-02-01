package speak_up;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class AdminShowOfficials extends JFrame {
    
    JPanel panel;
    JTable table;
    JScrollPane scrl;
    JComboBox cb_city, cb_sector;
    
    Color c1=new Color(50, 105, 168);
    Color c2=new Color(0,0,0);
    Color c3=new Color(255, 255, 255);
    
    AdminShowOfficials() 
    {
        setSize(570, 400);
        setBounds(300, 85, 570, 400);
        setTitle("Speak Up");
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(c3);
        
        JLabel lbl_head = new JLabel("Government Officials");
        lbl_head.setBounds(170, 20, 250, 30);
        lbl_head.setFont(new Font("serif", Font.BOLD, 22));
        panel.add(lbl_head);
        
        String[] sectors = {"--Select Sector--","Corporation", "Electricity", "Water"};
        cb_sector = new JComboBox(sectors);
        cb_sector.setBorder(new LineBorder(c2));
        cb_sector.setBackground(c3);
        cb_sector.setForeground(c2);
        cb_sector.setBounds(70, 80, 150, 30);
        cb_sector.addActionListener( e -> {
                String sector = cb_sector.getSelectedItem().toString();
                try{
                    String query1;
                    if(cb_sector.getSelectedIndex() >= 1){
                        if(cb_city.getSelectedIndex() == 0)
                            query1 = "select * from govt_servant where sector='" + sector + "'";
                        else{
                            String city = cb_city.getSelectedItem().toString();
                            query1 = "select * from govt_servant where sector='"+sector+"' and city='"+city+"'";
                                    
                        }
                    }
                    else{
                        if(cb_city.getSelectedIndex() == 0)
                            query1 = "select * from govt_servant";
                        else{
                            String city = cb_city.getSelectedItem().toString();
                            query1 = "select * from govt_servant where city='"+city+"'";
                        }
                    }
                    Connection con = DbConnection.getConnection();
                    Statement stmt1 = con.createStatement();
                    ResultSet rs1 = stmt1.executeQuery(query1);
                    panel.remove(scrl);
                    showTable(rs1);
                } catch(SQLException ex){
                    System.out.println("Exception occurred");
                }
        });
        
        String[] cities = {"--Select City--","City 1", "City 2"};

        cb_city = new JComboBox(cities);
        cb_city.setBorder(new LineBorder(c2));
        cb_city.setBackground(c3);
        cb_city.setForeground(c2);
        cb_city.setBounds(320, 80, 150, 30);
        cb_city.addActionListener( e -> {
            String city = cb_city.getSelectedItem().toString();
            try{
                String query1;
                if(cb_city.getSelectedIndex() >= 1){
                    if(cb_sector.getSelectedIndex() == 0)
                        query1 = "select * from govt_servant where city='" + city + "'";
                    else{
                        String sector = cb_sector.getSelectedItem().toString();
                        query1 = "select * from govt_servant where sector='"+sector+"' and city='"+city+"'";
                    }
                }
                else{
                    if(cb_sector.getSelectedIndex() == 0)
                        query1 = "select * from govt_servant";
                    else{
                        String sector = cb_sector.getSelectedItem().toString();
                        query1 = "select * from govt_servant where sector='"+sector+"'";
                    }
                }
                Connection con = DbConnection.getConnection();
                Statement stmt1 = con.createStatement();
                ResultSet rs1 = stmt1.executeQuery(query1);
                panel.remove(scrl);
                showTable(rs1);
            } catch(SQLException ex){
                System.out.println("Exception occurred");
            }
        });
        
        panel.add(cb_sector);
        panel.add(cb_city);
        
        try {
            Connection con = DbConnection.getConnection();
            con = DbConnection.getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from govt_servant";
            ResultSet rs = stmt.executeQuery(query);
            showTable(rs);
        } catch(SQLException ex) {
            System.out.println("Exception occurred  "+ex);
        }
        
        JButton btn_back = new JButton("Back");
        btn_back.setBorder(new LineBorder(c2));
        btn_back.setBackground(c1);
        btn_back.setForeground(c3);
        btn_back.setBounds(220, 320, 100, 30);
        btn_back.addActionListener(e -> {
            dispose();
        });
        add(btn_back);
        add(panel);

        setResizable(false);
        setVisible(true);
        setLayout(null);

       
    }
    void showTable(ResultSet rs) {
        
        Vector<String> col = new Vector<>();
        col.add("Name");
        col.add("Phone");
        col.add("Sector");
        col.add("City");

        Vector<Vector> arr = new Vector<>();
        try {
            while(rs.next()) {
                Vector<String> list = new Vector<>();
                list.add(rs.getString("name"));
                list.add(rs.getString("phone"));
                list.add(rs.getString("sector"));
                list.add(rs.getString("city"));
                arr.add(list);
            }
        } catch(SQLException ex) {
            System.out.println("Exception occurred "+ex);
        }

        table = new JTable(arr, col);
        table.setBackground(c3);
        table.setEnabled(false);
        scrl = new JScrollPane(table);    
        scrl.setBounds(20, 150, 500, 110);
        panel.add(scrl);
        panel.setBackground(c3);

        validate();
        repaint();
            
    }
}