package ca.stuckon.demos.github.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import ca.stuckon.core.ui.TwoTextView;
import ca.stuckon.demos.github.R;
import ca.stuckon.demos.github.network.Repo;

public class RepoDetailsView extends FrameLayout {

    private TwoTextView updatedView;
    private TwoTextView starsView;
    private TwoTextView forksView;

    public RepoDetailsView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public RepoDetailsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.dialog_repo_details, this);
        updatedView = findViewById(R.id.dialog_updated);
        starsView = findViewById(R.id.dialog_stars);
        forksView = findViewById(R.id.dialog_forks);
    }

    public void setRepo(Repo repo) {
        updatedView.setRightText("Date");//TODO format updated Jul 6, 2017 12:15:11 AM
        starsView.setRightText(String.valueOf(repo.getStargazers()));
        forksView.setRightText(String.valueOf(repo.getForks()));
    }
}
