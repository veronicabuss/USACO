/*
ID: volleyb3
PROB: cowcode
LANG: JAVA
 */

//  NAME                :   Nika Buss
//  GROUP               :   DM
//  PROBLEM ID          :   USACO Secret Cow Code
//  LAST MODIFIED       :   15 January 2016
//  DESCRIPTION         :   By taking off three outliers, determines min area of a field
//  HELPERS             :   None

import java.util.Scanner;
import java.io.*;
public class cowcode
{
    public static void main (String[] args) throws IOException
    {
        //initializing scanner and printwriter
        Scanner scan = new Scanner(new File("cowcode.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
        //scan.useDelimiter("[:,\\s]+");

        String original = scan.next();
        String full = new String(original);
        long index = scan.nextLong();
        String char1;
        long runTimes = index/original.length();

        for(long j = 0; j<runTimes; j++)
        {
          char1 = Character.toString(full.charAt(full.length()-1)); //can't use longs with charAt
          full+= char1 + full.substring(0,full.length()-1); //or with substring
        }
        //System.out.println(full);
        while(index > 2147483640)
        {
          //System.out.println("cutting");
          full = full.substring(2147483639);
        }
          System.out.println(full.substring(index-1,index));
        System.out.close();
    }
}
