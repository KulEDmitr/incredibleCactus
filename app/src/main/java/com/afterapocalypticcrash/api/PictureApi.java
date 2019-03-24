package com.afterapocalypticcrash.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PictureApi {

    @GET("search/photos/?per_page=48&client_id=bdd50ffe92fce77f51ece58c469f6a7867781199430eb9d13a1c90ce66a87113")
    Observable<PictureApiContent> getResults(@Query("query") String query);

}
