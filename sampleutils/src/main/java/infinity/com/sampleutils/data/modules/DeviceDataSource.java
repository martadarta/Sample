package infinity.com.sampleutils.data.modules;

import android.content.Context;

import infinity.com.sampleutils.data.DeviceInfo;

public interface DeviceDataSource {

    interface LoadStatisticResponce{
        void loadStatistic(String loadInfo);
    }

    DeviceInfo nameApp(Context context);

    DeviceInfo getBuildInfo();

    DeviceInfo getInstallApps(Context context);

    DeviceInfo getNetworkInfo(Context context);

    DeviceInfo getIpInfo();

    DeviceInfo getEmulatorCheck(Context context);

    DeviceInfo getDeviceInfo();

    String setDomain(String domain);

    void sendStatistic(LoadStatisticResponce loadStatisticResponce);
}
