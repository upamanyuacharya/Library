import java.io.*;
class Rename_Delete
{
    public static void renDel(String oldName, String newName)
    {
        String CurrentLine = "";

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(oldName));
            BufferedWriter bw = new BufferedWriter(new FileWriter(newName));

            while ((CurrentLine = br.readLine()) != null)
            {
                bw.write(CurrentLine);
                bw.newLine();
            }
            File tempfile = new File(oldName);
            if (tempfile.exists()) 
            {
                tempfile.setWritable(true);
                tempfile.delete();
                
            } 
            else 
            {
                System.err.println("File not found");
            }

            br.close();
            bw.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

    }
}