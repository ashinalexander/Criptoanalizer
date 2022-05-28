public class BruteForce {
    public static char[] bruteForce(char[] ALPHABET, char[] srcCharsArray) {
        char[] destCharArray = new char[srcCharsArray.length]; //инициация результирующего массива
        char maxChar = ALPHABET[0];

        for (int criptKey = 0; criptKey < ALPHABET.length; criptKey++) { // перебор всех возможных ключей
            destCharArray = Coder.coder(ALPHABET, srcCharsArray, criptKey); // декодирование с различными ключами
            int maxCharCounter = 0;
            for (char charOfAlphabet : ALPHABET) { //перебираем алфавит и находим символ, встречающийся чаще всего
                int charCounter = 0;
                for (char charInDestArray : destCharArray) {
                    if (charInDestArray == charOfAlphabet) {
                        charCounter++;
                    }
                }
                if (maxCharCounter < charCounter) {
                    maxCharCounter = charCounter;
                    maxChar = charOfAlphabet;
                }
            }
            if(maxChar == ' '){ //если самый частый символ - это ПРОБЕЛ, то прерываем дальнейшее выполнение
                break;
            }
        }
        return destCharArray; //метод возвращает результирующий массив символов
    }
}
