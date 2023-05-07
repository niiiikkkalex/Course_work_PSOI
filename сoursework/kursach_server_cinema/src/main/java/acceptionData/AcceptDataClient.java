package acceptionData;

import dao.ClientDao;
import dao.FilmDao;
import dao.TicketDao;
import dao.TimetableDao;
import entity.*;
import org.example.CastList;
import service.FilmService;
import service.KeysService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AcceptDataClient {
    public void MenuClient(int choise, ObjectInputStream sois, ObjectOutputStream soos, int id, Client client) throws IOException, ClassNotFoundException {
        switch (choise) {
            case 1: {
                List<Film> films = new ArrayList<Film>();
                films = FilmDao.getAllFilm();
                soos.writeObject(films);
                break;
            }
            case 2: {
                switch ((int) sois.readObject()) {
                    case 1: {
                        List<Ticket> tickets = new ArrayList<Ticket>();
                        tickets = TicketDao.getAllTickets();
                        soos.writeObject(tickets);
                        List<Timetable> timetables = new ArrayList<Timetable>();
                        timetables = TimetableDao.getAllTimetable();
                        soos.writeObject(timetables);
                        break;
                    }
                    case 2: {
                        List<Ticket> tickets = new ArrayList<Ticket>();
                        Object obj = sois.readObject();
                        if (obj != null) {
                            tickets = CastList.castList(obj, Ticket.class);
                            for (Ticket i : tickets) {
                                i.setId_client(client);
                                TicketDao.save(i);
                                List<Film> films = new ArrayList<Film>();
                                films = FilmDao.getAllFilm();
                                for(int j = 0; j < films.size(); j++){
                                    if(films.get(j).getName().equals(i.getId_time().getFilms().getName())){
                                        films.get(j).setKol_tickets(films.get(j).getKol_tickets() + 1);
                                        FilmDao.update(films.get(j));
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 3:{
                        List<Ticket> tickets = new ArrayList<Ticket>();
                        tickets = TicketDao.getAllTickets();
                        soos.writeObject(tickets);
                        break;
                    }
                }
            break;
            }
            case 3: {
                switch((int)sois.readObject()){
                    case 1:{
                        List<Ticket> tickets = new ArrayList<Ticket>();
                        tickets = TicketDao.getAllTickets();
                        List<Ticket> ticketsClient = new ArrayList<Ticket>();
                        for(int i = 0; i < tickets.size(); i++){
                            if(tickets.get(i).getId_client().getId_client() == id && tickets.get(i).getStatus().equals("куплен")){
                                ticketsClient.add(tickets.get(i));
                            }
                        }
                        soos.writeObject(ticketsClient);
                        break;
                    }
                    case 2:{
                        Ticket ticket = new Ticket();
                        ticket = (Ticket) sois.readObject();
                        ticket.setStatus("возвращён");
                        TicketDao.update(ticket);
                        List<Film> films = new ArrayList<Film>();
                        films = FilmDao.getAllFilm();
                        for(int j = 0; j < films.size(); j++){
                            if(films.get(j).getName().equals(ticket.getId_time().getFilms().getName())){
                                films.get(j).setKol_tickets(films.get(j).getKol_tickets() - 1);
                                films.get(j).setKol_return(films.get(j).getKol_return() + 1);
                                FilmDao.update(films.get(j));
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 4: {
                String lastPass = (String) sois.readObject();
                String fname =(String) sois.readObject();
                String lname =(String) sois.readObject();
                String number =(String) sois.readObject();
                String login = (String) sois.readObject();
                String pass = (String) sois.readObject();

                LoginKeys key = new LoginKeys();
                key.setLogin(login);
                key.setPassword(pass);

                Client cl = new Client();
                cl.setFirst_name(fname);
                cl.setLast_name(lname);
                cl.setPhone_number(number);
                cl.setId_client(id);
                cl.setId_keys(key);

                soos.writeObject(KeysService.changeDataClient(lastPass, key, cl));
                break;
            }
            case 5: {
                ClientDao.delete(client);
                break;
            }
            case 6: {

                List<Ticket> tickets = new ArrayList<Ticket>();
                tickets = TicketDao.getAllTickets();
                List<Ticket> ticketsClient = new ArrayList<Ticket>();
                for(int i = 0; i < tickets.size(); i++){
                    if(tickets.get(i).getId_client().getId_client() == id){
                        ticketsClient.add(tickets.get(i));
                    }
                }
                soos.writeObject(ticketsClient);
                break;
            }
        }
    }
}
