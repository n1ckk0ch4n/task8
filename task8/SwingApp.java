package cs.vsu.ru.goryacheva.task8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.io.*;






public class SwingApp {
         public static int MaxTab=10;
         public static int[][] data = new int[MaxTab][MaxTab];
         public static int[][] data1 = new int[MaxTab][MaxTab];
         public static String filePath;
    public static void App() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(700, 700);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object[][] rowData = new Object[MaxTab][MaxTab];
        Object[] columnNames = new Object[MaxTab];

        for (int i = 0; i < MaxTab; i++) {
            for (int j = 0; j < MaxTab; j++) {
                rowData[i][j] = " ";
            }   columnNames[i]  = " ";
        }



        JTable table = new JTable(rowData, columnNames);

        JButton button1 = new JButton("Считать файл");
        JButton button2 = new JButton("Найти дружественные элементы");
        JButton button3 = new JButton("Записать файл");


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(table, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        jFrame.add(mainPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(jFrame, "Открыть файл", FileDialog.LOAD);
                fileDialog.setVisible(true);

                if (fileDialog.getFile() != null) {
                    filePath = fileDialog.getDirectory() + fileDialog.getFile();
                    File file = new File(filePath);

                    data = FileReader.readInputFile(file);
                    if (data != null) {
                        updateTable(data, rowData);
                        table.updateUI();

                    }
                }
            }
        });


        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                data1=FindFriendlyElements.solution(data);
                updateTable(data1, rowData);
                table.updateUI();
                System.out.println("Дружественные элементы найдены и записаны в таблицу'");

            }
        });


        button3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String filePath1=filePath .replaceFirst("input", "output");
                File file = new File(filePath1);
                FileWriter.writeOutputFile(file,data1);
                System.out.println("== Файл записан ==  " + filePath1 );

            }
        });


        jFrame.setVisible(true);
    }
    private static void updateTable(int[][] data, Object rowData[][]) {
        int rows = data.length;
        int col= data[0].length;
        for (int i = 0; i < MaxTab; i++) {
            for (int j = 0; j < MaxTab ; j++) {
                rowData[i][j]=(i<rows&&j<col)?Integer.toString(data[i][j]):" ";
             }
        }
    }
}
