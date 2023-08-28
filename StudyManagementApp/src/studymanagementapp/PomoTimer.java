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
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class PomoTimer extends JFrame implements ActionListener {

    public static String user;
    public static int count = 0, index = 0, in = 0;
    public static String[] taskstring = new String[100];
    public static String[][] workh = new String[100][2];
    Container container = getContentPane();
    JLabel bgImage = new JLabel(new ImageIcon("D:\\StudyManagementApp\\src\\img\\timerbg.png"));
    JLabel time = new JLabel("00:00");
    JLabel c = new JLabel("Pomodoro Count: 0");
    JLabel taskname = new JLabel("");

    JButton back = new JButton("Back");
    JButton startbut = new JButton("Start");
    JButton endbut = new JButton("Mark As Done");

    Timer t;

    public PomoTimer(String user) {
        this.user = user;
        this.setTitle("FocusFlow - Pomodoro Timer");
        this.setBounds(300, 60, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.setLayout(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\StudyManagementApp\\src\\img\\logo.png"));

        
        try {
            String path = "D:\\StudyManagementApp\\src\\user\\" + user + ".txt";
            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                taskstring[index] = line;
                //System.out.println(taskstring[index]);
                index++;

            }

        } catch (Exception e) {
            System.out.println("FILE NOT FOUND");
        }

        Initialize();
        addActionEvent();

    }

    private void Initialize() {

        taskname.setText("TASK NAME: " + String.valueOf(taskstring[in]));

        taskname.setBounds(80, 0, 500, 100);
        taskname.setForeground(Color.white);
        taskname.setFont(new Font("Dosis SemiBold", Font.BOLD, 25));
        taskname.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(taskname);

        time.setBounds(185, 80, 300, 300);
        time.setForeground(Color.black);
        time.setFont(new Font("Dosis SemiBold", Font.BOLD, 50));
        time.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(time);

        c.setBounds(185, 140, 300, 300);
        c.setForeground(Color.black);
        c.setFont(new Font("Dosis SemiBold", Font.BOLD, 20));
        c.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(c);

        startbut.setBounds(100, 400, 160, 40);
        startbut.setBorderPainted(false);
        startbut.setBackground(new Color(159, 89, 155));
        startbut.setForeground(Color.WHITE);
        startbut.setFont(new Font("San Francisco", Font.BOLD, 15));
        container.add(startbut);

        endbut.setBounds(420, 400, 160, 40);
        endbut.setBorderPainted(false);
        endbut.setBackground(new Color(159, 89, 155));
        endbut.setForeground(Color.WHITE);
        endbut.setFont(new Font("San Francisco", Font.BOLD, 15));
        container.add(endbut);

        back.setBounds(305, 500, 78, 40);
        back.setBorderPainted(false);
        back.setBackground(Color.WHITE);
        back.setForeground(new Color(159, 89, 155));
        back.setFont(new Font("San Francisco", Font.BOLD, 14));
        container.add(back);

        bgImage.setBounds(-10, -20, 700, 600);
        bgImage.setOpaque(true);
        container.add(bgImage);

    }

    public void workhour(int circle, int min) {
        int in = 0, tmp;
        String path = "D:\\StudyManagementApp\\src\\user\\workh.txt";
        try {
            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String line, workuser, workhour;

            while ((line = br.readLine()) != null) {
                workuser = line.split(" ")[0];
                workhour = line.split(" ")[1];
                System.out.println(workhour + " " + workuser + " " + user);

                workh[in][0] = workuser;
                if (workuser.equals(user)) {
                    tmp = Integer.parseInt(workhour);
                    tmp += circle + min;
                    workhour = String.valueOf(tmp);

                }
                workh[in][1] = workhour;
                in++;
            }

        } catch (Exception e) {
            System.out.println("FILE NOT FOUND - WorkH");
        }
        System.out.println(in);

        path = "D:\\StudyManagementApp\\src\\user\\workh.txt";
        try {

            FileWriter myWriter = new FileWriter(path, false);
            for (int i = 0; i <= in; i++) {
                myWriter.write(String.valueOf(workh[i][0]) + " " + String.valueOf(workh[i][1]) + "\n");
            }
            myWriter.close();

        } catch (IOException ep) {
            System.out.println("FILE NOT FOUND");
        }
    }

    private void addActionEvent() {
        startbut.addActionListener(this);
        endbut.addActionListener(this);
        back.addActionListener(this);
    }
    int s = 0, h = 0;
    boolean chk = false;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startbut) {
            chk = true;
            t = new Timer(1000, new ActionListener() { // Change the timer delay to 1000 milliseconds (1 second)

                @Override
                public void actionPerformed(ActionEvent e) {
                    s++;

                    if (s == 60) {
                        s = 0;
                        h++;
                          //take a break after 25 minutes
                        if (h == 25) {
                            t.stop();
                            int result = JOptionPane.showConfirmDialog((Component) null, "Take a short Break. Work Done?", "Take A Break", JOptionPane.YES_NO_OPTION);

                            if (result == 1) { // No
                                count++;
                                h = 0;
                                c.setText("Pomodoro Count: " + count);

                            } else if (result == 0) {
                                in++;
                                taskname.setText("TASK NAME: " + String.valueOf(taskstring[in]));
                                workhour(count, h);
                                h = 0;
                                count = 0;
                                c.setText("Pomodoro Count: " + count);
                            }
                        }
                    }

                    time.setText(String.format("%02dm:%02ds", h, s)); // Format the time display

                }

            });

            t.start();

        }
        if (e.getSource() == endbut) {
            if (chk == true) {
                t.stop();
            }
            in++;

            workhour(count, h);

            taskname.setText("TASK NAME:" + String.valueOf(taskstring[in]));
            s = h = count = 0;
            c.setText("Pomodoro Count: " + count);
            time.setText(String.valueOf(h + "m:" + s + "s"));

        }

        if (e.getSource() == back) {
            this.setVisible(false);
            HomePage homePage = new HomePage(user);
            homePage.setVisible(true);
        }

    }

}
