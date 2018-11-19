package infinity.com.sampleutils.data.modules.remote;


import infinity.com.sampleutils.data.DeviceInfo;
import infinity.com.sampleutils.data.modules.remote.ipentity.IpApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IpInfo {

    private IpApi ipApi;
    private DeviceInfo mDeviceInfo;

    public IpApi loadInfo(){

        ApiInterface apiInterface = Connection.getConnect("http://ip-api.com/").create(ApiInterface.class);
        Call<IpApi> call = apiInterface.getInfo();
        call.enqueue(new Callback<IpApi>() {
            @Override
            public void onResponse(Call<IpApi> call, Response<IpApi> response) {
                ipApi = response.body();
            }

            @Override
            public void onFailure(Call<IpApi> call, Throwable t) {
                ipApi = null;
            }
        });
        return ipApi;
    }
}