import java.io.*;
public class Library
{

    BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
    public void main() throws IOException
    {
        Loading.display();
        Art.display1();
        Art.display2();       
        System.out.println("");
        System.out.println("Press ENTER to continue.");
        br.readLine();
        menu();
    }

    private void menu () throws IOException
    {
        int flag = 0;
        int choice = 0;
        do{
            System.out.print("\f");
            Art.display3();
            System.out.println("1: Create Library Card");
            System.out.println("2: Borrow a Book (Must possess a Library Card)");
            System.out.println("3: See Your Library Card");
            System.out.println("4: Donate a Book");
            System.out.println("5: Return a Borrowed Book");
            System.out.println("6: Exit");
            while(flag == 0)
            {
                flag = 1;
                try
                {
                    do
                    {
                        choice = Integer.parseInt(br.readLine());
                    }while(choice>6 || choice == 0);
                }
                catch(Exception e)
                {
                    System.out.println("Error: "+e.getMessage());
                    System.out.println("Please enter your choice as a number!");
                    flag = 0;

                }
            }
            switch (choice)
            {
                case 1: createCard();
                break;
                case 2: borrowBookGenre();
                break;
                case 3: showCard();
                break;
                case 4: donateBook();
                break;
                case 5: returnBook();
                break;
                case 6: exit();
                break;
                default: flag = 0;
                break;
            }
        }while(flag == 0);
    }

    private void exit() throws IOException
    {
        System.out.print("\f");
        Art.display4(); //Calls the Thank you display
        System.out.println("");
        Art.display5(); //Calls the Table display
        System.out.println("");
        br.readLine();
        System.exit(0);
    }
    int linecount;
    /*
     * It's function is to count the number of lines in the file BookNumberList.txt
     * The number of lines is stored as an integer in an instance variable linecount
     * int linecount is then used to instantiate the array of objects Book book[]
     * 
     * This code ensures that the number of objects instantiated is always exactly what is needed.
     * Hence no wastage of memory takes place.
     */
    private int lineCounter(String s)
    {
        try{
            BufferedReader b = new BufferedReader(new FileReader(s));
            String line = "";

            // For each line read, increment the counter
            while ((line = b.readLine()) != null) 
            {
                linecount++;
            }

            b.close();}
        catch(Exception e)
        {System.out.println("Error: "+e.getMessage());}
        return linecount;
    }

    /*
     * The next array, genre[], is used to store the values of the 11 different book genres available in my library as an instance variable array.
     */
    String genre[] = {"Classics","Dystopian","Fantasy","Satire","Biography","Children","Romance","Fiction","Science Fiction","Drama","Non-Fiction"};

    /*
     * The method String spaces delivers the number of spaces required as per the inputed number.
     * This method is extremely useful in maintaining an equal number of characters in each object array.
     * This it helps maintain fluidity in the tabular displays.
     */
    

    Book book[] = new Book[lineCounter("BookNumberList.txt")]; //instantiating object with the number of lines in the BookNumberList.txt file
    /*
     * Our librarian method is the method that sorts the books from the txt files into the object arrays. 
     * It is called librarian because just like a librarian, it manages the files and stores the books in appropriate places with regard to relevancy.
     */
    private void librarian()throws IOException
    {
        //File Handling starts here with reading into each of the different files to obtain set of books along with relevant data.
        FileReader fauthor = new FileReader("BookAuthorList.txt"); 
        BufferedReader bufferAuthor = new BufferedReader(fauthor);

        FileReader fgenre = new FileReader("BookGenreList.txt"); 
        BufferedReader bufferGenre = new BufferedReader(fgenre);

        FileReader fname = new FileReader("BookNameList.txt"); 
        BufferedReader bufferName = new BufferedReader(fname);

        FileReader fnumber = new FileReader("BookNumberList.txt"); 
        BufferedReader bufferNumber = new BufferedReader(fnumber);

        for(int i=0; i<linecount; i++) //instance variable linecount
        {
            book[i] = new Book("1000", "Book Name", "Author of Book", "Genre of Book"); //instantiating array element to avoid NullPointerException
            book[i].code = bufferNumber.readLine();
            book[i].author = bufferAuthor.readLine();
            book[i].genre = bufferGenre.readLine();
            book[i].name = bufferName.readLine();
        }
        /*
         * The following for loop trims each of the lines obtained from the file, to remove spaces and help maintain equal number of characters.
         */
        for(int i=0; i<linecount; i++) //trimming
        {
            book[i].code = book[i].code.trim();
            book[i].author = book[i].author.trim();
            book[i].genre = book[i].genre.trim();
            book[i].name = book[i].name.trim();
        }

        for(int i=0; i<linecount; i++) //add set number of lines. this helps in making tabular forms
        {
            /*
             * Utilising concatenation from Library classes here. 
             * Function spaces is called.
             */
            book[i].code = book[i].code.concat(Spacebar.spaces(5 - book[i].code.length()));
            book[i].author = book[i].author.concat(Spacebar.spaces(25 - book[i].author.length()));
            book[i].genre = book[i].genre.concat(Spacebar.spaces(15 - book[i].genre.length()));
            book[i].name = book[i].name.concat(Spacebar.spaces(70 - book[i].name.length()));
        }
    }

