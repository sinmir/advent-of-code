package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/main/resources/day2Input.txt");
        BufferedReader br = new BufferedReader(fr);

        List<List<Integer>> listOfList = new ArrayList<>();

        String st;
        while ((st = br.readLine()) != null)
        {
            String [] split = st.split(" ");
            List<Integer> report = new ArrayList<>();

            for(String s : split)
            {
                report.add(Integer.valueOf(s));
            }
            listOfList.add(report);
        }

       System.out.println(safeReports(listOfList));
    }

    public static boolean isSafeWithDampner(List<Integer> report) {
        if (isSafe(report)) {
            return true;
        }

        for (int i = 0; i < report.size(); i++) {
            List<Integer> modifiedList = new ArrayList<>(report);
            modifiedList.remove(i);
            if (isSafe(modifiedList)) {
                return true;
            }
        }

        return false; 
    }

    public static int safeReports(List<List<Integer>> listOfList)
    {
        int result = 0;

        for(List<Integer> report : listOfList)
        {
            if(isSafeWithDampner(report))
            {
                result++;
            }
        }
        return result;
    }

    private static boolean isSafe(List<Integer> report) {
        boolean isIncreasing = false;

        if(report.get(0) - report.get(1) < 0)
        {
            isIncreasing = true;
        } else if (report.get(0) - report.get(1) == 0) {
            return false;
        }

        for(int i = 0; i< report.size()-1; i++)
        {
            int diff = report.get(i) - report.get(i+1);

            if(isIncreasing)
            {
                if(!(diff <= -1 && diff >= -3))
                {
                    return false;
                }
            }
            else
            {
                if(!(diff >= 1 && diff <=3))
                {
                    return false;
                }
            }
        }

       return true;
    }
}
