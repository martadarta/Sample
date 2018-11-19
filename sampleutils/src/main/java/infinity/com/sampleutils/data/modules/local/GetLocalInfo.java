package infinity.com.sampleutils.data.modules.local;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

import infinity.com.sampleutils.data.modules.local.entity.BuildInfo;
import infinity.com.sampleutils.data.modules.local.entity.InstallApps;
import infinity.com.sampleutils.data.modules.local.entity.TelephonyManagerInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.TELEPHONY_SERVICE;

public class GetLocalInfo {

    private BuildInfo mBuildInfo;
    private TelephonyManagerInfo mTelephonyManagerInfo;
    private InstallApps mInstallApps;

    public BuildInfo getBuildInfo(){
        mBuildInfo = new BuildInfo();
        mBuildInfo.setCodeName(Build.VERSION.CODENAME);
        mBuildInfo.setIncremental(Build.VERSION.INCREMENTAL);
        mBuildInfo.setAndroidVersion(Build.VERSION.RELEASE);
        mBuildInfo.setSdkInt(String.valueOf(Build.VERSION.SDK_INT));
        mBuildInfo.setDevice(Build.DEVICE);
        mBuildInfo.setFingerprint(Build.FINGERPRINT);
        mBuildInfo.setHardware(Build.HARDWARE);
        mBuildInfo.setId(Build.ID);
        mBuildInfo.setHost(Build.HOST);
        mBuildInfo.setManufacturer(Build.MANUFACTURER);
        mBuildInfo.setModel(Build.MODEL);
        mBuildInfo.setProduct(Build.PRODUCT);
        mBuildInfo.setKernerVersion(System.getProperty("os.version"));
        mBuildInfo.setArch(System.getProperty("os.arch"));
        return mBuildInfo;
    }

    public TelephonyManagerInfo getTelephoneManagerInfo(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);

        String simState;
        switch (telephonyManager.getSimState()) {
            case TelephonyManager.SIM_STATE_UNKNOWN: {
                simState = "UNKNOWN";
                break;
            }
            case TelephonyManager.SIM_STATE_ABSENT: {
                simState = "ABSENT";
                break;
            }
            case TelephonyManager.SIM_STATE_PIN_REQUIRED: {
                simState = "PIN_REQUIRED";
                break;
            }
            case TelephonyManager.SIM_STATE_PUK_REQUIRED: {
                simState = "PUK_REQUIRED";
                break;
            }
            case TelephonyManager.SIM_STATE_READY: {
                simState = "STATE_READY";
                break;
            }
            case TelephonyManager.SIM_STATE_NOT_READY: {
                simState = "NOT_READY";
                break;
            }
            case TelephonyManager.SIM_STATE_PERM_DISABLED: {
                simState = "PERM_DISABLED";
                break;
            }
            case TelephonyManager.SIM_STATE_CARD_IO_ERROR: {
                simState = "CARD_IO_ERROR";
                break;
            }
            case TelephonyManager.SIM_STATE_CARD_RESTRICTED: {
                simState = "CARD_RESTRICTED";
                break;
            }
            default: {
                simState = "ERROR";
                break;
            }
        }

        mTelephonyManagerInfo = new TelephonyManagerInfo();
        mTelephonyManagerInfo.setCountryIso(telephonyManager.getSimCountryIso());
        if (Build.VERSION.SDK_INT > 19) {
            mTelephonyManagerInfo.setMmsUserAgent(telephonyManager.getMmsUserAgent());
        }
        mTelephonyManagerInfo.setSimOperatorName(telephonyManager.getSimOperatorName());
        mTelephonyManagerInfo.setNetworkOperator(telephonyManager.getNetworkOperator());
        mTelephonyManagerInfo.setSimState(simState);

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        if (is3g){
            mTelephonyManagerInfo.setTypeConnection("cellular");
        }
        boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();
        if (isWifi){
            mTelephonyManagerInfo.setTypeConnection("wifi");
        }

        return mTelephonyManagerInfo;
    }

    public InstallApps getInstallApps(Context context){
        List<String> installApps = new ArrayList<>();
        mInstallApps = new InstallApps();
        List<ApplicationInfo> applist = context.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        for (int i = 0; i < applist.size(); i++){
            String apk = applist.get(i).packageName;
            if (!(apk.startsWith("com.android") || apk.startsWith("com.huawei") || apk.startsWith("com.majeur") ||
                    apk.startsWith("com.sonymobile") || apk.startsWith("com.htc") || apk.startsWith("com.google"))) {
                installApps.add(apk);
            }
        }
        mInstallApps.setAppsList(installApps);
        return mInstallApps;
    }
}
