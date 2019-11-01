package service;
import domain.FootballClub;
import java.util.List;

public interface FootballClubManager {
    void create(FootballClub footballClub);
    FootballClub read(Integer id);
    void update(FootballClub footballClub);
    void delete(FootballClub footballClub);
    List<FootballClub> listAllSeries();
}
