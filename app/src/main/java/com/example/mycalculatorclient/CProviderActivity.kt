package com.example.mycalculatorclient

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class CProviderActivity : AppCompatActivity() {
    lateinit var cpListview: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cprovider)
        cpListview = findViewById(R.id.cpListView)
//        val uriSms: Uri = Uri.parse("content://sms/inbox")
        var fromColumn = arrayOf("normalized_number","DURATION","DATE")
        var toTextView = intArrayOf(android.R.id.text1,android.R.id.text2,android.R.id.text2)
        var rowLayout = android.R.layout.simple_list_item_2
        if(!checkReadSMSPermission()){
            //  onPermissionCallBack = this
            getReadSMSPermission();
        }
        var dataCursor =
            contentResolver.query(CallLog.Calls.CONTENT_URI,null,null,null,null,null)

        var adapter:SimpleCursorAdapter =
            SimpleCursorAdapter(this,rowLayout,dataCursor,fromColumn,toTextView,)
        cpListview.adapter =adapter
    }
    private fun checkReadSMSPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                false
            }
        } else {
            true
        }
    }
    var REQUEST_READ_CALL_LOG_PERMISSION = 123
    var onPermissionCallBack: RequestPermissionAction? = null

    fun getReadSMSPermission() {
        //onPermissionCallBack = onPermissionCallBack
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkReadSMSPermission()) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_CALL_LOG),
                    REQUEST_READ_CALL_LOG_PERMISSION
                )
                return
            }
        }
        //if (onPermissionCallBack != null) onPermissionCallBack.permissionGranted()
    }


    //fun getReadSmsPermission(){}
    open interface RequestPermissionAction {
        fun permissionDenied()
        fun permissionGranted()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}