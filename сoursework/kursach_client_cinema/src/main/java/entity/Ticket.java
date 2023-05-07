package entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class Ticket  implements Serializable {
    public static final Long SerialVersionUID = 56341234L;
    private long id_ticket;
    private Client id_client;
    private Timetable id_time;
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
