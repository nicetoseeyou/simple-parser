package lab.nice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Numeric {
    private static final String NUMERIC_REGEX = "^(\\-|\\+)?(\\d+)(\\.\\d+)?$";
    private static final String ZERO_LEADING_REGEX = "^0+(?!$)";
    private static final String ZERO_ENDING_REGEX = "0+?$";
    private static final String DOT_ENDING_REGEX = "[.]$";

    public static boolean isNumeric(String str) {
        return str.matches(NUMERIC_REGEX);
    }

    /**
     * compare floating-point number in String,
     * Accept format:
     * <p>
     * 1) integer, like 0, 100, -100, +100
     * 2) floating-type, like 1.01, -2.02, +3.03
     * <p>
     * returning:
     * 0 -> number1 equals number2
     * less than 0 -> number1 less than number2
     * greater than 0 -> number1 greater than number2
     * <p>
     * Notice: input String must trim
     *
     * @param number1
     * @param number2
     * @return
     */
    public static int compare(String number1, String number2) {
        Pattern pattern = Pattern.compile(NUMERIC_REGEX);
        Matcher matcher = pattern.matcher(number1);
        String[] group1 = extractMatcherGroup(matcher);
        matcher = pattern.matcher(number2);
        String[] group2 = extractMatcherGroup(matcher);
        System.out.print("g1=");
        for (int i = 0; i < group1.length; i++) {
            System.out.print(group1[i] + " ");
        }
        System.out.println();
        System.out.print("g2=");
        for (int i = 0; i < group2.length; i++) {
            System.out.print(group2[i] + " ");
        }
        System.out.println();
        System.out.println(afterDot(".00001", ".001"));
        int beforeDot = beforeDot(group1[1], group2[1]);
        if (beforeDot == 0) {

        } else {

        }
        return 0;
    }

    private static int afterDot(String str1, String str2) {
        String num1 = str1.replaceAll(ZERO_ENDING_REGEX, "").replaceAll(DOT_ENDING_REGEX, "");
        String num2 = str2.replaceAll(ZERO_ENDING_REGEX, "").replaceAll(DOT_ENDING_REGEX, "");
        return num1.compareTo(num2);
    }

    private static int beforeDot(String str1, String str2) {
        String noZero1 = str1.replaceFirst(ZERO_LEADING_REGEX, "");
        String noZero2 = str2.replaceFirst(ZERO_LEADING_REGEX, "");
        int length1 = noZero1.length(), lenght2 = noZero2.length();
        if (length1 != lenght2) {
            return length1 - lenght2;
        } else {
            char[] c1 = noZero1.toCharArray(), c2 = noZero2.toCharArray();
            for (int i = 0; i < length1; i++) {
                if (c1[i] != c2[i]) {
                    return c1[i] - c2[i];
                }
            }
        }
        return 0;
    }

    private static String[] extractMatcherGroup(Matcher matcher) {
        String[] group;
        if (matcher.find()) {
            int count = matcher.groupCount();
            group = new String[count];
            for (int i = 1; i <= count; i++) {
                group[i - 1] = matcher.group(i);
            }
        } else {
            group = new String[]{};
        }
        return group;
    }
}
