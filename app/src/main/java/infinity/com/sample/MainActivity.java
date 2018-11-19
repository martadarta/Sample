package infinity.com.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import infinity.com.sampleutils.data.modules.DeviceDataSource;
import infinity.com.sampleutils.data.modules.DeviceRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DeviceDataSource mDeviceDataSource = DeviceRepository.getInstance();
        mDeviceDataSource.nameApp(this);
        mDeviceDataSource.getBuildInfo();
        mDeviceDataSource.getEmulatorCheck(this);
        mDeviceDataSource.getInstallApps(this);
        mDeviceDataSource.getNetworkInfo(this);
        mDeviceDataSource.setDomain("http://familyabstract.host/");

        mDeviceDataSource.sendStatistic(new DeviceDataSource.LoadStatisticResponce() {
            @Override
            public void loadStatistic(String loadInfo) {
                System.out.println();
            }
        });
    }
}
