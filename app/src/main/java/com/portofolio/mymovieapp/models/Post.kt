package com.portofolio.moviesapp.Models

/**
 * Created by paul on 12/29/2019 at 10:52 PM.
 */
data class Post(val id: Int, val title: String, val body: String )

data class PostList(val posts: ArrayList<Post>)