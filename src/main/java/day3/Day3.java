package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    private static final String STRING = "mul\\(([0-9]{1,3}),([0-9]{1,3})\\)";
    private static final Pattern REGEX = Pattern.compile(STRING);
    private static final Pattern DO = Pattern.compile("do\\(\\)");
    private static final Pattern DONT = Pattern.compile("don't\\(\\)");

    private static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("src/main/resources/day3Input.txt");
        BufferedReader br = new BufferedReader(fr);

        StringBuilder sb = new StringBuilder();

        String st;
        while ((st = br.readLine()) != null)
        {
            sb.append(st);
        }

       System.out.println(mulInstructionsWithEnableDisable(sb.toString()));
    }

    public static int mulInstructions(List<String> instructions) {
        int result = 0;
        boolean isEnabled = true;

        for (String line : instructions) {
                Matcher matcher = REGEX.matcher(line);

                while (matcher.find())
                {
                    System.out.println(matcher.group(0));
                    result+= (Integer.valueOf(matcher.group(1)) * Integer.valueOf(matcher.group(2)));

                }
        }

        return result;
    }

    public static int mulInstructionsWithEnableDisable(String line) {
        int result = 0;
        boolean isEnabled = true;

        Matcher matcher = REGEX.matcher(line);
        Matcher doo = DO.matcher(line);
        Matcher dont = DONT.matcher(line);

        while (matcher.find()) {
            String group = matcher.group(0);
            System.out.println(group);
            map.put(matcher.start(), group);
        }

        while (doo.find()) {
            String group = doo.group(0);
            System.out.println(group);
            map.put(doo.start(), group);
        }

        while (dont.find()) {
            String group = dont.group(0);
            System.out.println(group);
            map.put(dont.start(), group);
        }

        List<Integer> sortedKeys = new ArrayList(map.keySet());
        Collections.sort(sortedKeys);

        for (Integer i : sortedKeys) {
            if (map.get(i).equals("do()")) {
                isEnabled = true;
            } else if (map.get(i).equals("don't()")) {
                isEnabled = false;
            } else if (isEnabled) {
                Matcher matcher2 = REGEX.matcher(map.get(i));

                if (matcher2.find()) {
                    result += (Integer.valueOf(matcher2.group(1)) * Integer.valueOf(matcher2.group(2)));
                }
            }
        }
        return result;
    }

}
