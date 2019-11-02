package service;

import domain.FootballClubBuilder;
import domain.FootballClubDates;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FootballClubManagerTest {

    private FootballClubManager footballClubManager;
    private FootballClubDates footballClubDates;
    private LocalDateTime localDateTime;


    @Before
    public void setUp() {
        footballClubManager = new FootballClubManagerImpl();

        footballClubDates = new FootballClubBuilder().byId(1).byName("FC Barcelona").byStadiumCapacity(4).byLocation("Barcelona").byGround("Camp Nou").byLeague("La Liga").build();
    }
    @Test
    public void CreateNewFootballClub() {
        footballClubManager.create(footballClubDates);

        assertEquals(1, footballClubManager.listAllSeries().size());
    }
    @Test (expected = IllegalArgumentException.class)
    public void ShowWithIdAlreadyExists() {
        FootballClubDates newClub = new FootballClubBuilder().byId(1).build();

        footballClubManager.create(footballClubDates);
        footballClubManager.create(newClub);
    }
    @Test (expected = NullPointerException.class)
    public void ClubNotFoundById() {
        footballClubManager.read(53);
    }

    @Test
    public void ReadFootbalClub() {
        footballClubManager.create(footballClubDates);
        FootballClubDates createdClub = footballClubManager.read(1);

        assertEquals(footballClubDates, createdClub);
    }
    @Test (expected = NullPointerException.class)
    public void DeleteClub() {
        footballClubManager.create(footballClubDates);
        footballClubManager.delete(footballClubDates);
        footballClubManager.read(footballClubDates.getId());
    }
    @Test
    public void testmock1()  {
        //  create mock
        FootballClubDates footballClubManager = mock(FootballClubDates.class);

        // define return value for method getUniqueId()
        when(footballClubManager.getCreationDate()).thenReturn(localDateTime);

        // use mock in test....
        assertEquals(footballClubManager.getCreationDate(), localDateTime);
    }
    @Test
    public void testMock2()  {
        // create and configure mock
        FootballClubDates test = Mockito.mock(FootballClubDates.class);
        when(test.getId()).thenReturn(0);

        verify(test, times(0)).getId();

        // other alternatives for verifiying the number of method calls for a method
        verify(test, never()).getLastAccessDate("never called");
        //verify(test, atLeastOnce()).getCreationDate("called at least once");
        //verify(test, atLeast(2)).getUpdateDate("called at least twice");
        //verify(test, times(5)).someMethod("called five times");
        //verify(test, atMost(3)).someMethod("called at most 3 times");
        // This let's you check that no other methods where called on this object.
        // You call it after you have verified the expected method calls.
        verifyNoMoreInteractions(test);
    }

    @Test
    public void UpdateClub() {
        footballClubManager.create(footballClubDates);
        FootballClubDates newClub = new FootballClubBuilder().byId(1).byName("Real Madryt").byStadiumCapacity(80000).byLocation("Madrid").byGround("Bernabeu").byLeague("La Liga").build();
        footballClubManager.update(newClub);
        assertEquals("Real Madryt", footballClubManager.read(1).getName());
        assertEquals(80000, footballClubManager.read(1).getStadiumCapacity());
        assertEquals("Madrid", footballClubManager.read(1).getLocation());

    }
    @Test (expected = NullPointerException.class)
    public void UpdateNonExistentFootballClub () {
        footballClubManager.update(footballClubDates);
    }





}
