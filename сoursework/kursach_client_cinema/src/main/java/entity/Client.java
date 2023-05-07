package entity;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.SimpleStyleableStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class  Client implements Serializable {
public static final Long SerialVersionUID = 43211234L;

    private long id_client;
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
/*

    public SimpleStringProperty getFirstName(){
        return new SimpleStringProperty(first_name);
    }
    public SimpleStringProperty getLastName(){
        return new SimpleStringProperty(last_name);
    }
    public SimpleStringProperty getNumberPhone(){
        return new SimpleStringProperty(phone_number);
    }
*/

}