    /*
     * The following method, borrowBookGenre, is used when the user wants to borrow a book based on the genre of the book.
     */
    private void borrowBookGenre() throws IOException
    {
        librarian();
        int flag = 0;
        boolean repeat = false;
        char iRepeat='a';
        int choice = 13;//int choice is set as 13 because if somehow an integer is not entered properly, it will repeat due to the do while loop.
        int selectedCode = 0;
        do
        {System.out.println("\fWelcome. Here, you can borrow a book based on its genre.");
            System.out.println("No of genres: "+genre.length);
            for (int i = 0; i<genre.length; i++)
            {
                System.out.println((i+1)+": "+genre[i]); //Displays Genres
            }
            System.out.println("");
            System.out.println("Enter your choice in an integer form.");

            while(flag == 0)
            {
                flag = 1;
                try
                {
                    do
                    {
                        choice = Integer.parseInt(br.readLine());
                    }while(choice>=12 || choice == 0);
                }
                catch(Exception e)
                {
                    System.out.println("Error: "+e.getMessage());
                    System.out.println("Please enter the number as an integer from 1 to 11.");
                    flag = 0;

                }
            }
            flag = 0;
            switch (choice)
            {
                case 1: displayBookChoice(0);
                break;
                case 2: displayBookChoice(1);
                break;
                case 3: displayBookChoice(2);
                break;
                case 4: displayBookChoice(3);
                break;
                case 5: displayBookChoice(4);
                break;
                case 6: displayBookChoice(5);
                break;
                case 7: displayBookChoice(6);
                break;
                case 8: displayBookChoice(7);
                break;
                case 9: displayBookChoice(8);
                break;
                case 10: displayBookChoice(9);
                break;
                case 11: displayBookChoice(10);
                break;
                default: System.out.println("Error: You inputed a wrong number.");
            }

            System.out.println("Enter the code of the book you'd like to borrow.");

            while(flag == 0)
            {
                flag = 1;
                try
                {
                    do
                    {
                        selectedCode = Integer.parseInt(br.readLine());
                    }while(selectedCode > linecount || selectedCode == 0);
                }
                catch(Exception e)
                {
                    System.out.println("Error: "+e.getMessage());
                    System.out.println("Please enter the valid integer code on the left.");
                    flag = 0;

                }
            }
            flag = 0;
            System.out.println("\fYou have selected "+book[selectedCode-1].name.trim()+ " by "+book[selectedCode-1].author.trim());
            System.out.println("Y or y: If this is the book you want.");
            System.out.println("N or n: If this is NOT the book you wanted.");
            while(flag == 0)
            {
                flag = 1;
                try
                {

                    do
                    { 
                        iRepeat = (char)br.readLine().charAt(0);
                        //System.out.println("You entered: "+iRepeat);
                    }while(iRepeat != 'y' && iRepeat != 'n' && iRepeat != 'N' && iRepeat != 'Y' );
                }
                catch(Exception e)
                {
                    System.out.println("Error: "+e.getMessage());
                    System.out.println("Please enter either y or n.");
                    flag = 0;

                }
            }
            flag = 0;
            if (iRepeat == 'n' || iRepeat == 'N')
            {
                System.out.println("Okay. Press Enter to go back.");
                br.readLine();
                repeat = true;
            }
            else
                repeat = false;
        }while(repeat == true);
        System.out.println("");
        System.out.println("1: YES I am a member, and I possess a Library Card.");
        System.out.println("2: NO I do not possess a Library Card.");
        while(flag == 0)
        {
            flag = 1;
            try
            {
                do
                {
                    choice = Integer.parseInt(br.readLine());
                }while(choice<1 || choice>2);
            }
            catch(Exception e)
            {
                System.out.println("Error: "+e.getMessage());
                System.out.println("Please enter your choice, 1 or 2.");
                flag = 0;

            }
        }
        flag = 0;

        if (choice == 2)
        {
            System.out.println("\fYou will now enter the Library Card Creation Area. Press Enter to continue.");
            br.readLine();
            createCard();
        }

        if (choice == 1)
        {
            System.out.println("Enter the name on your library card.");
            String n = br.readLine();

            if(Checkfile.fileExist(n) == true) //calling function from class Checkfile to check whether the file with the name inputed exists.
            {
                System.out.println("Yes, you appear to have a Library Card!");
                if(selectedCode <= linecount)
                {

                    String d = book[selectedCode-1].name;
                    bookPrinter(n,d);

                    System.out.println("Here is your book! You borrowed "+book[selectedCode-1].name.trim()+".");

                    Art.display6();
                    System.out.println("");
                }
            }
            else
            {
                System.out.println("The name you mentioned doesn't seem to exist in our registry.");
                System.out.println("You will now be guided to the main menu");
                br.readLine();
                menu();
            }
        }
        System.out.println("You will now return to the main menu. \nPRESS ENTER.");
        br.readLine();
        menu();
    }

