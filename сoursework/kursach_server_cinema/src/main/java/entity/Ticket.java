package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name="ticket")
public class Ticket  implements Serializable {
    public static final Long SerialVersionUID = 56341234L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_ticket;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private Client id_client;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_time")
    private Timetable id_time;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "id_place")
    private Place id_place;
    private String status;


    public Ticket(){

    }

    public Ticket(long id_ticket, Client id_client, Timetable id_time, Place id_place, String status){
    this.id_ticket = id_ticket;
    this.id_client = id_client;
    this.id_time = id_time;
    this.id_place = id_place;
    this.status = status;
    }
}
