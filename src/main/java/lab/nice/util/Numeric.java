package lab.nice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Numeric {
    private static final String NUMERIC_REGEX = "^([-+])?(\\d+)(\\.\\d+)?$";
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
     * @param number1 the left hand number
     * @param number2 the right hand number
     * @return code with meaning [0, EQUAL], [>0, GREATER_THAN], [<0, LESS_THAN]
     */
    public static int compare(String number1, String number2) {
        Pattern pattern = Pattern.compile(NUMERIC_REGEX);
        Matcher matcher = pattern.matcher(number1);
        String[] group1 = splitNumber(matcher);
        matcher = pattern.matcher(number2);
        String[] group2 = splitNumber(matcher);
        int beforeDot = beforeDotCompare(group1[1], group2[1]);
        System.out.println("BeforeDot: " + beforeDot);
        int afterDot = afterDotCompare(group1[2], group2[2]);
        System.out.println("AfterDot: " + afterDot);
        if (beforeDot == 0) {

        } else {

        }
        return 0;
    }

    /**
     * Compare two unsigned integral number(before dot), the leading zero would be ignored.
     *
     * @param str1 the first unsigned integral number
     * @param str2 the second unsigned integral number
     * @return the code meaning: EQUAL(0), GREATER_THAN(>0), LESS_THAN(<0)
     */
    private static int afterDotCompare(String str1, String str2) {
        String num1 = str1, num2 = str2;
        if (num1 == null) {
            num1 = "";
        }
        if (num2 == null) {
            num2 = "";
        }
        num1 = num1.replaceAll(ZERO_ENDING_REGEX, "").replaceAll(DOT_ENDING_REGEX, "");
        num2 = num2.replaceAll(ZERO_ENDING_REGEX, "").replaceAll(DOT_ENDING_REGEX, "");
        return num1.compareTo(num2);
    }

    /**
     * Compare two dot leading decimal(after dot, dot included), the dot and ending zero would be ignored.
     *
     * @param str1 the first dot leading decimal
     * @param str2 the second dot leading decimal
     * @return the code meaning: EQUAL(0), GREATER_THAN(>0), LESS_THAN(<0)
     */
    private static int beforeDotCompare(String str1, String str2) {
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

    /**
     * Split number matched in matcher into three group: sign symbol, unsigned integral number(before dot), dot leading decimal(after dot, dot included)
     *
     * @param matcher matcher for numeric pattern
     * @return String Array contains three group in order, [sign-symbol, unsigned-integral-number, dot-leading-decimal]
     */
    private static String[] splitNumber(Matcher matcher) {
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
