package  io.github.muxiaobai.spring_my_demo.demo.vo;

import io.github.muxiaobai.spring_my_demo.mybatis.annotation.Column;

public class User
{
    @Column
    private String username;
    @Column
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
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
