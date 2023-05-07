package entity;

import java.io.Serializable;
import java.util.Objects;



public class LoginKeys  implements Serializable {
    public static final Long SerialVersionUID = 12344321L;

    private int id_keys;
    private String login;
    private String password;

    private String role = "client";

    public LoginKeys(){

    }

    public LoginKeys(int id_keys, String login, String password, String role){
        this.id_keys = id_keys;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public LoginKeys(String login, String password, String role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginKeys keys)) return false;
        return this.login.equals(keys.login) && this.password.equals(keys.password) && this.id_keys == keys.id_keys;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_keys, login, password, role);
    }

    public int getId_keys() {
        return id_keys;
    }

    public void setId_keys(int id_keys) {
        this.id_keys = id_keys;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
