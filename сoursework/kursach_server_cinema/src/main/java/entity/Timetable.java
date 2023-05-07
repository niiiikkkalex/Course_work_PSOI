package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="timetable")
public class Timetable  implements Serializable {
    public static final Long SerialVersionUID = 12345678L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_time;
    private Time time;
    private Date day;
    private int hall;
    private double price;
    @ManyToOne
    @JoinColumn(name = "id_film")
    private Film films;

    public Timetable(){

    }
    public Timetable(long id_time, Time time, Date day, int hall, double price){
        this.id_time = id_time;
        this.time = time;
        this.day = day;
        this.hall = hall;
        this.price = price;
    }
    public Timetable(Time time, Date day, int hall, double price){
        this.time = time;
        this.day = day;
        this.hall = hall;
        this.price = price;
    }
}
