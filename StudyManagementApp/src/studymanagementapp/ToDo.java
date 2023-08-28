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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ToDo extends JFrame implements ActionListener {
    public static int count = 0;
    JLabel wlc = new JLabel("");
    Container container = getContentPane();
    JLabel bgImage = new JLabel(new ImageIcon("D:\\StudyManagementApp\\src\\img\\homebackground.jpg"));

    JPanel taskPanel = new JPanel();
    JTextField add = new JTextField();
    JButton addButton = new JButton("ADD");
    JButton back = new JButton("Back");
    JButton deleteButton = new JButton("Delete");

    public static String user;

    public ToDo(String user) {
        this.user = user;
        this.setTitle("FocusFLow++ - ToDo");
        this.setBounds(300, 60, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\StudyManagementApp\\src\\img\\logo.png"));

        String path = "D:\\StudyManagementApp\\src\\user\\" + user + ".txt";
        try {
            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                JCheckBox checkBox = new JCheckBox(line);
                checkBox.setFont(new Font("San Francisco", Font.BOLD, 14));
                checkBox.setForeground(Color.BLACK);
                taskPanel.add(checkBox);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("FILE NOT FOUND");
        }

        Initialize();
        addActionEvent();
    }

    private void Initialize() {
        wlc = new JLabel("Create ToDo List");
        wlc.setBounds(170, 0, 350, 100);
        wlc.setForeground(Color.white);
        wlc.setFont(new Font("Dosis SemiBold", Font.BOLD, 40));
        wlc.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(wlc);

        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBounds(45, 100, 600, 300);
        container.add(taskPanel);

        add.setBounds(45, 450, 400, 35);
        container.add(add);

        addButton.setBounds(470, 450, 80, 35);
        addButton.setBorderPainted(false);
        addButton.setBackground(new Color(159, 89, 155));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("San Francisco", Font.BOLD, 15));
        container.add(addButton);

        deleteButton.setBounds(570, 450, 90, 35);
        deleteButton.setBorderPainted(false);
        deleteButton.setBackground(new Color(250, 74, 74));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("San Francisco", Font.BOLD, 15));
        container.add(deleteButton);

        back.setBounds(297, 505, 78, 40);
        back.setBorderPainted(false);
        back.setBackground(Color.WHITE);
        back.setForeground(new Color(159, 89, 155));
        back.setFont(new Font("San Francisco", Font.BOLD, 14));
        container.add(back);

        bgImage.setBounds(0, 0, 700, 600);
        bgImage.setOpaque(true);
        container.add(bgImage);
    }

    private void addActionEvent() {
        addButton.addActionListener(this);
        back.addActionListener(this);
        deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String path = "D:\\StudyManagementApp\\src\\user\\" + user + ".txt";
            try {
                FileWriter myWriter = new FileWriter(path, true);
                myWriter.write(add.getText() + "\n");
                myWriter.close();
                JOptionPane.showMessageDialog(null, "Successfully Added!", "Confirmation",
                        JOptionPane.WARNING_MESSAGE);
            } catch (IOException ep) {
                System.out.println("FILE NOT FOUND");
            }
            this.setVisible(false);
            ToDo a = new ToDo(user);
            a.setVisible(true);
        }
        if (e.getSource() == back) {
            this.setVisible(false);
            HomePage homePage = new HomePage(user);
            homePage.setVisible(true);

        }
        if (e.getSource() == deleteButton) {
            for (int i = 0; i < taskPanel.getComponentCount(); i++) {
            JCheckBox checkBox = (JCheckBox) taskPanel.getComponent(i);
            if (checkBox.isSelected()) {
                String task = checkBox.getText();
                taskPanel.remove(i);
                i--;
                // Remove task from the user file
                removeTaskFromFile(task);
            }
        }
        taskPanel.revalidate();
        taskPanel.repaint();
        }
    }

    private void removeTaskFromFile(String task) {
        String path = "D:\\StudyManagementApp\\src\\user\\" + user + ".txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            StringBuilder content = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals(task)) {
                    content.append(line).append("\n");
                }
            }
            br.close();

            FileWriter writer = new FileWriter(path);
            writer.write(content.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Error occurred while removing task from file.");
        }
    }
}
