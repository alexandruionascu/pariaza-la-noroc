package BetRadar.Models;

/**
 * Created by faraonul on 5/19/17.
 */
public class Team {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public Team(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
