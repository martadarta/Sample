package infinity.com.sampleutils.data.modules.remote;


import infinity.com.sampleutils.data.modules.local.entity.Result;
import infinity.com.sampleutils.data.modules.remote.ipentity.IpApi;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("json")
    Call<IpApi> getInfo();

    @Headers({"Content-Type: text/plain"})
    @POST("/api/1.0/log")
    Call<Result> sendStat(@Body RequestBody deviceInfo);

}
