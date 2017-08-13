package example.chatea.servicecamera;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by jaspreet on 02-08-2017.
 */

public class SharedPreferenceHelper {

    public static final String TAG = SharedPreferenceHelper.class.getSimpleName();
    public static final String NAME = "CameraService";

    public static final String IS_RECORDING = "isRecording";

    private static SharedPreferenceHelper instance;
    protected final SharedPreferences sharedPreferences;
    protected final SharedPreferences.Editor sharedPreferencesEditor;

    public static void init(Context context) {
        if(instance == null){
            instance = new SharedPreferenceHelper(context);
        }
    }

    private SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
    }

    public static SharedPreferenceHelper getInstance() {
        if (instance == null) {
            Log.e(TAG, "SharedHelper was not initialized!");
        }
        return instance;
    }

    protected void delete(String key) {
        if (sharedPreferences.contains(key)) {
            sharedPreferencesEditor.remove(key).commit();
        }
    }

    protected void savePref(String key, Object value) {
        delete(key);

        if (value instanceof Boolean) {
            sharedPreferencesEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            sharedPreferencesEditor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            sharedPreferencesEditor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            sharedPreferencesEditor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            sharedPreferencesEditor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            sharedPreferencesEditor.putString(key, value.toString());
        } else if (value != null) {
            Log.e(TAG, "Attempting to save non-primitive preference");
        }

        sharedPreferencesEditor.commit();
    }

    protected <T> T getPref(String key) {
        return (T) sharedPreferences.getAll().get(key);
    }

    protected <T> T getPref(String key, T defValue) {
        T returnValue = (T) sharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    public void clearAll() {
        sharedPreferencesEditor.clear();
        sharedPreferencesEditor.commit();
    }

    public boolean getIsRecording() {
        return getPref(IS_RECORDING, false);
    }

    public void saveIsRecording(boolean userId) {
        savePref(IS_RECORDING, userId);
    }

}

