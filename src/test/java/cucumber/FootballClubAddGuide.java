package cucumber;


import FC.domain.FootballClub;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import FC.domain.FootballClubBuilder;
import FC.service.FootballClubManager;
import FC.service.FootballClubManagerImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FootballClubAddGuide {
    private FootballClubManager footballClubManager;
    private FootballClubBuilder footballClubBuilder;
    private List<FootballClub>footballClubList;
    private FootballClub addedFootballClub;

    public FootballClubAddGuide(){
        footballClubBuilder = new FootballClubBuilder();
        footballClubManager = new FootballClubManagerImpl();
    }


    @Given("Football Club ID {int} FootballClub Name {string}")
    public void footballClubWithNameAdded (Integer id, String name) {
        footballClubBuilder = new FootballClubBuilder();
        footballClubBuilder.byId(id).byName(name);
    }
    @Given("Football Club Stadium Capacity {int}")
    public void footballClubStadiumCapacity (Integer stadiumCapacity){
        footballClubBuilder.byStadiumCapacity(stadiumCapacity);
    }
    @Given("Football Club Location {string}")
    public void footballClubLocation(String location) {
        footballClubBuilder.byLocation(location);
    }

    @Given("Football Club Ground {string}")
    public void footballClubGround(String ground) {
        footballClubBuilder.byGround(ground);
    }
    @Given("Football Club League {string}")
    public void footballClubLeague(String league) {
        footballClubBuilder.byLeague(league);
    }

    @When("Football Club is added")
    public void footballClubAdded() {
        addedFootballClub = footballClubBuilder.build();
        footballClubManager.create(addedFootballClub);
    }

    @Then("it will exist with a given id {int} in the database")
    public void footballClubAddedCorrectly(Integer id){
        FootballClub footballClubCreated = footballClubManager.read(id);
        assertEquals(addedFootballClub.getId(), footballClubCreated.getId());
    }
    @Given("Football Clubs in base")
    public void addFootballClubToBase() {
        footballClubManager.create(new FootballClubBuilder().byId(1).byName("FC Barcelona").byStadiumCapacity(95000).byLocation("Barcelona").byGround("Camp Nou").byLeague("La Liga").build());
        footballClubManager.create(new FootballClubBuilder().byId(2).byName("Real Madrid").byStadiumCapacity(80000).byLocation("Madrid").byGround("Estadio Bernabeu").byLeague("La Liga").build());
        footballClubManager.create(new FootballClubBuilder().byId(3).byName("Sevilla FC").byStadiumCapacity(50000).byLocation("Sevilla").byGround("Estadio Ramón Sánchez Pizjuán").byLeague("La Liga").build());
        footballClubManager.create(new FootballClubBuilder().byId(4).byName("Atletico Madrid").byStadiumCapacity(75000).byLocation("Madrid").byGround("Wanda Metropolitano").byLeague("La Liga").build());
        footballClubManager.create(new FootballClubBuilder().byId(5).byName("Valencia CF").byStadiumCapacity(60000).byLocation("Valencia").byGround("Estadio Mestalla").byLeague("La Liga").build());
    }

    @When("finding out how many leagues start with the word La{string}")
    public void checkTvShowTitleForWords(String regex) {
        footballClubList = footballClubManager.findInLeague(regex);
    }

    @Then("I am counting it {int} which leagues contains La")
    public void countWordAppearances (int number) {
        assertEquals(number, footballClubList.size());
    }
}
