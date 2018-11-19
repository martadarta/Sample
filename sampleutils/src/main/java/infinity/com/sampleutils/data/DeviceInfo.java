package infinity.com.sampleutils.data;

import infinity.com.sampleutils.data.modules.local.entity.BuildInfo;
import infinity.com.sampleutils.data.modules.local.entity.InstallApps;
import infinity.com.sampleutils.data.modules.local.entity.TelephonyManagerInfo;
import infinity.com.sampleutils.data.modules.remote.ipentity.IpApi;

public class DeviceInfo {

    private String appName;
    private Boolean emulator;
    private BuildInfo mBuildInfo;
    private InstallApps mInstallApps;
    private TelephonyManagerInfo mTelephonyManagerInfo;
    private IpApi mNetworkInfo;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public TelephonyManagerInfo getTelephonyManagerInfo() {
        return mTelephonyManagerInfo;
    }

    public void setTelephonyManagerInfo(TelephonyManagerInfo telephonyManagerInfo) {
        mTelephonyManagerInfo = telephonyManagerInfo;
    }

    public Boolean getEmulator() {
        return emulator;
    }

    public void setEmulator(Boolean emulator) {
        this.emulator = emulator;
    }

    public BuildInfo getBuildInfo() {
        return mBuildInfo;
    }

    public void setBuildInfo(BuildInfo buildInfo) {
        mBuildInfo = buildInfo;
    }

    public InstallApps getInstallApps() {
        return mInstallApps;
    }

    public void setInstallApps(InstallApps installApps) {
        mInstallApps = installApps;
    }

    public IpApi getNetworkInfo() {
        return mNetworkInfo;
    }

    public void setNetworkInfo(IpApi networkInfo) {
        mNetworkInfo = networkInfo;
    }
}
