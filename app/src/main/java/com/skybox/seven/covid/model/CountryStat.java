package com.skybox.seven.covid.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryStat implements Parcelable {

    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("countryInfo")
    @Expose
    private CountryInfo countryInfo;
    @SerializedName("cases")
    @Expose
    private Integer cases;
    @SerializedName("todayCases")
    @Expose
    private Integer todayCases;
    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("todayDeaths")
    @Expose
    private Integer todayDeaths;
    @SerializedName("recovered")
    @Expose
    private Integer recovered;
    @SerializedName("todayRecovered")
    @Expose
    private Integer todayRecovered;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("critical")
    @Expose
    private Integer critical;
    @SerializedName("casesPerOneMillion")
    @Expose
    private Double casesPerOneMillion;
    @SerializedName("deathsPerOneMillion")
    @Expose
    private Double deathsPerOneMillion;
    @SerializedName("tests")
    @Expose
    private Double tests;
    @SerializedName("testsPerOneMillion")
    @Expose
    private Double testsPerOneMillion;
    @SerializedName("population")
    @Expose
    private Long population;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("oneCasePerPeople")
    @Expose
    private Integer oneCasePerPeople;
    @SerializedName("oneDeathPerPeople")
    @Expose
    private Integer oneDeathPerPeople;
    @SerializedName("oneTestPerPeople")
    @Expose
    private Integer oneTestPerPeople;
    @SerializedName("activePerOneMillion")
    @Expose
    private Double activePerOneMillion;
    @SerializedName("recoveredPerOneMillion")
    @Expose
    private Double recoveredPerOneMillion;
    @SerializedName("criticalPerOneMillion")
    @Expose
    private Double criticalPerOneMillion;

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(Integer todayCases) {
        this.todayCases = todayCases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(Integer todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(Integer todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public Double getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(Double casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public Double getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(Double deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public Double getTests() {
        return tests;
    }

    public void setTests(Double tests) {
        this.tests = tests;
    }

    public Double getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(Double testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Integer getOneCasePerPeople() {
        return oneCasePerPeople;
    }

    public void setOneCasePerPeople(Integer oneCasePerPeople) {
        this.oneCasePerPeople = oneCasePerPeople;
    }

    public Integer getOneDeathPerPeople() {
        return oneDeathPerPeople;
    }

    public void setOneDeathPerPeople(Integer oneDeathPerPeople) {
        this.oneDeathPerPeople = oneDeathPerPeople;
    }

    public Integer getOneTestPerPeople() {
        return oneTestPerPeople;
    }

    public void setOneTestPerPeople(Integer oneTestPerPeople) {
        this.oneTestPerPeople = oneTestPerPeople;
    }

    public Double getActivePerOneMillion() {
        return activePerOneMillion;
    }

    public void setActivePerOneMillion(Double activePerOneMillion) {
        this.activePerOneMillion = activePerOneMillion;
    }

    public Double getRecoveredPerOneMillion() {
        return recoveredPerOneMillion;
    }

    public void setRecoveredPerOneMillion(Double recoveredPerOneMillion) {
        this.recoveredPerOneMillion = recoveredPerOneMillion;
    }

    public Double getCriticalPerOneMillion() {
        return criticalPerOneMillion;
    }

    public void setCriticalPerOneMillion(Double criticalPerOneMillion) {
        this.criticalPerOneMillion = criticalPerOneMillion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.updated);
        dest.writeString(this.country);
        dest.writeParcelable(this.countryInfo, flags);
        dest.writeValue(this.cases);
        dest.writeValue(this.todayCases);
        dest.writeValue(this.deaths);
        dest.writeValue(this.todayDeaths);
        dest.writeValue(this.recovered);
        dest.writeValue(this.todayRecovered);
        dest.writeValue(this.active);
        dest.writeValue(this.critical);
        dest.writeValue(this.casesPerOneMillion);
        dest.writeValue(this.deathsPerOneMillion);
        dest.writeValue(this.tests);
        dest.writeValue(this.testsPerOneMillion);
        dest.writeValue(this.population);
        dest.writeString(this.continent);
        dest.writeValue(this.oneCasePerPeople);
        dest.writeValue(this.oneDeathPerPeople);
        dest.writeValue(this.oneTestPerPeople);
        dest.writeValue(this.activePerOneMillion);
        dest.writeValue(this.recoveredPerOneMillion);
        dest.writeValue(this.criticalPerOneMillion);
    }

    public CountryStat() {
    }

    protected CountryStat(Parcel in) {
        this.updated = in.readString();
        this.country = in.readString();
        this.countryInfo = in.readParcelable(CountryInfo.class.getClassLoader());
        this.cases = (Integer) in.readValue(Integer.class.getClassLoader());
        this.todayCases = (Integer) in.readValue(Integer.class.getClassLoader());
        this.deaths = (Integer) in.readValue(Integer.class.getClassLoader());
        this.todayDeaths = (Integer) in.readValue(Integer.class.getClassLoader());
        this.recovered = (Integer) in.readValue(Integer.class.getClassLoader());
        this.todayRecovered = (Integer) in.readValue(Integer.class.getClassLoader());
        this.active = (Integer) in.readValue(Integer.class.getClassLoader());
        this.critical = (Integer) in.readValue(Integer.class.getClassLoader());
        this.casesPerOneMillion = (Double) in.readValue(Double.class.getClassLoader());
        this.deathsPerOneMillion = (Double) in.readValue(Double.class.getClassLoader());
        this.tests = (Double) in.readValue(Double.class.getClassLoader());
        this.testsPerOneMillion = (Double) in.readValue(Double.class.getClassLoader());
        this.population = (Long) in.readValue(Long.class.getClassLoader());
        this.continent = in.readString();
        this.oneCasePerPeople = (Integer) in.readValue(Integer.class.getClassLoader());
        this.oneDeathPerPeople = (Integer) in.readValue(Integer.class.getClassLoader());
        this.oneTestPerPeople = (Integer) in.readValue(Integer.class.getClassLoader());
        this.activePerOneMillion = (Double) in.readValue(Double.class.getClassLoader());
        this.recoveredPerOneMillion = (Double) in.readValue(Double.class.getClassLoader());
        this.criticalPerOneMillion = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<CountryStat> CREATOR = new Creator<CountryStat>() {
        @Override
        public CountryStat createFromParcel(Parcel source) {
            return new CountryStat(source);
        }

        @Override
        public CountryStat[] newArray(int size) {
            return new CountryStat[size];
        }
    };
}