package FC.rest;

import FC.domain.FootballClub;
import FC.domain.FootballClubBuilder;
import FC.service.FootballClubManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("footballclubs")
public class FootballClubController {
    private final FootballClubManager footballClubManager;

    public FootballClubController(FootballClubManager footballClubManager) {
        this.footballClubManager = footballClubManager;

        FootballClub newClub= new FootballClubBuilder()
                .byId(1).byName("FC Barcelona")
                .byStadiumCapacity(6)
                .byLocation("Location")
                .byGround("Camp Nou")
                .byLeague("La Liga")
                .build();

        FootballClub newClub2 = new FootballClubBuilder()
                .byId(2).byName("Real Madrid")
                .byStadiumCapacity(4)
                .byLocation("Madrid")
                .byGround("Bernabeu")
                .byLeague("La Liga")
                .build();

        footballClubManager.create(newClub);
        footballClubManager.create(newClub2);
    }

    @GetMapping("")
    public List<FootballClub> getAllClub() {
        return footballClubManager.listAllSeries();
    }

    @GetMapping("/{id}")
    public FootballClub getFootballClubw(@PathVariable Integer id) {
        return footballClubManager.read(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClub(@PathVariable Integer id) {
        footballClubManager.delete(footballClubManager.read(id));
    }

}
