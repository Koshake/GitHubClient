package com.koshake1.mygithubclient.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepository (
    @Expose val id: String,
    @Expose val name: String? = null,
    @Expose val forksCount: Int? = null
): Parcelable