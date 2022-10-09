package step.learning;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.*;

@DemoClass
public class Complex {

    public Complex(){
        Random random = new Random();
    }
    private void arraysDemo()
    {

        int[] arr1 = new int[4];
        int[] arr2 = new int[] {5,4,3,2,1};
        int[] arr3 = {5,4,3,2,1};

        for (int i = 0; i < 4;  ++i){
            System.out.printf("arr1[ %d ]= %d%n",i, arr1[i]);
        }
        System.out.println("----------------------");
        int j = 0;
        for(; j < arr2.length; j++){
            System.out.println(arr2[j]);
        }
        System.out.println("----------------------");
        for(int a : arr3)
        {
            System.out.println(a);
        }
        System.out.println("----------------------");
    // Jagged arrays
        int[][] arr4 = {{1,2,3}, {4,5,6,7}, {8,9}};
        int[][] arr5 = new int [3][4];


        printArr(arr4);

        System.out.println("----------------------");
        randArr(arr5);
        printArr(arr5);

    }

    private void printArr(int[][] arr)

    {
        for(int[] a: arr){
            for(int x : a){
                System.out.print(x + " ");
            }
            System.out.println(" ");
        }
    }
    private void randArr(int[][] arr)
    {
        for(int i = 0; i < arr.length;i++){
            for(int j = 0; j < arr[i].length;j++) {
                arr[i][j] = (int)(Math.random() * 50) - 1;
            }
        }
    }

    private void collectionsDemo()
    {
        System.out.println("======================");
        ArrayList<Integer> arr1 = new ArrayList<>();
        arr1.add ( 10 ) ;
        arr1.add ( 20 ) ;
        arr1.add ( 30 ) ;
        arr1.add ( 40 ) ;
        for(Integer x : arr1){
            System.out.print(x + " ");
        };
        System.out.println();
        System.out.println("----------------------");
        for (int i  = 0; i < arr1.size(); i++){
            System.out.printf("i=%d, x=%d%n", i, arr1.get(i));
        };

        System.out.println("======================");

        Map<String,String> map = new HashMap<>();
        map.put("Hello", "Привіт");
        map.put("Bye", "Бувай");
        map.put("Hi", "Привіт");
        for (String key : map.keySet()){
            System.out.printf( "%s  -- %s\n", key, map.get(key));
        }

        System.out.println("======================");

        System.out.println("Введите слово: ");
        Scanner kbScanner = new Scanner(System.in);
        String str = kbScanner.nextLine();
        //while(System.in.available()){int c = System.in.read()};
        String translate = map.get(str);
        System.out.println(str + " - " + translate);


    }

    @EntryPoint
    public void  run()
    {
        arraysDemo();
        collectionsDemo();
    }

}
