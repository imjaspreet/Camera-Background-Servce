package example.chatea.servicecamera.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

/**
 * Alarm Helper class is used to find new alarm from local database and set alarm
 */

public class AlarmHelper {

    private final static String ONE_TIME = "onetime";

    private Context context;

    public AlarmHelper(Context context) {

        this.context = context;
    }

    public void findNextAlarm() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 15);
        setAlarm(calendar.getTimeInMillis());
    }

    private void setAlarm(long milliseconds) {

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP, milliseconds, pi);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, milliseconds, pi);
        }
    }
}
