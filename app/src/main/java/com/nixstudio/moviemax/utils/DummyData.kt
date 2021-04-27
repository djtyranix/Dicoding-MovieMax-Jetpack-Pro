package com.nixstudio.moviemax.utils

import com.nixstudio.moviemax.models.MovieEntity
import com.nixstudio.moviemax.models.TvShowsEntity

object DummyData {
    fun generateMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                0,
                "Interstellar",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
                2015,
                "Sci-Fi",
                "The adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage."
            )
        )

        movies.add(
            MovieEntity(
                1,
                "The Avengers",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/RYMX2wcKCBAr24UyPD7xwmjaTn.jpg",
                2012,
                "Action",
                "When an unexpected enemy emerges and threatens global safety and security, Nick Fury, director of the international peacekeeping agency known as S.H.I.E.L.D., finds himself in need of a team to pull the world back from the brink of disaster. Spanning the globe, a daring recruitment effort begins!"
            )
        )

        movies.add(
            MovieEntity(
                2,
                "Avengers: Age of Ultron",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4ssDuvEDkSArWEdyBl2X5EHvYKU.jpg",
                2015,
                "Action",
                "When Tony Stark tries to jumpstart a dormant peacekeeping program, things go awry and Earth’s Mightiest Heroes are put to the ultimate test as the fate of the planet hangs in the balance. As the villainous Ultron emerges, it is up to The Avengers to stop him from enacting his terrible plans, and soon uneasy alliances and unexpected action pave the way for an epic and unique global adventure."
            )
        )

        movies.add(
            MovieEntity(
                3,
                "Avengers: Infinity War",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                2018,
                "Action",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
            )
        )

        movies.add(
            MovieEntity(
                4,
                "Avengers: Endgame",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ulzhLuWrPK07P1YkdWQLZnQh1JL.jpg",
                2019,
                "Action",
                "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store."
            )
        )

        movies.add(
            MovieEntity(
                5,
                "Ad Astra",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
                2019,
                "Sci-Fi",
                "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown."
            )
        )

        movies.add(
            MovieEntity(
                6,
                "2001: A Space Odyssey",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ve72VxNqjGM69Uky4WTo2bK6rfq.jpg",
                1968,
                "Sci-Fi",
                "Humanity finds a mysterious object buried beneath the lunar surface and sets off to find its origins with the help of HAL 9000, the world's most advanced super computer."
            )
        )

        movies.add(
            MovieEntity(
                7,
                "Zack Snyder's Justice League",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                2021,
                "Action",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions."
            )
        )

        movies.add(
            MovieEntity(
                8,
                "Raya and the Last Dragon",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                2021,
                "Adventure",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people."
            )
        )

        movies.add(
            MovieEntity(
                9,
                "Godzilla vs. Kong",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                2021,
                "Action",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )

        movies.add(
            MovieEntity(
                10,
                "Mortal Kombat",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                2021,
                "Action",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )

        return movies
    }

    fun generateTvShows(): List<TvShowsEntity> {
        val tvShows = ArrayList<TvShowsEntity>()

        tvShows.add(
            TvShowsEntity(
                0,
                "The Falcon and the Winter Soldier",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                2021,
                "Action",
                1,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            )
        )

        tvShows.add(
            TvShowsEntity(
                1,
                "Grey's Anatomy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                2005,
                "Drama",
                17,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
            )
        )

        tvShows.add(
            TvShowsEntity(
                2,
                "WandaVision",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                2021,
                "Mystery",
                1,
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems."
            )
        )

        tvShows.add(
            TvShowsEntity(
                3,
                "Riverdale",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                2017,
                "Mystery",
                5,
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade."
            )
        )

        tvShows.add(
            TvShowsEntity(
                4,
                "The Walking Dead",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                2010,
                "Action",
                10,
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way."
            )
        )

        tvShows.add(
            TvShowsEntity(
                5,
                "Superman & Lois",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg",
                2021,
                "Action",
                1,
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society."
            )
        )

        tvShows.add(
            TvShowsEntity(
                6,
                "Game of Thrones",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                2011,
                "Fantasy",
                8,
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
            )
        )

        tvShows.add(
            TvShowsEntity(
                7,
                "The Mandalorian",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                2019,
                "Fantasy",
                2,
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter."
            )
        )

        tvShows.add(
            TvShowsEntity(
                8,
                "Money Heist",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/MoEKaPFHABtA1xKoOteirGaHl1.jpg",
                2017,
                "Crime",
                2,
                "To carry out the biggest heist in history, a mysterious man called The Professor recruits a band of eight robbers who have a single characteristic: none of them has anything to lose. Five months of seclusion - memorizing every step, every detail, every probability - culminate in eleven days locked up in the National Coinage and Stamp Factory of Spain, surrounded by police forces and with dozens of hostages in their power, to find out whether their suicide wager will lead to everything or nothing."
            )
        )

        tvShows.add(
            TvShowsEntity(
                9,
                "Snabba Cash",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2euUp19voQVYaPVJZ2XRrXfl0sj.jpg",
                2021,
                "Crime",
                1,
                "The lives of an ambitious businesswoman, a charming gang enforcer and a troubled teen collide amidst a desperate — and sinister — pursuit of wealth."
            )
        )

        tvShows.add(
            TvShowsEntity(
                10,
                "Marvel's Agent of S.H.I.E.L.D.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gHUCCMy1vvj58tzE3dZqeC9SXus.jpg",
                2013,
                "Drama",
                7,
                "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary."
            )
        )

        return tvShows
    }

    fun generateLatestMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                7,
                "Zack Snyder's Justice League",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                2021,
                "Action",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions."
            )
        )

        movies.add(
            MovieEntity(
                8,
                "Raya and the Last Dragon",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                2021,
                "Adventure",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people."
            )
        )

        movies.add(
            MovieEntity(
                9,
                "Godzilla vs. Kong",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                2021,
                "Action",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )

        movies.add(
            MovieEntity(
                10,
                "Mortal Kombat",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                2021,
                "Action",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."
            )
        )

        return movies
    }

    fun generateLatestTvShows(): List<TvShowsEntity> {
        val tvShows = ArrayList<TvShowsEntity>()

        tvShows.add(
            TvShowsEntity(
                0,
                "The Falcon and the Winter Soldier",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                2021,
                "Action",
                1,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            )
        )

        tvShows.add(
            TvShowsEntity(
                5,
                "Superman & Lois",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg",
                2021,
                "Action",
                1,
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society."
            )
        )

        tvShows.add(
            TvShowsEntity(
                2,
                "WandaVision",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                2021,
                "Mystery",
                1,
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems."
            )
        )

        tvShows.add(
            TvShowsEntity(
                9,
                "Snabba Cash",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2euUp19voQVYaPVJZ2XRrXfl0sj.jpg",
                2021,
                "Crime",
                1,
                "The lives of an ambitious businesswoman, a charming gang enforcer and a troubled teen collide amidst a desperate — and sinister — pursuit of wealth."
            )
        )

        return tvShows
    }
}