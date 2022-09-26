package step.learning.files;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FilesDemo {

    public void run(){
        //fsDemo();
        ioDemo();
    }

    // regions Working with the fs
    /**
     Working with the fs (file system)
     */
    private void fsDemo(){
        File file = new File("newItem");  // "." - this (working) directory
        if (file.isDirectory()){
            System.out.println( file.getName() + " - is existing directory" );
            String[] list = file.list();
            if (list != null){
                for(String itemName : list){
                    System.out.println(itemName);
                }
            }else System.out.println( "List request error" );
            // Offer to delete directory
            System.out.println( "Delete directory? (y/...)"  );
            int sym;
            try{
                sym = System.in.read();
            }
            catch (IOException ex){
                System.out.println( "System error: " + ex.getMessage() );
                return;
            }
            if (sym == 'y'){
                boolean res = file.delete();
                if(res){
                    System.out.println( "Directory deleted successfully" );
                }else System.out.println( "Deletion error" );
            }else System.out.println( "Delete cancelled" );
        }
        else if(file.isFile()){
            System.out.println( file.getName() + " - is existing file" );

            if(file.canRead()) System.out.println( "Readable" );
            else System.out.println( "Non-Readable" );

            if(file.canWrite()) System.out.println( "Writable" );
            else System.out.println( "Non-Writable" );

            if(file.canExecute()) System.out.println( "Executable" );
            else System.out.println( "Non-Executable" );

            System.out.println( "File size: " + file.length() );

        }
        else {
            System.out.println( file.getName() + " - dose not exist" );
            boolean res = file.mkdir();  // create directory
            if (res){
                System.out.printf( "Directory '%s' created%n", file.getName() );
            }else System.out.println( "Directory creation error" );
        }
    }

    private void ioDemo(){
        String fileContent;
        StringBuilder sb = new StringBuilder();
        try(InputStream reader = new FileInputStream("hello.txt")){
            int symbol;
            while( (symbol = reader.read() ) != -1){    // "12345" - 5 symbols (10+45- 100+5000- 1k+500k-
                //fileContent += (Char) symbol;           // "" - "1" - "12"- "123" - "1234" - trash: 5 objects, 10 symbols
                sb.append( (char)symbol );
            }
        }
        catch ( IOException ex ){
            System.out.println( ex.getMessage() );
            return;
        }

        fileContent = new String(
                sb.toString().getBytes(
                        StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8);
        System.out.println(fileContent);


        try( FileWriter writer = new FileWriter("result.txt" ) ){
            writer.write("Як справы?");
            writer.flush();
        }
        catch( IOException ex)
        {
            System.out.println( ex.getMessage() );
            return;
        }

        System.out.println( "Write finished" );

    }


}

