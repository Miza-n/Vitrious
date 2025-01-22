package com.example.vitrious.util

import com.example.vitrious.R
import kotlinx.serialization.Serializable


enum class Screen {
    login, register, home, splash
}

sealed class Status {
    data object Authenticated : Status()
    data object NotAuthenticated : Status()
    data object Loading : Status()
    data class Error(val message: String) : Status()
}


val devsList = listOf(
    News(
        image = R.drawable.mizanur,
        title = "Want to be a explorer of the world",
        genre = "Adventure",
        author = "Mizan Rahman",
        date = "12th Jan, 2025"
    ),
    News(
        image = R.drawable.adnan,
        title = "Want to be a edu content creater",
        genre = "Education",
        author = "Adnan Shad",
        date = "13th Jan, 2026"
    ),
    News(
        image = R.drawable.red,
        title = "Want to be a professional developer",
        genre = "Developer",
        author = "Red1 Hussain",
        date = "14th Jan, 2026"
    ),
    News(
        image = R.drawable.ton,
        title = "What to be a dedicated premik",
        genre = "Premik",
        author = "Tonu Chanda",
        date = "14th Jan, 2026"
    ),
    News(
        image = R.drawable.mizanur,
        title = "Want to be a explorer of the world",
        genre = "Adventure",
        author = "Mizan Rahman",
        date = "12th Jan, 2025"
    ),
    News(
        image = R.drawable.adnan,
        title = "Want to be a edu content creater",
        genre = "Education",
        author = "Adnan Shad",
        date = "13th Jan, 2026"
    ),
)
