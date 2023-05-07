package acceptionData;

import dao.ClientDao;
import dao.FilmDao;
import dao.TicketDao;
import dao.TimetableDao;
import entity.*;
import service.FilmService;
import service.KeysService;
import service.TimetableService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AcceptDataAdmin {
    public void MenuAdmin(int choise, ObjectInputStream sois, ObjectOutputStream soos) throws IOException, ClassNotFoundException {
        switch (choise){
            case 1:{
                String lastPass = (String) sois.readObject();
                String login = (String) sois.readObject();
                String pass = (String) sois.readObject();

                LoginKeys keys = new LoginKeys();
                keys.setLogin(login);
                keys.setPassword(pass);

                soos.writeObject(KeysService.changeLoginAdmin(lastPass, keys));

                break;
            }
            case 2:{
                switch((int) sois.readObject()){
                    case 1:{
                        List<Film> films = new ArrayList<Film>();
                        films = FilmDao.getAllFilm();
                        soos.writeObject(films);
                        break;
                    }
                    case 2:{
                        String name = (String) sois.readObject();
                        String genre = (String) sois.readObject();
                        String country = (String) sois.readObject();
                        int time = (int)sois.readObject();
                        int age = (int)sois.readObject();

                        Film films = new Film();
                        films.setName(name);
                        films.setGanre(genre);
                        films.setCountry(country);
                        films.setTime(time);
                        films.setAge(age);

                        int num = 0;
                        num = FilmService.checkFilm(films);
                        if(num == 1){
                            FilmDao.save(films);
                        }
                        soos.writeObject(num);
                        break;
                    }
                    case 3:{
                        Film delete = (Film)sois.readObject();
                        FilmDao.delete(delete);
                        break;
                    }
                    case 4:{
                        Film update = (Film)sois.readObject();
                        FilmDao.update(update);
                        break;
                    }
                }
                break;
            }
            case 3:{
                switch((int) sois.readObject()) {
                    case 1: {
                        List<Timetable> timetables = new ArrayList<Timetable>();
                        timetables = TimetableDao.getAllTimetable();
                        //timetables = TimetableService.checkData((String) sois.readObject());
                        soos.writeObject(timetables);
                        List<Film> films = new ArrayList<Film>();
                        films = FilmDao.getAllFilm();
                        soos.writeObject(films);
                        break;
                    }
                    case 2:{
                            Timetable timetable = new Timetable();
                            timetable = (Timetable) sois.readObject();
                            TimetableDao.save(timetable);
                            break;
                    }
                    case 3:{
                        Timetable timetable = new Timetable();
                        timetable = TimetableService.checkTimetable((Timetable) sois.readObject());
                        if(timetable != null) {
                            TimetableDao.delete(timetable);
                            soos.writeObject(1);
                        }
                        else{
                            soos.writeObject(0);
                        }
                        break;
                    }
                    case 4:{
                        Timetable timetable_new = new Timetable();
                        timetable_new = (Timetable) sois.readObject();
                        Timetable timetable = new Timetable();
                        timetable = TimetableService.checkTimetable(timetable_new);
                        if(timetable != null){
                            timetable_new.setId_time(timetable.getId_time());
                            //timetable.setPrice(timetable_new.getPrice());
                            //timetable.setFilms(timetable_new.getFilms());
                            TimetableDao.update(timetable_new);
                            soos.writeObject(1);
                        }
                        else{
                            soos.writeObject(0);
                        }
                        break;
                    }
                }
                break;
            }
            case 4:{
                List<Client> cl = new ArrayList<Client>();
                cl = ClientDao.getAllClient();
                soos.writeObject(cl);
                break;
            }
            case 5:{
                List<Film> films = new ArrayList<Film>();
                films = FilmDao.getAllFilm();
                soos.writeObject(films);
                break;
            }
            case 6:{
                //здесь просто схема зала выводится
                break;
            }
            case 7:{
                List<Film> films = new ArrayList<Film>();
                films = FilmDao.getAllFilm();
                soos.writeObject(films);
                break;
            }
            case 8:{
                List<Film> films = new ArrayList<Film>();
                films = FilmDao.getAllFilm();
                soos.writeObject(films);
                break;
            }
            case 9:{
                double revenue = 0;
                List<Ticket> tickets = new ArrayList<Ticket>();
                tickets = TicketDao.getAllTickets();
                for(int i = 0; i < tickets.size(); i++){
                    if(tickets.get(i).getStatus().equals("куплен")){
                        revenue += tickets.get(i).getId_time().getPrice();
                    }
                }
                soos.writeObject(revenue);
                break;
            }
            case 10:{
                double revenue = 0;
                List<Ticket> tickets = new ArrayList<Ticket>();
                tickets = TicketDao.getAllTickets();
                for(int i = 0; i < tickets.size(); i++){
                    if(tickets.get(i).getStatus().equals("возвращён")){
                        revenue += tickets.get(i).getId_time().getPrice();
                    }
                }
                soos.writeObject(revenue);
                break;
            }

        }
    }
}
