package FifaDB.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by faraonul on 5/13/17.
 */
public class Club {
    private ResponsiveImagePair imageUrls;
    @SerializedName("abbrName")
    private String shortName;
    private int id;
    private String name;

    public ResponsiveImagePair getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ResponsiveImagePair imgUrls) {
        this.imageUrls = imgUrls;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
