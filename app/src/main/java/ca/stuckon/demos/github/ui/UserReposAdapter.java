package ca.stuckon.demos.github.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ca.stuckon.demos.github.R;
import ca.stuckon.demos.github.network.Repo;

public class UserReposAdapter extends RecyclerView.Adapter<UserReposAdapter.Holder> implements View.OnClickListener {

    private List<Repo> repos = Collections.emptyList();
    private Listener listener;

    public UserReposAdapter() {
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setData(List<Repo> repos) {
        if (repos == null) {
            this.repos = Collections.emptyList();
        } else {
            this.repos = repos;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_card_view, parent, false),
                this);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Repo repo = repos.get(position);
        holder.name.setText(repo.getName());
        holder.desc.setText(repo.getDescription());
        holder.itemView.setTag(repo);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null && v.getTag() instanceof Repo) {
            listener.onRepoClicked((Repo) v.getTag());
        }
    }

    public static class Holder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView desc;

        public Holder(View root, View.OnClickListener listener) {
            super(root);

            name = root.findViewById(R.id.repo_name);
            desc = root.findViewById(R.id.repo_desc);
            root.setOnClickListener(listener);
        }
    }

    public interface Listener {
        void onRepoClicked(Repo repo);
    }
}
