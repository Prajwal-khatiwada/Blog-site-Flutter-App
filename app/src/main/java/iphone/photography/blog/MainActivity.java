package iphone.photography.blog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    RecyclerView post_list;
    List<Post> posts;
    PostsAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        posts = new ArrayList<>();

        drawer = findViewById(R.id.drawer);
        post_list = findViewById(R.id.post_list);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        navigationView = findViewById(R.id.nav_view);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        extractPosts(getResources().getString(R.string.url));
        GridLayoutManager manager = new GridLayoutManager(this,2);
        post_list.setLayoutManager(manager);

        adapter = new PostsAdapter(posts);
        post_list.setAdapter(adapter);



    }

    public void extractPosts(String URL){

        RequestQueue queue = Volley.newRequestQueue(this);
        @SuppressLint("NotifyDataSetChanged") JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, response -> {
            Log.d("TAG", "onResponse: " + response.toString());
            for (int i = 0; i < response.length();i++) {

                try {
                    Post p = new Post();

                    JSONObject jsonObjectData = response.getJSONObject(i);
                    p.setDate(jsonObjectData.getString("date"));

                    JSONObject titleObject = jsonObjectData.getJSONObject("title");
                    p.setTitle(titleObject.getString("rendered"));

                    JSONObject contentObject = jsonObjectData.getJSONObject("content");
                    p.setContent(contentObject.getString("rendered"));

                    JSONObject excerptObject = jsonObjectData.getJSONObject("excerpt");
                    p.setExcerpt(excerptObject.getString("rendered"));

                    p.setFeature_images(jsonObjectData.getString("uagb_featured_image_src"));

                    posts.add(p);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            }, error -> Toast.makeText(MainActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show()
        );
        queue.add(request);

    }
}