/*
ID: volleyb3
PROB: pprime
LANG: JAVA
 */

//  NAME                :   Nika Buss
//  GROUP               :   DM
//  PROBLEM ID          :   USACO Prime Palindromes
//  LAST MODIFIED       :   22 December 2016
//  DESCRIPTION         :   Finds a list of prime palindrome in a given range
//  HELPERS             :   Usaco Hints

import java.util.*;
import java.io.*;
public class pprime
{
    private static int LOWER_BOUND;
    private static int UPPER_BOUND;
    private static List<Integer> nums = new ArrayList<Integer>();
    public static void main (String[] args) throws IOException
    {
        //initializing scanner and printwriter
        Scanner scan = new Scanner(new File("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        //scan.useDelimiter("[:,\\s]+");

        int lower = scan.nextInt();
        int upper = scan.nextInt();

        if(lower%2 == 0) //starts with odd values for good measure
          lower++;
        if(upper%2 == 0)
          upper--;
        LOWER_BOUND = lower; //sets lower and upper bounds to private variables
        UPPER_BOUND = upper;

        int lowerNumDigits = Integer.toString(lower).length();
        int upperNumDigits = Integer.toString(upper).length();

        for(int j=lowerNumDigits; j<=upperNumDigits; j++)
          genPalindromes(j); //generates all the palindromes 
                      //for all the various numbers of digits

       //prints results 
        for(int j=0; j<nums.size(); j++)
          out.println(nums.get(j));
        out.close(); //closes program
    }

    //method to determine if number is prime
    public static boolean isPrime(int num)
    {
        //if(num%2=0)     //(NOT NEEDED)
          //return false;
        //divides from 3 to square root of num to test if it is prime
        for (int i=3; i*i <= num; i+=2)
            if (num % i == 0) return false;
        return true; //no factors go into it, so prime
    }
    
    //generates palindromes, and then determines if they're prime
    public static void genPalindromes(int numDigits)
    {
      int palindrome;
      if(numDigits == 1)
      {
        for(int j=LOWER_BOUND; j<10; j+=2)
        {
          if(isPrime(j))
            nums.add(j);
        }
        return;
      }

      if(numDigits == 2) //generates two digit palindromes, then checks if prime
      {
        for(int d1=1; d1<=9; d1+=2)
        {
          palindrome = 10*d1 + d1;
          if(palindrome < LOWER_BOUND)
            continue; //keep going until you reach lower bound
          if(palindrome > UPPER_BOUND)
            return; //done with method, exceeded limit
          if(isPrime(palindrome))
            nums.add(palindrome);
        }
        return;
      }

      if(numDigits == 3) //generates three digit palindromes, then checks if prime
      {
        for(int d1=1; d1<=9; d1=d1+2) //prime numbers can only have odd last digits
        {
          for(int d2=0; d2<=9; d2++)
          {
              //generates aba number
              palindrome = 100*d1 + 10*d2 + d1; //first and last digis have to match
            //out.println(numDigits + " " + d1 + " " + d2 + " " + palindrome);
            if(palindrome < LOWER_BOUND)
              continue;
            if(palindrome > UPPER_BOUND)
              return;
            if(isPrime(palindrome))
              nums.add(palindrome);
          }
        }
        return;
      }

      if(numDigits == 4) //see comments for num == 3, rest are same except more loops
      {
        for(int d1=1; d1<=9; d1= d1+2)
        {
          for(int d2=0; d2<=9; d2++)
          {
            //generates abba
            palindrome = 1000*d1 + 100*d2 +10*d2 + d1;
            if(palindrome < LOWER_BOUND)
              continue;
            if(palindrome > UPPER_BOUND)
              return;
            if(isPrime(palindrome))
              nums.add(palindrome);
          }
        }
        return;
      }

      if(numDigits == 5)
      {
        for(int d1=1; d1<=9; d1= d1+2)
        {
          for(int d2=0; d2<=9; d2++)
          {
            for (int d3 = 0; d3 <= 9; d3++)
            {
              //generates abcba
              palindrome = 10000*d1 + 1000*d2 +100*d3 + 10*d2 + d1;
              if(palindrome < LOWER_BOUND)
                continue;
              if(palindrome > UPPER_BOUND)
                return;
              if(isPrime(palindrome))
                nums.add(palindrome);
            }
          }
        }
        return;
      }

      if(numDigits == 6)
      {
        for(int d1=1; d1<=9; d1= d1+2)
        {
          for(int d2=0; d2<=9; d2++)
          {
            for (int d3 = 0; d3 <= 9; d3++)
            {
              palindrome = 100000*d1 + 10000*d2 +1000*d3 + +100*d3 + 10*d2 + d1;
              if(palindrome < LOWER_BOUND)
                continue;
              if(palindrome > UPPER_BOUND)
                return;
              if(isPrime(palindrome))
                nums.add(palindrome);
            }
          }
        }
        return;
      }

      if(numDigits == 7)
      {
        for(int d1=1; d1<=9; d1= d1+2)
        {
          for(int d2=0; d2<=9; d2++)
          {
            for (int d3 = 0; d3 <= 9; d3++)
            {
              for (int d4=0; d4<=9; d4++)
              {
                palindrome = 1000000*d1 + 100000*d2 +10000*d3 + +1000*d4 + 100*d3 + 10*d2 + d1;
                if(palindrome < LOWER_BOUND)
                  continue;
                if(palindrome > UPPER_BOUND)
                  return;
                if(isPrime(palindrome))
                  nums.add(palindrome);
              }
            }
          }
        }
        return;
      }

      if(numDigits == 8)
      {
        for(int d1=0; d1<=9; d1= d1+2)
        {
          for(int d2=0; d2<=9; d2++)
          {
            for (int d3 = 0; d3 <= 9; d3++)
            {
              for (int d4=0; d4<=9; d4++)
              {
                palindrome = 10000000*d1 + 1000000*d2 +100000*d3 + +10000*d4 + 1000*d4 + 100*d3 + 10*d2 + d1;
                if(palindrome < LOWER_BOUND)
                  continue;
                if(palindrome > UPPER_BOUND)
                  return;
                if(isPrime(palindrome))
                  nums.add(palindrome);
              }
            }
          }
        }
      }
      return;
    }
}
