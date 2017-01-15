/*
ID: volleyb3
PROB: hps
LANG: JAVA
 */

//  NAME                :   Nika Buss
//  GROUP               :   DM
//  PROBLEM ID          :   USACO Counting hps
//  LAST MODIFIED       :   17 December 2016
//  DESCRIPTION         :   By taking off three outliers, determines minimum area of a field enclosure
//  HELPERS             :   None

import java.util.*;
import java.io.*;
public class hps
{
    public static void main (String[] args) throws IOException
    {
        //initializing scanner and printwriter
        Scanner scan = new Scanner(new File("hps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        //scan.useDelimiter("[:,\\s]+");
        //out.print("Enter data: ");

        int numMatches = scan.nextInt(), maxMatches=2, numP=0, numS=0, numH=0,max=0;
        char[] matches = new char[numMatches];
        int[] pp = new int[numMatches];
        int[] ss = new int[numMatches];
        int[] hh = new int[numMatches];
        int[] maxphs = new int[6];

        for(int j=0; j<numMatches; j++)
        {
          matches[j] = scan.next().charAt(0);
          if(matches[j] == 'P')
          {
            numP++;
          }
          else
          {
            if(matches[j] == 'H')
            {
              numH++;
            }
            else
            {
              numS++;
            }
          }
          pp[j] = numP;
          ss[j] = numS;
          hh[j] = numH;
        }

        if(numS >= numMatches-1 || numH >= numMatches-1 || numP >= numMatches-1)
        {
          out.println(numMatches);
          out.close();
        }
        int temp;
        for(int j=2; j<=numMatches-maxMatches/2; j++)
        {
          maxphs[0] = pp[j] + hh[numMatches-1]-hh[j];
          maxphs[1]  = pp[j] + ss[numMatches-1]-ss[j];
          maxphs[2]  = ss[j] + hh[numMatches-1]-hh[j];
          maxphs[3]  = ss[j] + pp[numMatches-1]-pp[j];
          maxphs[4]  = hh[j] + pp[numMatches-1]-pp[j];
          maxphs[5]  = hh[j] + ss[numMatches-1]-ss[j];

          temp = maxphs[0];
          for(int k=1; k<6; k++)
          {
            if(maxphs[k]>temp)
              temp = maxphs[k];
          }
          if(temp>max)
          {
            max = temp;
          }
        }

        out.println(max);
        out.close();
    }
}
