package com.example.globallogicapp.data

import android.os.Parcel
import android.os.Parcelable

class Result : ArrayList<Result.ResultItem>(){
    data class ResultItem(
        var description: String? = null,
        var image: String? = null,
        var title: String? = null
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(description)
            parcel.writeString(image)
            parcel.writeString(title)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ResultItem> {
            override fun createFromParcel(parcel: Parcel): ResultItem {
                return ResultItem(parcel)
            }

            override fun newArray(size: Int): Array<ResultItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}