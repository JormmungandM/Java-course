package step.learning;

import step.learning.anno.AnnotationsDemo;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;
import step.learning.files.FileExplorer;
import step.learning.files.FilesDemo;
import step.learning.oop.Library;
import step.learning.serial.SerializationDemo;

import java.io.File;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {




    public static void main(String[] args) {

        // Definition of the current class
        Class<?> currentClass = Main.class;

        // Get the package name
        String packageName = currentClass.getPackage().getName();

        // Get a list of class names from a package
        List<String> classNames = getClassNames(packageName);
        if (classNames == null){
            System.out.println("Error scanning package");
            return;
        }
        //
        List<Class<?>> demoClasses = new ArrayList<>();

        for(String className : classNames){
            Class<?> theClass;
            try{
                theClass = Class.forName(className);

            }catch (Exception ignored){
                continue;
            }
            if(theClass.isAnnotationPresent(DemoClass.class)){
                demoClasses.add(theClass);
            }

            //System.out.println(className);
        }


        System.out.println( "Demo classes: " );
        int i = 1;
        for (Class<?> theClass : demoClasses){
            System.out.printf("%d. %s %n",i++,theClass.getName());
        }
        System.out.println("0. Exit");
        System.out.print( "Make your choice: ");


        Scanner kbScanner = new Scanner(System.in);
        int userChoice = -1;
        try{
            userChoice = kbScanner.nextInt();
        } catch (InputMismatchException ignored){
            System.out.println( "Incorrect choice" );
        }

        if(userChoice == 0){
            System.out.println( "Bye" );
            return;
        }
        int index = userChoice -1;
        if(index >= demoClasses.size() || index < 0) {
            System.out.println( "Incorrect choice" );
        }
        else {
            Class<?> execClass = demoClasses.get(index);
            Method[] methods = execClass.getDeclaredMethods();
            for(Method method: methods)
            {
                if(method.isAnnotationPresent(EntryPoint.class)){
                    try{
                        method.invoke( execClass.getDeclaredConstructor().newInstance() );
                    }
                    catch (Exception ex){
                        System.out.println( "Execution error: " + ex.getMessage() );
                    }
                }
            }
        }


        // System.out.println( "Choice: " + userChoice);

        // region  Run()
        // new DataTypes().run();
        // new Complex().run();
        // new Dictionary().run();
        // new Library().run();
        // new SerializationDemo().run();
        // new FilesDemo().run();
        // new FileExplorer().run();
        // new AnnotationsDemo().run();
        // endregion
    }


    private  static List<String> getClassNames(String packageName)
    {


        // Class loader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageResource = classLoader.getResource(packageName.replace(".","/"));

        if(packageResource == null) {
            System.out.println("Resource identification error");
            return null;
        }
        String packagePath = packageResource.getPath();

        File packageBase = new File(packagePath);
        File[] list = packageBase.listFiles();
        if(list == null){
            System.out.println( "Error scanning directory " + packageBase );
            return null;
        }

        List<String> classNames = new ArrayList<>();
        for (File file: list){
            if (file.isFile() ){
                String filename = file.getName();
                if( filename.endsWith(".class")){
                    classNames.add(packageName + "." + filename.substring(0, filename.lastIndexOf(".")));
                }
            }
            if (file.isDirectory()){
                File[] SecondList = file.listFiles();
                if(SecondList != null){
                    for(File sub : SecondList){
                        if (sub.isFile() ){
                            String filename = sub.getName();
                            if( filename.endsWith(".class")){
                                classNames.add(packageName + "." + file.getName() + "." + filename.substring(0, filename.lastIndexOf(".")));
                            }
                        }
                    }
                }

            }
        }
        return classNames;
    }
}

