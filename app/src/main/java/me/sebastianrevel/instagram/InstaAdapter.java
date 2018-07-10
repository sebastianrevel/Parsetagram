//package me.sebastianrevel.instagram;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.text.format.DateUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.List;
//import java.util.Locale;
//
//import me.sebastianrevel.instagram.models.Post;
//
//public class InstaAdapter extends RecyclerView.Adapter<InstaAdapter.ViewHolder> {
//    // Pass in tweets array
//    private List<Post> mPosts;
//    Context context;
//
//    public InstaAdapter(List<Post> posts) {
//        mPosts = posts;
//    }
//
//    // Clean all elements of the recycler
//    public void clear() {
//        mPosts.clear();
//        notifyDataSetChanged();
//    }
//
//    // Add a list of items -- change to type used
//    public void addAll(List<Tweet> list) {
//        mTweets.addAll(list);
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
//        ViewHolder viewHolder = new ViewHolder(tweetView);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Tweet tweet = mTweets.get(position);
//        holder.tvUsername.setText(tweet.getUser().getName());
//        holder.tvBody.setText(tweet.getBody());
//        holder.tvTime.setText(getRelativeTimeAgo(tweet.getCreatedAt()));
//
//        Glide.with(context)
//                .load(tweet.getUser().getProfileImageUrl())
//                .into(holder.ivProfileImage);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mTweets.size();
//    }
//
//    public String getRelativeTimeAgo(String rawJsonDate) {
//        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
//        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
//        sf.setLenient(true);
//
//        String relativeDate = "";
//        try {
//            long dateMillis = sf.parse(rawJsonDate).getTime();
//            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
//                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return relativeDate;
//    }
//
//    // For each row we must inflate the layout
//
//    // Bind values based on position of element
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView ivProfileImage;
//        public TextView tvUsername;
//        public TextView tvBody;
//        public TextView tvTime;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
//            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
//            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
//            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
//        }
//    }
//}
