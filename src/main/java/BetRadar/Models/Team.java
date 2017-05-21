package BetRadar.Models;

import BetRadar.Controllers.BetRadar;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faraonul on 5/19/17.
 */
public class Team {
    private String name;
    private String location;
    private String id;
    private String imageUrl;
    private static final int ID_INDEX = 3;
    private List<Fixture> fixtures;
    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static int getIdIndex() {
        return ID_INDEX;
    }

    public Team(String name, String location) {
        this.name = name;
        this.location = location;
        this.id = location.split("'")[ID_INDEX];
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }
}
