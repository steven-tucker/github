package ca.stuckon.demos.github.framework;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ca.stuckon.core.network.SimpleResponseListener;
import ca.stuckon.demos.github.R;
import ca.stuckon.demos.github.network.GithubRequestFactory;
import ca.stuckon.demos.github.network.Repo;
import ca.stuckon.demos.github.network.User;
import ca.stuckon.demos.github.ui.UserReposView;

public class MainActivity extends Activity {

    private UserReposView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new UserReposView(this);
        setContentView(view);

        view.setListener(new UserReposView.Listener() {
            @Override
            public void onSearchSubmit(String username) {
                hideKeyboard();
                requestData(username);
            }

            @Override
            public void onRepoClicked(Repo repo) {
                showDialog(repo);
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void showDialog(Repo repo) {
        //TODO show dialog
        Log.d("TEST", "Repo clicked: " + repo.getName());
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_repo_details);
        TextView a = dialog.findViewById(R.id.details_a);
        TextView b = dialog.findViewById(R.id.details_b);
        TextView c = dialog.findViewById(R.id.details_c);
        a.setText(repo.getName());
        b.setText("" + repo.getStargazers());
        c.setText("" + repo.getForks());
        //updated Jul 6, 2017 12:15:11 AM
        //stars
        //fork
        dialog.show();
    }

    private void requestData(String username) {
        GithubRequestFactory.getUser(username, new SimpleResponseListener<User>() {
            @Override
            public void onSuccess(User user) {
                if (view != null) {
                    view.setUser(user);
                }
            }

            @Override
            public void onNoConnection(Throwable t) {
                Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponseError(Throwable t) {
                Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
            }
        });

        GithubRequestFactory.getRepos(username, new SimpleResponseListener<List<Repo>>() {
            @Override
            public void onSuccess(List<Repo> repos) {
                if (view != null) {
                    view.setRepos(repos);
                }
            }

            @Override
            public void onNoConnection(Throwable t) {
                //Consume
            }

            @Override
            public void onResponseError(Throwable t) {
                //Consume
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        view.setListener(null);
        view = null;
    }
}
