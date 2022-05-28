import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    private static final char[] ALPHABET = {
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т',
            'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я',
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т',
            'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            '(', '.', ',', '«', '»', '"', '\'', ':', '-', '!', '?', ' ', ')'};

    public static void main(String[] args) {
        Path srcPath;
        Path destPath;
        int criptKey;
        char[] destCharArray;
        System.out.println("Выберите режим работы программы:\n1 - Шифрование\n2 - Расшифровка\n3 - Brute Force");
        System.out.print("Ваш выбор: ");
        Scanner scanner = new Scanner(System.in);
        int operatingMode = scanner.nextInt();

        if (operatingMode == 1) {
            System.out.println("Выбран режим шифрования");
            srcPath = setInputFile(); // получаем путь к исходному фалйу
            criptKey = setCriptKey(1); // нормализуем ключ под задачу и крипто-алфавит
            destCharArray = Coder.coder(ALPHABET, readFiletoCharArray(srcPath), criptKey); // кодорование символов
            destPath = createDestFilePath(srcPath, "encript.txt"); // формируем путь для выходного файла
            writeToDestFile(destPath, destCharArray); // предаем выходные данные в файл
        } else if (operatingMode == 2) {
            System.out.println("Выбран режим дешифровки");
            srcPath = setInputFile();
            criptKey = setCriptKey(-1);
            destCharArray = Coder.coder(ALPHABET, readFiletoCharArray(srcPath), criptKey);
            destPath = createDestFilePath(srcPath, "decript.txt");
            writeToDestFile(destPath, destCharArray);
        } else if (operatingMode == 3) {
            System.out.println("Выбран режим подбора ключа методом Brute Force");
            srcPath = setInputFile();
            destCharArray = BruteForce.bruteForce(ALPHABET, readFiletoCharArray(srcPath)); // рализация брутфорса
            destPath = createDestFilePath(srcPath, "bruteforce.txt");
            writeToDestFile(destPath, destCharArray);
        } else {
            System.out.println("Выбранного режима работы не существует");
        }
    }

    //  Ввод пути к файлу с текстом / проверка на наличие файла
    private static Path setInputFile() {
        Scanner scanner = new Scanner(System.in);
        Path sourceFilePath;
        do {
            System.out.println("Введите путь к файлу с исходными данными в формате UTF-8");
            sourceFilePath = Path.of(scanner.nextLine());
            if (Files.notExists(sourceFilePath)) {
                System.out.println("Указанный файл не существует");
            }
        } while (Files.notExists(sourceFilePath));
        return sourceFilePath; // метод возвращает путь к исходному файлу
    }

    //  Записываем данные из файла в массив символов
    private static char[] readFiletoCharArray(Path sourceFilePath) {
        char[] srcChars = new char[0];
        try {
            srcChars = Files.readString(sourceFilePath).toCharArray(); //данные из файла сохраняем в массив символов
        } catch (IOException e) {
            e.printStackTrace();
        }
        return srcChars; //метод возвращает массив символов
    }

    //  Формирование пути к файлу с выходными данными в той же папке, что и исходный, но с заданным именем
    private static Path createDestFilePath(Path sourceFilePath, String destFileName) {
        Path destFilePath = Path.of(sourceFilePath.getParent().toString(), destFileName);
        if (Files.notExists(destFilePath)) {
            try {
                Files.createFile(destFilePath); // создаем файл, если его не существует
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return destFilePath; //мтеод возвращает путь к фалу для выходных данных
    }

    //  Вводим и преобраузем ключ для шифрования/дешифровки под возможности выбранного крипто-алфавита
    //  Если введенное число больше алфавита, то берем остаток от деления на его размер
    //  criptTrigger - принимает значения "1" и "-1" и меняет знак ключа - для шифровки и дешифровки.
    private static int setCriptKey(int criptTrigger) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите целое число в качестве ключа шифрования");
        int criptKey = scanner.nextInt() * criptTrigger % ALPHABET.length;
        return criptKey; // метод возвращает преобразованный ключ
    }

    //  Передаем строку в выходной файл
    private static void writeToDestFile(Path destFilePath, char[] destCharArray) {
        StringBuilder destString = new StringBuilder(); // переменная для сборки сроки
        for (int i = 0; i < destCharArray.length; i++) { // перебираем результирующий массив символов
            destString.append(destCharArray[i]); //добавляем символы в строку
        }
        try {
            Files.writeString(destFilePath, destString); //записываем строку в файл
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("Результат записан в файл " + destFilePath);
    }

}
