package com.malouane.data.repository.mapper;

import com.malouane.data.local.model.VenueDetailsLocalModel;
import com.malouane.data.local.model.VenueLocalModel;
import com.malouane.data.local.model.VenueTipsLocalModel;
import com.malouane.data.local.model.venue.HereNowLocal;
import com.malouane.data.local.model.venue.LocationLocalModel;
import com.malouane.data.remote.model.VenueDetailsRemoteModel;
import com.malouane.data.remote.model.VenueRemoteModel;
import com.malouane.data.remote.model.VenueTipRemoteModel;
import com.malouane.data.remote.model.venue.HereNowRemote;
import com.malouane.data.remote.model.venue.LocationRemoteModel;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

//**
/* Map remote entities to local one
 */
public class VenueMapper {

  public VenueDetailsLocalModel toLocal(@NotNull VenueDetailsRemoteModel venue) {
    return new VenueDetailsLocalModel(venue.getId(), venue.getName(),
        remoteLocationTolocal(venue.getLocation()), venue.getRating(),
        venue.getBestPhoto() == null ? "" : venue.getBestPhoto().getPhotoURl(), formatPrice(venue),
        "rating");
  }

  private String formatPrice(@NotNull VenueDetailsRemoteModel venue) {
    return venue.getPrice() == null ? "N/A"
        : venue.getPrice().getCurrency() + " " + venue.getPrice().getMessage();
  }

  public VenueLocalModel toLocal(@NotNull VenueRemoteModel venue) {
    return new VenueLocalModel(venue.getId(), venue.getName(),
        remoteLocationTolocal(venue.getLocationRemoteModel()), venue.getVerified(),
        venue.getHasPerk(), remoteHereTolocal(venue.getHereNow()));
  }

  @NotNull public List<VenueLocalModel> toLocal(@NotNull List<VenueRemoteModel> items) {
    List<VenueLocalModel> outputList = new ArrayList<VenueLocalModel>();

    for (VenueRemoteModel item : items) {
      outputList.add(toLocal(item));
    }

    return outputList;
  }

  private LocationLocalModel remoteLocationTolocal(@NotNull LocationRemoteModel location) {
    return new LocationLocalModel(location.getAddress(), location.getCrossStreet(),
        location.getLat(), location.getLng(), location.getDistance(), location.getPostalCode(),
        location.getCc(), location.getCity(), location.getState(), location.getPostalCode());
  }

  private HereNowLocal remoteHereTolocal(@NotNull HereNowRemote hereNow) {
    return new HereNowLocal(hereNow.getCount(), hereNow.getSummary());
  }

  public VenueTipsLocalModel toLocal(@NotNull VenueTipRemoteModel tip) {
    return new VenueTipsLocalModel(tip.getId(), "", tip.getCreatedAt(), tip.getText(),
        tip.getAgreeCount(), tip.getDisagreeCount(), tip.getUser().getName(),
        tip.getUser().getPhoto().getPhotoURl());
  }

  public List<VenueTipsLocalModel> tipsToLocal(@NotNull List<VenueTipRemoteModel> tips) {
    List<VenueTipsLocalModel> outputList = new ArrayList<VenueTipsLocalModel>();

    for (VenueTipRemoteModel item : tips) {
      outputList.add(toLocal(item));
    }

    return outputList;
  }

}
