package service;

import domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.AtMost;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class FootballClubManagerTest {

    @Mock
    private DateService dateService;

    @InjectMocks
    private FootballClubManagerImpl footballClubManager;
    private FootballClubDates footballClubDates;
    private LocalDateTime currentTime;

    @Before
    public void setUp() {
        footballClubManager = new FootballClubManagerImpl();

        footballClubDates = new FootballClubBuilder().byId(1).byId(1).byName("FC Barcelona").byStadiumCapacity(4).byLocation("Barcelona").byGround("Camp Nou").byLeague("La Liga").build();

        currentTime = LocalDateTime.now();

        MockitoAnnotations.initMocks(this);

        when(dateService.getCurrentTime()).thenReturn(currentTime);
    }

    @Test
    public void CreateNewFootballClub() {
        footballClubManager.create(footballClubDates);

        assertEquals(1, footballClubManager.listAllSeries().size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void ClubIdAlreadyExists() {
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
    public void UpdateClub() {
        footballClubManager.create(footballClubDates);
        FootballClubDates newClub = new FootballClubBuilder().byId(1).byName("Real Madryt").byStadiumCapacity(80000).byLocation("Madrid").byGround("Bernabeu").byLeague("La Liga").build();
        footballClubManager.update(newClub);
        assertEquals("Real Madryt", footballClubManager.read(1).getName());
        assertEquals(80000, footballClubManager.read(1).getStadiumCapacity());
        assertEquals("Madrid", footballClubManager.read(1).getLocation());
    }

    @Test (expected = NullPointerException.class)
    public void UpdateNonExistentFootbalClub () {
        footballClubManager.update(footballClubDates);
    }

    @Test
    public void ReadingClubChangesLastAccessDateIfEnabled() {
        footballClubManager.setLastAccessDateEnabled(true);
        footballClubManager.create(footballClubDates);
        footballClubManager.read(1);
        assertNotNull(footballClubDates.getLastAccessDate());
        assertEquals(currentTime, footballClubDates.getLastAccessDate());
    }

    @Test
    public void ReadingClubDoesNotChangeLastAccessDateIfDisabled() {
        footballClubManager.setCreationTimeEnabled(false);
        footballClubManager.setLastAccessDateEnabled(false);
        footballClubManager.create(footballClubDates);
        FootballClubDates createdClub = footballClubManager.read(1);
        verify(dateService,never()).getCurrentTime();
        assertNull(createdClub.getLastAccessDate());
    }

    @Test
    public void CreatedClubHasCreationDateIfEnabled() {
        footballClubManager.setCreationTimeEnabled(true);
        footballClubManager.create(footballClubDates);
        assertNotNull(footballClubDates.getCreationDate());
        assertEquals(currentTime, footballClubDates.getCreationDate());
    }

    @Test
    public void CreatedClubHasNoCreationDateIfDisabled() {
        footballClubManager.setCreationTimeEnabled(false);
        footballClubManager.create(footballClubDates);
        FootballClubDates createdClub = footballClubManager.read(1);
        verify(dateService, only()).getCurrentTime();
        assertNull(createdClub.getCreationDate());
    }

    @Test
    public void UpdatedClubHasUpdateDateIfEnabled() {
        footballClubManager.setUpdateDateEnabled(true);
        footballClubManager.create(footballClubDates);
        FootballClubDates newClub = new FootballClubBuilder().byId(1).byName("FC Barcelona").byStadiumCapacity(4).byLocation("Barcelona").byGround("Camp Nou").byLeague("La Liga").build();
        footballClubManager.update(newClub);
        FootballClubDates createdClub = footballClubManager.read(1);
        assertNotNull(createdClub.getUpdateDate());
        assertEquals(currentTime, createdClub.getUpdateDate());
    }

    @Test
    public void UpdatedShowHasNoUpdateDateIfDisabled() {
        footballClubManager.setUpdateDateEnabled(false);
        footballClubManager.create(footballClubDates);
        FootballClubDates newClub = new FootballClubBuilder().byId(1).byName("FC Barcelona").byStadiumCapacity(4).byLocation("Barcelona").byGround("Camp Nou").byLeague("La Liga").build();
        footballClubManager.update(newClub);
        FootballClubDates createdClub = footballClubManager.read(1);
        verify(dateService, atMost(2)).getCurrentTime();
        assertNull(createdClub.getUpdateDate());
    }
}
