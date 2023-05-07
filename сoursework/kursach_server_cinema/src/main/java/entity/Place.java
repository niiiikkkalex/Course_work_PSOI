package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="place")
public class Place  implements Serializable {
    public static final Long SerialVersionUID = 52341234L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_place;
    @Column(name = "row_hall")
    private int row;
    @Column(name = "place_hall")
    private int place;

    public Place(){

    }

    public Place(long id_place, int row, int place){
        this.id_place = id_place;
        this.row = row;
        this.place = place;
    }
    public Place(int row, int place){
        this.row = row;
        this.place = place;
    }
}
