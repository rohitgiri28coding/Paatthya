package com.coaching.paatthya.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.coaching.paatthya.R
import com.coaching.paatthya.domain.model.Review

class ResultViewModel: ViewModel() {
    val reviewSamples = listOf(
        Review(
            review = "I was very weak in coding until I met Shubham sir. With the help of sir's DSA and Full Stack Development course, where I learned to make real life project and after few months of hard work and sir's guidance I was able to crack the big MNC so easily.",
            name = "Nishant",
            designation = "SDE at Amazon",
            image = R.drawable.review_img
        ),
        Review(
            review = "He is one of the best teacher for computer science student in Patna. The way he clears each and every topics you will fall in love with coding. He is not only a teacher, he is like our elder brother who will guide you in your carrier path just like your big brother will do.",
            name = "Sneha Verma",
            designation = "               M.C.A.",
            image = R.drawable.review_image2
        ),
        Review(
            review = "I have been trying to find a good course on Web development for a very long time, both online and offline. But the courses that I liked were not up to date. Finally found a perfect course for it in Paatthya Computer Classes. Really enjoying your way of Explaining Concepts Sir.",
            name = "Abhishek",
            designation = "           B.Tech",
            image = R.drawable.review_image3
        ),
    )
}