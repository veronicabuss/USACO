/*
ID: volleyb3
PROB: calfflac
LANG: JAVA
 */

//  NAME                :   Nika Buss
//  GROUP               :   DM
//  PROBLEM ID          :   USACO Arithimetic Progressions
//  LAST MODIFIED       :   25 December 2016
//  DESCRIPTION         :   By taking off three outliers, determines minimum area of a field enclosure
//  HELPERS             :   None

import java.util.*;
import java.io.*;
public class calfflac
{
    private static String saying;
    private static String origUpper;
    private static String palFound;
    private static int lengthOfActual=1;
    public static void main (String[] args) throws IOException
    {
        //initializing scanner and printwriter
        Scanner scan = new Scanner(new File("calfflac1.txt"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
        //scan.useDelimiter("[:,\\s]+");

        //while file.hasNext() ... changed+=scan.nextLine();
        String original = "", tempPal = "";
        while(scan.hasNext())
        {
          original += scan.nextLine();
          original+= "\n";
        }
        String origUppercase = new String(original.toUpperCase());
        String changed = new String(original);
        String finalPal = Character.toString(original.charAt(0));
        boolean twoInRow = false;
        int minPal = 1, maxPal = 1, minIndex = 0, maxIndex = 0, deleted, endInd;

        changed = changed.replaceAll("[^A-Za-z]+", "").toUpperCase(); //takes out all space and punctuation
        changed += "!"; //so it can check the end of the line

        //sets to private variables so methods can use them
        saying = new String(changed);
        origUpper = new String(origUppercase);
        deleted = original.length()-changed.length();

        //printing
        //for(int j=0; j<origUpper.length(); j++)
        {
          //System.out.print(j%10);
        }
        //System.out.println();

        //assuming palindrome of three or more, goes thru string to find
        for(int j=0; j<changed.length()-2; j++)
        {
          //checks if two letters repeated for palindrome of 2
          if(!twoInRow && changed.charAt(j) == changed.charAt(j+1))
          {
            twoInRow = true;
            minPal = 2;
            minIndex = j;
            finalPal = changed.substring(j,j+1);
          }

          //if there exists a reverse of a substring in the string
          if(changed.contains(reverse(changed.substring(j,j+2))))
          {
            //finds the length of the palindrome
            int temp = findPal(changed.substring(j,j+2),j,j+2);
            if(temp>maxPal) //sets if largest palindrome found so far
            {
              maxPal = temp;
              maxIndex = j;
              finalPal = new String(palFound);
              j+=(temp/2)-1; //skips forward in the string to avoid repeats
            }
            if(temp==maxPal)
            {
              System.out.println("\n\n\nMEEEEE\n\n\n");
              j+=(temp/2)-1;
            }
          }
        }
        if(minPal>maxPal) //if palindrome of length 3 or greater is NOT found
        {
          maxPal = minPal;
          maxIndex = minIndex;
        }

        maxIndex = findPalStart(maxIndex, finalPal);
        //endInd = indexPalEnd(finalPal, maxIndex);

        System.out.println(maxPal);
        System.out.println(original.substring(maxIndex,maxIndex+lengthOfActual));
        System.out.close();
    }

    //reverses a string
    public static String reverse(String stg)
    {
        //converts string to char array for ease and speed
        char[] stgArr = stg.toCharArray();
        int stgLength = stgArr.length;
        //flip-flops chars in array and outputs as string
        for (int i=0; i<stgLength/2; i++)
        {
            char temp = stgArr[i];
            stgArr[i] = stgArr[stgLength-1-i];
            stgArr[stgLength-1-i] = temp;
        }
        return new String(stgArr);
    }

    //checks if palindrome using recursion
    public static boolean isPal(String pal)
    {
        //base case, checks chars on both sides and if it gets to this point,
        //every char has matched and is palindrome
        if(pal.length() == 0 || pal.length() == 1)
            return true;
        if(pal.charAt(0) == pal.charAt(pal.length()-1)) //if chars are equal
            return isPal(pal.substring(1, pal.length()-1)); //check next chars

        //if something is not equal, not palindrome
        return false;
    }

    //finds length if palindrome, else returns zero
    public static int findPal(String curSeq, int start, int end)
    {
      if(curSeq.matches("(.)\\1*") && curSeq.length()>3)
      {
        int count = 0;
        while(curSeq.matches("(.)\\1*"))
        {
          curSeq += saying.charAt(end+count);
          count++;
        }
        //aaaaa4aaaaa
        //01234567890
        //System.out.println(curSeq.substring(0,curSeq.length()-2).equals(saying.substring(curSeq.length()+1, 2*(curSeq.length())-1)));
        //System.out.println(curSeq.substring(0,curSeq.length()-2) + "\n\n" + saying.substring(curSeq.length()+1, 2*(curSeq.length())-1));
        if(start+curSeq.length()+1<saying.length() && curSeq.substring(0,curSeq.length()-2).equals(saying.substring(start+curSeq.length()+1, start+ 2*(curSeq.length())-1)))
        {
          if(curSeq.length()%10==0)
            System.out.println("NN " + curSeq.length());
          palFound = new String(saying.substring(start,start+2*(curSeq.length()-1)));
          return (2*(curSeq.length()-1)+1);
        }
        else
        {
          if(curSeq.length()%10==0)
            System.out.println("MM " + curSeq.length());
          palFound = curSeq.substring(0,curSeq.length()-1);
          return curSeq.length()-1;
          //return (curSeq.length()-1)*2+1;
        }
      }

      //finds if the string contains more of the palindrome by recursing
      if(saying.contains(reverse(curSeq + saying.charAt(end))) && end!=saying.length()-1)
      {
        //System.out.println("RECURSING: " + curSeq);
        return findPal(curSeq+saying.charAt(end),start,end+1);
      }
      else{
        //base case if string found is together and is palindrome, return length
        if(isPal(curSeq))
        {
          palFound = new String(curSeq);
          return (end-start);
        }
        else //not palindrome or not together, return zero
        {
          return 0;
        }
      }
    }

    //finds index of palindrome in original string
    public static int findPalStart(int curIndex, String finPal)
    {
      int tempOrigIndex = curIndex;
      int actualLength = 0;
      int count = 0;
      //System.out.println("STARTING INDEX: "+curIndex+ " " + finPal);
      for(int j=tempOrigIndex; j<origUpper.length(); ++j)
      {
          if(origUpper.charAt(j) == finPal.charAt(0))
          {
            actualLength = 1;
            count = 1;
            if(j+actualLength>=origUpper.length())
              break;
            while(origUpper.charAt(j+actualLength) == finPal.charAt(count) || !(Character.isLetter(origUpper.charAt(j+actualLength))) ||Character.isWhitespace(origUpper.charAt(j+actualLength)))
            {
              //System.out.println("2origUpper: " + origUpper.charAt(j+actualLength) + " finPal: " + finPal.charAt(count) + " " + j + " " + count+ " " + actualLength + Character.isLetter(j+actualLength));
              if(origUpper.charAt(j+actualLength) == finPal.charAt(count))
                count++;
              actualLength++;
              //System.out.println("3origUpper: " + origUpper.charAt(j+actualLength) + " finPal: " + finPal.charAt(count) + " " + j+ " " + count+ " " + actualLength);
              if(actualLength+j>=origUpper.length() || count>=finPal.length())
                break;
            }
            //System.out.println("Nope: 4origUpper: " + origUpper.charAt(j+actualLength) + " finPal: " + finPal.charAt(count) + " " + j+ " " + count+ " " + actualLength);
            if(count>=finPal.length() && actualLength>lengthOfActual)
            {
              curIndex = j;
              lengthOfActual = actualLength;
              break;
            }
          }
      }
      return curIndex;
    }
}
