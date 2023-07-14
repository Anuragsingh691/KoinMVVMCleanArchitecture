package com.example.swipemvvmkoin.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swipemvvmkoin.R

class PermissionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)
    }

    override fun onResume() {
        super.onResume()
        getPermissions()
    }

    private fun getPermissions() {
        val permissionList = mutableListOf<String>()
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) permissionList.add(
            android.Manifest.permission.CAMERA
        )
        else if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) permissionList.add(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        else if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) permissionList.add(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        else{
            startActivity(Intent(this,MainActivity::class.java))
        }
        if (permissionList.size > 0) {
            requestPermissions(permissionList.toTypedArray(), 101)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach {
            if (it != PackageManager.PERMISSION_GRANTED) {
                getPermissions()
            } else {
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }
}