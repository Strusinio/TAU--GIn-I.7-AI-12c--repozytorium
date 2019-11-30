package FC.service;

import FC.domain.FootballClub;
import FC.domain.FootballClubBuilder;
import FC.service.DateService;
import FC.service.FootballClubManagerImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FootballClubManagerTest {

    @Mock
    private DateService dateService;

    @InjectMocks
    private FootballClubManagerImpl footballClubManager;
    private FootballClub footballClub;
    private LocalDateTime currentTime;

    @Before
    public void setUp() {
        footballClubManager = new FootballClubManagerImpl();

        footballClub = new FootballClubBuilder().byId(1).byId(1).byName("FC Barcelona").byStadiumCapacity(4).byLocation("Barcelona").byGround("Camp Nou").byLeague("La Liga").build();

        currentTime = LocalDateTime.now();

        MockitoAnnotations.initMocks(this);

        when(dateService.getCurrentTime()).thenReturn(currentTime);
    }

    @Test
    public void CreateNewFootballClub() {
        footballClubManager.create(footballClub);

        assertEquals(1, footballClubManager.listAllSeries().size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void ClubIdAlreadyExists() {
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
    public void UpdateNonExistentFootbalClub () {
        footballClubManager.update(footballClub);
    }

    @Test
    public void ReadingClubChangesLastAccessDateIfEnabled() {
        footballClubManager.setLastAccessDateEnabled(true);
        footballClubManager.create(footballClub);
        footballClubManager.read(1);
        assertNotNull(footballClub.getLastAccessDate());
        assertEquals(currentTime, footballClub.getLastAccessDate());
    }

    @Test
    public void ReadingClubDoesNotChangeLastAccessDateIfDisabled() {
        footballClubManager.setCreationTimeEnabled(false);
        footballClubManager.setLastAccessDateEnabled(false);
        footballClubManager.create(footballClub);
        FootballClub createdClub = footballClubManager.read(1);
        verify(dateService,never()).getCurrentTime();
        assertNull(createdClub.getLastAccessDate());
    }

    @Test
    public void CreatedClubHasCreationDateIfEnabled() {
        footballClubManager.setCreationTimeEnabled(true);
        footballClubManager.create(footballClub);
        assertNotNull(footballClub.getCreationDate());
        assertEquals(currentTime, footballClub.getCreationDate());
    }

    @Test
    public void CreatedClubHasNoCreationDateIfDisabled() {
        footballClubManager.setCreationTimeEnabled(false);
        footballClubManager.create(footballClub);
        FootballClub createdClub = footballClubManager.read(1);
        verify(dateService, only()).getCurrentTime();
        assertNull(createdClub.getCreationDate());
    }

    @Test
    public void UpdatedClubHasNoUpdateDateIfDisabled() {
        footballClubManager.setUpdateDateEnabled(false);
        footballClubManager.create(footballClub);
        FootballClub newClub = new FootballClubBuilder().byId(1).byName("Real Madryt").byStadiumCapacity(80000).byLocation("Madrid").byGround("Bernabeu").byLeague("La Liga").build();
        footballClubManager.update(newClub);
        FootballClub createdClub = footballClubManager.read(1);
        verify(dateService, atMost(2)).getCurrentTime();
        assertNull(createdClub.getUpdateDate());
    }
}
