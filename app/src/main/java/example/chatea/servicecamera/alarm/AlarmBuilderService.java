package example.chatea.servicecamera.alarm;

import android.app.IntentService;
import android.content.Intent;
import android.hardware.Camera;
import android.util.Log;
import example.chatea.servicecamera.CameraService;
import example.chatea.servicecamera.SharedPreferenceHelper;

/**
 *  Intent service to handle alarm and generate local notification
 */

public class AlarmBuilderService extends IntentService {

    public AlarmBuilderService() {
        super(AlarmBuilderService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if(SharedPreferenceHelper.getInstance().getIsRecording()){
            stopRecording();
        } else {
            startRecording();
        }
        // check if Update in Alarm
        AlarmHelper helper = new AlarmHelper(this);
        helper.findNextAlarm();
    }

    private void startRecording() {
        Log.e("AlarmBuilderService", "start");
        CameraService.startToStartRecording(this, Camera.CameraInfo.CAMERA_FACING_FRONT, null);
    }

    private void stopRecording(){
        Log.e("AlarmBuilderService", "stop");
        CameraService.startToStopRecording(this, null);
    }

}
