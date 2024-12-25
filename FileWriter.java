package cs.vsu.ru.goryacheva.task8;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FileWriter {

    public static void writeOutputFile(File file, int[][] matrix) {
        try {
            StringBuilder sb = new StringBuilder();


            sb.append(matrix.length).append(' ').append(matrix[0].length).append('\r').append('\n');


            for (int[] row : matrix) {
                for (int value : row) {
                    sb.append(value).append(' ');
                }

                sb.append('\r');
                sb.append('\n');
            }


            Files.write(file.toPath(), sb.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}