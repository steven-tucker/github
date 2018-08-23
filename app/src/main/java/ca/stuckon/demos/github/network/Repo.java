package ca.stuckon.demos.github.network;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Repo {

    private static final String SERVER_DATE = "yyyy-MM-dd'T'HH:mm:ssZ";

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("updated_at")
    private String updated;
    private transient Date updateDate;

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
        if (updateDate == null && updated != null) {
            try {
                updateDate = new SimpleDateFormat(SERVER_DATE, Locale.CANADA).parse(updated.replace("Z", "-0000"));
            } catch (ParseException e) {
                return null;
            }
        }
        return updateDate;
    }

    public int getStargazers() {
        return stargazers == null ? 0 : stargazers;
    }

    public int getForks() {
        return forks == null ? 0 : forks;
    }
}
