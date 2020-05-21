package com.skybox.seven.covid.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.NewsArticle;
import com.skybox.seven.covid.adapters.NewsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * A View that holds all the news {@link Fragment}.
 */
public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    TextView networkErrors;
    SwipeRefreshLayout refreshLayout;

    public NewsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = rootView.findViewById(R.id.recycler);
        networkErrors = rootView.findViewById(R.id.network_error);
        refreshLayout = rootView.findViewById(R.id.swipe_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext(),new ArrayList());
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> new NewsCall().execute());
        new NewsCall().execute();

        return rootView;
    }

    private class NewsCall extends AsyncTask<String, Void, String> {

        HttpResponse<String> response;

        @Override
        protected String doInBackground(String... strings) {

            try {
                response = Unirest.get("https://newsapi.org/v2/everything?q=covid&qInTitle=covid&pageSize=10&from=2020-04-24&to=2020-05-27&sortBy=popularity&apiKey=b3d54181b14e4d9faaa3be9909a1953f").asString();
            } catch (UnirestException e) {
                networkErrors.setVisibility(View.VISIBLE);
                e.printStackTrace();
            }

            if (response != null) {
                return response.getBody();
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            refreshLayout.setRefreshing(false);
            if (response != null) {
                networkErrors.setVisibility(View.GONE);
                ArrayList<NewsArticle> list = new ArrayList();
                Gson gson = new Gson();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        NewsArticle article = gson.fromJson(jsonArray.getJSONObject(i).toString(), NewsArticle.class);
                        list.add(article);
                        System.out.println(article.getAuthor());
                    }

                    adapter.setNews(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println(response);
            } else {
                networkErrors.setVisibility(View.VISIBLE);
            }
        }
    }


}
