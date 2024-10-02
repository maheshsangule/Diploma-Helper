@file:Suppress("DEPRECATION")

package com.developermaheshsofttechltd.diplomahelper.views.activities

//import com.loopj.android.http.AsyncHttpClient
//import com.loopj.android.http.RequestParams
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codepath.asynchttpclient.RequestParams
import com.developermaheshsofttechltd.diplomahelper.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import org.asynchttpclient.AsyncHttpClient
import java.util.concurrent.TimeUnit

class OtpActivity : AppCompatActivity() {

    private lateinit var inputCode1: EditText
    private lateinit var inputCode2: EditText
    private lateinit var inputCode3: EditText
    private lateinit var inputCode4: EditText
    private lateinit var inputCode5: EditText
    private lateinit var inputCode6: EditText
    private lateinit var verificationId: String
    private lateinit var dialog: Dialog
    private lateinit var sheetDialog: BottomSheetDialog
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var tvMobileNumber: TextView
    private lateinit var tvResendOtp: TextView
    private lateinit var tvTimer: TextView

    private lateinit var client: AsyncHttpClient
    private lateinit var params: RequestParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()

        sheetDialog = BottomSheetDialog(this, R.style.BottomSheetStyle)

        tvMobileNumber = findViewById(R.id.activity_otp_tv_mobile_number)
        tvResendOtp = findViewById(R.id.activity_otp_et_tv2_resend_otp)
        tvTimer = findViewById(R.id.activity_otp_et_tv2_timer)

        dialog = Dialog(this).apply {
            requestWindowFeature(1)
            setContentView(R.layout.progressbar)
            setCanceledOnTouchOutside(false)
        }

        setupOtpInputs()
        setupVerifyButton()
        setupResendOtp()

        verificationId = intent.getStringExtra("verification_id") ?: ""

        tvMobileNumber.text = "+91${intent.getStringExtra("mobile_number")}"

        startOtpCountdown()
    }

    private fun setupOtpInputs() {
        inputCode1 = findViewById(R.id.activity_otp_et_otp1)
        inputCode2 = findViewById(R.id.activity_otp_et_otp2)
        inputCode3 = findViewById(R.id.activity_otp_et_otp3)
        inputCode4 = findViewById(R.id.activity_otp_et_otp4)
        inputCode5 = findViewById(R.id.activity_otp_et_otp5)
        inputCode6 = findViewById(R.id.activity_otp_et_otp6)

        addTextChangeListener(inputCode1, inputCode2)
        addTextChangeListener(inputCode2, inputCode3)
        addTextChangeListener(inputCode3, inputCode4)
        addTextChangeListener(inputCode4, inputCode5)
        addTextChangeListener(inputCode5, inputCode6)
    }

    private fun addTextChangeListener(from: EditText, to: EditText) {
        from.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    to.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupVerifyButton() {
        findViewById<Button>(R.id.activity_otp_btn_verify).setOnClickListener {
            val code = inputCode1.text.toString() +
                    inputCode2.text.toString() +
                    inputCode3.text.toString() +
                    inputCode4.text.toString() +
                    inputCode5.text.toString() +
                    inputCode6.text.toString()

            if (code.length != 6) {
                Toast.makeText(this, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show()
            } else if (verificationId.isNotEmpty()) {
                dialog.show()
                val credential = PhoneAuthProvider.getCredential(verificationId, code)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        dialog.dismiss()
                        if (task.isSuccessful) {
                            editor.putBoolean("is_login", true).apply()
                            editor.putString(
                                "mobile_number",
                                intent.getStringExtra("mobile_number")
                            ).apply()
                            val intent = Intent(this, HomePageActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Incorrect OTP", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    private fun setupResendOtp() {
        tvResendOtp.setOnClickListener {
            val mobileNumber = intent.getStringExtra("mobile") ?: ""
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91$mobileNumber",
                60,
                TimeUnit.SECONDS,
                this,
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

                    override fun onVerificationFailed(e: FirebaseException) {
                        Toast.makeText(this@OtpActivity, e.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onCodeSent(
                        newVerificationId: String,
                        token: PhoneAuthProvider.ForceResendingToken
                    ) {
                        verificationId = newVerificationId
                        Toast.makeText(this@OtpActivity, "OTP sent", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

    private fun startOtpCountdown() {
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = "Resend OTP in: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                tvTimer.text = "Click on Resend button!"
            }
        }.start()
    }

    fun showDialog(dialogMsg1: String, dialogMsg2: String) {
        val view = layoutInflater.inflate(
            R.layout.no_internet_2,
            findViewById<LinearLayout>(R.id.bottomsheet_dialog_no_internet)
        )
        val btnVpnRefresh = view.findViewById<Button>(R.id.btn_no_internet2)
        view.findViewById<TextView>(R.id.tv1).text = dialogMsg1
        view.findViewById<TextView>(R.id.tv2).text = dialogMsg2
        sheetDialog.apply {
            setContentView(view)
            setCancelable(false)
            show()
        }
        btnVpnRefresh.text = "OK"
        btnVpnRefresh.setOnClickListener {
            object : CountDownTimer(1000, 2000) {
                override fun onTick(millisUntilFinished: Long) {
                    dialog.show()
                    sheetDialog.dismiss()
                }

                override fun onFinish() {
                    dialog.dismiss()
                    sheetDialog.dismiss()
                }
            }.start()
        }
    }
}
