package me.sebastianrevel.instagram;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;

import me.sebastianrevel.instagram.models.Post;

public class InstaAdapter extends RecyclerView.Adapter<InstaAdapter.ViewHolder> {

    private ArrayList<Post> posts;
    private Context context;

    public InstaAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    // creates and inflates a new view.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // receive context
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.post_item, parent, false);
        return new ViewHolder(postView);
    }
    // associates a view with items
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // get the movie data at the specified position
        Post post = posts.get(position);
        Log.v("+++++", String.format("%d", posts.size()));
        holder.tvDescription.setText(post.getDescription());
        holder.tvDescription.setText(post.getDescription());
        holder.tvUser.setText(post.getUser().getUsername());
//      holder.tvLikebar.setText(post.getDescription());

        // build image for post
        ParseFile postImage = post.getImage();

        ParseFile profileImage = post.getPImage();

        String url = postImage.getUrl();
        String PUrl = profileImage.getUrl();

        ImageView imageView = holder.ivPost;
        ImageView profileView = holder.ivProfile;
        // image load
        Glide.with(context)
                .load(url)
                .into(imageView);
        Glide.with(context)
                .load(PUrl)
                .into(profileView);


    }

    // returns total number of items.
    @Override
    public int getItemCount() {
        return posts.size();
    }
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        //  tracking view objects
        ImageView ivPost;
        TextView tvDescription;
        TextView tvUser;
        ImageView ivProfile;
        TextView tvLikebar;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPost = itemView.findViewById(R.id.post_iv);
            tvDescription = itemView.findViewById(R.id.description_tv);
            tvLikebar = itemView.findViewById(R.id.like_comment_tv);
            tvUser = itemView.findViewById(R.id.profile_tv);
            ivProfile = itemView.findViewById(R.id.profile_iv);
        }
    }
}
