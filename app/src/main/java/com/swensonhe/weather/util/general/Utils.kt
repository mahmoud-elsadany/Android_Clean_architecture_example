package com.swensonhe.weather.util.general

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.swensonhe.weather.R
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun showLoginErrorToast(context: Context, error: String?) {
        var msg = error
        if (msg.isNullOrEmpty())
            msg = context.getString(R.string.error_mssg)

        Toast.makeText(
            context,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun getAppVersion(context: Context): String {
        val packageInfo: PackageInfo
        try {
            packageInfo =
                context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            return "V "
        }
        return packageInfo.versionName.toString()
    }

    fun showSnackBar(activity: Activity, view: View, message: String, action: String? = null,
                     actionListener: View.OnClickListener? = null, duration: Int = Snackbar.LENGTH_SHORT) {
        val snackBar = Snackbar.make(activity,view, message, duration)
        val snackBarView: View = snackBar.view

        snackBarView.setBackgroundColor(activity.getColor(R.color.colorPrimary))
        val textView: TextView = snackBarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(activity.getColor(R.color.white))

        if (action != null && actionListener!=null) {
            snackBar.setAction(action, actionListener)
        }
        snackBar.show()
    }


    fun getCurrentYear():String{
        val now = Calendar.getInstance()
        val year = now[Calendar.YEAR]
        return year.toString()
    }

    fun getCurrentTime():String{
        return SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date())
    }

    fun getCurrentDate():String{
        return SimpleDateFormat("EEEE, d MMM yyyy", Locale.getDefault()).format(Date())
    }

    fun showHideView(view:View){
        if (view.isVisible)
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE

    }
}