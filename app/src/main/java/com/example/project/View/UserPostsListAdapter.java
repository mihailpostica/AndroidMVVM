package com.example.project.View;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project.Model.UserPost;
import com.example.project.R;
import com.example.project.Services.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.project.Services.PostsService.BASE_URL;
public class UserPostsListAdapter extends RecyclerView.Adapter<UserPostsListAdapter.PostViewHolder> {
    private List<UserPost> posts;
    public UserPostsListAdapter(List<UserPost> posts) {
        this.posts = posts;
    }
    public void updatePosts(List<UserPost> newPosts) {
        posts.clear();
        posts.addAll(newPosts);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView userImage;
        @BindView(R.id.nume)
        TextView userNume;
        @BindView(R.id.titlu)
        TextView postTitlu;
        @BindView(R.id.descriere)
        TextView postDescriere;
        @BindView(R.id.rating)
        TextView postRating;
        @BindView(R.id.categorie)
        TextView categorie;
        @BindView(R.id.data)
        TextView data;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(UserPost post) {
            userNume.setText(post.getUtilizator().getNume()+" "+post.getUtilizator().getPrenume());
            //time ago
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(post.getData());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long millis = date.getTime();
            data.setText(DateUtils.getRelativeTimeSpanString(millis).toString());
            postTitlu.setText(post.getTitlu());
            postDescriere.setText(post.getDescriere());
            categorie.setText(post.getCategorie().getNume());
            postRating.setText(post.getAvgRating().toString());
            Util.loadImage(userImage,BASE_URL+ "/storage/"+post.getUtilizator().getImagine() , Util.getProgressDrawable(userImage.getContext()));
        }
    }
}
