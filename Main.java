package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main{

    private static String GetTextFile(String FilePath)
    {
        String FileContext = "";
        try(FileReader reader = new FileReader(FilePath))
        {
            int c;
            while((c=reader.read())!=-1){

                FileContext += (char)c;
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return FileContext;
    }
    private static void ReadStatistic(BookWord book)
    {
        System.out.println("Statistic repear words:");
        for (RepeatStatistics stat:
                book.Get_Staticstic_Repear_Words()) {
            System.out.println(stat.ToString());
        }
    }
    private static String ConvertURLToProgramFormat(String URL)
    {
        String newURL = "";
        for (int i = 0; i < URL.length(); i++) {
            if(URL.charAt(i) == '/') newURL += '\\';
            else
                newURL += URL.charAt(i);
        }
        return newURL;
    }
    //C:\Users\golde\Desktop\Тетрадь\ЧНУ\JAVA\Harry Potter and the Sorcerer.txt
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try{
        System.out.print("Write path to .txt file:\n>");
        String FPath = ConvertURLToProgramFormat(in.nextLine());
        BookWord TheBook = new BookWord(GetTextFile(FPath));
        ReadStatistic(TheBook);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            in.nextLine();
            System.out.flush();
            main(args);
        }
        in.close();
    }
}
