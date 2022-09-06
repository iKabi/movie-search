package me.demo.ui.template;

import lombok.extern.slf4j.Slf4j;
import me.demo.ui.MovieInfoContainer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@Slf4j
public abstract class AbstractMovieInfoContainer implements MovieInfoContainer {

	public static final ZoneId UTC = ZoneId.of("UTC");
	public static final Map<String, ZoneId> COUNTRY_NAME_TO_ZONE_ID = Map.ofEntries(
		Map.entry("United States", ZoneId.of("America/New_York")),
		Map.entry("India", ZoneId.of("Asia/Kolkata"))
	);

	protected abstract String readCountry();

	protected abstract ZonedDateTime readReleaseDate();

	@Override
	public MovieInfo readMovieInfo() {
		var country = readCountry();
		var releaseDate = readReleaseDate();
		return new MovieInfo(country, releaseDate);
	}

	protected ZonedDateTime unixTime(LocalDate onlyDate, String zone) {
		if (isValidKey(zone)) {
			return onlyDate.atStartOfDay(COUNTRY_NAME_TO_ZONE_ID.get(zone))
				.withZoneSameInstant(UTC);
		}
		log.error("Unknown country key '{}' detected", zone);
		throw new IllegalArgumentException(zone);
	}

	private boolean isValidKey(String zone) {
		return COUNTRY_NAME_TO_ZONE_ID.containsKey(zone);
	}

}
