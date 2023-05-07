package service;

import dao.LoginKeysDao;
import dao.TimetableDao;
import entity.LoginKeys;
import entity.Timetable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TimetableService {
    public static Timetable checkTimetable(Timetable timetable){
        List<Timetable> timetables = new ArrayList<Timetable>();
        timetables = TimetableDao.getAllTimetable();

        for(int i = 0; i < timetables.size(); i++){
            if(timetable.getTime().toString().equals(timetables.get(i).getTime().toString()) && timetable.getDay().toString().equals(timetables.get(i).getDay().toString())) {
                return timetables.get(i);
            }
        }
        return null;
    }

    public static List<Timetable> checkData(String date){
        List<Timetable> timetables = new ArrayList<Timetable>();
        timetables = TimetableDao.getAllTimetable();
        List<Timetable> timetablesWithDate = new ArrayList<Timetable>();
        int j = 0;
        for(int i = 0; i < timetables.size(); i++){
            if(date.equals(timetables.get(i).getDay().toString())) {
                timetablesWithDate.add(timetables.get(i));
                }
        }
        return timetablesWithDate;
    }

    public static int checkTimeAndDate(Timetable timetable){
        List<Timetable> timetables = new ArrayList<Timetable>();
        timetables = TimetableDao.getAllTimetable();
        for(int i = 0; i < timetables.size(); i++){
            if(timetable.getTime().toString().equals(timetables.get(i).getTime().toString()) && timetable.getDay().toString().equals(timetables.get(i).getDay().toString())) {
                return 1;
            }
        }
        return 0;
    }
}
