package com;

import com.sd.modules.security.security.JwtTokenProvider;

/**
 * @author siyang
 * @create 2020-01-12 21:09
 */
public class Test {
    public static void main(String[] args) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String siyang = jwtTokenProvider.createToken("siyang");
        System.out.println(siyang);
    }
}
