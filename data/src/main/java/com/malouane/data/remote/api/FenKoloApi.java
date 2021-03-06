package com.malouane.data.remote.api;

import com.malouane.data.BuildConfig;
import com.malouane.data.remote.api.util.FourSquareRequestInter;
import com.malouane.data.remote.api.util.MoshiConverters;
import com.malouane.data.remote.model.VenueDetailsRemoteModel;
import com.malouane.data.remote.model.VenueRemoteModel;
import com.malouane.data.remote.model.VenueTipRemoteModel;
import com.malouane.data.remote.model.VenueTypeRemoteModel;
import com.serjltt.moshi.adapters.ElementAt;
import com.serjltt.moshi.adapters.Wrapped;
import com.squareup.moshi.Moshi;
import io.reactivex.Observable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class FenKoloApi implements FenKoloService {
  private FenKoloService apiService;

    public FenKoloApi() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        FourSquareRequestInter interceptor = new FourSquareRequestInter();
        MoshiConverters converters =  new MoshiConverters();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);

        long TIMEOUT = 10L;
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(interceptor).build();

        Moshi moshi = new Moshi.Builder().add(Wrapped.ADAPTER_FACTORY)
            .add(ElementAt.ADAPTER_FACTORY)
            .add(converters)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();

        apiService = retrofit.create(FenKoloService.class);
    }

    @Override
    public Observable<List<VenueRemoteModel>> getVenues(String locationLatLong, String catId,
        String query, String radius) {
      return apiService.getVenues(locationLatLong, catId, query, "450");
    }

    @Override public Observable<VenueTypeRemoteModel> getVenueTypes() {
        return apiService.getVenueTypes();
    }

  @Override public Observable<VenueDetailsRemoteModel> getVenueById(String id) {
        return apiService.getVenueById(id);
  }

  @Override public Observable<List<VenueTipRemoteModel>> getVenueTipsOf(String id) {
    return apiService.getVenueTipsOf(id);
  }
}
