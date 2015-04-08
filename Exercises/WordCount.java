import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCount
{
    public static void main( String[] args) throws FileNotFoundException
    {
        boolean a = true;
        while(a)
        {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter a file name: ");
            String name = s.next();
            try
            {
                File file = new File(name);

                Scanner in = new Scanner(file);
                in.useDelimiter("");
                int countChar = 0;
                while (in.hasNext())
                {
                    char ch = in.next().charAt(0);
                    countChar++;
                }
                in.close();

                Scanner in2 = new Scanner(file);
                in2.useDelimiter("[^A-Za-z]+");
                int countWords = 0;
                while (in2.hasNext())
                {
                    String ch = in2.next();
                    countWords++;
                }
                in2.close();

                Scanner in3 = new Scanner(file);
                in3.useDelimiter("");
                int countLines = 0;
                while (in3.hasNextLine())
                {
                    String ch = in3.nextLine();
                    countLines++;
                }
                in3.close();

                System.out.println("Characters: " + countChar);
                System.out.println("Words: " + countWords);
                System.out.println("Lines: " + countLines);
                a = false;
            }
            catch (FileNotFoundException exception)
            {
                System.out.println("File Not Found");
            }
        }
    }

}