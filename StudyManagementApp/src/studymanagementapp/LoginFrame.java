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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();

    JLabel appLabel = new JLabel("FocusFlow");
    JLabel userlbl = new JLabel("Username: ");
    JLabel passlbl = new JLabel("Password: ");
    JLabel newlbl = new JLabel("New Here?");

    JLabel bgImage = new JLabel(new ImageIcon("D:\\StudyManagementApp\\src\\img\\homebackground.jpg"));

    JTextField user = new JTextField();
    JPasswordField pass = new JPasswordField();

    JButton loginbut = new JButton("Sign In");
    JButton signbut = new JButton("Sign Up");

    public LoginFrame() {

        this.setTitle("FocusFlow - Study Management Application");
        this.setBounds(300, 50, 700, 600);
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\StudyManagementApp\\src\\img\\logo.png"));

        initializePage();
        addActionEvent();

    }

    private void initializePage() {
        container.setLayout(null);

        appLabel.setForeground(new Color(5,40,48));
        appLabel.setFont(new Font("Arial Black", Font.BOLD, 80));
        appLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appLabel.setVerticalAlignment(SwingConstants.CENTER);
        appLabel.setBorder(BorderFactory.createLineBorder(new Color(182,103,219), 3));
        appLabel.setBounds(80, 50, 550, 100);
        container.add(appLabel);

        userlbl.setBounds(100, 180, 200, 100);
        userlbl.setForeground(new Color(255,255,255));
        userlbl.setFont(new Font("San Francisco", Font.BOLD, 22));
        container.add(userlbl);

        user.setBounds(250, 220, 300, 30);
        user.setBackground(new Color(234, 246, 206));
        user.setFont(new Font("Arial Black", Font.BOLD, 22));
        container.add(user);

        passlbl.setBounds(100, 230, 200, 100);
        passlbl.setForeground(new Color(255,255,255));
        passlbl.setFont(new Font("San Francisco", Font.BOLD, 22));
        container.add(passlbl);

        pass.setBounds(250, 268, 300, 30);
        pass.setBackground(new Color(234, 246, 206));
        pass.setFont(new Font("Arial Black", Font.BOLD, 22));
        container.add(pass);

        loginbut.setBounds(280, 350, 150, 35);
        loginbut.setHorizontalTextPosition(SwingConstants.CENTER);
        loginbut.setVerticalTextPosition(SwingConstants.CENTER);
        loginbut.setFont(new Font("San Francisco", Font.BOLD, 22));
        loginbut.setBorderPainted(false);
        loginbut.setBackground(new Color(182, 103, 219));
        loginbut.setForeground(Color.WHITE);
        container.add(loginbut);

        newlbl.setBounds(220, 430, 200, 100);
        newlbl.setForeground(new Color(255, 255, 255));
        newlbl.setFont(new Font("San Francisco", Font.BOLD, 18));
        container.add(newlbl);

        signbut.setBounds(335, 463, 150, 35);
        signbut.setHorizontalTextPosition(SwingConstants.CENTER);
        signbut.setVerticalTextPosition(SwingConstants.CENTER);
        signbut.setFont(new Font("San Francisco", Font.BOLD, 22));
        signbut.setBorderPainted(false);
        signbut.setBackground(Color.WHITE);
        signbut.setForeground(new Color(182, 103, 219));
        container.add(signbut);

        // bgImage
        bgImage.setBounds(0, 0, 700, 600);
        bgImage.setHorizontalAlignment(SwingConstants.CENTER);
        bgImage.setOpaque(true);
        container.add(bgImage);

    }

    private void addActionEvent() {
        loginbut.addActionListener(this);
        signbut.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginbut) {

            String userText;
            String passText;

            userText = user.getText();
            passText = pass.getText();

            try {
                String path = "D:\\StudyManagementApp\\src\\user\\user.txt";

                FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr);

                boolean isLoginSuccess = false;
                int u = 0;
                String line, fuserEmail, fpass, fuserID;

                while ((line = br.readLine()) != null) {
                    fuserEmail = line.split(" ")[2];
                    fpass = line.split(" ")[3];

                    fuserID = fuserEmail;

                    if (fuserID.equals(userText) && fpass.equals(passText)) {
                        isLoginSuccess = true;
                        System.out.println("SUCCESS");
                        this.setVisible(false);

                        HomePage dashboard = new HomePage(fuserEmail.split("@")[0]);
                        dashboard.setVisible(true);

                        break;
                    } else if (fuserID.equalsIgnoreCase(userText) || fuserEmail.equalsIgnoreCase(userText)) {
                        u++;
                    }
                }
                if (!isLoginSuccess) {
                    if (u > 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Password!", "WARNING!!",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid User!", "WARNING!!", JOptionPane.WARNING_MESSAGE);
                    }
                }

                fr.close();

            } catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");

            }
        }

        if (e.getSource() == signbut) {
            this.setVisible(false);
            SignUpFrame s = new SignUpFrame();
            s.setVisible(true);
        }

    }

}
