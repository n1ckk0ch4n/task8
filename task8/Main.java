package cs.vsu.ru.goryacheva.task8;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
             SwingApp.App();
        } else {
            String inputFile = null;
            String outputFile = null;

            for (String arg : args) {
                if (arg.startsWith("--input-file=")) {
                    inputFile = arg.substring(13);
                } else if (arg.startsWith("--output-file=")) {
                    outputFile = arg.substring(14);
                }
            }

            if (inputFile == null || outputFile == null) {
                System.err.println("Не все параметры переданы корректно!");
                printUsage();
                System.exit(1);
            }

            System.out.println("Input file: " + inputFile);
            System.out.println("Output file: " + outputFile);


            if (inputFile != null && outputFile != null) {
                File input = new File(inputFile);
                if (!input.exists()) {
                    System.err.println("Ошибка: Входной файл не существует. "+inputFile);
                    printUsage();
                    return;
                }


                int[][] matrix = FileReader.readInputFile(input);

                if (matrix == null) {
                    System.err.println("Ошибка: Файл пуст или имеет неверный формат.");
                    printUsage();
                    return;
                }


                System.out.println("Исходный массив:");
                printMatrix(matrix);


                int[][] transformedMatrix = FindFriendlyElements.solution(matrix);
                System.out.println("Преобразованный массив:");
                printMatrix(transformedMatrix);

                FileWriter.writeOutputFile(new File(outputFile), transformedMatrix);

                System.out.println("Преобразованный массив записан в файл " + outputFile);
            } else {
                System.err.println("Ошибка: Не указаны обязательные параметры.");
                printUsage();
            }
        }
    }
    private static void printUsage() {
        System.out.println("Использование: java CommandLineArgumentsParser [параметры]");
        System.out.println("Обязательные параметры:");
        System.out.println("\t--input-file=<файл> Указать входной файл");
        System.out.println("\t--output-file=<файл> Указать выходной файл");
    }
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}