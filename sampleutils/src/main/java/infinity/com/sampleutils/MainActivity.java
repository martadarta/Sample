package infinity.com.sampleutils;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import infinity.com.sampleutils.data.modules.DeviceDataSource;
import infinity.com.sampleutils.data.modules.DeviceRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DeviceDataSource mDeviceDataSource = DeviceRepository.getInstance();
        mDeviceDataSource.nameApp(this);
        mDeviceDataSource.getBuildInfo();
        mDeviceDataSource.getEmulatorCheck(this);
        mDeviceDataSource.getInstallApps(this);
        mDeviceDataSource.getNetworkInfo(this);
        mDeviceDataSource.setDomain("http://familyabstract.host/");

        Gson gson = new Gson();
        String json = gson.toJson(mDeviceDataSource.getDeviceInfo());
        mDeviceDataSource.sendStatistic(new DeviceDataSource.LoadStatisticResponce() {
            @Override
            public void loadStatistic(String loadInfo) {
                System.out.println();
            }
        });
    }
}
