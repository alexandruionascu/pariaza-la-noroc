package Models;

/**
 * Created by faraonul on 5/13/17.
 */
public class Player {
    private String commonName;
    private String firstName;
    private String headshotImgUrl;

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHeadshotImgUrl() {
        return headshotImgUrl;
    }

    public void setHeadshotImgUrl(String headshotImgUrl) {
        this.headshotImgUrl = headshotImgUrl;
    }
}
