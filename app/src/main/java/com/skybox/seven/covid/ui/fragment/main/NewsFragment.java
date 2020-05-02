package com.skybox.seven.covid.ui.fragment.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.NewsArticle;
import com.skybox.seven.covid.ui.adapters.contactAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.skybox.seven.covid.ui.adapters.contactAdapter.NEWS_LIST;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    contactAdapter adapter;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = rootView.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new contactAdapter(getContext(),new ArrayList(),NEWS_LIST);
        recyclerView.setAdapter(adapter);
        new newsColl().execute();

        return rootView;
    }

    private class newsColl extends AsyncTask<String, Void, String> {

        HttpResponse<String> response;

        @Override
        protected String doInBackground(String... strings) {

            try {
                response = Unirest.get("https://newsapi.org/v2/everything?q=covid&qInTitle=covid&pageSize=10&from=2020-04-24&to=2020-05-27&sortBy=popularity&apiKey=b3d54181b14e4d9faaa3be9909a1953f").asString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

            return response.getBody();
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            ArrayList<NewsArticle> list = new ArrayList();
            Gson gson = new Gson();

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("articles");

                for(int i=0;i<jsonArray.length();i++){
                    NewsArticle article = gson.fromJson(jsonArray.getJSONObject(i).toString(),NewsArticle.class);
                    list.add(article);
                    System.out.println(article.getAuthor());
                }

                adapter.setNews(list);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(response);
        }
    }


}
