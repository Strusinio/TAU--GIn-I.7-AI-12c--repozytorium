package service;
import domain.FootballClubDates;
import java.util.List;

public interface FootballClubManager {
    void create(FootballClubDates footballClubDates);
    FootballClubDates read(Integer id);
    void update(FootballClubDates footballClubDates);
    void delete(FootballClubDates footballClubDates);
    List<FootballClubDates> listAllSeries();
}
