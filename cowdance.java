/*
ID: volleyb3
PROB: cowdance
LANG: JAVA
 */

//  NAME                :   Nika Buss
//  GROUP               :   DM
//  PROBLEM ID          :   USACO Counting cowdance
//  LAST MODIFIED       :   17 December 2016
//  DESCRIPTION         :   By taking off three outliers, determines minimum area of a field enclosure
//  HELPERS             :   None

import java.util.*;
import java.io.*;
public class cowdance
{
    public static void main (String[] args) throws IOException
    {
      //initializing scanner and printwriter
      Scanner scan = new Scanner(new File("cowdance.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));

      int numDancers = scan.nextInt(); //number items
      int sizeBin = scan.nextInt();
      int[] dancers = new int[numDancers]; //array of items
      //Integer[] dancersDecreasing = new Integer[numDancers];

      for (int j=0; j<numDancers; j++)
      {
        dancers[j] = scan.nextInt();
        //dancersDecreasing[j] = dancers[j];
      }
      Arrays.sort(dancers);
      //Arrays.sort(dancersDecreasing, Collections.reverseOrder());

      int binsNeeded = binPacking(dancers, sizeBin, numDancers);
      out.println(binsNeeded);
      out.close();
   }

  public static int binPacking(int[] cows, int sizeOfBin, int totalDancers)
  {
    int binCount = 0;
    int[] binValues = new int[totalDancers];
    for (int i = 0; i < binValues.length; i++)
      binValues[i] = sizeOfBin;

    for (int i = 0; i < totalDancers; i++)
      for (int j = 0; j < binValues.length; j++)
      {
        if (binValues[j] - cows[i] >= 0)
        {
          binValues[j] -= cows[i];
          break;
        }
      }

    for (int i = 0; i < binValues.length; i++)
      if (binValues[i] != sizeOfBin)
        binCount++;

    return binCount;
  }
}
