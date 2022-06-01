package com.l_es.communityrecipes;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Environment;

import androidx.appcompat.app.AlertDialog;

import com.l_es.communityrecipes.Services.AlarmBroadcastReceiver;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
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

    public static final String LOG_FLAG                 =   "LOG_TESTING_HERE";
    public static final String NULL                     =   "NULL";
    protected static final String IMAGES_PATH           =   "Recipes/";
    protected static final int REQUEST_IMAGE_CAPTURE    =   1;
    private static final int errorInt                   =   -99999;
    // SharedPreferences Flags
    public static final String SP_CREATION_TAG          =   "SHARED_PREFERENCES";
    protected static final String SP_CATEGORIES         =   "CATEGORIES";
    public static final String SP_RECIPES_TYPE          =   "RECIPES_TYPE";
    public static final String SP_CATEGORY_TYPE         =   "CATEGORY_TYPE";
    public static final String SP_RECIPE_NAME           =   "RECIPE_NAME";
    protected static final String SP_RECIPE_IMAGE       =   "RECIPE_IMAGE";
    protected static final String SP_MUSIC_DEFAULT      =   "MUSIC_STATUS";
    public static final String SP_MUSIC_SONG            =   "RAW_MUSIC";
    public static final String SP_NOTIFICATIONS         =   "NOTIFICATIONS_STATUS";
    // Categories Flags
    public static final String CATEGORIES               =   "Categories";
    public static final String FEATUREDS                =   "Featureds";
    public static final String RECIPES                  =   "Recipes";
    public static final String CATEGORY_CUISINE         =   "Cuisine";
    public static final String CATEGORY_MEAL            =   "Meal";
    public static final String CATEGORY_OCCASION        =   "Occasion";
    // Attributes Flags
    protected static final String AVG_HOURS             =   "avg_hours";
    protected static final String AVG_MINUTES           =   "avg_minutes";
    protected static final String IMAGE                 =   "image_link";
    protected static final String PREPARATION           =   "preparation";
    protected static final String INGREDIENTS           =   "ingredients";
    public static final String LIKES                    =   "likes";
    public static final String DISLIKES                 =   "dislikes";
    // Notifications Related
    public static String CHANNEL_ID                     =   "10001";
    public static String CHANNEL_NAME                   =   "recipe_notifications";
    public static String NOTIFICATION_TITLE             =   "It's cooking time!";
    public static String NOTIFICATION_CONTENT_TEXT      =   "Try making an amazing Friday night dinner using one of our delicious recipes!";
    public static int NOTIFICATION_ID                   =   1;
    // Rate Recipe Related
    public static int LIKE_DISLIKE_INT                  =   -1;
    public static int DISLIKE_INT                       =   0;
    public static int LIKE_INT                          =   1;
    // Email Related
    public static final String EMAIL_TO                 =   "development.l.es@gmail.com\n";
    public static final String EMAIL_SUBJECT            = "Recipe Feature Request - <UserName>";
    public static final String EMAIL_MSG = "<UserName>, <UserRecipe>, Recipe Feature Request\n"
            + "Why do you think your recipe should be featured?\n"
            + "> Answer: \n"
            + "Featured recipe costs 5$ per month, is this acceptable to you?\n"
            + "> Answer: \n";
    // Animation Transition Related
    public static final int ANIMATION_SPLIT             =   0;
    public static final int ANIMATION_SHRINK            =   1;
    public static final int ANIMATION_CARD              =   2;
    public static final int ANIMATION_IN_AND_OUT        =   3;
    public static final int ANIMATION_SWIPE_LEFT        =   4;
    public static final int ANIMATION_SWIPE_RIGHT       =   5;
    public static final int ANIMATION_SLIDE_UP          =   6;
    public static final int ANIMATION_SLIDE_DOWN        =   7;
    public static final int ANIMATION_SLIDE_LEFT        =   8;
    public static final int ANIMATION_SLIDE_RIGHT       =   9;
    public static final int ANIMATION_ZOOM              =   10;
    public static final int ANIMATION_FADE              =   11;
    public static final int ANIMATION_SPIN              =   12;
    public static final int ANIMATION_DIAGONAL          =   13;
    public static final int ANIMATION_WINDMILL          =   14;

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
        String s = "";
        try {
            PackageInfo pInfo = a.getPackageManager().getPackageInfo(
                    a.getPackageName(), PackageManager.GET_META_DATA);
            s += "\n APP Package Name: " + a.getPackageName();
            s += "\n APP Version Name: " + pInfo.versionName;
            s += "\n APP Version Code: " + pInfo.versionCode;
            s += "\n";
        } catch (PackageManager.NameNotFoundException e) {
        }
        s += "\n OS Version: " + System.getProperty("os.version") + " ("
                + android.os.Build.VERSION.INCREMENTAL + ")";
        s += "\n OS API Level: " + android.os.Build.VERSION.SDK;
        s += "\n Device: " + android.os.Build.DEVICE;
        s += "\n Model (and Product): " + android.os.Build.MODEL + " ("
                + android.os.Build.PRODUCT + ")";
        // TODO add application version!

        // more from
        // http://developer.android.com/reference/android/os/Build.html :
        s += "\n Manufacturer: " + android.os.Build.MANUFACTURER;
        s += "\n Other TAGS: " + android.os.Build.TAGS;

        s += "\n screenWidth: "
                + a.getWindow().getWindowManager().getDefaultDisplay()
                .getWidth();
        s += "\n screenHeigth: "
                + a.getWindow().getWindowManager().getDefaultDisplay()
                .getHeight();
        s += "\n Keyboard available: "
                + (a.getResources().getConfiguration().keyboard != Configuration.KEYBOARD_NOKEYS);

        s += "\n Trackball available: "
                + (a.getResources().getConfiguration().navigation == Configuration.NAVIGATION_TRACKBALL);
        s += "\n SD Card state: " + Environment.getExternalStorageState();
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        String key = "";
        while (keys.hasMoreElements()) {
            key = (String) keys.nextElement();
            s += "\n > " + key + " = " + (String) p.get(key);
        }
        return s;
    }

    public static void createAboutDialog(Activity activity) {
        String title = "About";
        String msg = activity.getResources().getString(R.string.my_name_and_id);;
        msg += getInfoAboutDevice(activity);
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Got It", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    public static <T, E> Map<T, E> sortMapByKeyLength(Map<T, E> map, int goodValue, int badValue){
        TreeMap<T, E> treeMap = new TreeMap<T, E>( // TODO got a problem with it
                new Comparator<T>() {
                    @Override
                    public int compare(T t1, T t2) {
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

}
