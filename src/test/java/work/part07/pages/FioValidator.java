package work.part07.pages;

public class FioValidator {

    /**
     * Проверяет, содержит ли строка только русские буквы (включая Ёё).
     * Более производительная версия без использования регулярных выражений.
     */
    public static boolean containsOnlyRussianLetters(String fio) {
        if (fio == null || fio.isEmpty()) {
            return false;
        }

        for (int i = 0; i < fio.length(); i++) {
            char c = fio.charAt(i);

            // Проверка диапазонов русских букв в Unicode
            // А-Я: 1040-1071, Ё: 1025
            // а-я: 1072-1103, ё: 1105
            boolean isRussianLetter =
                    (c >= 'А' && c <= 'Я') ||   // заглавные А-Я (кроме Ё)
                            c == 'Ё' ||                  // заглавная Ё
                            (c >= 'а' && c <= 'я') ||   // строчные а-я (кроме ё)
                            c == 'ё';                   // строчная ё

            if (!isRussianLetter) {
                return false;
            }
        }

        return true;
    }
}