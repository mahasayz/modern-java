package com.learn.fundamentals;

import java.util.function.Function;

class StringFormatter {
    public static String removeExtraSpaces(String str) { return str.replaceAll("\\s+", " "); }
    public static String removeSpecialChars(String str) { return str.replaceAll("[^a-zA-Z0-9\\s]", ""); }
    public static String peek(String str) {
        System.out.println("We got : " + str);
        return str;
    }
}

public class LambdaComposer {

    public static void main(String[] args) {
        Function<String, String> stringWithoutSpaces = StringFormatter::removeExtraSpaces;
        Function<String, Integer> formattingPipeline = stringWithoutSpaces
                .andThen(StringFormatter::removeSpecialChars)
                .andThen(StringFormatter::peek)
                .andThen(String::length);

        int stringLength = formattingPipeline.apply("me   you        and      us");

        System.out.println("Length of newly formatted string : " + stringLength);
    }

}
