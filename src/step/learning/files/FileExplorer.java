package step.learning.files;

// some modules to work
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileExplorer {

    private final StringBuilder path = new StringBuilder(); // this path is used for interface and for cdMethod
    public void run()
    {
        cdMethod("."); // show directory before work


        while(true){
            System.out.print("\n" + this.path + " -> ");    // show current directory
            Scanner kbScanner = new Scanner(System.in);

            String[] user_command_parts = (kbScanner.nextLine()).split(" ");    // split commands user

            if (user_command_parts.length > 2){     // checks if the command is correct
                    System.out.println( "Command error" );
                    return;
                }

            switch (user_command_parts[0]) {
                case "cd" -> cdMethod(user_command_parts[1]);       // 'cd' method
                case "cat" -> catMethod(user_command_parts[1]);     // 'cat' method
                case "close" -> {                                   // closes program
                    System.out.println( "File explorer closed" );
                    return;
                }
                default ->  System.out.println( "Command unknown" );
            }


        }
    }

    private void cdMethod(String dir)
    {
        String temp = this.path.toString();     // temporally String to save the path

        if (dir.equals(".."))     // if there is 'cd ..', then go back one folder
        {
            if (this.path.indexOf("\\") == -1)          // clear the path if there is no backslash
            {
                this.path.delete(0, this.path.length());
                temp = ".";
            }
            else                                        // clear the path if there is backslash
            {
                this.path.delete(this.path.lastIndexOf("\\"), this.path.length());
                temp = this.path.toString();
            }
        }
        else if(!this.path.isEmpty())   // if not the first folder add a backslash
        {
            temp += "\\";
            temp += dir;
        }
        else  temp += dir;  // if the program is just running or the first folder


        File file = new File(temp);     // create a file object

        String[] list = file.list();
        if (list != null){
            for(String itemName : list){
                System.out.print(itemName + "\t");
            }
            this.path.delete(0,this.path.length());     // deleting begin path
            this.path.append(temp);                     // saving worked path
        }else System.out.print( "Resource not found" );

        if(this.path.indexOf(".") == 0) {      // if keep this character at the beginning, there will be problems with the following paths.
            this.path.deleteCharAt(0);
        }
    }

    // the same was in practice in the lesson
    private void catMethod(String path)
    {
        String fileContent;
        StringBuilder sb = new StringBuilder();
        try(InputStream reader = new FileInputStream(path)){
            int symbol;
            while( (symbol = reader.read() ) != -1){
                sb.append( (char)symbol );
            }
        }
        catch ( IOException ex ){
            System.out.print( "Resource not found" );
            return;
        }

        fileContent = new String(
                sb.toString().getBytes(
                        StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8);
        System.out.print(fileContent);
    }

}
