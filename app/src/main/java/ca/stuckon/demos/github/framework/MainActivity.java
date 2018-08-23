package ca.stuckon.demos.github.framework;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.List;

import ca.stuckon.core.network.SimpleResponseListener;
import ca.stuckon.demos.github.network.GithubRequestFactory;
import ca.stuckon.demos.github.network.Repo;
import ca.stuckon.demos.github.network.User;
import ca.stuckon.demos.github.ui.RepoDetailsView;
import ca.stuckon.demos.github.ui.UserReposView;

public class MainActivity extends AppCompatActivity {

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
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void showDialog(Repo repo) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        RepoDetailsView view = new RepoDetailsView(this);
        view.setRepo(repo);
        dialog.setContentView(view);
        ((View)view.getParent()).setBackgroundDrawable(null);
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
