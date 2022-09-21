package step.learning.oop;

import java.util.*; // import all utils

public class Library {
    private final List<Literature> funds ; // this list contains all the literatures (book, journal and newspaper)

    public Library() {
        funds = new ArrayList<>() ; // create list
    }

    public void add(Literature literature) {
        funds.add(literature); // this method adds some literature
    }

    public void printFunds() {
        for(Literature literature : funds) {
            literature.print();  // abstract method.
        }
    }

    public void Run() {
        add(new Book().setAuthor("J. R. R. Tolkien").setTitle("The Lord of the Rings"));    // Adds new book
        add(new Journal().setNumber(322).setTitle("RandomName"));                           // Adds new journal
        printFunds();                                                                       // Prints all literature
    }
}
/*
    ООП
    Сущности :  Классы, Интерфейсы, Структуры, Абстрактные класс,
        Делегаты, Атрибуты, Enum, Event, Generics
    Поля, модификаторы,конструкторы, деструкторы,
        свойства.
    Наследования: множественное, наследование/реализация,
        модификаторы наследования, where(Generics),
        виртуальность( и замещение методов), делегирования.
    Полиморфизм: преобразование типов UpCast / DownCast, в
        т. ч. проверка принадлежности к типу, перегрузки.
-------------------------------------------------------------------
В Java свойств нет, принято создавать аксессоры для полей.

Интерфес (В ООП) - это класс, который содержит
 - только методы
 - только абстрактные
 - только public

 */