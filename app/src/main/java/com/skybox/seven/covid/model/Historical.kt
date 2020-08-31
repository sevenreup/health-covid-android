package com.skybox.seven.covid.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Historical(
        val country: String = "",
        val timeline: TimeLine = TimeLine())

data class TimeLine(
        @SerializedName("cases") val casesMap: LinkedHashMap<String, Int> = linkedMapOf(),
        @SerializedName("deaths") val deathsMap: LinkedHashMap<String, Int> = linkedMapOf(),
        @SerializedName("recovered") val recoveredMap: LinkedHashMap<String, Int> = linkedMapOf()
)

class HistoricalResult(
        val country: String = "",
        val cases: List<TimeLineResult>,
        val deaths: List<TimeLineResult>,
        val recovered: List<TimeLineResult>
)

data class TimeLineResult(val date: String?, val cases: Int, val deaths: Int, val recovered: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeInt(cases)
        parcel.writeInt(deaths)
        parcel.writeInt(recovered)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<TimeLineResult> {
        override fun createFromParcel(parcel: Parcel): TimeLineResult {
            return TimeLineResult(parcel)
        }

        override fun newArray(size: Int): Array<TimeLineResult?> {
            return arrayOfNulls(size)
        }
    }
}