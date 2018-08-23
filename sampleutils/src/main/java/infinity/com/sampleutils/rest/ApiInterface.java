package infinity.com.sampleutils.rest;

import infinity.com.sampleutils.model.Params;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("json")
    Call<Params> getLocation();
}