    /*
     * bookPrinter is called when the date of your book along with the book name has to be printed on your library card
     */
    private void bookPrinter(String name, String book) throws IOException
    {
        try
        {
            PrintWriter p = new PrintWriter (new BufferedWriter (new FileWriter ("LibraryCards\\"+name+".txt", true)));
            p.println(book.trim());
            p.println(DoDate.display()); //Prints the date to the Library Card

            p.close();
        }
        catch (Exception e)
        {
            System.out.println("Sorry. There was an error in registering your book.");
        }
    }

    /*
     * displayBookChoice is called when a list of books with regard to genre has to be displayed.
     * This function is so that in the switch statements, the same thing does not have to be repeated, and we can just call this method.
     */
    private void displayBookChoice(int n) throws IOException
    {
        System.out.println("Your choice is "+genre[n]);
        System.out.println("Press Enter to see our "+genre[n]+" collection!");
        br.readLine();
        System.out.println("Your choice of books includes:");
        System.out.println();
        System.out.println("Code \t"+"Book Name"+Spacebar.spaces(70-9)+"\tAuthor"+Spacebar.spaces(25-6)+"\tGenre"+Spacebar.spaces(15-5));
        System.out.println();
        for (int i=0; i<linecount; i++)
        {
            if(book[i].genre.trim().equalsIgnoreCase(genre[n].trim()))
                System.out.println(book[i].code +"\t"+book[i].name+"\t"+book[i].author+"\t"+book[i].genre);
        }
    }

    private void createCard() throws IOException
    {
        System.out.print("\f");

        BufferedReader cardreader = new BufferedReader (new InputStreamReader (System.in));

        System.out.println("Welcome to the Library Card Creation Screen.");
        System.out.println("");
        System.out.println("Press ENTER to continue.");
        //System.out.println("");
        br.readLine();
        System.out.print("\f");
        System.out.println("Enter your name to be put on the Library Card.");
        String n=null;
        int flag = 0;
        while(flag == 0)
        {
            flag = 1;
            try
            {
                do
                {
                    n = cardreader.readLine();
                }while(n==null);
            }
            catch(Exception e)
            {
                System.out.println("Error: "+e.getMessage());
                System.out.println("Please enter a valid name!");
                flag = 0;

            }
        }

        FileWriter fname = new FileWriter ("LibraryCards\\"+n+".txt", true);
        PrintWriter pname = new PrintWriter (new BufferedWriter (fname));
        pname.println(n);

        System.out.println("Enter your email address, as contact information.");
        String p = null;
        flag = 0;
        while(flag == 0)
        {
            flag = 1;
            try
            {

                do
                {
                    System.out.println("It must be a valid email account!");
                    p = cardreader.readLine();
                }while(Email.isEmail(p) == false);
            }
            catch(Exception e)
            {
                System.out.println("Error: "+e.getMessage());
                System.out.println("Please enter a valid email address (It must have logical syntax)!");
                flag = 0;

            }
        }
        pname.println(p);
        int g = 0;

        g = (int)(Math.random()*1000);
        pname.println(g); 

        System.out.println("\fYour name has been saved as:                        "+n);
        System.out.println("Your email address has been registered as:          "+p);
        System.out.println("You have been given a Library Number. It is:        "+g);
        pname.close();
        System.out.println("You will now return to the main menu. \nPlease press ENTER.");
        br.readLine();
        menu();
    }

