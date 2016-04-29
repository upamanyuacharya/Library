/*
 * This class checks if a string is an email account or not, by using the following procedures:
 * 1) Checks if the string contains an '@'
 * 2) If the string contains an '@', then it checks if there is a dot ('.')
 * 3) To satisfy conditions, the dot must be AFTER the '@' and not before it
 * 4) A boolean value is returned in method isEmail
 */
class Email
{ 
    public static boolean isEmail(String n)
    {
        int c1=0;
        int c2=0;
        int point = 0;
        for (int i = 0; i<n.length(); i++)
        {
            if (n.charAt(i) == '@')
            {
                c1++;
                point = i; //stores the index of the '@'
            }
        }
        if (c1 > 0)
        {
            String n1 = n.substring(point, (n.length() - 1));
            for (int i = 0; i<n1.length(); i++)
            {
                if (n1.charAt(i) == '.')
                {
                    c2++;

                }
            }
        }
        if(c2 > 0)

            return true;

        else
            return false;
    }

}