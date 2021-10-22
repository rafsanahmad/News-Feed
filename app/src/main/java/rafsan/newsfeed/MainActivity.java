package rafsan.newsfeed;

/**
 * Created by RAFSAN on 07-Nov-17.
 */

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView nRecylerview;
    private AdpaterNews mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Newsfeed> data = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray news_array = obj.getJSONArray("newsFeed");

            for (int i = 0; i < news_array.length(); i++) {
                JSONObject news = news_array.getJSONObject(i);
                Newsfeed newsData = new Newsfeed();
                newsData.cardImageURL = news.optString("cardImageURL");
                newsData.commentCount = news.optString("commentCount");
                newsData.isCommented = news.optBoolean("isCommented");
                newsData.isLiked = news.optBoolean("isLiked");
                newsData.isUrgentPost = news.optBoolean("isUrgentPost");
                newsData.likeCount = news.optString("likeCount");
                newsData.miniAppColor = news.optString("miniAppColor");
                newsData.miniAppIconImage = news.optString("miniAppIconImage");
                newsData.miniAppName = news.optString("miniAppName");
                newsData.newsFeedLinkText = news.optString("newsFeedLinkText");
                newsData.newsID = news.optString("newsId");
                newsData.newsTitle = news.optString("newsTitle");
                newsData.postTime = news.optString("postTime");
                newsData.postType = news.optString("postType");
                data.add(newsData);

                // Setup and Handover data to recyclerview
                nRecylerview = (RecyclerView) findViewById(R.id.recylerView);
                mAdapter = new AdpaterNews(MainActivity.this, data);
                nRecylerview.setAdapter(mAdapter);
                nRecylerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("TestJSON.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
