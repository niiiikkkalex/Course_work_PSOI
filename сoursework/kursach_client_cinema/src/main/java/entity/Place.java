package entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Place  implements Serializable {
    public static final Long SerialVersionUID = 52341234L;
    private long id_place;
    private int row;
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
