package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String lineSource = "Alibaba or Alibubab? I do not know!";
        String linePattern = "b*b";
        char[] source = lineSource.toCharArray();
        char[] pattern = linePattern.toCharArray();

        int[] found = new int[3];
        search(source, pattern, found);
        System.out.println();
        System.out.println("Строка b*b - начинается с позиций: " + Arrays.toString(found));
    }

    public static void search(char[] source, char[] pattern, int[] found) {
        int count = 0;
        int windowHash = 0;

        if (source.length < pattern.length) {
            System.out.println("Такой подстроки нет!");
        } else {
            int patternHash = pattern[0] + pattern[1] + pattern[2] - pattern[1];

            for (int start = 0; start < source.length - (pattern.length - 1); start++) {
                int asterik_position = start + 1;
                if (start == 0) {
                    windowHash = source[start] + source[asterik_position] + source[start + 2];
                    windowHash = windowHash - source[asterik_position];
                } else {
                    windowHash = windowHash + source[start];
                    windowHash = windowHash - source[start - 1];
                    windowHash = windowHash + source[start + (pattern.length - 1)];
                    windowHash = windowHash - source[asterik_position];
                }
                if (windowHash == patternHash) {
                    for (int j = 0; j < pattern.length; j++) {
                        if (pattern[j] == source[start + j]) {
                            found[count] = start;
                        }
                    }
                    count++;
                }
            }
        }
    }
}
 /*Рабин-Карп с шаблонами
Давайте применим упрощённый алгоритм Рабина-Карпа для поиска подстроки в строке, но теперь в шаблоне будет один спец-символ *, который будет означать, что подойдёт любая буква.

Давайте рассмотрим на примере. Строка, в которой будем искать: Alibaba or Alibubab? I do not know!. В качестве шаблона возьмём b*b. Есть три подстроки, который подойдёт под этот шаблон: bab (начинается на позиции 3), bub (начинается на позиции 14) и второй bab (начинается на позиции 16).

Давайте для этого модифицируем алгоритм Рабина-Карпа. Мы всё ещё будем использовать упрощённую версию, где в качестве хеша считаем просто сумму кодов символов. В чём же будет состоять модификация? Вместо того чтобы считать хеш на всём шаблоне и сравнивать его с хешами на всех символов очередного кандидата на найденную подстроку, будем считать хеш на всём шаблоне без учёта символа *, а из хеша кандидата вычитать код символа, который стоит на позиции, соответствующей позиции * в шаблоне. Также при равенстве хешей мы будем проверять на ревенство все символы, пропуская позицию с *, тк в неё подойдёт любой символ текста.

Заметим, что тогда если есть подстрока, подходящая под шаблон, то хеш шаблона и хеш кандидата будут совпадать. Также, ничего нам не мешает всё также динамично поддерживать хеш кандидата, подсчитывая его из хеша предыдущего кандидата за O(1). Итоговая асимптотика будет такая же, как и у обычного Рабина-Карпа.

def search(source, pattern):
  if source короче pattern:
    return Такой подстроки точно нет!
  found = []
  pattern_hash = сумма кодов символов в pattern без учёта *
  asterik_position = позиция '*' в pattern
  for start от 0 до длина(source) - длина(pattern) + 1
    if start == 0:
      window_hash = сумма кодов первых длина(pattern) символов source
      window_hash -= код символа в source на позиции asterik_position
    else:
      window_hash -= код символа в source на позиции start-1
      window_hash += код символа в source на позиции start+длина(pattern) - 1
      window_hash -= код символа в source на позиции start+asterik_position
    if window_hash == pattern_hash:
      for i от 0 до длина(pattern):
        if pattern[i] != '*' И source[start + i] != pattern[i]:
          не подходит
      если подошёл, то добавим start в found
    window_hash += код символа в source на позиции start+asterik_position
  return found
Реализация
Напишите функцию поиска шаблона в строке по схеме, данной выше
Проверьте её на примере выше, убедитесь что ответы совпали
Загрузите ваше решение на сайт repl.it, отправьте ссылку на него на проверку.*/










