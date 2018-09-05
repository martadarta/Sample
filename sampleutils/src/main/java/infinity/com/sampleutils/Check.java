package infinity.com.sampleutils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.EventListener;
import java.util.List;
import java.util.Locale;

import infinity.com.sampleutils.emulatorDetector.EmulatorDetector;
import infinity.com.sampleutils.model.Params;
import infinity.com.sampleutils.rest.ApiInterface;
import infinity.com.sampleutils.rest.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Check {

    private final String TAG = "Check";
    private Context context;
    private Params params;
    private Country countryCode;

    private CallbackResponse callbackResponse;

    public interface CallbackResponse{
        void callback(boolean isCloak);
    }

    public Check(Context context){
        this.context = context;
    }

    public void setCallbackResponse(CallbackResponse callbackResponse) {
        this.callbackResponse = callbackResponse;
    }

    public void check(){
        checkSim();
    }

    private void checkSim() {
        checkIp();
    }

    private void checkIp(){
        countryCode = new Country();

        ApiInterface apiInterface  = WeatherApi.getClient().create(ApiInterface.class);
        Call<Params> callData = apiInterface.getLocation();
        callData.enqueue(new Callback<Params>() {
            @Override
            public void onResponse(final Call<Params> call, Response<Params> response) {
                params = response.body();

                TelephonyManager telman = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
                Locale locale;
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    locale = context.getResources().getConfiguration().getLocales().get(0);
                } else {
                    locale = context.getResources().getConfiguration().locale;
                }

                Log.d(TAG, "countryCode " + params.getCountryCode());
                Log.d(TAG, "localeLang " + locale.getLanguage());
                Log.d(TAG, "tel iso " + telman.getNetworkCountryIso());
                if(countryCode.getCodes().contains(params.getCountryCode()) &&
                        countryCode.getCodes().contains(locale.getLanguage()) &&
                        countryCode.getCodes().contains(telman.getNetworkCountryIso())) {
                    checkEmul();

                    callbackResponse.callback(false);
                } else {
                    callbackResponse.callback(true);
                }
            }

            @Override
            public void onFailure(Call<Params> call, Throwable t) {
                Log.d("tag", t.toString());
            }
        });

    }

    private void checkEmul(){
        EmulatorDetector.with(context)
                .setCheckTelephony(true)
                .setDebug(true)
                .detect(new EmulatorDetector.OnEmulatorDetectorListener() {
                    @Override
                    public void onResult(boolean isEmulator) {
                        if (isEmulator){
                            callbackResponse.callback(true);
                        }
                    }
                });
    }

    private boolean checkPermission(String permissions){

        int perm = context.checkCallingOrSelfPermission(permissions);
        if (perm != PackageManager.PERMISSION_GRANTED){
            requestPermission(permissions, 1);
            if (context.checkCallingOrSelfPermission(permissions) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions((Activity) context,
                new String[]{permissionName}, permissionRequestCode);
    }

}