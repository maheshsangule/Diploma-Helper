package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codepath.asynchttpclient.RequestParams
import com.developermaheshsofttechltd.diplomahelper.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.asynchttpclient.AsyncHttpClient

class MobileNumberActivity : AppCompatActivity() {

    companion object {
        const val is_login2 = "is_login2"
    }

    private lateinit var b1: Button
    private lateinit var client: AsyncHttpClient
    private lateinit var dialog: Dialog
    private lateinit var et: EditText
    private val params = RequestParams()
    private lateinit var sheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_number)

        dialog = Dialog(this).apply {
            requestWindowFeature(1)
            setContentView(R.layout.progressbar)
            setCanceledOnTouchOutside(false)
        }

        sheetDialog = BottomSheetDialog(this, R.style.BottomSheetStyle)
        b1 = findViewById(R.id.activity_mobile_number_btn_getotp)
        et = findViewById(R.id.activity_mobile_number_et)

//        b1.setOnClickListener {
//            when {
//                et.text.toString().trim().isEmpty() -> {
//                    Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show()
//                }
//
//                et.text.toString().length != 10 -> {
//                    Toast.makeText(
//                        this,
//                        "Please Enter Valid And 10 digit Number",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                else -> {
//                    dialog.show()
//                    params.put("mobile_number", et.text.toString())
//                    client.post(Urls.urlMobile_Number, params, object : JsonHttpResponseHandler() {
//                        override fun onSuccess(
//                            statusCode: Int,
//                            headers: Array<Header>,
//                            response: JSONObject
//                        ) {
//                            super.onSuccess(statusCode, headers, response)
//                            try {
//                                val isSuccess = response.getString("success")
//                                when (isSuccess) {
//                                    AppEventsConstants.EVENT_PARAM_VALUE_YES -> {
//                                        dialog.show()
//                                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                            "+91${et.text.toString()}",
//                                            60,
//                                            TimeUnit.SECONDS,
//                                            this@MobileNumberActivity,
//                                            object :
//                                                PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                                override fun onVerificationCompleted(
//                                                    phoneAuthCredential: PhoneAuthCredential
//                                                ) {
//                                                    dialog.dismiss()
//                                                    Toast.makeText(
//                                                        this@MobileNumberActivity,
//                                                        "Verification Completed",
//                                                        Toast.LENGTH_SHORT
//                                                    ).show()
//                                                }
//
//                                                override fun onVerificationFailed(e: FirebaseException) {
//                                                    dialog.dismiss()
//                                                    Toast.makeText(
//                                                        this@MobileNumberActivity,
//                                                        e.message,
//                                                        Toast.LENGTH_SHORT
//                                                    ).show()
//                                                }
//
//                                                override fun onCodeSent(
//                                                    verificationId: String,
//                                                    forceResendingToken: PhoneAuthProvider.ForceResendingToken
//                                                ) {
//                                                    super.onCodeSent(
//                                                        verificationId,
//                                                        forceResendingToken
//                                                    )
//                                                    dialog.dismiss()
//                                                    val intent = Intent(
//                                                        this@MobileNumberActivity,
//                                                        OtpActivity::class.java
//                                                    ).apply {
//                                                        putExtra(
//                                                            "mobile_number",
//                                                            et.text.toString()
//                                                        )
//                                                        putExtra("verification_id", verificationId)
//                                                    }
//                                                    Toast.makeText(
//                                                        this@MobileNumberActivity,
//                                                        "OTP sent",
//                                                        Toast.LENGTH_SHORT
//                                                    ).show()
//                                                    startActivity(intent)
//                                                }
//                                            }
//                                        )
//                                    }
//
//                                    ExifInterface.GPS_MEASUREMENT_2D -> {
//                                        dialog.dismiss()
//                                        showDialog()
//                                    }
//
//                                    ExifInterface.GPS_MEASUREMENT_3D -> {
//                                        dialog.dismiss()
//                                        Toast.makeText(
//                                            this@MobileNumberActivity,
//                                            "Table is Empty",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//                                    }
//
//                                    else -> {
//                                        dialog.dismiss()
//                                        Toast.makeText(
//                                            this@MobileNumberActivity,
//                                            "Something Went Wrong!",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//                                    }
//                                }
//                            } catch (e: JSONException) {
//                                Toast.makeText(
//                                    this@MobileNumberActivity,
//                                    "Hi Error",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        }
//
//                        override fun onFailure(
//                            statusCode: Int,
//                            headers: Array<Header>,
//                            throwable: Throwable,
//                            errorResponse: JSONObject?
//                        ) {
//                            super.onFailure(statusCode, headers, throwable, errorResponse)
//                            Toast.makeText(
//                                this@MobileNumberActivity,
//                                "Server Error",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//
//                        override fun onFailure(
//                            statusCode: Int,
//                            headers: Headers?,
//                            response: String?,
//                            throwable: Throwable?
//                        ) {
//                            TODO("Not yet implemented")
//                        }
//
//                        override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
//                            TODO("Not yet implemented")
//                        }
//                    })
//                }
//            }
//        }
    }

    private fun showDialog() {
        val view1 = layoutInflater.inflate(
            R.layout.no_internet_2,
            findViewById<LinearLayout>(R.id.bottomsheet_dialog_no_internet)
        )
        val btnVpnRefresh = view1.findViewById<Button>(R.id.btn_no_internet2)
        view1.findViewById<TextView>(R.id.tv1).text = "Please SignUp Mobile Number Not"
        view1.findViewById<TextView>(R.id.tv2).text = "Found Create Account"
        sheetDialog.setContentView(view1)
        sheetDialog.setCancelable(false)
        sheetDialog.show()
        btnVpnRefresh.text = "OK"
        btnVpnRefresh.setOnClickListener {
            object : CountDownTimer(1000, 2000) {
                override fun onTick(l: Long) {
                    dialog.show()
                    sheetDialog.dismiss()
                }

                override fun onFinish() {
                    dialog.dismiss()
                    sheetDialog.dismiss()
                    startActivity(Intent(this@MobileNumberActivity, SignupActivity::class.java))
                }
            }.start()
        }
    }
}
