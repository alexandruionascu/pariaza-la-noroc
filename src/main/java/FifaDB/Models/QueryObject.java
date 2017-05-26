package FifaDB.Models;

import com.google.gson.Gson;

/**
 * Created by faraonul on 5/13/17.
 */
public class QueryObject {
    private int page;
    private String name;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private String position;

    public String toString() {
        return new Gson().toJson(this);
    }
}
