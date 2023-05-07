package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Year;

@Entity
@Getter
@Setter
@Table(name="admin")
public class Admin implements Serializable {
    public static final Long SerialVersionUID = 12341234L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_admin;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_keys")
    private LoginKeys id_keys;

    public Admin(){

    }

    public Admin(int id_admin, LoginKeys id_keys){
        this.id_admin = id_admin;
        this.id_keys = id_keys;
    }


}