    private void showCard() throws IOException
    {
        System.out.println("Enter the name on your library card.");
        String n = br.readLine();

        if(Checkfile.fileExist(n) == true) //calling function from class Checkfile to check whether the file with the name inputed exists.
        {
            FileReader f = new FileReader("LibraryCards\\"+n+".txt"); 
            BufferedReader cardReader = new BufferedReader(f);
            System.out.println(n+", your Library Card exists! Press ENTER to see it!");
            br.readLine();
            String arrayDisplay[] = {"Name:           ","Email:          ","Library Number: ","Book:           ","Date:           "};
            System.out.println("  /= = = = = = = = = = = = = = = = = = = = = = = = =\\");
            System.out.println(" /= = = = = = = = = = = = = = = = = = = = = = = = = =\\");
            for (int i = 1; i<=5; i++)
            {
                String temp = cardReader.readLine();
                temp = temp.trim();
                System.out.println("||  "+arrayDisplay[i-1]+temp+Spacebar.spaces(33 -temp.length())+"||");
            }
            System.out.println(" \\= = = = = = = = = = = = = = = = = = = = = = = = = =/");
            System.out.println("  \\= = = = = = = = = = = = = = = = = = = = = = = = =/");
        }
        else
        {
            System.out.println("\fThe name you mentioned doesn't seem to exist in our registry!");
            System.out.println();
            Art.crying_charlie();
            System.out.println("Press ENTER to be guided to the main menu.");

            br.readLine();
            menu();
        } 
        System.out.println("You will now return to the main menu. \nPlease press ENTER.");
        br.readLine();
        menu();
    }

    private void donateBook()throws IOException
    {
        librarian();

        //FileWriting

        FileWriter fw1 = new FileWriter ("BookAuthorList.txt", true);
        PrintWriter pauthor = new PrintWriter (new BufferedWriter (fw1));

        FileWriter fw2 = new FileWriter ("BookGenreList.txt", true);
        PrintWriter pgenre = new PrintWriter (new BufferedWriter (fw2));

        FileWriter fw3 = new FileWriter ("BookNameList.txt", true);
        PrintWriter pname = new PrintWriter (new BufferedWriter (fw3));

        FileWriter fw4 = new FileWriter ("BookNumberList.txt", true);
        PrintWriter pnumber = new PrintWriter (new BufferedWriter (fw4));

        System.out.println("\fThank you for trying to donate a book. Please press ENTER.");
        br.readLine();
        System.out.println("So what is the name of the book you are trying to donate?");

        /*
         * The next bit are the variables used for storing values into the txt files through file writer.
         */
        String n = null; 
        String a = null;
        String g = null; 
        int num = 0;

        int flag = 0;
        int choice = 0;
        while (flag == 0)
        {
            flag = 1;
            try
            {
                do
                {
                    System.out.println("Enter name of book: ");
                    n = br.readLine();
                }while(n==null);
            }
            catch(Exception e)
            {
                System.out.println("Error: "+e.getMessage());
                System.out.println("Please enter a valid name!");
                flag = 1;
            }

        }
        flag = 0;
        System.out.println("You have chosen to donate \""+n+"\"");
        System.out.println("Press any key to continue.");
        br.readLine();

        while (flag == 0)
        {
            flag = 1;
            try
            {
                do
                {
                    System.out.println("\fYou are donating "+n);
                    System.out.println("Enter name of the author of the book \""+n+"\": ");
                    a = br.readLine();
                }while(a==null);
            }
            catch(Exception e)
            {
                System.out.println("Error: "+e.getMessage());
                System.out.println("Please enter a valid name of the author!");
                flag = 1;
            }
        }
        flag = 0;

        System.out.println("You have told us that the author of the book is \""+a+"\"");
        System.out.println("Press any key to continue.");
        br.readLine();

        while (flag == 0)
        {
            flag = 1;
            System.out.println("\fSelect the genre of the book.");
            System.out.println("No of genres: "+genre.length);
            for (int i = 0; i<genre.length; i++)
            {
                System.out.println((i+1)+": "+genre[i]);
            }
            System.out.println("");
            System.out.println("Enter your choice in an integer form.");
            try
            {
                do
                {
                    choice = Integer.parseInt(br.readLine());
                }while(choice>=12 || choice == 0);
            }
            catch(Exception e)
            {
                System.out.println("Error: "+e.getMessage());
                System.out.println("Please enter the number as an integer from 1 to 11.");
                flag = 0;

            }
        }

        System.out.println("You have chosen "+genre[choice-1]+" as the genre of the book you are donating.");
        g = genre[choice-1];

        for (int i=1; i<=linecount; i++)
        {
            num = i + 1;
        }
        pname.println();
        pname.print(n);
        pname.close();

        pauthor.println();
        pauthor.print(a);
        pauthor.close();

        pgenre.println();
        pgenre.print(g);
        pgenre.close();

        pnumber.println();
        pnumber.print(num);
        pnumber.close();

        librarian(); //calling librarian method to put books into order once again (by storing the new books in the object array)
        System.out.println("THANK YOU very much for donating!");
        System.out.println();
        Art.display7();

        System.out.println("You will now return to the main menu. \nPRESS ENTER.");
        br.readLine();
        menu();
    }

