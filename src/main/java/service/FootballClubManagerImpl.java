package service;

import domain.FootballClub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootballClubManagerImpl implements FootballClubManager{
    private Map<Integer, FootballClub> footballClubMap = new HashMap<>();

    @Override
    public void create(FootballClub footballClub) {
        if(footballClubMap.containsKey(footballClub.getId())) {
            throw new IllegalArgumentException("Football Club ID: " + footballClub.getId() + " already exists.");
        } else {
            footballClubMap.put(footballClub.getId(), footballClub);
        }

    }

    @Override
    public FootballClub read(Integer id) {
        if (footballClubMap.containsKey(id)) {
            return footballClubMap.get(id);
        } else {
            throw new NullPointerException("Football Club ID: " + id + " doesn't exist.");
        }
    }

    @Override
    public void update(FootballClub footballClub) {
        if (footballClubMap.containsKey(footballClub.getId())) {
            footballClubMap.replace(footballClub.getId(), footballClub);
        } else {
            throw new NullPointerException("Football Club ID: " + footballClub + " doesn't exist.");
        }

    }

    @Override
    public void delete(FootballClub footballClub) {
        footballClubMap.remove(footballClub.getId());
    }

    @Override
    public List<FootballClub> listAllSeries() {
        return new ArrayList<>(footballClubMap.values());
    }
}
