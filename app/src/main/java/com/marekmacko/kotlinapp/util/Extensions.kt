package com.marekmacko.kotlinapp.util

import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ImageView.loadFromUrl(url: String) = Picasso.with(context).load(url).into(this)
