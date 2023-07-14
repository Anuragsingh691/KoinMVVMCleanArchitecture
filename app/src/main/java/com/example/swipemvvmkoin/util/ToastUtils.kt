@file:Suppress("UNNECESSARY_SAFE_CALL")

package com.example.swipemvvmkoin.util

import android.app.Activity
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.swipemvvmkoin.R


object ToastUtils {

    @JvmStatic
    @JvmOverloads
    fun showToastError(activity: Activity?, msg: String?) {
        showToast(activity, R.layout.comp_toast_error, msg)
    }

    @JvmStatic
    @JvmOverloads
    fun showToastInfo(activity: Activity?, msg: String?, duration: Int = Toast.LENGTH_LONG) {
        showToast(activity, R.layout.comp_toast_info, msg, duration)
    }

    private fun showToast(activity: Activity?, layoutId: Int, msg: String?, duration: Int = Toast.LENGTH_LONG) {
        if (activity == null || activity?.isFinishing == true || msg.isNullOrEmpty()) return
        val li = LayoutInflater.from(activity)
        val toastView = li.inflate(layoutId, null)
        val toastMsg: TextView = toastView?.findViewById(R.id.msg)!!
        val toastObj = Toast(activity)
        toastObj.duration = duration
        toastObj.setGravity(Gravity.TOP, 0, 125)
        toastObj.view = toastView
        toastMsg?.text = msg
        if (activity != null && !activity.isFinishing) {
            toastObj.show()
        }
    }

    @JvmStatic
    @JvmOverloads
    fun showToastSuccess(activity: Activity?, msg: String?, duration: Int = Toast.LENGTH_SHORT) {
        showToast(activity, R.layout.comp_toast_success, msg, duration)
    }

}