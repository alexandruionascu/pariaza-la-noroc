package FifaDB.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by faraonul on 5/13/17.
 */
public class ResponsiveImage {
    @SerializedName(value="small",  alternate={"smallImgUrl"})
    private String smallImageUrl;
    @SerializedName(value="medium",  alternate={"mediumImgUrl"})
    private String mediumImageUrl;
    @SerializedName(value="large",  alternate={"largeImgUrl"})
    private String largeImageUrl;

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }
}
