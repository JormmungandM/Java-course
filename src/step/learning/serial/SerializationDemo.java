package step.learning.serial;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;

public class SerializationDemo implements Serializable {


    public void run()
    {
        serializeList();
        deserializeList();
    }



    public void serializeList()
    {
        List<DataObject> list = new ArrayList<>();
        list.add( new DataObject(110, 10f, "Hello 1", "Trans") );
        list.add( new DataObject(120, 20f, "Hello 2", "Trans") );
        list.add( new DataObject(130, 30f, "Hello 3", "Trans") );
        list.add( new DataObject(140, 40f, "Hello 4", "Trans") );
        list.add( new DataObject(150, 50f, "Hello 5", "Trans") );
        try (FileOutputStream file = new FileOutputStream("save.ser"))
        {
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(list);
            oos.flush();
            System.out.println( "List serialized" );
        }
        catch (IOException ex)
        {
            System.out.println( "List error serialization: " + ex.getMessage() );
            return;
        }
    }

    public void deserializeList()
    {
        try (FileInputStream file = new FileInputStream("save.ser"))
        {
            ObjectInputStream ois = new ObjectInputStream(file);
            /*
            List<DataObject> list = (List<DataObject>) ois.readObject() ;
            System.out.println( "List deserialized" );
            for(DataObject data : list){
                System.out.println( data );
            }*/
            List<?> list = (List<?>) ois.readObject();
            for( Object data : list) {
                if(data instanceof DataObject){
                    System.out.println( data );
                }
            }
            System.out.println( "-- Done --" );
        }
        catch (Exception ex)
        {
            System.out.println( "List error deserialization: " + ex.getMessage() );
            return;
        }
    }

    public void serialize()
    {
        DataObject data1 = new DataObject();
        DataObject data2 = new DataObject(10);
        DataObject data3 = new DataObject(10, 20.5f);
        DataObject data4 = new DataObject(10, 20.5f, "Hello","Trans");
        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);
        System.out.println(data4);

        try(FileOutputStream file = new FileOutputStream("save.ser")){
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(data1);
            oos.writeObject(data2);
            oos.writeObject(data3);
            oos.writeObject(data4);
            oos.flush();
            System.out.println( "Serialized\n" );
        }
        catch (IOException ex)
        {
            System.out.println( "Error serialization: " + ex.getMessage() );
        }


    }
    public void deserialize()//Deserialize
    {
        serialize();
        try(FileInputStream file = new FileInputStream("save.ser")){
            ObjectInputStream ois = new ObjectInputStream(file);
            DataObject data = (DataObject)  ois.readObject();
            System.out.println( data );
            data = (DataObject) ois.readObject();
            System.out.println( data );
            data = (DataObject) ois.readObject();
            System.out.println( data );
            data = (DataObject) ois.readObject();
            System.out.println( data );
            System.out.println( "Done" );
        }
        catch (Exception ex)
        {
            System.out.println( "Error deserialization: " + ex.getMessage() );
        }

    }

}
