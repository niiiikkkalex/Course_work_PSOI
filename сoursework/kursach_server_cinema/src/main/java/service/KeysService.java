package service;

import com.mysql.cj.log.Log;
import dao.ClientDao;
import dao.FilmDao;
import dao.LoginKeysDao;
import entity.Client;
import entity.Film;
import entity.LoginKeys;

import java.util.ArrayList;
import java.util.List;


public class KeysService {
    public static LoginKeys getLoginKeys(LoginKeys key){
        List<LoginKeys> keys = new ArrayList<LoginKeys>();
        keys = LoginKeysDao.getAllAdmin();
        for(int i = 0; i < keys.size(); i++){
            if(key.getLogin().equals(keys.get(i).getLogin()) && key.getPassword().equals(keys.get(i).getPassword())) {
                key = keys.get(i);
                return key;
            }
        }
        return null;
    }
    public static int checkUser(LoginKeys key){
        List<LoginKeys> keys = new ArrayList<LoginKeys>();
        keys = LoginKeysDao.getAllAdmin();
        int check = 3;
        for(int i = 0; i < keys.size(); i++){
            if(key.getLogin().equals(keys.get(i).getLogin()) && key.getPassword().equals(keys.get(i).getPassword()) && keys.get(i).getRole().equals("admin")) {
                check = 1;
                break;
            }
            if(key.getLogin().equals(keys.get(i).getLogin()) && key.getPassword().equals(keys.get(i).getPassword()) && keys.get(i).getRole().equals("client")) {
                check = 2;
                break;
            }
        }
        return check;
    }

    public static int checkClient(LoginKeys key){
        List<LoginKeys> keys = new ArrayList<LoginKeys>();
        keys = LoginKeysDao.getAllAdmin();
        int check = 1;
        for(int i = 0; i < keys.size(); i++){
            if(key.getLogin().equals(keys.get(i).getLogin()) && key.getPassword().equals(keys.get(i).getPassword())) {

                check = 0;
                break;
            }
        }
        return check;
    }

    public static int checkLastPassAdmin(LoginKeys key){
        List<LoginKeys> keys = new ArrayList<LoginKeys>();
        keys = LoginKeysDao.getAllAdmin();
        int check = 1;
        for(int i = 0; i < keys.size(); i++){
            if(key.getPassword().equals(keys.get(i).getPassword())) {
                check = 0;
                break;
            }
        }
        return check;
    }

    public static int changeLoginAdmin(String lastPass, LoginKeys key){
        List<LoginKeys> keys = new ArrayList<LoginKeys>();
        keys = LoginKeysDao.getAllAdmin();
        int check = 0;
        for(int i = 0; i < keys.size(); i++){
            if(keys.get(i).getPassword().equals(lastPass) && keys.get(i).getRole().equals("admin")){
                key.setRole("admin");
                key.setId_keys(keys.get(i).getId_keys());
                LoginKeysDao.update(key);
                check = 1;
                break;
            }
        }
        return check;
    }

    public static int changeDataClient(String lastPass, LoginKeys key, Client cl){
        List<LoginKeys> keys = new ArrayList<LoginKeys>();
        keys = LoginKeysDao.getAllAdmin();
        Client update = new Client();
        int check = 0;
        for(int i = 0; i < keys.size(); i++){
            if(keys.get(i).getPassword().equals(lastPass) && keys.get(i).getRole().equals("client")){
                int id = keys.get(i).getId_keys();
                key.setId_keys(id);
                update = ClientDao.findClientByIdKeys(id);
                cl.setId_client(update.getId_client());
                cl.setId_keys(key);
                ClientDao.update(cl);
                check = 1;
                break;
            }
        }
        return check;
    }
}
