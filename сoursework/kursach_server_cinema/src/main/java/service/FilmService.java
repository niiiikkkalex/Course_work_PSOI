package service;

import dao.FilmDao;
import entity.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmService {
    public static int checkFilm(Film film){
        List<Film> films;
        films = FilmDao.getAllFilm();
        int check = 1;
        for(int i = 0; i < films.size(); i++){
            if(film.getName().equals(films.get(i).getName())) {
                check = 0;
                break;
            }
        }
        return check;
    }
}
