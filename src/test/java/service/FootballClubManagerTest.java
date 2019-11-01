package service;

import domain.FootballClub;
import domain.FootballClubBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FootballClubManagerTest {

    private FootballClubManager footballClubManager;
    private FootballClub footballClub;

    @Before
    public void setUp() {
        footballClubManager = new FootballClubManagerImpl();

        footballClub = new FootballClubBuilder().byId(1).byName("FC Barcelona").byStadiumCapacity(4).byLocation("Barcelona").byGround("Camp Nou").byLeague("La Liga").build();
    }
    @Test
    public void CreateNewFootballClub() {
        footballClubManager.create(footballClub);

        assertEquals(1, footballClubManager.listAllSeries().size());
    }
    @Test (expected = IllegalArgumentException.class)
    public void ShowWithIdAlreadyExists() {
        FootballClub newClub = new FootballClubBuilder().byId(1).build();

        footballClubManager.create(footballClub);
        footballClubManager.create(newClub);
    }
    @Test (expected = NullPointerException.class)
    public void ClubNotFoundById() {
        footballClubManager.read(53);
    }

    @Test
    public void ReadFootbalClub() {
        footballClubManager.create(footballClub);
        FootballClub createdClub = footballClubManager.read(1);

        assertEquals(footballClub, createdClub);
    }
    @Test (expected = NullPointerException.class)
    public void DeleteClub() {
        footballClubManager.create(footballClub);
        footballClubManager.delete(footballClub);
        footballClubManager.read(footballClub.getId());
    }
    @Test
    public void UpdateClub() {
        footballClubManager.create(footballClub);
        FootballClub newClub = new FootballClubBuilder().byId(1).byName("Real Madryt").byStadiumCapacity(80000).byLocation("Madrid").byGround("Bernabeu").byLeague("La Liga").build();
        footballClubManager.update(newClub);
        assertEquals("Real Madryt", footballClubManager.read(1).getName());
        assertEquals(80000, footballClubManager.read(1).getStadiumCapacity());
        assertEquals("Madrid", footballClubManager.read(1).getLocation());

    }
    @Test (expected = NullPointerException.class)
    public void UpdateNonExistentTvShow () {
        footballClubManager.update(footballClub);
    }





}
