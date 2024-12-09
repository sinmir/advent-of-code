package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/main/resources/day1Input.txt");
        BufferedReader br = new BufferedReader(fr);

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        String st;
        while ((st = br.readLine()) != null)
        {
            String [] split = st.split("   ");
            l1.add(Integer.valueOf(split[0]));
            l2.add(Integer.valueOf(split[1]));
        }

        System.out.println(totalDistance(l1, l2));
        System.out.println(similarityScore(l1, l2));
    }

    public static int similarityScore(List<Integer> l1, List<Integer> l2)
    {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int result = 0;

        for(Integer i : l2)
        {
            freqMap.compute(i, (k, v) -> v == null? 1: v+1);
        }

        for(int i : l1)
        {
            result+= freqMap.get(i) == null? 0 : freqMap.get(i)*i;
        }

        return result;
    }

    public static int totalDistance(List<Integer> l1, List<Integer> l2)
    {
        int result = 0;
        l1.sort(Comparator.naturalOrder());
        l2.sort(Comparator.naturalOrder());

        for(int i=0; i<l1.size(); i++)
        {
            int diff = l1.get(i) - l2.get(i);
            result+= diff < 0? -1 * diff: diff;
        }

        return result;
    }

}
