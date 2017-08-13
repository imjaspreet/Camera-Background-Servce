package example.chatea.servicecamera.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This class is used to receive alarm when alarm manager trigger
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent eventService = new Intent(context, AlarmBuilderService.class);
        context.startService(eventService);
    }
}
