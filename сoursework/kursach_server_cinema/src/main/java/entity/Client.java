package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
@Table(name="clients")
public class  Client implements Serializable {
public static final Long SerialVersionUID = 43211234L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_client;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_keys")
    private LoginKeys id_keys;
    private String first_name;
    private String last_name;
    private String phone_number;

    public Client(){

    }

    public Client(int id_client, LoginKeys id_keys, String first_name, String last_name, String phone_number){
        this.id_client = id_client;
        this.id_keys = id_keys;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    public Client(String first_name, String last_name, String phone_number){
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

}
