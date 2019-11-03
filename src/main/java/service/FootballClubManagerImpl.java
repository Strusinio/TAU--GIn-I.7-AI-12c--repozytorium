package service;


import domain.FootballClubDates;


import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootballClubManagerImpl implements FootballClubManager {
    private Map<Integer, FootballClubDates> footballClubMap = new HashMap<>();
    private DateService dateService = new DateServiceImpl();

    private boolean creationTimeEnabled = true;
    private boolean lastAccessDateEnabled = true;
    private boolean updateDateEnabled = true;


   @Override
    public void create(FootballClubDates footballClub) {
        if(footballClubMap.containsKey(footballClub.getId())) {
            throw new IllegalArgumentException("Football Club ID: " + footballClub.getId() + " already exists.");
        }else if (creationTimeEnabled) {
            footballClub.setCreationDate(dateService.getCurrentTime());
            footballClubMap.put(footballClub.getId(), footballClub);
        }else
            footballClubMap.put(footballClub.getId(), footballClub);
        }


    @Override
    public FootballClubDates read(Integer id) {
        if (footballClubMap.containsKey(id) && !lastAccessDateEnabled) {
            return footballClubMap.get(id);
        }else if(footballClubMap.containsKey(id) && lastAccessDateEnabled){
            FootballClubDates footballClubDates= footballClubMap.get(id);
            footballClubDates.setLastAccessDate(dateService.getCurrentTime());
            return footballClubMap.get(id);
        }else
            throw new NullPointerException("Football Club ID: " + id + " doesn't exist.");
        }

    @Override
    public void update(FootballClubDates footballClub) {
        if (footballClubMap.containsKey(footballClub.getId())) {
            footballClubMap.replace(footballClub.getId(), footballClub);
        } else {
            throw new NullPointerException("Football Club ID: " + footballClub + " doesn't exist.");
        }

    }

    @Override
    public void delete(FootballClubDates footballClub) {
        footballClubMap.remove(footballClub.getId());
    }

    @Override
    public List<FootballClubDates> listAllSeries() { return new ArrayList<>(footballClubMap.values());}


    public void setCreationTimeEnabled(boolean creationTimeEnabled){
       this.creationTimeEnabled = creationTimeEnabled;
    }
    public void setLastAccessDateEnabled(boolean lastAccessDateEnabled) {
        this.lastAccessDateEnabled = lastAccessDateEnabled;
    }
    public void setUpdateDateEnabled(boolean updateDateEnabled) {
        this.updateDateEnabled = updateDateEnabled;
    }

}