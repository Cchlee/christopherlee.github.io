package processor;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger
{
    private static final Logger instance = new Logger();

    private Logger() {}

    public static Logger getLogger()
    {
        return instance;
    }

    public void log(String outputFile, String text)
    {
        BufferedWriter output = null;
        try
        {
            File file = new File(outputFile);
            if (!file.exists())
            {
                file.createNewFile();
            }
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.flush();
        }
        catch (IOException e)
        {
            System.out.println("Error writing to " + outputFile);
        }
        finally
        {
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException e)
                {
                    System.out.println("Error closing " + outputFile);
                }
            }
        }
    }
}
