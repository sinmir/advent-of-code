package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    private static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/main/resources/day4Input.txt");
        BufferedReader br = new BufferedReader(fr);

        List<List<String>> charArray = new ArrayList<>();

        String st;
        while ((st = br.readLine()) != null)
        {
            String[] strSplit = st.split("");
            List<String> strList =
                    Arrays.asList(strSplit);

            charArray.add(strList);
        }

       // System.out.println(findXmas(charArray));
        System.out.println(findCorrectXmas(charArray));
    }

    private static int findCorrectXmas(List<List<String>> charArray) {
        int result = 0;

        // 00, 01, 02, 03
        // 10, 11, 12, 13
        // 20, 21, 22, 23
        // 30, 31, 32, 33

        for(int i=1; i<charArray.size()-1; i++)
        {
            for(int j=1; j<charArray.size()-1; j++)
            {
                if (charArray.get(i).get(j).equals("A")) {
                    if (((charArray.get(i - 1).get(j - 1).equals("M")) && (charArray.get(i + 1).get(j + 1).equals("S")))
                            || ((charArray.get(i - 1).get(j - 1).equals("S")) && (charArray.get(i + 1).get(j + 1)
                            .equals("M")))) {

                        if (((charArray.get(i - 1).get(j + 1).equals("M")) && (charArray.get(i + 1).get(j - 1).equals(
                                "S"))) || ((charArray.get(i - 1).get(j + 1).equals("S")) && (charArray.get(i + 1).get(
                                j - 1).equals("M")))) {
                            result++;
                        }
                    }
                }
            }

        }
        return result;
    }

    public static int findXmas(List<List<String>> charArray)
    {
        return findHor(charArray) + findVer(charArray) + findDiagFor(charArray) + findDiagBack(charArray);
    }

    public static int findHor(List<List<String>> charArray)
    {
        int result = 0;

        for(int i=0; i<charArray.size(); i++)
        {
            for(int j=0; j<charArray.size()-3; j++)
            {
                List<String> row = charArray.get(i);

                if(row.get(j).equals("X") && row.get(j+1).equals("M") && row.get(j+2).equals("A") && row.get(j+3).equals("S"))
                {
                    result++;
                    continue;
                }

                if(row.get(j).equals("S") && row.get(j+1).equals("A") && row.get(j+2).equals("M") && row.get(j+3).equals("X"))
                {
                    result++;
                }
            }

        }
        return result;
    }

    public static int findVer(List<List<String>> charArray)
    {
        int result = 0;

        for(int i=0; i<charArray.size(); i++)
        {
            for(int j=0; j<charArray.size()-3; j++)
            {
                if(charArray.get(j).get(i).equals("X") && charArray.get(j+1).get(i).equals("M") && charArray.get(j+2).get(i).equals("A") && charArray.get(j+3).get(i).equals("S"))
                {
                    result++;
                    continue;
                }

                if(charArray.get(j).get(i).equals("S") && charArray.get(j+1).get(i).equals("A") && charArray.get(j+2).get(i).equals("M") && charArray.get(j+3).get(i).equals("X"))
                {
                    result++;
                }
            }
        }

        return result;
    }

    public static int findDiagBack(List<List<String>> charArray)
    {
        int result = 0;

        // 00, 01, 02, 03
        // 10, 11, 12, 13
        // 20, 21, 22, 23
        // 30, 31, 32, 33

        //inc row and inc col


        //No of diagonals loop top half
        for(int i=0; i<charArray.size()-3; i++)
        {
            int row = 0;
            int col = i;

            while(col<charArray.size()-3)
            {
                if(charArray.get(row).get(col).equals("X") && charArray.get(row+1).get(col+1).equals("M") && charArray.get(row+2).get(col+2).equals("A") && charArray.get(row+3).get(col+3).equals("S"))
                {
                    result++;
                    row++;
                    col++;
                    continue;
                }

                if(charArray.get(row).get(col).equals("S") && charArray.get(row+1).get(col+1).equals("A") && charArray.get(row+2).get(col+2).equals("M") && charArray.get(row+3).get(col+3).equals("X"))
                {
                    result++;
                    row++;
                    col++;
                    continue;
                }

                row++;
                col++;

            }
        }

        //No of diagonals loop bottom half
        for(int j=1; j<charArray.size()-3; j++)
        {
            int row = j;
            int col = 0;

            while (row<charArray.size()-3)
            {
                if(charArray.get(row).get(col).equals("X") && charArray.get(row+1).get(col+1).equals("M") && charArray.get(row+2).get(col+2).equals("A") && charArray.get(row+3).get(col+3).equals("S"))
                {
                    result++;
                    row++;
                    col++;
                    continue;
                }

                if(charArray.get(row).get(col).equals("S") && charArray.get(row+1).get(col+1).equals("A") && charArray.get(row+2).get(col+2).equals("M") && charArray.get(row+3).get(col+3).equals("X"))
                {
                    result++;
                    row++;
                    col++;
                    continue;
                }

                row++;
                col++;
            }
        }

        return result;
    }

    public static int findDiagFor(List<List<String>> charArray)
    {
        int result = 0;
        //inc row and dec col
        int i=3;

        //No of diagonals loop top half
        for(; i<charArray.size(); i++)
        {
            int row = 0;
            int col = i;

            while(col-3>=0)
            {
                if(charArray.get(row).get(col).equals("X") && charArray.get(row+1).get(col-1).equals("M") && charArray.get(row+2).get(col-2).equals("A") && charArray.get(row+3).get(col-3).equals("S"))
                {
                    result++;
                    row++;
                    col--;
                    continue;
                }

                if(charArray.get(row).get(col).equals("S") && charArray.get(row+1).get(col-1).equals("A") && charArray.get(row+2).get(col-2).equals("M") && charArray.get(row+3).get(col-3).equals("X"))
                {
                    result++;
                    row++;
                    col--;
                    continue;
                }

                row++;
                col--;

            }
        }

        //No of diagonals loop bottom half
        for(int j=1; j<charArray.size()-3; j++)
        {
            int row = j;
            int col = i-1;

            while (row+3<charArray.size())
            {
                if(charArray.get(row).get(col).equals("X") && charArray.get(row+1).get(col-1).equals("M") && charArray.get(row+2).get(col-2).equals("A") && charArray.get(row+3).get(col-3).equals("S"))
                {
                    result++;
                    row++;
                    col--;
                    continue;
                }

                if(charArray.get(row).get(col).equals("S") && charArray.get(row+1).get(col-1).equals("A") && charArray.get(row+2).get(col-2).equals("M") && charArray.get(row+3).get(col-3).equals("X"))
                {
                    result++;
                    row++;
                    col--;
                    continue;
                }

                row++;
                col--;
            }
        }

        // 00, 01, 02, 03
        // 10, 11, 12, 13
        // 20, 21, 22, 23
        // 30, 31, 32, 33

        return result;
    }

}
