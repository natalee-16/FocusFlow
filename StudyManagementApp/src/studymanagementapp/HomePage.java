/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studymanagementapp;

/**
 *
 * @author nbui6
 */
import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel bgImage = new JLabel(new ImageIcon("D:\\StudyManagementApp\\src\\img\\homebackground.jpg"));

    JTextField userTextField = new JTextField();

    JLabel wlc = new JLabel("");

    Icon act = new ImageIcon("D:\\StudyManagementApp\\src\\img\\actlog.jpg");
    JButton actbut = new JButton(act);

    Icon todo = new ImageIcon("D:\\StudyManagementApp\\src\\img\\todo.jpg");
    JButton todobut = new JButton(todo);

    Icon pomo = new ImageIcon("D:\\StudyManagementApp\\src\\img\\timer.jpg");
    JButton pomobut = new JButton(pomo);

    public static String user;

    public HomePage(String user) {
        this.user = user;
        this.setTitle("FocusFlow - HomePage");
        this.setBounds(300, 60, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.setLayout(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\StudyManagementApp\\src\\img\\logo.png"));

        Initialize();
        addActionEvent();
    }

    private void Initialize() {
        wlc = new JLabel("Welcome " + user + " !", SwingConstants.CENTER);

        wlc.setForeground(new Color(5,40,48));
        wlc.setFont(new Font("Dosis SemiBold", Font.BOLD, 50));
        wlc.setHorizontalAlignment(SwingConstants.CENTER);
        wlc.setVerticalAlignment(SwingConstants.CENTER);
        wlc.setBorder(BorderFactory.createLineBorder(new Color(182,103,219), 3));
        wlc.setBounds(130, 50, 450, 100);
        container.add(wlc);

        actbut.setBounds(200, 200, 300, 50);
        container.add(actbut);

        todobut.setBounds(200, 290, 300, 50);
        container.add(todobut);

        pomobut.setBounds(200, 380, 300, 50);
        container.add(pomobut);

        bgImage.setBounds(0, 0, 700, 600);
        bgImage.setOpaque(true);
        container.add(bgImage);
    }

    private void addActionEvent() {
        actbut.addActionListener(this);
        todobut.addActionListener(this);
        pomobut.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actbut) {
            this.setVisible(false);
            Activity a = new Activity(user);
            a.setVisible(true);
        }
        if (e.getSource() == todobut) {
            this.setVisible(false);
            ToDo t = new ToDo(user);
            t.setVisible(true);

        }
        if (e.getSource() == pomobut) {
            this.setVisible(false);
            PomoTimer tm = new PomoTimer(user);
            tm.setVisible(true);

        }
    }
}
