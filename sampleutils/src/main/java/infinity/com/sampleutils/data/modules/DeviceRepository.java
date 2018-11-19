package infinity.com.sampleutils.data.modules;

import android.content.Context;
import android.util.Base64;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import infinity.com.sampleutils.data.DeviceInfo;
import infinity.com.sampleutils.data.modules.local.GetLocalInfo;
import infinity.com.sampleutils.data.modules.local.emulator.EmulatorCheck;
import infinity.com.sampleutils.data.modules.remote.DeviceInfoApi;
import infinity.com.sampleutils.data.modules.remote.IpInfo;

public class DeviceRepository implements DeviceDataSource {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static DeviceInfo mDeviceInfo;
    private static GetLocalInfo mGetLocalInfo;
    private static DeviceRepository mDeviceRepository;
    private String domain;

    public static DeviceRepository getInstance(){
        if (mDeviceRepository== null){
            mDeviceInfo = new DeviceInfo();
            mDeviceRepository = new DeviceRepository();
            return mDeviceRepository;
        }
        return mDeviceRepository;
    }

    @Override
    public DeviceInfo nameApp(Context context) {
        mDeviceInfo.setAppName(context.getPackageName());
        return mDeviceInfo;
    }

    @Override
    public DeviceInfo getBuildInfo() {
        mGetLocalInfo = new GetLocalInfo();
        mDeviceInfo.setBuildInfo(mGetLocalInfo.getBuildInfo());
        return mDeviceInfo;
    }

    @Override
    public DeviceInfo getInstallApps(Context context) {
        mGetLocalInfo = new GetLocalInfo();
        mDeviceInfo.setInstallApps(mGetLocalInfo.getInstallApps(context));
        return mDeviceInfo;
    }

    @Override
    public DeviceInfo getNetworkInfo(Context context) {
        mGetLocalInfo = new GetLocalInfo();
        mDeviceInfo.setTelephonyManagerInfo(mGetLocalInfo.getTelephoneManagerInfo(context));
        return mDeviceInfo;
    }

    @Override
    public DeviceInfo getIpInfo() {
        IpInfo ipInfo = new IpInfo();
        mDeviceInfo.setNetworkInfo(ipInfo.loadInfo());
        return mDeviceInfo;
    }

    @Override
    public DeviceInfo getEmulatorCheck(Context context) {
        EmulatorCheck emulatorCheck = new EmulatorCheck();
        mDeviceInfo.setEmulator(emulatorCheck.checkEmul(context));
//        mDeviceInfo.setEmulator(false);
        return mDeviceInfo;
    }

    @Override
    public DeviceInfo getDeviceInfo() {
        return mDeviceInfo;
    }

    @Override
    public String setDomain(String domain) {
        return this.domain = domain;
    }

    @Override
    public void sendStatistic(final LoadStatisticResponce loadStatisticResponce) {
        DeviceInfoApi deviceInfoApi = new DeviceInfoApi();
        Gson gson = new Gson();
        String gsonString = gson.toJson(mDeviceInfo);
        String password = "password";
        String encyptedMes = encrypt(gsonString);

        deviceInfoApi.sendInfo(encyptedMes, domain, new LoadStatisticResponce() {
            @Override
            public String loadStatistic(String loadInfo) {
                return loadStatisticResponce.loadStatistic(loadInfo);
            }
        });
    }

    public static String encrypt(String str) {
        try {
            return new String(Base64.encodeToString(encrypt(str.getBytes("UTF-8")), Base64.DEFAULT));
        } catch (Exception e) {
            return str;
        }
    }

    public static byte[] encrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getEncryptionKey("password"));
            return cipher.doFinal(data);
        } catch (Exception e) {
            return data;
        }
    }

    private static Key getEncryptionKey(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytes = password.getBytes("UTF-8");
        return new SecretKeySpec(MessageDigest.getInstance("MD5").digest(bytes), "AES");
    }
}
