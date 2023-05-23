package pl.quizmemory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EditFileGUI {

    public static JPanel filenamePanel;
    public static JPanel editFileContentPanel;
    public static String fileName;
    public static JTextArea changeFileTextArea;

    public static void filenameGUI(){ //moze da sie to zrobic tak zeby tworzyc znowu takiej funkcji tylko uzyc jej z innej klasy

        JLabel title=new JLabel();
        title.setText("Add content to a file");
        //title.setHorizontalTextPosition(JLabel.CENTER);
        //title.setVerticalTextPosition(JLabel.TOP);
        title.setForeground(new Color(255,255,255));
        title.setFont(new Font("Arial",Font.PLAIN,40));
        //title.setVerticalAlignment(JLabel.CENTER);
        //title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(450,50,400,100);

        JLabel text1=new JLabel();
        text1.setText("Enter file name:");
        text1.setHorizontalTextPosition(JLabel.CENTER);
        //text1.setVerticalTextPosition(JLabel.TOP);
        text1.setForeground(new Color(255,255,255));
        text1.setFont(new Font("Arial",Font.PLAIN,25));
        text1.setBounds(500,300,200,100);

        JButton buttonFile=MenuGUI.setButton(550,500,100,50);
        buttonFile.setText("Submit");

        JTextField textField = new JTextField();
        //textField.setPreferredSize(new Dimension(250,40));
        textField.setBounds(500,400,200,50);
        textField.setFont(new Font("Arial",Font.PLAIN,20));
        buttonFile.addActionListener(e-> {
            try {
                openFile(e,textField,buttonFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        filenamePanel=new MenuGUI.Panel();
        filenamePanel.setLayout(null);
        filenamePanel.add(title);
        filenamePanel.add(text1);
        filenamePanel.add(textField);
        filenamePanel.add(buttonFile);

        MenuGUI.menuPanel.setVisible(false);
        MenuGUI.mainFrame.add(filenamePanel);
    }

    public static void editContentFileGUI() {
        JLabel title = new JLabel();
        title.setText("Edit input file");
        title.setForeground(new Color(255, 255, 255));
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setBounds(500, 50, 300, 100);

        changeFileTextArea = new JTextArea();
        changeFileTextArea.setBackground(new Color(255, 255, 255));
        changeFileTextArea.setForeground(new Color(0, 0, 0));
        changeFileTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
        changeFileTextArea.setLineWrap(true);
        String fileContent = readFileContent();
        changeFileTextArea.setText(fileContent);
        changeFileTextArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(changeFileTextArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(250, 200, 700, 500);

        JButton buttonSubmit = MenuGUI.setButton(550, 750, 100, 50);
        buttonSubmit.setText("Submit");

        buttonSubmit.addActionListener(e -> {
            try {
                changeText(e, changeFileTextArea, buttonSubmit);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton buttonExit=MenuGUI.setButton(50,50,100,50);
        buttonExit.setText("Return");
        buttonExit.addActionListener(e-> returnMenu());

        editFileContentPanel = new MenuGUI.Panel();
        editFileContentPanel.setLayout(null);
        editFileContentPanel.add(title);
        editFileContentPanel.add(scrollPane);
        editFileContentPanel.add(buttonSubmit);
        editFileContentPanel.add(buttonExit);

        MenuGUI.mainFrame.add(editFileContentPanel);
    }

    public static void returnMenu()
    {
        editFileContentPanel.setVisible(false);
        MenuGUI.menuPanel.setVisible(true);
    }

    public static void openFile(ActionEvent e, JTextField textField, JButton buttonFile) throws IOException {
        if(e.getSource()==buttonFile){
            fileName=textField.getText();
            filenamePanel.setVisible(false);
            editContentFileGUI();
        }
    }

    public static String readFileContent() {
        try {
            File file = new File(CreateFile.checkFileName(fileName));
            String filePath = file.getAbsolutePath();
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void changeText(ActionEvent e, JTextArea textArea, JButton button) throws IOException {
        if (e.getSource() == button) {
            File file = new File(CreateFile.checkFileName(fileName));
            String filePath = file.getAbsolutePath();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(textArea.getText());
            }
        }
    }
}