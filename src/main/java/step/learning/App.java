package step.learning;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;
import step.learning.services.DataTime;
import step.learning.services.RandomProvider;
import step.learning.services.StringService;
import step.learning.services.hash.HashService;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class App {

    @Inject @Named("MsConnectionString")
    private String MsConnectionString;

    @Inject @Named("OracleConnectionString")
    private String OracleConnectionString;

    private final StringService stringService;
    private final RandomProvider randomProvider;
    private final HashService hash128;
    private final HashService hash160;
    private final DataTime dataTimeService;

    @Inject     // injection through constructor
    public App(@Named("ten") RandomProvider randomProvider,
               @Named("128") HashService hash128,
               @Named("160") HashService hash160,
               DataTime dataTimeService,
               StringService stringService){
        this.randomProvider = randomProvider;
        this.hash128 = hash128;
        this.hash160 = hash160;
        this.dataTimeService = dataTimeService;
        this.stringService = stringService;
    }

    public void run(){
        // region classwork
        System.out.println( "IoC Demo" );
        System.out.println( "StringService: " + stringService.getString() );
        System.out.println( "RandomProvider: " + randomProvider.getN() );
        System.out.println( "HashService (128bit): " + hash128.hash("Hello") );
        System.out.println( "HashService (160bit): " + hash160.hash("Hello") );
        System.out.println( "MsConnectionString -> " + MsConnectionString );
        System.out.println( "OracleConnectionString -> " + OracleConnectionString );
        // endregion

        System.out.println( "Date: " + dataTimeService.getDate());
        System.out.println( "Time: " + dataTimeService.getTime() );
    }

    public void runMenu() {

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
        }

        // Sort demoClasses with Comparator
        demoClasses.sort(new Comparator<>() {
            public int compare(Class<?> o1, Class<?> o2) {
                return Integer.compare(     // The Integer.compare(x, y) returns -1 if x is less than y, 0 if they're equal, and 1 otherwise
                        o1.getAnnotation(DemoClass.class).priority(),
                        o2.getAnnotation(DemoClass.class).priority()
                                * -1 );     // I multiply by -1 so that those who have more priority are higher
            }
        });

        int userChoice;
        do {
            System.out.println(ConsoleColors.RED_BOLD + "Demo classes: ");
            int i = 1;
            for (Class<?> theClass : demoClasses){
                System.out.printf("%d. %s %n",i++,theClass.getName());
            }
            System.out.println("0. Exit");
            System.out.print( "Make your choice: ");
            Scanner kbScanner = new Scanner(System.in);
            userChoice = -1;
            try{
                userChoice = kbScanner.nextInt();
            } catch (InputMismatchException ignored){
                System.out.println( "Incorrect choice" );
            }
            System.out.println(ConsoleColors.RESET + "-------------------------------");
            if(userChoice == 0){
                System.out.println( "Bye" );
                System.out.println("-------------------------------");
                return;
            }
            int index = userChoice -1;
            if(index >= demoClasses.size() || index < 0) {
                System.out.println( "Incorrect choice" );
            }
            else {
                List<Object> objects = new ArrayList<>();   // list of class object stores that have been started previously
                Class<?> execClass = demoClasses.get(index);
                Method[] methods = execClass.getDeclaredMethods();
                for(Method method: methods){
                    if(method.isAnnotationPresent(EntryPoint.class)){
                        try{
                            method.invoke(getClassObject(objects, execClass));  // the method returns the required class object
                        }
                        catch (Exception ex){
                            System.out.println( "Execution error: " + ex.getMessage() );
                        }
                    }
                    else {
                        System.out.println("\"Entry point\" missing");
                    }
                }
            }
            System.out.println("-------------------------------");
        }while(true);


        // region  Runs
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


    private static Object getClassObject(List<Object> list, Class<?> obj_)
    {
        Object temp;
        try{

            if(!list.isEmpty()){
                for(Object obj : list){
                    if(obj.getClass() == obj_){     // checks if this object has already been created then returns
                        return obj;
                    }
                }
            }

            // if the class was called for the first time
            temp = obj_.getDeclaredConstructor().newInstance();   // temporarily an object to avoid reusing newInstance
            list.add(temp);                                       // adding to the list
            return temp;
        }
        catch (Exception ex){
            System.out.println( "Execution error: " + ex.getMessage() );
            return null;
        }
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

