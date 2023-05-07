package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name="films")
public class Film  implements Serializable {
    public static final Long SerialVersionUID = 12344321L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_film;
    private String name;
    private String ganre;
    private String country;
    private int time;
    private int age;
    private int kol_tickets;
    private int kol_return;

    public Film(){

    }

    public Film(int id_film, String name, String ganre, int time, String country, int age, int kol_tickets, int kol_return){
        this.id_film = id_film;
        this.name = name;
        this.ganre = ganre;
        this.time = time;
        this.country = country;
        this.age = age;
        this.kol_tickets = kol_tickets;
        this.kol_return = kol_return;
    }

    public Film(String name, String ganre, int time, String country, int age, int kol_tickets, int kol_return){
        this.name = name;
        this.ganre = ganre;
        this.time = time;
        this.country = country;
        this.age = age;
        this.kol_tickets = kol_tickets;
        this.kol_return = kol_return;
    }
}
