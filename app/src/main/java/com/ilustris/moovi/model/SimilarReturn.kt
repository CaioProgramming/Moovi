package com.ilustris.moovi.model

import com.ilustris.moovify.model.Movie

class SimilarReturn(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    total_results: Int
)