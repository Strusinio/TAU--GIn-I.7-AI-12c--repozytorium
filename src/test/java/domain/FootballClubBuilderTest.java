package domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class FootballClubBuilderTest {
    private FootballClubBuilder footballClubBuilder;

    @Before
    public void setUp() {footballClubBuilder = new FootballClubBuilder();}

    @Test
    public void BuildNewFootballClub() {
        FootballClub footballClub;

        footballClub = footballClubBuilder.build();

        assertNotNull(footballClub);
    }
    @Test
    public void FootballClubHasId() {
        FootballClub footballClub;

        footballClub = footballClubBuilder.byId(1).build();

        assertEquals(1, footballClub.getId());
    }
    @Test
    public void FootballClubHasName() {
        FootballClub footballClub;

        footballClub = footballClubBuilder.byName("FC Barcelona").build();

        assertEquals("FC Barcelona", footballClub.getName());
    }
    @Test
    public void FootballClubHasStadiumCapacity() {
        FootballClub footballClub;

        footballClub = footballClubBuilder.byStadiumCapacity(4).build();

        assertEquals(4, footballClub.getStadiumCapacity());
    }
    @Test
    public void TvShowHasLocation() {
        FootballClub footballClub;

        footballClub = footballClubBuilder.byLocation("Barcelona").build();

        assertEquals("Barcelona", footballClub.getLocation());
    }
    @Test
    public void TvShowHasGround() {
        FootballClub footballClub;

        footballClub = footballClubBuilder.byGround("Camp Nou").build();

        assertEquals("Camp Nou", footballClub.getGround());
    }
    @Test
    public void TvShowHasGLeague() {
        FootballClub footballClub;

        footballClub = footballClubBuilder.byLeague("La Liga").build();

        assertEquals("La Liga", footballClub.getLeague());
    }


}
