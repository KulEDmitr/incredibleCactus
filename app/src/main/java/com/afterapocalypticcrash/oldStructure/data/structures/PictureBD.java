package com.afterapocalypticcrash.oldStructure.data.structures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favourites")
public class PictureBD {

    @PrimaryKey
    @NonNull
    private String id; //    String id;

    private int likes;

    private String username; // String user.username

    @ColumnInfo(name = "profile_image")
    private String authorsProfileImage;   //  String user.profile_image.small

    @ColumnInfo(name = "user_html")
    private String userHtml;    //  String user.links.html

    private String thumb; //urls.thumb

    private String regular;   //urls.regular

    private String name; //user.name

    private String description;

    @Ignore
    public PictureBD(@NonNull String id, int likes, String description, String username,
                     String authorsProfileImage, String userHtml, String thumb,
                     String regular, String name) {
        this.id = id;
        this.likes = likes;
        this.description = description;
        this.username = username;
        this.authorsProfileImage = authorsProfileImage;
        this.userHtml = userHtml;
        this.thumb = thumb;
        this.regular = regular;
        this.name = name;
    }

    public PictureBD(@NonNull String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthorsProfileImage() {
        return authorsProfileImage;
    }
    public void setAuthorsProfileImage(String authorsProfileImage) {
        this.authorsProfileImage = authorsProfileImage;
    }

    public String getUserHtml() {
        return userHtml;
    }
    public void setUserHtml(String userHtml) {
        this.userHtml = userHtml;
    }

    public String getThumb() {
        return thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getRegular() {
        return regular;
    }
    public void setRegular(String regular) {
        this.regular = regular;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PictureBD{" +
                "\n id ='" + id + '\'' +
                ",\n likes =" + likes +
                ",\n username ='" + username + '\'' +
                ",\n authorsProfileImage ='" + authorsProfileImage + '\'' +
                ",\n userHtml ='" + userHtml + '\'' +
                ",\n thumb ='" + thumb + '\'' +
                ",\n regular ='" + regular + '\'' +
                ",\n name= '" + name + '\'' +
                ",\n description= '" + description + '\'' +
                '}';
    }
}
