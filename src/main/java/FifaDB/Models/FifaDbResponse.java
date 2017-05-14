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

}
