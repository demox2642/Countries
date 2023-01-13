package com.demox.countries.domain.mainscreen.model

import android.os.Parcel
import android.os.Parcelable

data class Country(
    val name: String,
    val flag: String,
    val region: String,
    val capital: String,
    val currencies: List<Currency>,
    val timezones: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(Currency)!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(flag)
        parcel.writeString(region)
        parcel.writeString(capital)
        parcel.writeTypedList(currencies)
        parcel.writeStringList(timezones)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}
