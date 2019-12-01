package FC.service;

import FC.domain.FootballClub;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class FootballClubManagerImpl implements FootballClubManager {
    private Map<Integer, FootballClub> footballClubMap = new HashMap<>();
    private DateService dateService = new DateServiceImpl();

    private boolean creationTimeEnabled = true;
    private boolean lastAccessDateEnabled = true;
    private boolean updateDateEnabled = true;


   @Override
    public void create(FootballClub footballClub) {
        if(footballClubMap.containsKey(footballClub.getId())) {
            throw new IllegalArgumentException("Football Club ID: " + footballClub.getId() + " already exists.");
        }else if (creationTimeEnabled) {
            footballClub.setCreationDate(dateService.getCurrentTime());
            footballClubMap.put(footballClub.getId(), footballClub);
        }else
            footballClubMap.put(footballClub.getId(), footballClub);
        }


    @Override
    public FootballClub read(Integer id) {
        if (footballClubMap.containsKey(id) && !lastAccessDateEnabled) {
            return footballClubMap.get(id);
        }else if(footballClubMap.containsKey(id) && lastAccessDateEnabled){
            FootballClub footballClub= footballClubMap.get(id);
            footballClub.setLastAccessDate(dateService.getCurrentTime());
            return footballClubMap.get(id);
        }else
            throw new NullPointerException("Football Club ID: " + id + " doesn't exist.");
        }

    @Override
    public void update(FootballClub footballClub) {
        if (footballClubMap.containsKey(footballClub.getId()) && updateDateEnabled) {
            footballClub.setUpdateDate(dateService.getCurrentTime());
            footballClubMap.replace(footballClub.getId(), footballClub);
        } else if (footballClubMap.containsKey(footballClub.getId()) && !updateDateEnabled) {
            footballClubMap.replace(footballClub.getId(), footballClub);
        } else {
            throw new NullPointerException("footbal Club: " + footballClub + " doesn't exist.");
        }

    }
    @Override
    public void delete(FootballClub footballClub) {
        footballClubMap.remove(footballClub.getId());
    }

    @Override
    public List<FootballClub> listAllSeries() { return new ArrayList<>(footballClubMap.values());}


    @Override
    public List<FootballClub> findInLeague(String regex) {
        if (regex == null) {
            throw new IllegalArgumentException("Not working Correctly ");
        }
        List<FootballClub> result = new ArrayList<>();
        footballClubMap.values().stream()
                .filter(footballClub -> footballClub.getLeague().matches(regex))
                .forEach(result::add);
        return result;
    }


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