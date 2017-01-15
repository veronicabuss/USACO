/*
ID: volleyb3
PROB: sprime
LANG: JAVA
 */

//  NAME                :   Nika Buss
//  GROUP               :   DM
//  PROBLEM ID          :   USACO SuperPrime rib
//  LAST MODIFIED       :   22 December 2016
//  DESCRIPTION         :   Generates list of prime numbers of certain length
//                      :   which are also prime numbers in their other forms
//  HELPERS             :   None

import java.util.*;
import java.io.*;
public class sprime
{
    //declares private variables to be used by methods
    private static List<Integer> nums = new ArrayList<Integer>();

    public static void main (String[] args) throws IOException
    {
        //initializing scanner and printwriter
        Scanner scan = new Scanner(new File("sprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
        //scan.useDelimiter("[:,\\s]+");

        int length = scan.nextInt(); //gets length of rib
        genRibs(length); //generates ribs of that length

        //prints data
        for(int j=0; j<nums.size(); j++)
        {
          out.println(nums.get(j));
        }
        out.close(); //exits program
    }

    //method to determine if number is prime
    public static boolean isPrime(int num)
    {
        if(num==2)
          return true;
        if(num%2==0 || num<2)
          return false;
        //divides from 3 to square root of num to test if it is prime
        for (int i=3; i*i <= num; i+=2)
            if (num % i == 0) return false;
        return true; //no factors go into it, so prime
    }

    //method that generates each rib with only odd digits, THEN checks if it is
    //prime and if its other forms are also prime
    public static void genRibs(int numDigits)
    {
      int rib;
      if(numDigits == 1) //if length of rib is one
      {
        //potentially nums 3-9
        nums.add(2); //because 2 is prime
        for(int j=3; j<10; j+=2) //adds by 2 to get odd nums
        {
          if(isPrime(j))
            nums.add(j);
        }
        return;
      }

      //generates two digits ribs, checks if prime and adds to final ans
      //see numDigits == 3 for detailed comments
      if(numDigits == 2)
      {
        for(int d1=2; d1<=9; ++d1)
        {
          if(d1!=2 && d1%2==0)
            continue;
          for(int d2=1; d2<=9; d2+=2)
          {
            rib = 10*d1 + d2;
            if(isPrime(rib) && isPrime((rib-rib%10)/10))
              nums.add(rib);
          }
        }
        return;
      }

      //generates three digit ribs, checks if it and all of its forms are prime
      //and adds to final answer
      if(numDigits == 3)
      {
        for(int d1=2; d1<=9; ++d1) //starts at 2 bc 2 is prime
        {
          if(d1!=2 && d1%2==0) //skips even numbers
            continue;
          for(int d2=1; d2<=9; d2+=2) //adds odd nums only to number
          {
            //skips 5 because at some point 5 is on the end of the number, and
            //therefore isn't prime
            if(d2==5)
              continue;
            for(int d3=1; d3<=9; d3+=2)
            {
              if(d3==5) //same comment
                continue;
              rib = 100*d1 + 10*d2+ d3; //generates num using digits from loops
              //checks if other forms are also prime and adds if they are
              if(isPrime(rib) && isPrime((rib-rib%10)/10) && isPrime((rib-rib%100)/100))
                nums.add(rib);
            }
          }
        }
        return;
      }

      //see comments for ==3. All following are same except with one more loop
      if(numDigits == 4)
      {
        for(int d1=2; d1<=9; ++d1)
        {
          if(d1!=2 && d1%2==0)
            continue;
          for(int d2=1; d2<=9; d2+=2)
          {
            if(d2==5)
              continue;
            for(int d3=1; d3<=9; d3+=2)
            {
              if(d3==5)
                continue;
              for(int d4=1; d4<=9; d4+=2)
              {
                if(d4==5)
                  continue;
                rib = 1000*d1 + 100*d2+ 10*d3 + d4;
                if(isPrime(rib) && isPrime((rib-rib%10)/10) && isPrime((rib-rib%100)/100) && isPrime((rib-rib%1000)/1000))
                  nums.add(rib);
                }
              }
            }
          }
        return;
      }

      if(numDigits == 5)
      {
        for(int d1=2; d1<=9; ++d1)
        {
          if(d1!=2 && d1%2==0)
            continue;
          for(int d2=1; d2<=9; d2+=2)
          {
            if(d2==5)
              continue;
            for(int d3=1; d3<=9; d3+=2)
            {
              if(d3==5)
                continue;
              for(int d4=1; d4<=9; d4+=2)
              {
                if(d4==5)
                  continue;
                for(int d5=1; d5<=9; d5+=2)
                {
                  if(d5==5)
                    continue;
                  rib = 10000*d1 + 1000*d2+ 100*d3 + 10*d4 + d5;
                  if(isPrime(rib) && isPrime((rib-rib%10)/10) && isPrime((rib-rib%100)/100) && isPrime((rib-rib%1000)/1000) && isPrime((rib-rib%10000)/10000))
                    nums.add(rib);
                }
              }
            }
          }
        }
        return;
      }

      if(numDigits == 6)
      {
        for(int d1=2; d1<=9; ++d1)
        {
          if(d1!=2 && d1%2==0)
            continue;
          for(int d2=1; d2<=9; d2+=2)
          {
            if(d2==5)
              continue;
            for(int d3=1; d3<=9; d3+=2)
            {
              if(d3==5)
                continue;
              for(int d4=1; d4<=9; d4+=2)
              {
                if(d4==5)
                  continue;
                for(int d5=1; d5<=9; d5+=2)
                {
                  if(d5==5)
                    continue;
                  for(int d6=1; d6<=9; d6+=2)
                  {
                    if(d6==5)
                      continue;
                    rib = 100000*d1 + 10000*d2+ 1000*d3 + 100*d4 + 10*d5 + d6;
                    if(isPrime(rib) && isPrime((rib-rib%10)/10) && isPrime((rib-rib%100)/100) && isPrime((rib-rib%1000)/1000) && isPrime((rib-rib%10000)/10000) && isPrime((rib-rib%100000)/100000))
                      nums.add(rib);

                  }
                }
              }
            }
          }
        }
        return;
      }

      if(numDigits == 7)
      {
        for(int d1=2; d1<=9; ++d1)
        {
          if(d1!=2 && d1%2==0)
            continue;
          for(int d2=1; d2<=9; d2+=2)
          {
            if(d2==5)
              continue;
            for(int d3=1; d3<=9; d3+=2)
            {
              if(d3==5)
                continue;
              for(int d4=1; d4<=9; d4+=2)
              {
                if(d4==5)
                  continue;
                for(int d5=1; d5<=9; d5+=2)
                {
                  if(d5==5)
                    continue;
                  for(int d6=1; d6<=9; d6+=2)
                  {
                    if(d6==5)
                      continue;
                    for(int d7=1; d7<=9; d7+=2)
                    {
                      if(d7==5)
                        continue;
                      rib = 1000000*d1 + 100000*d2+ 10000*d3 + 1000*d4 + 100*d5 + 10*d6 + d7;
                      if(isPrime(rib) && isPrime((rib-rib%10)/10) && isPrime((rib-rib%100)/100) && isPrime((rib-rib%1000)/1000) && isPrime((rib-rib%10000)/10000) && isPrime((rib-rib%100000)/100000) && isPrime((rib-rib%1000000)/1000000))
                        nums.add(rib);
                    }
                  }
                }
              }
            }
          }
        }
        return;
      }

      if(numDigits == 8)
      {
        for(int d1=2; d1<=9; ++d1)
        {
          if(d1!=2 && d1%2==0)
            continue;
          for(int d2=1; d2<=9; d2+=2)
          {
            if(d2==5)
              continue;
            for(int d3=1; d3<=9; d3+=2)
            {
              if(d3==5)
                continue;
              for(int d4=1; d4<=9; d4+=2)
              {
                if(d4==5)
                  continue;
                for(int d5=1; d5<=9; d5+=2)
                {
                  if(d5==5)
                    continue;
                  for(int d6=1; d6<=9; d6+=2)
                  {
                    if(d6==5)
                      continue;
                    for(int d7=1; d7<=9; d7+=2)
                    {
                      if(d7==5)
                        continue;
                      for(int d8=1; d8<=9; d8+=2)
                      {
                        if(d8==5)
                          continue;
                        rib = 10000000*d1 + 1000000*d2+ 100000*d3 + 10000*d4 + 1000*d5 + 100*d6 + 10*d7 + d8;
                        if(isPrime(rib) && isPrime((rib-rib%10)/10) && isPrime((rib-rib%100)/100) && isPrime((rib-rib%1000)/1000) && isPrime((rib-rib%10000)/10000) && isPrime((rib-rib%100000)/100000) && isPrime((rib-rib%1000000)/1000000) && isPrime((rib-rib%10000000)/10000000))
                          nums.add(rib);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      return;
    }
  }
}
