package site.dopplerxd.ui;

/**
 * @Author: Doppler
 * @Project: {puzzleGame}
 * @Package: {site.dopplerxd.ui}
 * @Date: {2024/3/26}
 * @Time: {16:11}
 */
public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
