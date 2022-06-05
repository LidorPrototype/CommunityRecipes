package com.l_es.communityrecipes;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Environment;

import androidx.appcompat.app.AlertDialog;

import com.l_es.communityrecipes.Services.AlarmBroadcastReceiver;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.TreeMap;

import spencerstudios.com.bungeelib.Bungee;

/**
 * Created by Lidor on 04/08/2022.
 * Developer name: L-ES
 *  _        _   _____     ____    ______
 * | |      |_| |  __ \   / __ \  |  O   |
 * | |      | | | |  | | | |  | | |   ___/
 * | |____  | | | |__| | | |__| | | | \
 * |______| |_| |_____/   \____/  |_|__\
 *  ____         ____
 * |  __|       |  __|
 * |  __|   _   |__  |
 * |____|  |_|  |____|
 */
public class Utilities {

    public static final String LOG_FLAG                     =   "LOG_TESTING_HERE";
    public static final String NULL                         =   "NULL";
    protected static final String IMAGES_PATH               =   "Recipes/";
    protected static final int REQUEST_IMAGE_CAPTURE        =   1;
    private static final int errorInt                       =   -99999;
    // SharedPreferences Flags
    public static final String SP_CREATION_TAG              =   "SHARED_PREFERENCES";
    protected static final String SP_CATEGORIES             =   "CATEGORIES";
    public static final String SP_RECIPES_TYPE              =   "RECIPES_TYPE";
    public static final String SP_CATEGORY_TYPE             =   "CATEGORY_TYPE";
    public static final String SP_RECIPE_NAME               =   "RECIPE_NAME";
    protected static final String SP_RECIPE_IMAGE           =   "RECIPE_IMAGE";
    protected static final String SP_MUSIC_DEFAULT          =   "MUSIC_STATUS";
    public static final String SP_MUSIC_SONG                =   "RAW_MUSIC";
    public static final String SP_NOTIFICATIONS             =   "NOTIFICATIONS_STATUS";
    // Categories Flags
    public static final String CATEGORIES                   =   "Categories";
    public static final String FEATUREDS                    =   "Featureds";
    public static final String RECIPES                      =   "Recipes";
    public static final String CATEGORY_CUISINE             =   "Cuisine";
    public static final String CATEGORY_MEAL                =   "Meal";
    public static final String CATEGORY_OCCASION            =   "Occasion";
    // Attributes Flags
    protected static final String AVG_HOURS                 =   "avg_hours";
    protected static final String AVG_MINUTES               =   "avg_minutes";
    protected static final String IMAGE                     =   "image_link";
    protected static final String PREPARATION               =   "preparation";
    protected static final String INGREDIENTS               =   "ingredients";
    public static final String LIKES                        =   "likes";
    public static final String DISLIKES                     =   "dislikes";
    // Notifications Related
    public static String CHANNEL_ID                         =   "10001";
    public static String CHANNEL_NAME                       =   "recipe_notifications";
    public static String NOTIFICATION_TITLE                 =   "It's cooking time!";
    public static String NOTIFICATION_CONTENT_TEXT          =   "Try making an amazing Friday night dinner using one of our delicious recipes!";
    public static int NOTIFICATION_ID                       =   1;
    // Rate Recipe Related
    public static int LIKE_DISLIKE_INT                      =   -1;
    public static int DISLIKE_INT                           =   0;
    public static int LIKE_INT                              =   1;
    // Email Related
    public static final String EMAIL_TO                     =   "development.l.es@gmail.com\n";
    public static final String EMAIL_SUBJECT                = "Recipe Feature Request - <UserName>";
    public static final String EMAIL_MSG = "<UserName>, <UserRecipe>, Recipe Feature Request\n"
            + "Why do you think your recipe should be featured?\n"
            + "> Answer: \n"
            + "Featured recipe costs 5$ per month, is this acceptable to you?\n"
            + "> Answer: \n";
    // Animation Transition Related
    public static final int ANIMATION_SPLIT                 =   0;
    public static final int ANIMATION_SHRINK                =   1;
    public static final int ANIMATION_CARD                  =   2;
    public static final int ANIMATION_IN_AND_OUT            =   3;
    public static final int ANIMATION_SWIPE_LEFT            =   4;
    public static final int ANIMATION_SWIPE_RIGHT           =   5;
    public static final int ANIMATION_SLIDE_UP              =   6;
    public static final int ANIMATION_SLIDE_DOWN            =   7;
    public static final int ANIMATION_SLIDE_LEFT            =   8;
    public static final int ANIMATION_SLIDE_RIGHT           =   9;
    public static final int ANIMATION_ZOOM                  =   10;
    public static final int ANIMATION_FADE                  =   11;
    public static final int ANIMATION_SPIN                  =   12;
    public static final int ANIMATION_DIAGONAL              =   13;
    public static final int ANIMATION_WINDMILL              =   14;
    // Language Related
    public static final String LANGUAGE_ENGLISH             =   "English";
    public static final String LANGUAGE_HEBREW              =   "עברית";
    public static final String LANGUAGE_RUSSIAN             =   "Русский";
    public static final String CATEGORY_CUISINE_ENGLISH     =   "Cuisine";
    public static final String CATEGORY_MEAL_ENGLISH        =   "Meal";
    public static final String CATEGORY_OCCASION_ENGLISH    =   "Occasion";
    public static final String CATEGORY_CUISINE_HEBREW      =   "מטבח";
    public static final String CATEGORY_MEAL_HEBREW         =   "ארוחה";
    public static final String CATEGORY_OCCASION_HEBREW     =   "אירוע";
    public static final String CATEGORY_CUISINE_RUSSIAN     =   "Кухня";
    public static final String CATEGORY_MEAL_RUSSIAN        =   "Еда";
    public static final String CATEGORY_OCCASION_RUSSIAN    =   "Повод";
    public static final String UNSUPPORTED_LANGUAGE         =   "UnsupportedLanguage";

