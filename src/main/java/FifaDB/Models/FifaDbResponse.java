package FifaDB.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by faraonul on 5/14/17.
 */
public class FifaDbResponse {
    private int page;
    private int totalPages;
    private int count;
    @SerializedName("items")
    private Player[] players;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
