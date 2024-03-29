package synch1MWdeadlock;

/**
 * This program shows how multiple threads can safely access a data structure.
 * @version 1.32 2018-04-10
 * @author Cay Horstmann
 */
public class Total2Test
{
   public static final int NACCOUNTS = 5;
   public static final double INITIAL_BALANCE = 1000;
   public static final double MAX_AMOUNT = 1000;
   public static final double MAX_INTEREST = 10;
   public static final double LIMIT = 1200;
   public static final int DELAY = 10;
   
   public static void main(String[] args)
   {
      Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
      for (int i = 0; i < NACCOUNTS; i++)
      {
         int fromAccount = i;
         int accountID = i;
         Runnable r = () -> {
            try
            {
               while (true)
               {
                  int toAccount = (int) (bank.size() * Math.random());
                  double amount = MAX_AMOUNT * Math.random();
                  bank.transfer(fromAccount, toAccount, amount);
                  
                  double interest = MAX_INTEREST * Math.random();
                  bank.addInterest2(accountID, interest, LIMIT);
                  
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
