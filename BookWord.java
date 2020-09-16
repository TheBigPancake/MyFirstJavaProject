package com.company;

import java.util.ArrayList;
class RepeatStatistics
{
    public final String Word;
    public final int Repeat;
    public RepeatStatistics(String Word_, int Repeat_)
    {
        Word = Word_;
        Repeat = Repeat_;
    }
    public String ToString()
    {
        return "Word \t[" + Word + "]\t repeated \t" + Repeat + "\t times";
    }
}
public class BookWord {
    public String text;
    private String[] words;
    private RepeatStatistics[] statistic;
    public BookWord(String Text_)
    {
        text = Text_;
        words = String_Into_Words_FromArray(Text_);
        SetStatistic();
    }
    public static int Sum_Words(String Text)
    {
        int words = 0;
        for(char
                symbol: Text.toCharArray())
        {
            switch (symbol)
            {
                case ' ':
                case '.':
                case ',':
                case ':':
                case ';':
                case '\n':
                case '\r':
                case '!':
                case '?':
                {
                    words++;
                }break;
            }
        }
        return words;
    }
    public static String[] String_Into_Words_FromArray(String Text)
    {
        String[] words = new String[Sum_Words(Text) + 1];
        String word = "";
        for (int i = 0, j = 0; i < Text.length(); i++) {
            switch (Text.charAt(i)) {
                //dividing marks
                case ' ':
                case '.':
                case ',':
                case ':':
                case ';':
                case '\n':
                case '\r':
                case '!':
                case '?':
                    if (i - 1 >= 0)
                        if (Text.charAt(i - 1) != ' ' &&
                                Text.charAt(i - 1) != '.' &&
                                Text.charAt(i - 1) != ',' &&
                                Text.charAt(i - 1) != ':' &&
                                Text.charAt(i - 1) != ';' &&
                                Text.charAt(i - 1) != '\n' &&
                                Text.charAt(i - 1) != '?' &&
                                Text.charAt(i - 1) != '!' &&
                                Text.charAt(i - 1) != '\r') {
                            words[j++] = word;
                            word = "";
                        }
                    break;
                //Ignored
                case '—':
                case '"':
                case '\t':
                case '(':
                case ')':
                case ']':
                case '[':
                case '{':
                case '}':
                case '*':
                case '^':
                case '~':
                    break;
                default://letters
                    word += Text.charAt(i);
            }
        }
        return words;
    }
    private int SearchWord(String key_word, int From) {
        int sum_repeat = 0;
        for (int i = From; i < words.length; i++) {
            if (words[i] != null)
                if (words[i].equalsIgnoreCase(key_word)) {
                    sum_repeat++;
                }
        }
        return sum_repeat;
    }
    private void InsertWord(String DelWord, int From) {
        for (int i = From; i < words.length; i++) {
            if (words[i] != null)
                if (words[i].equalsIgnoreCase(DelWord))
                    words[i] = "";
        }
    }
    private String[] GetUniqueWords()
    {
        int i = 0;
        ArrayList<String> UniqueWords = new ArrayList<String>();
        for (String word:
                words) {
            if(word != "" && word != null)
            {
                UniqueWords.add(word);
                InsertWord(word,i);
                i++;
            }
        }
        words = String_Into_Words_FromArray(this.text);
        return UniqueWords.toArray(new String[0]);
    }
    private void SetStatistic()
    {
        String[] UniqueWords = GetUniqueWords();
        statistic = new RepeatStatistics[UniqueWords.length];
        int i = 0;
        for (String word:
                UniqueWords) {
                statistic[i++] = new RepeatStatistics(word, SearchWord(word,i));
        }
    }
    public RepeatStatistics[] Get_Staticstic_Repear_Words()
    {
        return statistic;
    }
}