    private void returnBook() throws IOException
    {
        System.out.println("\fYou are wanting to return the book you have borrowed.");
        System.out.println("Enter the name on your library card.");
        String n = br.readLine();

        if(Checkfile.fileExist(n) == true) //calling function from class Checkfile to check whether the file with the name inputed exists.
        {
            File file = new File("LibraryCards\\"+n+".txt"); //creating LibraryCard file to use in this method
            FileReader f = new FileReader(file);
            BufferedReader cardReader = new BufferedReader(f);

            /*
             * TEMPORARY FILE IS CREATED
             * REASON: 
             * A temporary file is used because it is needed to store the details; name, email account and card number
             * The temporary file is then renamed to the actual name on the library card
             * 
             * We cannot use PrintWriter to delete lines from the middle of a file. It can only append files.
             * Hence a new file is created and later renamed.
             */
            File tempfile = new File("LibraryCards\\"+n+"$$$$temp"+".txt"); //creating a temporary file to store the details in
            if(!tempfile.exists())
            {
                tempfile.createNewFile();
            }
            FileReader f1 = new FileReader(tempfile);
            BufferedReader tempReader = new BufferedReader(f1);

            PrintWriter p = new PrintWriter (new BufferedWriter (new FileWriter (file, true))); //PrintWriter for original file
            PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (tempfile, true)));//Printwriter for temporaryfile
            String details[] = {"","","","",""};
            for (int i = 0; i<5; i++)
            {
                details[i] = cardReader.readLine();
            }
            for (int i = 0; i<5; i++)
            {
                if (i == 3 )
                {
                    System.out.println("You have returned "+details[i].trim());
                    details[i] = ""; //the Book line is deleted
                }
                if (i == 4 )
                {
                    System.out.println("It was borrowed on "+details[i].trim()+" and today is "+DoDate.display());
                    details[i] = ""; //the Date line is deleted
                }
            }
            for (int i = 0; i<5; i++)
            {
                pw.println(details[i]);
            }

            pw.close();
            for (int i = 0; i<3; i++)
            {
                details[i] = tempReader.readLine();
            }
            for (int i = 0; i<3; i++)
            {
                p.println(details[i]);
            }

            p.close();

            Rename_Delete.renDel("LibraryCards\\"+n+"$$$$temp"+".txt", "LibraryCards\\"+n+".txt"); //Calling function from class Rename_Delete
            /*
             * NOTE: THERE IS A BUG IN JAVA THAT DOES NOT ALLOW THE FILE TO BE DELETED
             * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4715154
             * 
             * FILE DELETION IS NOT POSSIBLE
             * 
             * Despite this, the overall functioning of the library method is not affected.
             */

        }
        else
        {
            System.out.println("\fThe name you mentioned doesn't seem to exist in our registry!");
            System.out.println();
            Art.charlie();
            System.out.println();
            System.out.println("Press ENTER to be guided to the main menu.");
            br.readLine();
            menu();
        } 
        System.out.println("Please press ENTER to be taken back to the main menu.");
        br.readLine();
        menu();

    }

}