package com.example.student.readingroomdemo;

import com.google.gson.annotations.SerializedName;

public class MyArticle {
    @SerializedName("Id")
    public Integer id;

    @SerializedName("Title")
    public String title;

    @SerializedName("Url")
    public String url;

    @SerializedName("Description")
    public String description;

    @SerializedName("ImageLink")
    public String imageLink;

    @SerializedName("Likes")
    public Integer likes;

    @SerializedName("Author")
    public String author;

    @SerializedName("AuthorId")
    public String authorId;

    @SerializedName("Comment")
    public String comment;

    @SerializedName("Approved")
    public Boolean approved;

    @SerializedName("DateCreated")
    public String dateCreated;
}
