package domain;

import java.time.LocalDateTime;

public class FootballClubDates extends FootballClub{
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private LocalDateTime lastAccessDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDateTime getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(LocalDateTime lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }
}
