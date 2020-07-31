package xby.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeUtils {
    private static BCryptPasswordEncoder bc=new BCryptPasswordEncoder();

    public static String encodePassword(String password)
    {
        return bc.encode(password);
    }

    public static void main(String[] args) {
        String s="123";
        System.out.println(encodePassword(s));
    }
}
