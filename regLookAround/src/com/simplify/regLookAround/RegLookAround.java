package com.simplify.regLookAround;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegLookAround {

    private static final String SAMPLE_STR1 = "http://www.forta.com";
    private static final String SAMPLE_STR2 = "https://mail.forta.com";
    private static final String SAMPLE_STR3 = "ftp://ftp.forta.com";

    public static void main(String[] args) {

        System.out.println("# 전방탐색 - 찾는 단어 포함");

        Pattern pattern = Pattern.compile(".+(?=:)");
        Matcher matcher = pattern.matcher(SAMPLE_STR1);
        if(matcher.find()){
            System.out.println(matcher.group());
        }

        pattern = Pattern.compile(".+(:)");
        matcher = pattern.matcher(SAMPLE_STR1);
        if(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
