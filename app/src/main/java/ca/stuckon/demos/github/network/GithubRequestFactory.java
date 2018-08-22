package ca.stuckon.demos.github.network;

import java.util.List;

import ca.stuckon.core.network.BaseRequestFactory;
import ca.stuckon.core.network.SimpleCallback;
import ca.stuckon.core.network.SimpleResponseListener;

public class GithubRequestFactory extends BaseRequestFactory {

    private static GithubApi api;

    public static void init(String server) {
        api = createClient(server, GithubApi.class);
    }

    public static void getUser(String user, SimpleResponseListener<User> listener) {
        api.getUser(user).enqueue(new SimpleCallback<>(listener));
    }

    public static void getRepos(String user, SimpleResponseListener<List<Repo>> listener) {
        api.getRepos(user).enqueue(new SimpleCallback<>(listener));
    }
}
