package synch2MW;

/**
 * This program shows how multiple threads can safely access a data structure.
 * @version 1.32 2018-04-10
 * @author Cay Horstmann
 */
public class Interest1Test
{
   public static final int NACCOUNTS = 3;
   public static final double INITIAL_BALANCE = 1000;
   public static final double MAX_INTEREST = 10;
   public static final int DELAY = 10;
   
   public static void main(String[] args)
   {
      Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
      for (int i = 0; i < NACCOUNTS; i++)
      {
         int accountID = i;
         Runnable r = () -> {
            try
            {
               while (true)
               {
                  double interest = MAX_INTEREST * Math.random();
                  bank.addInterest1(accountID, interest);
                  
                  Thread.sleep((int) (DELAY * Math.random()));
               }
            }
            catch (InterruptedException e)
            {
            }            
         };
         Thread t = new Thread(r);
         t.start();
      }
   }
}
