package ca.stuckon.demos.github.network;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Repo {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("updated_at")
    private String updated;

    @SerializedName("stargazers_count")
    private Integer stargazers;

    @SerializedName("forks")
    private Integer forks;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getUpdated() {
        return null;//TODO "2017-08-14T08:08:10Z"
    }

    public int getStargazers() {
        return stargazers == null ? 0 : stargazers;
    }

    public int getForks() {
        return forks == null ? 0 : forks;
    }
}
