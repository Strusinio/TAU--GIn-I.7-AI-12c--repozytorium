package service;


import domain.FootballClubDates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootballClubManagerImpl implements FootballClubManager{
    private Map<Integer, FootballClubDates> footballClubMap = new HashMap<>();

    @Override
    public void create(FootballClubDates footballClub) {
        if(footballClubMap.containsKey(footballClub.getId())) {
            throw new IllegalArgumentException("Football Club ID: " + footballClub.getId() + " already exists.");
        } else {
            footballClubMap.put(footballClub.getId(), footballClub);
        }

    }

    @Override
    public FootballClubDates read(Integer id) {
        if (footballClubMap.containsKey(id)) {
            return footballClubMap.get(id);
        } else {
            throw new NullPointerException("Football Club ID: " + id + " doesn't exist.");
        }
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
    public List<FootballClubDates> listAllSeries() {
        return new ArrayList<>(footballClubMap.values());
    }
}
