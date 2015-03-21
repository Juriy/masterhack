package model;

/**
 * Created by Juriy on 3/21/2015.
 */
public class User {

    private String firstName;
    private String secondName;
    private String pictureUrl;

    private String userId;

    public User() {
    }

    public User(String firstName, String secondName, String pictureUrl, String userId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.pictureUrl = pictureUrl;
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
