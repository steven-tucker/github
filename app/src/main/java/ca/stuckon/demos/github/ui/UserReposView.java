package ca.stuckon.demos.github.ui;

import android.animation.LayoutTransition;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ca.stuckon.demos.github.R;
import ca.stuckon.demos.github.network.Repo;
import ca.stuckon.demos.github.network.User;

public class UserReposView extends RelativeLayout {

    private TextInputLayout inputLayout;
    private TextInputEditText inputEditText;
    private Listener listener;
    private ImageView avatarView;
    private TextView nameView;
    private RecyclerView recyclerView;
    private UserReposAdapter adapter;

    public UserReposView(Context context) {
        super(context);
        init(context);
    }

    public UserReposView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.user_repos_view, this);
        setBackgroundColor(0xFAFAFA);
        setFocusableInTouchMode(true);

        inputLayout = findViewById(R.id.userInputLayout);
        inputEditText = findViewById(R.id.userInputEditText);
        nameView = findViewById(R.id.username);
        avatarView = findViewById(R.id.avatar);
        recyclerView = findViewById(R.id.repo_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new UserReposAdapter();
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_search).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                notifySearchSubmit();
            }
        });
    }

    private void notifySearchSubmit() {
        if (listener != null) {
            listener.onSearchSubmit(inputEditText.getText().toString());
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
        adapter.setListener(listener);
    }

    public void setUser(User user) {
        if (user == null) {
            nameView.setText("");
            avatarView.setImageDrawable(null);
        } else {
            nameView.setText(user.getName());
            Picasso.with(getContext()).load(user.getAvatar()).into(avatarView);
        }
    }

    public void setRepos(List<Repo> repos) {
        Log.d("TEST", "setting repos");
        adapter.setData(repos);
    }

    public interface Listener extends UserReposAdapter.Listener {
        void onSearchSubmit(String username);
    }
}
