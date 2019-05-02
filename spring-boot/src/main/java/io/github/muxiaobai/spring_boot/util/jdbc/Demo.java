package io.github.muxiaobai.spring_boot.util.jdbc;

public class Demo {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
