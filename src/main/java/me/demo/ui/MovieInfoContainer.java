package me.demo.ui;

import java.time.ZonedDateTime;

public interface MovieInfoContainer {

	MovieInfo readMovieInfo();

	record MovieInfo(String country, ZonedDateTime releaseDate) {}

}
