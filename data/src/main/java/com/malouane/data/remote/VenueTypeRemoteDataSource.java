package com.malouane.data.remote;

import com.malouane.data.remote.api.FenKoloApi;
import com.malouane.data.remote.model.VenueTypeRemoteModel;
import io.reactivex.Observable;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class VenueTypeRemoteDataSource {
  private final FenKoloApi fenKoloApi;

  public Observable<List<VenueTypeRemoteModel>> getVenueTypes() {
    return fenKoloApi.getVenueTypes();
  }

  public VenueTypeRemoteDataSource(@NotNull FenKoloApi fenKoloApi) {
    this.fenKoloApi = fenKoloApi;
  }
}