    public static Map<String, Integer> songs = new HashMap<String, Integer>() {{
        put("Ambient Piano Amp Strings", R.raw.ambient_piano_amp_strings);
        put("Both Of Us", R.raw.both_of_us);
        put("Chill Abstract Intention", R.raw.chill_abstract_intention);
        put("Just Relax", R.raw.just_relax);
        put("Morning Garden Acoustic Chill", R.raw.morning_garden_acoustic_chill);
        put("Order", R.raw.order);
        put("Penguin Music Modern Chill-out", R.raw.penguinmusic_modern_chillout);
        put("Sedative", R.raw.sedative);
        put("Spirit Blossom", R.raw.spirit_blossom);
        put("The Cradle Of Your Soul", R.raw.the_cradle_of_your_soul);
    }};

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static boolean isMyServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void startAlarmBroadcastReceiver(Context context, int day, int hh, int mm, int ss) {
        Intent _intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                _intent,
                PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, hh);
        calendar.set(Calendar.MINUTE, mm);
        calendar.set(Calendar.SECOND, ss);
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public static void stopAlarmBroadcastReceiver(Context context){
        Intent _intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                _intent,
                PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    public static String getInfoAboutDevice(Activity a) {
        StringBuilder s = new StringBuilder();
        try {
            PackageInfo pInfo = a.getPackageManager().getPackageInfo(
                    a.getPackageName(), PackageManager.GET_META_DATA);
            s.append("\n APP Package Name: ").append(a.getPackageName());
            s.append("\n APP Version Name: ").append(pInfo.versionName);
            s.append("\n APP Version Code: ").append(pInfo.versionCode);
            s.append("\n");
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        s.append("\n OS Version: ").append(System.getProperty("os.version")).append(" (").append(android.os.Build.VERSION.INCREMENTAL).append(")");
        s.append("\n OS API Level: ").append(android.os.Build.VERSION.SDK);
        s.append("\n Device: ").append(android.os.Build.DEVICE);
        s.append("\n Model (and Product): ").append(android.os.Build.MODEL).append(" (").append(android.os.Build.PRODUCT).append(")");
        // TODO add application version!

        // more from
        // http://developer.android.com/reference/android/os/Build.html :
        s.append("\n Manufacturer: ").append(android.os.Build.MANUFACTURER);
        s.append("\n Other TAGS: ").append(android.os.Build.TAGS);

        s.append("\n screenWidth: ").append(a.getWindow().getWindowManager().getDefaultDisplay()
                .getWidth());
        s.append("\n screenHeigth: ").append(a.getWindow().getWindowManager().getDefaultDisplay()
                .getHeight());
        s.append("\n Keyboard available: ").append(a.getResources().getConfiguration().keyboard != Configuration.KEYBOARD_NOKEYS);

        s.append("\n Trackball available: ").append(a.getResources().getConfiguration().navigation == Configuration.NAVIGATION_TRACKBALL);
        s.append("\n SD Card state: ").append(Environment.getExternalStorageState());
        Properties p = System.getProperties();
        Enumeration<Object> keys = p.keys();
        String key;
        while (keys.hasMoreElements()) {
            key = (String) keys.nextElement();
            s.append("\n > ").append(key).append(" = ").append(p.get(key));
        }
        return s.toString();
    }

    public static void createAboutDialog(Activity activity) {
        String title = activity.getResources().getString(R.string.about);
        String msg = activity.getResources().getString(R.string.my_name_and_id);
        msg += getInfoAboutDevice(activity);
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(activity.getResources().getString(R.string.got_it), (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }

    public static <T, E> Map<T, E> sortMapByKeyLength(Map<T, E> map, int goodValue, int badValue){
        TreeMap<T, E> treeMap = new TreeMap<T, E>(
                (t1, t2) -> {
                    if (t1 instanceof String && t2 instanceof String){
                        String s1 = t1.toString();
                        String s2 = t2.toString();
                        if (s1.length() > s2.length()) {
                            return badValue;
                        } else if (s1.length() < s2.length()) {
                            return goodValue;
                        } else {
                            return s1.compareTo(s2);
                        }
                    }else{
                        return errorInt; // Not implemented yet!
                    }
                });
        treeMap.putAll(map);
        return treeMap;
    }

    /**
     * Using the Bungee library to move between activities
     * If the variable startActivityFlag is set to 'true' the variable classToStart will be ignored
     */
    public static void useBungee(Context context, Class<?> classToStart, int value, boolean startActivityFlag){
        if (startActivityFlag){
            context.startActivity(new Intent(context, classToStart));
        }
        switch(value){
            case ANIMATION_SPLIT:
                Bungee.split(context);
                break;
            case ANIMATION_SHRINK:
                Bungee.shrink(context);
                break;
            case ANIMATION_CARD:
                Bungee.card(context);
                break;
            case ANIMATION_IN_AND_OUT:
                Bungee.inAndOut(context);
                break;
            case ANIMATION_SWIPE_LEFT:
                Bungee.swipeLeft(context);
                break;
            case ANIMATION_SWIPE_RIGHT:
                Bungee.swipeRight(context);
                break;
            case ANIMATION_SLIDE_UP:
                Bungee.slideUp(context);
                break;
            case ANIMATION_SLIDE_DOWN:
                Bungee.slideDown(context);
                break;
            case ANIMATION_SLIDE_LEFT:
                Bungee.slideLeft(context);
                break;
            case ANIMATION_SLIDE_RIGHT:
                Bungee.slideRight(context);
                break;
            case ANIMATION_ZOOM:
                Bungee.zoom(context);
                break;
            case ANIMATION_FADE:
                Bungee.fade(context);
                break;
            case ANIMATION_SPIN:
                Bungee.spin(context);
                break;
            case ANIMATION_DIAGONAL:
                Bungee.diagonal(context);
                break;
            case ANIMATION_WINDMILL:
                Bungee.windmill(context);
                break;
        }
    }

    public static String getDeviceLanguage(){
//        1. Locale.getDefault().getLanguage();          // ---> en
//        2. Locale.getDefault().getISO3Language();      // ---> eng
//        3. Locale.getDefault().getCountry();           // ---> US
//        4. Locale.getDefault().getISO3Country();       // ---> USA
//        5. Locale.getDefault().getDisplayCountry();    // ---> United States
//        6. Locale.getDefault().getDisplayName();       // ---> English (United States)
//        7. Locale.getDefault().toString();             // ---> en_US
//        8. Locale.getDefault().getDisplayLanguage();   // ---> English
//        9. Locale.getDefault().toLanguageTag();        // ---> en-US
        return Locale.getDefault().getDisplayLanguage();
    }

    public static String getLanguageBasedCategory(String _key_){
        String deviceLanguage = getDeviceLanguage();
        switch (_key_){
            case CATEGORY_CUISINE:
                switch (deviceLanguage) {
                    case LANGUAGE_ENGLISH:
                        return CATEGORY_CUISINE_ENGLISH;
                    case LANGUAGE_HEBREW:
                        return CATEGORY_CUISINE_HEBREW;
                    case LANGUAGE_RUSSIAN:
                        return CATEGORY_CUISINE_RUSSIAN;
                }
                break;
            case CATEGORY_MEAL:
                switch (deviceLanguage) {
                    case LANGUAGE_ENGLISH:
                        return CATEGORY_MEAL_ENGLISH;
                    case LANGUAGE_HEBREW:
                        return CATEGORY_MEAL_HEBREW;
                    case LANGUAGE_RUSSIAN:
                        return CATEGORY_MEAL_RUSSIAN;
                }
                break;
            case CATEGORY_OCCASION:
                switch (deviceLanguage) {
                    case LANGUAGE_ENGLISH:
                        return CATEGORY_OCCASION_ENGLISH;
                    case LANGUAGE_HEBREW:
                        return CATEGORY_OCCASION_HEBREW;
                    case LANGUAGE_RUSSIAN:
                        return CATEGORY_OCCASION_RUSSIAN;
                }
                break;
        }
        return "Language Not Supported!";
    }

}
