package entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Admin implements Serializable {
    public static final Long SerialVersionUID = 12341234L;

    private long id_admin;

    private LoginKeys id_keys;

    public Admin(){

    }

    public Admin(int id_admin, LoginKeys id_keys){
        this.id_admin = id_admin;
        this.id_keys = id_keys;
    }


}
