package ua.edu.universityprograms.app.Utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

import ua.edu.universityprograms.app.R;
import ua.edu.universityprograms.app.activities.Event;

/**
 * Created by vcaciuc on 6/18/2014.
 */
public class IntentUtils {

    public static void shareChooser(Context v){
        String message = "Text I want to share.";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        v.startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));
    }

    public static void goTo(Context v, String address){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + address));
        v.startActivity(intent);
    }

    public static void shareAppLinkViaFacebook(Context v) {
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "up");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "text");

        PackageManager pm = v.getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        for (final ResolveInfo app : activityList)
        {
            if ((app.activityInfo.name).contains("facebook"))
            {
                final ActivityInfo activity = app.activityInfo;
                final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);
                v.startActivity(shareIntent);
                break;
            }
        }
    }

    public static void postToTwitter(Context v){
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "String");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Sting1");

        PackageManager pm = v.getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        for (final ResolveInfo app : activityList)
        {
            if ("com.twitter.android.PostActivity".equals(app.activityInfo.name))
            {
                final ActivityInfo activity = app.activityInfo;
                final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);
                v.startActivity(shareIntent);
                break;
            }
        }
    }
}
