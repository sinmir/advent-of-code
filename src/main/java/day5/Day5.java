package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day5 {

    private static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/main/resources/day5Input.txt");
        BufferedReader br = new BufferedReader(fr);

        Map<String, Set<String>> pageOrdering = new HashMap<>();
        List<List<String>> updates = new ArrayList<>();

        String st;
        while ((st = br.readLine()) != null)
        {
            if(st.isBlank())
            {
                break;
            }
            String[] strSplit = st.split("\\|");
            if(pageOrdering.containsKey(strSplit[1]))
            {
                pageOrdering.get(strSplit[1]).add(strSplit[0]);
            }else
            {
                Set<String> set = new HashSet<>();
                set.add(strSplit[0]);
                pageOrdering.put(strSplit[1], set);
            }
        }

        while ((st = br.readLine()) != null) {
            String[] strSplit = st.split(",");
            List<String> strList = Arrays.asList(strSplit);
            updates.add(strList);
        }

        System.out.println(findCorrectUpdates(pageOrdering, updates));
        System.out.println(findInCorrectUpdates(pageOrdering, updates));
    }

    private static int findInCorrectUpdates(Map<String, Set<String>> pageOrdering, List<List<String>> updates) {
        List<List<String>> inCorrectUpdates = findInCorrectUpdatesList(pageOrdering, updates);

        int result = 0;

        for (List<String> update : inCorrectUpdates)
        {

            for (int i = 0; i < update.size(); i++)
            {
                for (int j = i + 1; j < update.size(); j++)
                {
                    String page = update.get(i);
                    if (pageOrdering.get(page).contains(update.get(j)))
                    {
                        Collections.swap(update, i, j);
                        j = i;
                    }
                }
            }
            result += Integer.valueOf(update.get(update.size() / 2));
        }
        return result;
    }


    private static List<List<String>> findInCorrectUpdatesList(Map<String, Set<String>> pageOrdering, List<List<String>> updates)
    {

        List<List<String>> result = new ArrayList<>();

        for(List<String> update: updates)
        {
            boolean isBreak = false;

            for(int i=0; i<update.size(); i++)
            {
                String page = update.get(i);

                for(int j=i+1; j<update.size(); j++)
                {

                    if(pageOrdering.get(page).contains(update.get(j)))
                    {
                        isBreak = true;
                        break;
                    }
                }

                if(isBreak)
                {
                    break;
                }

            }

            if(isBreak)
            {
                result.add(update);
            }

        }
        return result;
    }

    private static int findCorrectUpdates(Map<String, Set<String>> pageOrdering, List<List<String>> updates) {
        int result = 0;

        for(List<String> update: updates)
        {
            boolean isBreak = false;

            for(int i=0; i<update.size(); i++)
            {
                String page = update.get(i);

                for(int j=i+1; j<update.size(); j++)
                {

                    if(pageOrdering.get(page).contains(update.get(j)))
                    {
                        isBreak = true;
                        break;
                    }
                }

                if(isBreak)
                {
                    break;
                }

            }

            if(!isBreak)
            {
                result+= Integer.valueOf(update.get(update.size()/2));
            }

        }
        return result;
    }
}
