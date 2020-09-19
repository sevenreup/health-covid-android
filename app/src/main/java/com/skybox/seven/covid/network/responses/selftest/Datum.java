package com.skybox.seven.covid.network.responses.selftest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("questions_english")
    @Expose
    private String questionsEnglish;
    @SerializedName("subtitle_english")
    @Expose
    private String subtitleEnglish;
    @SerializedName("answers_english")
    @Expose
    private List<String> answersEnglish;
    @SerializedName("questions_chichewa")
    @Expose
    private String questionsChichewa;
    @SerializedName("subtitle_chichewa")
    @Expose
    private String subtitleChichewa;
    @SerializedName("answers_chichewa")
    @Expose
    private List<String> answersChichewa;
    @SerializedName("type")
    @Expose
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionsEnglish() {
        return questionsEnglish;
    }

    public void setQuestionsEnglish(String questionsEnglish) {
        this.questionsEnglish = questionsEnglish;
    }

    public String getSubtitleEnglish() {
        return subtitleEnglish;
    }

    public void setSubtitleEnglish(String subtitleEnglish) {
        this.subtitleEnglish = subtitleEnglish;
    }

    public List<String> getAnswersEnglish() {
        return answersEnglish;
    }

    public void setAnswersEnglish(List<String> answersEnglish) {
        this.answersEnglish = answersEnglish;
    }

    public String getQuestionsChichewa() {
        return questionsChichewa;
    }

    public void setQuestionsChichewa(String questionsChichewa) {
        this.questionsChichewa = questionsChichewa;
    }

    public String getSubtitleChichewa() {
        return subtitleChichewa;
    }

    public void setSubtitleChichewa(String subtitleChichewa) {
        this.subtitleChichewa = subtitleChichewa;
    }

    public List<String> getAnswersChichewa() {
        return answersChichewa;
    }

    public void setAnswersChichewa(List<String> answersChichewa) {
        this.answersChichewa = answersChichewa;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}