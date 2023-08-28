/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studymanagementapp;

/**
 *
 * @author nbui6
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Activity extends JFrame implements ActionListener {

    public static int count = 0;
    public static String hourString;
    JLabel wlc = new JLabel("");
    JLabel nameJLabel = new JLabel();
    JLabel workh = new JLabel();

    Container container = getContentPane();
    JLabel bgImage = new JLabel(new ImageIcon("D:\\StudyManagementApp\\src\\img\\homebackground.jpg"));

    JButton back = new JButton("Back");

    public static String user;

    public Activity(String user) {

        this.user = user;
        this.setTitle("FocusFlow - Activity Log");
        this.setBounds(300, 60, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.setLayout(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\StudyManagementApp\\src\\img\\logo.png"));

        String path = "D:\\StudyManagementApp\\src\\user\\workh.txt";
        try {
            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String line, workuser, workhour;
            while ((line = br.readLine()) != null) {
                workuser = line.split(" ")[0];
                workhour = line.split(" ")[1];

                if (workuser.equals(user)) {
                    hourString = workhour;
                }

            }

        } catch (Exception e) {
            System.out.println("FILE NOT FOUND - WorkH");
        }

        Initialize();
        addActionEvent();

    }

    private void Initialize() {
        
        wlc = new JLabel("Activity Log");
        wlc.setForeground(new Color(5,40,48));
        wlc.setFont(new Font("Dosis SemiBold", Font.BOLD, 50));
        wlc.setHorizontalAlignment(SwingConstants.CENTER);
        wlc.setVerticalAlignment(SwingConstants.CENTER);
        wlc.setBorder(BorderFactory.createLineBorder(new Color(182,103,219), 3));
        wlc.setBounds(130, 50, 450, 100);
        container.add(wlc);

        nameJLabel.setText(String.valueOf(user) + "'s Record: ");
        nameJLabel.setBounds(120, 150, 400, 100);
        nameJLabel.setForeground(Color.white);
        nameJLabel.setFont(new Font("Dosis SemiBold", Font.BOLD, 25));
        container.add(nameJLabel);

        workh.setText("Your total working minutes : " + String.valueOf(hourString));
        workh.setBounds(120, 250, 400, 100);
        workh.setForeground(Color.white);
        workh.setFont(new Font("Dosis SemiBold", Font.BOLD, 25));
        container.add(workh);

        back.setBounds(300, 400, 78, 40);
        back.setBorderPainted(false);
        back.setBackground(Color.WHITE);
        back.setForeground(new Color(159, 89, 155));
        back.setFont(new Font("San Francisco", Font.BOLD, 18));
        container.add(back);

        bgImage.setBounds(0, 0, 700, 600);
        bgImage.setOpaque(true);
        container.add(bgImage);

    }

    private void addActionEvent() {
        back.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.setVisible(false);
            HomePage homePage = new HomePage(user);
            homePage.setVisible(true);
        }
    }

}
