package FifaDB.Models;

/**
 * Created by faraonul on 5/13/17.
 */
public class ResponsiveImagePair {
    private ResponsiveImage normal;
    private ResponsiveImage dark;

    public ResponsiveImage getNormal() {
        return normal;
    }

    public void setNormal(ResponsiveImage normal) {
        this.normal = normal;
    }

    public ResponsiveImage getDark() {
        return dark;
    }

    public void setDark(ResponsiveImage dark) {
        this.dark = dark;
    }
}
