package com.android.example.enoughwater

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */

class WaterAmount : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    // COMPANION OBJECTS:
    // If you need a function or a property to be tied to a class rather than to instances of it
    // you can declare it inside a companion object. You can call method with className.method
    // - here not though?

    companion object {
        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val widgetText = context.getString(R.string.amount)

            val views = RemoteViews(context.packageName, R.layout.water_amount)
            views.setOnClickPendingIntent(R.id.widget_button, getPendingIntent(
                context, 1)
            )

            views.setTextViewText(R.id.appwidget_text, widgetText)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        private fun getPendingIntent(context: Context, value: Int):
                PendingIntent {
            val intent = Intent(context, MainActivity::class.java)
            intent.action = ADD_CUP
            intent.putExtra(ONE_CUP, value)
            return PendingIntent.getActivity(context, value, intent, 0)
        }

    }
}

