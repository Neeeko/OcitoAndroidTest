package com.neekoentertainment.theguardianreader.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Nicolas on 7/30/2016.
 */
public class Article implements Parcelable {
    public String id;
    public String sectionId;
    public Date webPublicationDate;
    public String webTitle;
    public String webUrl;
    public String apiUrl;
    public String sectionName;
    public String headline;
    public String thumbnail;

    protected Article(Parcel in) {
        id = in.readString();
        sectionId = in.readString();
        webTitle = in.readString();
        webUrl = in.readString();
        apiUrl = in.readString();
        sectionName = in.readString();
        headline = in.readString();
        thumbnail = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(sectionId);
        dest.writeString(webTitle);
        dest.writeString(webUrl);
        dest.writeString(apiUrl);
        dest.writeString(sectionName);
        dest.writeString(headline);
        dest.writeString(thumbnail);
    }

    public String getId() {
        return id;
    }

    public String getSectionId() {
        return sectionId;
    }

    public Date getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
