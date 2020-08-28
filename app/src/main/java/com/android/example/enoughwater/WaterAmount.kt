package com.android.example.enoughwater

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */

class WaterAmount : AppWidgetProvider() {
    // if there is user interaction, register eventHandlers in this callback.
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // IDs: There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    // COMPANION OBJECTS:
    // we want some implementation to be a class but still want to expose some behavior as static.

    // “companion object” is an extension of the concept of “object”: an object that is a companion
    // to a particular class, and thus has !! access to it’s private level methods and properties. !!

    companion object {
        // deleted "internal" before fun - still works
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val widgetText = context.getString(R.string.amount)

            // remoteViews = responsible for displaying layout in another process --> this widget
            val views = RemoteViews(context.packageName, R.layout.water_amount)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            views.setOnClickPendingIntent(R.id.widget_button, getPendingIntent(
                context))

            appWidgetManager.updateAppWidget(appWidgetId, views) // Instruct the widget manager to update the widget
        }

        // getActivity versus getBroadcast
        private fun getPendingIntent(context: Context):
                PendingIntent {
            val intent = Intent(context, MainActivity::class.java)
            intent.action = ADD_CUP
            //intent.putExtra(ONE_CUP, 1) // always increased by one
            return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
}


