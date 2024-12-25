package cs.vsu.ru.goryacheva.task8;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;


public class FileReader {

    public static int[][] readInputFile(File file) {
        try {Scanner scanner = new Scanner(file);

        int col = scanner.nextInt();
        int rows = scanner.nextInt();
        int[][] x = new int[col][rows];

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < rows; j++) {
                x[i][j] = scanner.nextInt();
            }
         }
        return x;
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
}}

