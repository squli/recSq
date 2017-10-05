import java.io.*;
import java.util.ArrayList;

/**
 * Дано N прямоугольников со сторонами, параллельными осям координат. Требуется
 определить площадь фигуры, образованной объединением данных прямоугольников.
 Входные данные
 Входной файл (первый параметр вызова), в котором идет N строк, содержащих по 4 числа: x1,
 y1, x2, y2 - координаты двух противоположных углов прямоугольника. Все координаты –
 целые числа, не превосходящие по абсолютной величине 10 000. (1 <= N <= 100)
 Выходные данные
 В выходной файл (второй параметр вызова) выведите одно целое число – площадь фигуры.
 Дополнительные условия
 Объем используемой памяти не должен превышать 16мб.
 Должна быть проверка на корректность передаваемых параметров (args[]).
 Должна быть проверка input.txt на корректность формата.
 язык программирования  Java
 запуск приложения через
 public static void main(String args[ ])
 args[] 2 параметра: первый - файл входных данных, второй - файл для записи ответа.
 */



/**
 * Created by sq on 28.08.2017.
 */
public class RectangleApplication {

    private static final String FILES_EXT = ".txt";

    private static final int MAX_LINES_COUNT = 100;

    private static String outputFileName = "";
    private static String inputFileName = "";

    private static void debugMessage(final String message) {
        System.out.println(message);
    }

    private static boolean checkArgs(String args[]) {

        if (args.length != 2) {
            debugMessage("Error count of args");
            return false;
        }

        if (!args[0].contains(FILES_EXT) || !args[1].contains(FILES_EXT)) {
            debugMessage("Error extention of files");
            return false;
        }

        return true;
    }

    private static ArrayList<Rectangle> loadInputData(final String inputFile) {
        ArrayList<Rectangle> inputData = new ArrayList<>();

        File input = new File(inputFile);

        if (!(input.isFile() && input.canRead())) {
            debugMessage("Input file wrong");
            return inputData;
        }
        else {
            try(BufferedReader br = new BufferedReader(new FileReader(input))) {
                for(String line; (line = br.readLine()) != null; ) {
                    String[] splited = line.split("\\s+");
                    if (Rectangle.checkStringOfDots(splited)) {
                        inputData.add(new Rectangle(Integer.parseInt(splited[0]),
                                Integer.parseInt(splited[1]),
                                Integer.parseInt(splited[2]),
                                Integer.parseInt(splited[3])));
                        if (inputData.size() > MAX_LINES_COUNT) {
                            return null;
                        }
                    }
                    else {
                        debugMessage("Input data wrong");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inputData;
    }

    public static boolean saveResult(final int result) {
        try{
            PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
            writer.println(String.valueOf(result));
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            debugMessage("Output file wrong");
            return false;
        }
    }

    public static void main(String args[]) {

        if (!checkArgs(args))
            return;
        else {
            inputFileName = args[0];
            outputFileName = args[1];
        }

        ArrayList<Rectangle> inputData = loadInputData(inputFileName);

        if (!inputData.isEmpty()) {

            if (inputData.size() > 1) {
                RectangleArray rectangles = new RectangleArray(inputData);
                int sq = rectangles.calculateSquare();
                saveResult(sq);
            }
            else {
                saveResult(inputData.get(0).getSquare());
            }
            return;
        }

    }

}
