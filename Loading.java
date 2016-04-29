class Loading
{ 
    public static void display()
    {
        String s = "[          ]";
        String c[] = {"*","U","P","A","M","A","N","Y","U","*"};
        int n=1, m=n+1, y=0;
        for (int x=0; x<=100; x++)
        {
            y=x;
            System.out.print("Loading..."+"\n"+"\f                         "+x+" %"+"\n"+"                    "+s);
            for (long i=0;i<=111259499; i++)
            {
            }

            if(y%10==0 && m<12)
            {
                
                s = (s.substring(0,n)+c[n-1]+s.substring(m,s.length()));
            
                n++;
                m++;
            }
            if(x==100)
                System.out.println("\n"+"                  Loading complete.");
        }
    }
}

