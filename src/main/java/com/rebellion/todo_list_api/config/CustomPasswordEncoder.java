package com.rebellion.todo_list_api.config;

public class CustomPasswordEncoder {

    public String encode(String rawPassword) {
        char[] arr = rawPassword.toCharArray();
        String s = "afkakslkaksldk8798273gquiwjqnwmkln8798273sdjkabsjdgyuqwjejqweknjkhj89821asnlkdnkliujkjhkljkwggwjk45654865";
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if( c >= 1 && c <= 60 && c % 2 == 0) {
                sb.append(s.charAt(c+3));
            } else if(c > 60 && c <= 122) {
                sb.append(s.charAt(c-50));
            } else {
                sb.append(s.charAt(c-122));
            }
        }
        return sb.toString();
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return (this.encode(rawPassword)).equals(encodedPassword);
    }
}
