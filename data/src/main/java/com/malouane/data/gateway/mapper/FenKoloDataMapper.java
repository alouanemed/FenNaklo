package com.malouane.data.gateway.mapper;

import com.malouane.data.local.model.VenueLocalModel;
import com.malouane.data.local.model.VenuesTypeLocalModel;
import com.malouane.data.local.model.venue.HereNowLocal;
import com.malouane.data.local.model.venue.LocationLocalModel;
import com.malouane.fenkolo.domain.entity.HereNow;
import com.malouane.fenkolo.domain.entity.Location;
import com.malouane.fenkolo.domain.entity.Venue;
import com.malouane.fenkolo.domain.entity.VenueType;
import org.jetbrains.annotations.NotNull;

public class FenKoloDataMapper {
  public Venue venueToEntity(VenueLocalModel venue) {
    return new Venue(venue.getId(), venue.getName(),
        localLocationToEntity(venue.getLocationLocalModel()), venue.getVerified(),
        venue.getHasPerk(), localHereToEntity(venue.getHereNow()));
  }

  public VenueType localVenueTypeToEntity(VenuesTypeLocalModel venue) {
    return new VenueType(venue.getId(), venue.getName());
  }

  private HereNow localHereToEntity(@NotNull HereNowLocal hereNow) {
    return new HereNow(hereNow.getCount(), hereNow.getSummary());
  }

  private Location localLocationToEntity(LocationLocalModel location) {
    return new Location(location.getAddress(), location.getCrossStreet(), location.getLat(),
        location.getLng(), location.getDistance(), location.getPostalCode(), location.getCc(),
        location.getCity(), location.getState(), location.getPostalCode(),
        location.getFormattedAddress());
  }
}
