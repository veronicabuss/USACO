/*
ID: volleyb3
PROB: ariprog
LANG: JAVA
 */

//  NAME                :   Nika Buss
//  GROUP               :   DM
//  PROBLEM ID          :   USACO Arithimetic Progressions
//  LAST MODIFIED       :   29 December 2016
//  DESCRIPTION         :   By taking off three outliers, determines minimum area of a field enclosure
//  HELPERS             :   None

import java.util.*;
import java.io.*;
public class ariprog
{
    public static void main (String[] args) throws IOException
    {
        //initializing scanner and printwriter
        Scanner scan = new Scanner(new File("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        //scan.useDelimiter("[:,\\s]+");

        //System.out.print("Enter length prog and upper bound limit: ");
        int lengthProg = scan.nextInt();
        int boundary = scan.nextInt();
        int max = boundary*boundary*2; //maximum number possible: M^2 + M^2
        int maxLength = max/(lengthProg-1); //maximum length possible

        List<AriObj> results = new ArrayList<AriObj>(); //
        boolean[] allNums = new boolean[max+1];
        Arrays.fill(allNums, false);

        int totalTrue=0;
        for(int j=0; j<=boundary; j++)
        {
          for(int k=j; k<=boundary; k++)
          {
            allNums[j*j + k*k] = true;
            totalTrue++;
          }
        }

        int tempCount=0;
        boolean madeIt;
        for(int j=0; j<max; j++)
        {
          if(!allNums[j])
          {
            //System.out.println("NOPE " + j);
            continue;
          }

          for(int k=1; k<=maxLength; k++) //k=length aka b (a+b, a+2b, a+3b etc)
          {
            madeIt = true;
            for(int i=1; i<lengthProg; i++)//i= n (a, a+2b... a+nb)
            {
              if(j+k*i>max || !(allNums[j+k*i]))
              {
                madeIt = false;
                break;
              }
              //System.out.println("YUP " + j + " " + k + " " + i + " " + (j+k*i));
            }
            if(!madeIt)
              continue;
            results.add(new AriObj(j,k));
          }
        }
        if(results.isEmpty())
        {
          out.println("NONE");
          out.close();
        }
        Collections.sort(results);
        for(int j=0; j<results.size(); j++)
          out.println(results.get(j).getStartNum() + " " + results.get(j).getLength());

        out.close();
    }
}

class AriObj implements Comparator<AriObj>, Comparable<AriObj>
{
  private int length;
  private int startingNum;
  AriObj(int startingNumtemp, int lengthtemp)
  {
    startingNum = startingNumtemp;
    length = lengthtemp;
  }
  public int getLength()
  {
      return length;
  }
  public int getStartNum()
  {
    return startingNum;
  }
  //overrides standard compareTo method
  public int compareTo(AriObj obj)
  {
    return ((Integer)(this.length)).compareTo((Integer)(obj.length));
  }
  //overrides the compare method to sort the age
  public int compare(AriObj obj1, AriObj obj2)
  {
    return obj1.startingNum - obj2.startingNum;
  }
}
