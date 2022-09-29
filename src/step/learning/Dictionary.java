package step.learning;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.*;

@DemoClass
public class Dictionary {

    Map<String, String> map;
    Scanner kbScanner;

    public Dictionary() {
        kbScanner = new Scanner(System.in);
        map = new HashMap<String,String>();
        map.put("Hello", "Доброго дня");
        map.put("Bye", "Бувай");
        map.put("Hi", "Привіт");
    }

    @EntryPoint
    public void run()
    {
        System.out.print("""
                Англо-Украинский словарь
                1. Показать все
                2. Перевод англ. слова
                3. Перевод укр. слова
                4. Добавить слово
                0. Выход
                Введите выбор:\s""");
        String str = kbScanner.nextLine();
        System.out.println(" ");
        switch (str)
        {
            case "1":
                All_Word();
                System.out.print("\n\n");
                break;
            case "2":
                System.out.print("Введите слово на англ: ");
                System.out.print(SearchByEN(kbScanner.nextLine())+"\n\n");
                break;
            case "3":
                System.out.print("Введите слово на укр: ");
                System.out.print(SearchByUA(kbScanner.nextLine())+"\n\n");
                break;
            case "4":
                System.out.print("Введите слово и его перевод (Hi - Привет)");
                String[] parts = kbScanner.nextLine().split(" ");
                System.out.print(Add(parts[0],parts[2])+"\n\n");
                break;
            case "0":
                return;
            default:
                System.out.print("Ошибка ввода\n\n");
        }
        run();
    }

    public void All_Word(){
        for (String key : map.keySet()){
            System.out.printf( "%s  -- %s\n", key, map.get(key));
        }
    }

    public String SearchByEN(String EN_word){
        return EN_word + " - " + map.get(EN_word);
    }
    public String SearchByUA(String UA_word){
        for (String key : map.keySet()){
            if(map.get(key).equals(UA_word)){
                return key + " - " + UA_word;
            }
        }
        return "unknown word";
    }

    public String Add(String word, String translate)
    {
        if(word != null || translate != null)
        {
            map.put(word,translate);
            return "Добавлено!";
        }
        return "Ошибка!";
    }




}
