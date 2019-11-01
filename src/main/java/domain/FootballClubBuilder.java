package domain;

public class FootballClubBuilder {
    private int id;
    private String name;
    private int stadiumCapacity;
    private String location;
    private String ground;
    private String league;

    public FootballClubBuilder byId(int id) {
        this.id = id;
        return this;
    }
    public FootballClubBuilder byName (String name){
        this.name = name;
        return this;
    }
    public FootballClubBuilder byStadiumCapacity(int stadiumCapacity) {
        this.stadiumCapacity = stadiumCapacity;
        return this;
    }
    public FootballClubBuilder byLocation (String location){
        this.location = location;
        return this;
    }
    public FootballClubBuilder byGround (String ground){
        this.ground = ground;
        return this;
    }
    public FootballClubBuilder byLeague (String league){
        this.league = league;
        return this;
    }
    public FootballClub build() {
        FootballClub footballClub= new FootballClub();
        footballClub.setId(id);
        footballClub.setName(name);
        footballClub.setStadiumCapacity(stadiumCapacity);
        footballClub.setLocation(location);
        footballClub.setGround(ground);
        footballClub.setLeague(league);
        return footballClub;
    }
}
