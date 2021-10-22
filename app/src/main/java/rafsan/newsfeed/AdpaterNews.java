package rafsan.newsfeed;

/**
 * Created by RAFSAN on 07-Nov-17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;


public class AdpaterNews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Newsfeed> data = Collections.emptyList();
    Newsfeed current;
    int currentPos = 0;

    // create constructor to innitilize context and data sent from MainActivity
    public AdpaterNews(Context context, List<Newsfeed> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        Newsfeed current = data.get(position);
        myHolder.txtTitle.setText(current.newsTitle);
        myHolder.txtCommentCount.setText(current.commentCount);
        myHolder.txtFeedLink.setText(current.newsFeedLinkText);
        myHolder.txtLikeCount.setText(current.likeCount);
        myHolder.txtMiniAppTitle.setText(current.miniAppName);
        myHolder.txtPostTime.setText(current.postTime);

        if (current.isUrgentPost) {
            myHolder.imageViewUrgent.setVisibility(View.VISIBLE);
        } else {
            myHolder.imageViewUrgent.setVisibility(View.GONE);
        }

        if (isEmptyString(current.miniAppName)) {
            myHolder.txtMiniAppTitle.setVisibility(View.GONE);
            myHolder.txtBar.setVisibility(View.GONE);
        } else {
            myHolder.txtMiniAppTitle.setVisibility(View.VISIBLE);
            myHolder.txtBar.setVisibility(View.VISIBLE);
        }
        if (current.newsFeedLinkText.equals(": ")) {
            myHolder.txtFeedLink.setVisibility(View.GONE);
        }
        // load image into imageview using glide
        Glide.with(context).load(current.cardImageURL)
                .placeholder(R.drawable.rain)
                .error(R.drawable.rain)
                .into(myHolder.imageViewCard);

       /* Glide.with(context).load(current.miniAppIconImage)
                .placeholder(R.drawable.rain)
                .error(R.drawable.rain)
                .into(myHolder.imageViewCard);
*/
    }

    public static boolean isEmptyString(String text) {
        return (text == null || text.trim().equals("null") || text.trim()
                .length() <= 0);
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtMiniAppTitle;
        TextView txtFeedLink;
        TextView txtLikeCount;
        TextView txtCommentCount;
        TextView txtBar;
        TextView txtPostTime;
        ImageView imageViewCard;
        ImageView imageViewUrgent;


        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.textView_title);
            txtMiniAppTitle = (TextView) itemView.findViewById(R.id.textview_miniappName);
            txtFeedLink = (TextView) itemView.findViewById(R.id.textView_feedLink);
            txtLikeCount = (TextView) itemView.findViewById(R.id.textView_likeCount);
            txtCommentCount = (TextView) itemView.findViewById(R.id.textView_commentCount);
            txtBar = (TextView) itemView.findViewById(R.id.textViewBar);
            txtPostTime = (TextView) itemView.findViewById(R.id.textView_postTime);
            imageViewCard = (ImageView) itemView.findViewById(R.id.im_card_image);
            imageViewUrgent = (ImageView) itemView.findViewById(R.id.im_urgent);
        }

    }

}
