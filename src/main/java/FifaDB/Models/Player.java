package FifaDB.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by faraonul on 5/13/17.
 */
public class Player {

    private String commonName;
    private String firstName;
    private String name;
    private boolean isSpecialType;
    private String positionFull;
    private String baseId;
    private String headshotImgUrl;
    private League league;
    private Nation nation;
    private Club club;
    private String position;
    private int rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSpecialType() {
        return isSpecialType;
    }

    public void setSpecialType(boolean specialType) {
        isSpecialType = specialType;
    }

    public String getPositionFull() {
        return positionFull;
    }

    public void setPositionFull(String positionFull) {
        this.positionFull = positionFull;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getAggression() {
        return aggression;
    }

    public void setAggression(int aggression) {
        this.aggression = aggression;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBallControl() {
        return ballControl;
    }

    public void setBallControl(int ballControl) {
        this.ballControl = ballControl;
    }

    public String getFavouriteFoot() {
        return favouriteFoot;
    }

    public void setFavouriteFoot(String favouriteFoot) {
        this.favouriteFoot = favouriteFoot;
    }

    public int getCrossing() {
        return crossing;
    }

    public void setCrossing(int crossing) {
        this.crossing = crossing;
    }

    public int getCurve() {
        return curve;
    }

    public void setCurve(int curve) {
        this.curve = curve;
    }

    public int getDribbling() {
        return dribbling;
    }

    public void setDribbling(int dribbling) {
        this.dribbling = dribbling;
    }

    public int getFinishing() {
        return finishing;
    }

    public void setFinishing(int finishing) {
        this.finishing = finishing;
    }

    public int getFreeKickAccuracy() {
        return freeKickAccuracy;
    }

    public void setFreeKickAccuracy(int freeKickAccuracy) {
        this.freeKickAccuracy = freeKickAccuracy;
    }

    public int getGkDiving() {
        return gkDiving;
    }

    public void setGkDiving(int gkDiving) {
        this.gkDiving = gkDiving;
    }

    public int getGkHandling() {
        return gkHandling;
    }

    public void setGkHandling(int gkHandling) {
        this.gkHandling = gkHandling;
    }

    public int getGkKicking() {
        return gkKicking;
    }

    public void setGkKicking(int gkKicking) {
        this.gkKicking = gkKicking;
    }

    public int getGkPositioning() {
        return gkPositioning;
    }

    public void setGkPositioning(int gkPositioning) {
        this.gkPositioning = gkPositioning;
    }

    public int getGkReflexes() {
        return gkReflexes;
    }

    public void setGkReflexes(int gkReflexes) {
        this.gkReflexes = gkReflexes;
    }

    public int getHeadingAccuracy() {
        return headingAccuracy;
    }

    public void setHeadingAccuracy(int headingAccuracy) {
        this.headingAccuracy = headingAccuracy;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    public int getJumping() {
        return jumping;
    }

    public void setJumping(int jumping) {
        this.jumping = jumping;
    }

    public int getLongPassing() {
        return longPassing;
    }

    public void setLongPassing(int longPassing) {
        this.longPassing = longPassing;
    }

    public int getLongShots() {
        return longShots;
    }

    public void setLongShots(int longShots) {
        this.longShots = longShots;
    }

    public int getMarking() {
        return marking;
    }

    public void setMarking(int marking) {
        this.marking = marking;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getPositioning() {
        return positioning;
    }

    public void setPositioning(int positioning) {
        this.positioning = positioning;
    }

    public int getReactions() {
        return reactions;
    }

    public void setReactions(int reactions) {
        this.reactions = reactions;
    }

    public int getShortPassing() {
        return shortPassing;
    }

    public void setShortPassing(int shortPassing) {
        this.shortPassing = shortPassing;
    }

    public int getShotPower() {
        return shotPower;
    }

    public void setShotPower(int shotPower) {
        this.shotPower = shotPower;
    }

    public int getSlidingTackle() {
        return slidingTackle;
    }

    public void setSlidingTackle(int slidingTackle) {
        this.slidingTackle = slidingTackle;
    }

    public int getSprintSpeed() {
        return sprintSpeed;
    }

    public void setSprintSpeed(int sprintSpeed) {
        this.sprintSpeed = sprintSpeed;
    }

    public int getStandingTackle() {
        return standingTackle;
    }

    public void setStandingTackle(int standingTackle) {
        this.standingTackle = standingTackle;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrengh() {
        return strengh;
    }

    public void setStrengh(int strengh) {
        this.strengh = strengh;
    }

    public int getVision() {
        return vision;
    }

    public void setVision(int vision) {
        this.vision = vision;
    }

    public int getVolleys() {
        return volleys;
    }

    public void setVolleys(int volleys) {
        this.volleys = volleys;
    }

    public int getWeakFoot() {
        return weakFoot;
    }

    public void setWeakFoot(int weakFoot) {
        this.weakFoot = weakFoot;
    }

    private int height;
    private int weight;
    private String birthdate;
    private int age;
    private int acceleration;
    private int aggression;
    private int agility;
    private int balance;
    @SerializedName("ballcontrol")
    private int ballControl;
    @SerializedName("foot")
    private String favouriteFoot;
    private int crossing;
    private int curve;
    private int dribbling;
    private int finishing;
    @SerializedName("freekickaccuracy")
    private int freeKickAccuracy;
    @SerializedName("gkdiving")
    private int gkDiving;
    @SerializedName("gkhandling")
    private int gkHandling;
    @SerializedName("gkkicking")
    private int gkKicking;
    @SerializedName("gkpositioning")
    private int gkPositioning;
    @SerializedName("gkreflexes")
    private int gkReflexes;
    @SerializedName("headingaccuracy")
    private int headingAccuracy;
    private int interceptions;
    private int jumping;
    @SerializedName("longpassing")
    private int longPassing;
    @SerializedName("longshots")
    private int longShots;
    private int marking;
    private int penalties;
    private int positioning;
    private int reactions;
    @SerializedName("shortpassing")
    private int shortPassing;
    @SerializedName("shotpower")
    private int shotPower;
    @SerializedName("slidingtackle")
    private int slidingTackle;
    @SerializedName("sprintspeed")
    private int sprintSpeed;
    @SerializedName("standingTackle")
    private int standingTackle;
    private int stamina;
    private int strengh;
    private int vision;
    private int volleys;
    @SerializedName("weakfoot")
    private int weakFoot;



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
