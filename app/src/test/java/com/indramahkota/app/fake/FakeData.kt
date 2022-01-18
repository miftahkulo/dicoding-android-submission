package com.indramahkota.app.fake

import com.indramahkota.domain.model.Movie

object FakeData {
    val fake1 = Movie(
        id = 524434,
        title = "Eternals",
        voteCount = 2748,
        posterPath = "/b6qUu00iIIkXX13szFy7d0CyNcg.jpg",
        overview = "The Eternals are a team of ancient aliens who have...",
        originalLanguage = "en",
        releaseDate = "Wed, 3 Nov 2021",
        popularity = 11902.6,
        voteAverage = 7.3,
        favorite = false,
        isTvShows = false,
    )

    val fake2 = Movie(
        id = 425909,
        title = "Ghostbusters: Afterlife",
        voteCount = 1185,
        posterPath = "/sg4xJaufDiQl7caFEskBtQXfD4x.jpg",
        overview = "When a single mom and her two kids arrive in a sma...",
        originalLanguage = "en",
        releaseDate = "Thu, 11 Nov 2021",
        popularity = 7497.179,
        voteAverage = 7.6,
        favorite = false,
        isTvShows = false,
    )

    val fake3 = Movie(
        id = 634649,
        title = "Spider-Man: No Way Home",
        voteCount = 4203,
        posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        overview = "Peter Parker is unmasked and no longer able to sep...",
        originalLanguage = "en",
        releaseDate = "Wed, 15 Dec 2021",
        popularity = 7827.6,
        voteAverage = 8.4,
        favorite = false,
        isTvShows = false,
    )

    val fake4 = Movie(
        id = 568124,
        title = "Encanto",
        voteCount = 2939,
        posterPath = "/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
        overview = "The tale of an extraordinary family, the Madrigals...",
        originalLanguage = "en",
        releaseDate = "Wed, 24 Nov 2021",
        popularity = 6462.935,
        voteAverage = 7.8,
        favorite = false,
        isTvShows = false,
    )

    val fake5 = Movie(
        id = 460458,
        title = "Resident Evil: Welcome to Raccoon City",
        voteCount = 882,
        posterPath = "/7uRbWOXxpWDMtnsd2PF3clu65jc.jpg",
        overview = "Once the booming home of pharmaceutical giant Umbr...",
        originalLanguage = "en",
        releaseDate = "Wed, 24 Nov 2021",
        popularity = 5472.481,
        voteAverage = 6.1,
        favorite = false,
        isTvShows = false,
    )

    val fake6 = Movie(
        id = 624860,
        title = "The Matrix Resurrections",
        voteCount = 2220,
        posterPath = "/8c4a8kE7PizaGQQnditMmI1xbRp.jpg",
        overview = "Plagued by strange memories, Neo's life takes an u...",
        originalLanguage = "en",
        releaseDate = "Thu, 16 Dec 2021",
        popularity = 4703.672,
        voteAverage = 6.9,
        favorite = false,
        isTvShows = false,
    )

    val fake7 = Movie(
        id = 580489,
        title = "Venom: Let There Be Carnage",
        voteCount = 5838,
        posterPath = "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
        overview = "After finding a host body in investigative reporte...",
        originalLanguage = "en",
        releaseDate = "Thu, 30 Sep 2021",
        popularity = 3660.958,
        voteAverage = 7.1,
        favorite = false,
        isTvShows = false,
    )

    val fake8 = Movie(
        id = 512195,
        title = "Red Notice",
        voteCount = 2669,
        posterPath = "/lAXONuqg41NwUMuzMiFvicDET9Y.jpg",
        overview = "An Interpol-issued Red Notice is a global alert to...",
        originalLanguage = "en",
        releaseDate = "Thu, 4 Nov 2021",
        popularity = 3294.559,
        voteAverage = 6.8,
        favorite = false,
        isTvShows = false,
    )

    val fake9 = Movie(
        id = 585083,
        title = "Hotel Transylvania: Transformania",
        voteCount = 475,
        posterPath = "/teCy1egGQa0y8ULJvlrDHQKnxBL.jpg",
        overview = "When Van Helsing's mysterious invention, the \"Mons...",
        originalLanguage = "en",
        releaseDate = "Thu, 13 Jan 2022",
        popularity = 3619.893,
        voteAverage = 7.8,
        favorite = false,
        isTvShows = false,
    )

    val fake10 = Movie(
        id = 763788,
        title = "Dangerous",
        voteCount = 108,
        posterPath = "/vTtkQGC7qKlSRQJZYtAWAmYdH0A.jpg",
        overview = "A reformed sociopath heads to a remote island afte...",
        originalLanguage = "en",
        releaseDate = "Fri, 5 Nov 2021",
        popularity = 2695.138,
        voteAverage = 6.7,
        favorite = false,
        isTvShows = false,
    )

    fun fakeListMovie(): List<Movie> = listOf(
        fake1,
        fake2,
        fake3,
        fake4,
        fake5,
        fake6,
        fake7,
        fake8,
        fake9,
        fake10,
    )

    fun fakeListTv(): List<Movie> = fakeListMovie().map {
        it.isTvShows = true
        it
    }

    fun fakeFavoriteListMovie(): List<Movie> = fakeListMovie().map {
        it.favorite = true
        it
    }

    fun fakeFavoriteListTv(): List<Movie> = fakeListMovie().map {
        it.isTvShows = true
        it.favorite = true
        it
    }
}