package infinity.com.sampleutils.data.modules.remote;

import infinity.com.sampleutils.data.DeviceInfo;
import infinity.com.sampleutils.data.modules.DeviceDataSource;
import infinity.com.sampleutils.data.modules.local.entity.Result;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceInfoApi {
    private DeviceInfo mDeviceInfoApi;

    public void sendInfo(String deviceInfo, String domain, final DeviceDataSource.LoadStatisticResponce loadStatisticResponce){

        String dom = domain;
        dom = dom.replace("http://", "http://alfa.");

        ApiInterface apiInterface = Connection.getConnect(dom).create(ApiInterface.class);
        String strRequestBody = deviceInfo;
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"),strRequestBody);
        Call<Result> call = apiInterface.sendStat(requestBody);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                loadStatisticResponce.loadStatistic(response.body().getResult());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                mDeviceInfoApi = null;
            }
        });
    }
}
