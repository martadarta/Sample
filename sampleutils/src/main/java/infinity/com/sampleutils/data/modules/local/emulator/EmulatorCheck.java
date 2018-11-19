package infinity.com.sampleutils.data.modules.local.emulator;

import android.content.Context;

import infinity.com.sampleutils.data.modules.local.emulator.emulatorDetector.EmulatorDetector;

public class EmulatorCheck {

    public boolean checkEmul(Context context){
        return EmulatorDetector.with(context)
                .setCheckTelephony(true)
                .setDebug(true)
                .detect();
    }


}