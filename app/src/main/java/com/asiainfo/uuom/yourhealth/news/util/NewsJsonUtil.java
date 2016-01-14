package com.asiainfo.uuom.yourhealth.news.util;

import com.asiainfo.uuom.yourhealth.bean.NewsBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uuom on 16-1-13.
 */
public class NewsJsonUtil {

    public static List<NewsBean> parseJsonToBean(String json){

        List<NewsBean> list = null;
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("yi18").getAsJsonArray();
        if (jsonArray != null && jsonArray.size()>0){
            list = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject newsJsonObject = jsonArray.get(i).getAsJsonObject();
                NewsBean news = new NewsBean();
                news.setTitle(newsJsonObject.get("title").getAsString());
                JsonElement imgEle = newsJsonObject.get("img");
                if (imgEle != null){
                    news.setImg(imgEle.getAsString());
                }
                news.setId(newsJsonObject.get("id").getAsInt());
                list.add(news);
            }
        }

        return list;
    }

    public static NewsBean parseNewsDetailJsonToBean(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        JsonObject newsJsonObject = jsonObject.get("yi18").getAsJsonObject();
        NewsBean news = new NewsBean();
        news.setMessage(newsJsonObject.get("message").getAsString());
        return news;
    }
}
