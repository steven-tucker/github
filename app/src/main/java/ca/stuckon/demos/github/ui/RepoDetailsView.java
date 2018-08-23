package ca.stuckon.demos.github.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ca.stuckon.core.ui.TwoTextView;
import ca.stuckon.demos.github.R;
import ca.stuckon.demos.github.network.Repo;

public class RepoDetailsView extends FrameLayout {

    private static final String FORMAT_DATE = "MMM d, yyyy h:mm:ss a";

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
        Date updated = repo.getUpdated();
        if (updated != null) {
            updatedView.setRightText(new SimpleDateFormat(FORMAT_DATE, Locale.CANADA).format(updated));
        }
        starsView.setRightText(String.valueOf(repo.getStargazers()));
        forksView.setRightText(String.valueOf(repo.getForks()));
    }
}
