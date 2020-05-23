package com.skybox.seven.covid.viewmodels;

import android.app.Application;
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.skybox.seven.covid.model.NewsArticle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> networkErrors = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<List<NewsArticle>> newsArticles = new MutableLiveData<>(new ArrayList<>());
    public NewsViewModel(@NonNull Application application) {
        super(application);
    }
public void getNews() {
        new NewsCall().execute();
}
    private class NewsCall extends AsyncTask<String, Void, String> {

        HttpResponse<String> response;

        @Override
        protected String doInBackground(String... strings) {
            loading.postValue(true);
            try {
                response = Unirest.get("https://newsapi.org/v2/everything?q=covid&qInTitle=covid&pageSize=10&from=2020-04-24&to=2020-05-27&sortBy=popularity&apiKey=b3d54181b14e4d9faaa3be9909a1953f").asString();
            } catch (UnirestException e) {
                loading.postValue(false);
                networkErrors.postValue(true);
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

            if (response != null) {
                networkErrors.postValue(false);
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

                    newsArticles.postValue(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println(response);
            } else {
                networkErrors.postValue(true);
            }
            loading.postValue(false);
        }
    }

}
