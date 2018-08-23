package ca.stuckon.demos.github.network;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Test;

public class RepoTest {

    @Test
    public void validParsing() {
        String json = "{\"name\":\"Name\",\"description\":\"description\",\"updated_at\":\"2017-08-14T08:08:10Z\",\"stargazers_count\":1,\"forks\":2}";
        Repo repo = new Gson().fromJson(json, Repo.class);
        Assert.assertEquals(repo.getName(), "Name");
        Assert.assertEquals(repo.getDescription(), "description");
        Assert.assertEquals(repo.getStargazers(), 1);
        Assert.assertEquals(repo.getForks(), 2);
        Assert.assertEquals(repo.getUpdated().getTime(), 1502698090000L);
    }
}
