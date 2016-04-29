import java.util.Date;
class DoDate 
{
   public static String display() 
   {
       // Instantiate a Date object
       Date date = new Date();
       // display time and date using toString()
       String s = date.toString();
       s = s.substring(8,10)+" "+s.substring(4,7)+" "+s.substring((s.length()-4),s.length());
       //eg. 28 Oct 2012
       return s;
   }
}