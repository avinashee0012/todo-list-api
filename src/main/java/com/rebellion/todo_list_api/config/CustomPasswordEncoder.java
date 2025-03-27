package com.rebellion.todo_list_api.config;

public class CustomPasswordEncoder {

    public String encode(String rawPassword) {
        char[] arr = rawPassword.toCharArray();
        String s = "zxc5xcc5x598xczxc5bdjhpojklnJKgjha548asd8s4d5zxc5x598xczxc5x598iqkwdjc5x598xczxc5kasjkc5x598xczxc5bdjhpojklnJKgjha548a1asdf8489aw".toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if( c >= 1 && c <= 60 && c % 2 == 0) {
                sb.append(s.charAt(c+3));
            } else if(c > 60 && c <= 122) {
                sb.append(s.charAt(c-50));
            } else {
                sb.append(s.charAt(c-122));
            }
            for (int i = 0; i < (15-sb.length()); i++) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return (this.encode(rawPassword)).equals(encodedPassword);
    }
}
