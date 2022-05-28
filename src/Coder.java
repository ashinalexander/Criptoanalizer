public class Coder {
    public static char[] coder(char[] ALPHABET, char[] srcCharsArray, int criptKey) {

        char[] destCharArray = new char[srcCharsArray.length]; //инициация результирующего массива


        for (int charIndexInArrays = 0; charIndexInArrays < srcCharsArray.length; charIndexInArrays++) { // пребор исходного массива
            int charIndexInAlphhabet = 0;          //переменная для расчета позиции симола в алфавите.
                                                   // Инициируется для каждого символа исходного массива
            boolean charEqualsTrigger = false;     // инициация триггера замены символа
            for (char charOfAlphabet : ALPHABET) { //перебор алфавита
                if (srcCharsArray[charIndexInArrays] == charOfAlphabet) { // если символ исходного массива совпадает с текущим
                    charIndexInAlphhabet += criptKey;             // символом из алфавита, то смещаем его индекс на
                                                                  // велечину ключа,
                    if (charIndexInAlphhabet < 0) { // корректируем индекс символа, если он вышел за рамки алфавита
                                                    // после смещения,
                        charIndexInAlphhabet += ALPHABET.length;
                    }
                    if (charIndexInAlphhabet > ALPHABET.length - 1) {
                        charIndexInAlphhabet -= ALPHABET.length;
                    }
                    charEqualsTrigger = true;// активируем триггер замены
                    break;                   // и прерываем выполнение цикла, так как искомое значение найдено
                } else {
                    charIndexInAlphhabet++;  // если символы не идентичны, то увеличиваем индекс симвла в алфавите для
                                             // следующей итерации цикла
                }
            }
//          Запись символов в результирующий массив символов
            if (charEqualsTrigger == true) {
                destCharArray[charIndexInArrays] = ALPHABET[charIndexInAlphhabet];
            } else {
                destCharArray[charIndexInArrays] = srcCharsArray[charIndexInArrays];
            }
        }
        return destCharArray; // метод возращает результирующий массив символов
    }
}
