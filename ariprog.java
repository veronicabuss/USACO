/*
ID: volleyb3
PROB: ariprog
LANG: JAVA
 */

//  NAME                :   Nika Buss
//  GROUP               :   DM
//  PROBLEM ID          :   USACO Arithimetic Progressions
//  LAST MODIFIED       :   29 December 2016
//  DESCRIPTION         :   Finds how many arithimetic progressions exist in a certain number of bisquares
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

        //initializes and gets variables
        int lengthProg = scan.nextInt();
        int boundary = scan.nextInt();
        int max = boundary*boundary*2; //maximum number possible: M^2 + M^2
        int maxLength = max/(lengthProg-1); //maximum length possible
        int tempCount=0;
        boolean madeIt;
        
        //initializing objects
        List<AriObj> results = new ArrayList<AriObj>();
        boolean[] allNums = new boolean[max+1];
        Arrays.fill(allNums, false); //fills array of falses

        //if there exist a data at the point, true
        int totalTrue=0;
        for(int j=0; j<=boundary; j++)
        {
          for(int k=j; k<=boundary; k++)
          {
            allNums[j*j + k*k] = true;
            totalTrue++;
          }
        }

        //j is the starting number of the progression
        for(int j=0; j<max; j++)
        {
          if(!allNums[j]) //only looks where data exists
          {
            //System.out.println("NOPE " + j);
            continue;
          }
           
          for(int k=1; k<=maxLength; k++) //k=length of progression aka b (a+b, a+2b, a+3b etc)
          {
            madeIt = true; //starts true and only becomes false if no progression exists with this length
            for(int i=1; i<lengthProg; i++)//i= n (a, a+2b... a+nb)
            {
              if(j+k*i>max || !(allNums[j+k*i])) //if next number not part of progression
              {
                madeIt = false;
                break;
              }
              //System.out.println("YUP " + j + " " + k + " " + i + " " + (j+k*i));
            }
            if(!madeIt)
              continue;
            results.add(new AriObj(j,k)); //adds a new AriObj object to arraylist of totals
          }
        }
        if(results.isEmpty()) //if no progressions found
        {
          out.println("NONE");
          out.close();
        }
         
        //sorts results based on length, then starting point
        Collections.sort(results);
        
        //prints results
        for(int j=0; j<results.size(); j++)
          out.println(results.get(j).getStartNum() + " " + results.get(j).getLength());

        out.close(); //exits program
    }
}

//class to store objects to make it easier to sort
public class AriObj implements Comparator<AriObj>, Comparable<AriObj>
{
  //private data
  private int length;
  private int startingNum;
  
  //constructor
  AriObj(int startingNumtemp, int lengthtemp)
  {
    startingNum = startingNumtemp;
    length = lengthtemp;
  }
  
  public int getLength() //length getter
  {
      return length;
  }
  public int getStartNum() //start number getter
  {
    return startingNum;
  }
  
  //overrides standard compareTo method by comparing length
  public int compareTo(AriObj obj)
  {
    return ((Integer)(this.length)).compareTo((Integer)(obj.length));
  }
  //overrides the compare method to sort by starting number second
  public int compare(AriObj obj1, AriObj obj2)
  {
    return obj1.startingNum - obj2.startingNum;
  }
}
