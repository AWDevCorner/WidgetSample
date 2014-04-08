package it.androidworld.devcorner.widgetsample.widget;

import it.androidworld.devcorner.widgetsample.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;



public class MyWidgetProvider extends AppWidgetProvider {

    private static final String SYNC_CLICKED = "AwDevCorner";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews;
        ComponentName watchWidget;

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
        watchWidget = new ComponentName(context, MyWidgetProvider.class);

        remoteViews.setOnClickPendingIntent(R.id.buttonPlus, getPendingSelfIntent(context, SYNC_CLICKED));
        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);

        if (SYNC_CLICKED.equals(intent.getAction())) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            RemoteViews remoteViews;
            ComponentName watchWidget;

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);
            watchWidget = new ComponentName(context, MyWidgetProvider.class);
            Log.e("ProvaClick","Received");
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.androidworld.it/tag/devcorner"));
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(browserIntent);
            Toast.makeText(context.getApplicationContext(), "DevCorner: Nelle puntate precedenti...", Toast.LENGTH_LONG).show();;
            appWidgetManager.updateAppWidget(watchWidget, remoteViews);

        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
