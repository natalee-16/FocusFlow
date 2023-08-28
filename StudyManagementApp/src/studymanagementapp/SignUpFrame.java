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
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignUpFrame extends JFrame implements ActionListener {

    String path = "D:\\StudyManagementApp\\src\\user\\user.txt";

    Container sContainer = getContentPane();

    JLabel bgImage = new JLabel(new ImageIcon("D:\\StudyManagementApp\\src\\img\\signupbg.jpg"));

    JTextField fnTextField = new JTextField();
    JTextField lnTextField = new JTextField();
    JTextField idTextField = new JTextField();

    JLabel fname = new JLabel("First Name: ");
    JLabel lname = new JLabel("Last Name: ");
    JLabel username = new JLabel("Username: ");
    JLabel pass = new JLabel("Password: ");

    JLabel reg = new JLabel("Register");

    JPasswordField passwordField = new JPasswordField();

    JButton regButton = new JButton("Create Account");
    JButton loginButton = new JButton("Back");

    public SignUpFrame() {
        this.setTitle("FocusFlow - Sign Up");
        this.setBounds(300, 60, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\StudyManagementApp\\src\\img\\logo.png"));

        Initialize();
        addActionEvent();
    }

    public void Initialize() {
        sContainer.setLayout(null);

        reg.setBounds(225, 40, 280, 100);
        reg.setForeground(new Color(159, 89, 155));
        reg.setFont(new Font("Arial Black", Font.BOLD, 50));
        reg.setHorizontalAlignment(SwingConstants.CENTER);
        sContainer.add(reg);

        fname.setBounds(210, 152, 100, 100);
        fname.setForeground(new Color(0, 0, 0));
        fname.setFont(new Font("San Francisco", Font.BOLD, 16));
        sContainer.add(fname);
        fnTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        fnTextField.setBounds(370, 182, 270, 28);
        fnTextField.setToolTipText("First name");
        fnTextField.setFont(new Font("San Francisco", Font.PLAIN, 17));
        sContainer.add(fnTextField);

        lname.setBounds(210, 212, 100, 100);
        lname.setForeground(new Color(0, 0, 0));
        lname.setFont(new Font("San Francisco", Font.BOLD, 16));
        sContainer.add(lname);
        lnTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        lnTextField.setBounds(370, 242, 270, 28);
        lnTextField.setToolTipText("Last name");
        lnTextField.setFont(new Font("San Francisco", Font.PLAIN, 17));
        sContainer.add(lnTextField);

        username.setBounds(210, 270, 100, 100);
        username.setForeground(new Color(0, 0, 0));
        username.setFont(new Font("San Francisco", Font.BOLD, 16));
        sContainer.add(username);
        idTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        idTextField.setBounds(370, 300, 270, 28);
        idTextField.setToolTipText("Username");
        idTextField.setFont(new Font("San Francisco", Font.PLAIN, 17));
        sContainer.add(idTextField);

        pass.setBounds(210, 330, 100, 100);
        pass.setForeground(new Color(0, 0, 0));
        pass.setFont(new Font("San Francisco", Font.BOLD, 16));
        sContainer.add(pass);
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        passwordField.setBounds(370, 362, 270, 28);
        passwordField.setToolTipText("Password");
        passwordField.setFont(new Font("San Francisco", Font.PLAIN, 17));
        sContainer.add(passwordField);

        regButton.setBounds(397, 425, 200, 37);
        regButton.setBorderPainted(false);
        regButton.setBackground(new Color(159, 89, 155));
        regButton.setForeground(Color.WHITE);
        regButton.setFont(new Font("San Francisco", Font.BOLD, 18));
        sContainer.add(regButton);

        loginButton.setBounds(460, 490, 78, 40);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(159, 89, 155));
        loginButton.setFont(new Font("San Francisco", Font.BOLD, 18));
        sContainer.add(loginButton);

        bgImage.setBounds(0, 0, 800, 585);
        bgImage.setHorizontalAlignment(SwingConstants.CENTER);
        bgImage.setOpaque(true);
        sContainer.add(bgImage);

    }

    private void addActionEvent() {
        loginButton.addActionListener(this);
        regButton.addActionListener(this);
    }

    public boolean check(String email) {
        String line;
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                if (email.split("@")[0].equalsIgnoreCase(line.split(" ")[2].split("@")[0])) {
                    return true;
                }
            }
        } catch (Exception ep) {
            System.out.println("ERROR 404! File-Not-Found");
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
 
        boolean done = false;
        if (e.getSource() == loginButton) {
            this.setVisible(false);
            LoginFrame l = new LoginFrame();
            l.setVisible(true);
        }
        if (e.getSource() == regButton) {
            try {
                if (!check(idTextField.getText())) {
                    done = true;
                    FileWriter myWriter = new FileWriter(path, true);
                    myWriter.write(fnTextField.getText() + " " + lnTextField.getText() + " " + idTextField.getText() + " " + passwordField.getText() + "\n");
                    myWriter.close();
                    JOptionPane.showMessageDialog(null, "Successfully Registered! Please Login to continue...", "Confirmation", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Username already in use!", "Confirmation", JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
            }

            if (done == true) {

                path = "D:\\StudyManagementApp\\src\\user\\workh.txt";
                try {
                    String email = idTextField.getText();
                    System.out.println(email);
                    FileWriter Writer = new FileWriter(path, true);
                    Writer.write(email.split("@")[0] + " " + 0 + "\n");
                    Writer.close();
                } catch (IOException epp) {
                    System.out.println("File-Not-Found");
                }

            }

        }

    }

}
