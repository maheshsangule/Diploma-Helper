package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.codepath.asynchttpclient.RequestParams
import com.developermaheshsofttechltd.diplomahelper.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.asynchttpclient.AsyncHttpClient

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    companion object {
        const val is_login = Urls.is_login
    }

    private lateinit var btnLogin: Button
    private lateinit var client: AsyncHttpClient
    private lateinit var dialog: Dialog
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var email: EditText

    //    private lateinit var gsc: GoogleSignInClient
    private lateinit var gso: GoogleSignInOptions
    private lateinit var imgFacebookLogin: ImageView
    private lateinit var imgGoogleLogin: ImageView
    private lateinit var ivEtPassword: ImageView
    private lateinit var params: RequestParams
    private lateinit var password: EditText
    private lateinit var setError: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sheetDialog: BottomSheetDialog
    private lateinit var tvSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sheetDialog = BottomSheetDialog(this, R.style.BottomSheetStyle)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()

        btnLogin = findViewById(R.id.btn_login)
        tvSignup = findViewById(R.id.tv_signup)
        imgGoogleLogin = findViewById(R.id.activity_login_google_login_img)
        imgFacebookLogin = findViewById(R.id.activity_login_facebook_login_img)
        email = findViewById(R.id.activity_login_et_loginemail)
        password = findViewById(R.id.activity_login_et_loginpassword)
        setError = findViewById(R.id.activity_login_tv_error)
        ivEtPassword = findViewById(R.id.activity_login_iv_et_password)
        ivEtPassword.setImageResource(R.drawable.icon_eye_enable)

        dialog = Dialog(this)
        dialog.requestWindowFeature(1)
        dialog.setContentView(R.layout.progressbar)
        dialog.setCanceledOnTouchOutside(false)

        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
//        gsc = GoogleSignIn.getClient(this, gso)

        ivEtPassword.setOnClickListener {
            if (password.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                password.transformationMethod = PasswordTransformationMethod.getInstance()
                ivEtPassword.setImageResource(R.drawable.icon_eye_enable)
            } else {
                password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ivEtPassword.setImageResource(R.drawable.icon_eye_disable)
            }
        }

        imgFacebookLogin.setOnClickListener {
//            println("$s1$s2")
//            Toast.makeText(this, "$s1$s2", Toast.LENGTH_SHORT).show()
        }

        imgGoogleLogin.setOnClickListener {
            object : CountDownTimer(2000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    dialog.show()
                }

                override fun onFinish() {
//                    signIn()
                }
            }.start()
        }

        tvSignup.setOnClickListener {
            dialog.show()
            val intent = Intent(this, SignupActivity::class.java)
            dialog.dismiss()
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            when {
                email.text.toString().isEmpty() -> setError.text = "Username could not be Empty!"
                password.text.toString().isEmpty() -> setError.text = "Password could not be empty"
                else -> checkLoginUser()
            }
        }
    }

    private fun checkLoginUser() {
        params.put("email", email.text.toString())
        params.put("password", password.text.toString())
//        client.post(Urls.urlLoginUser, params, object : JsonHttpResponseHandler() {
////            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
////                super.onSuccess(statusCode, headers, response)
////                try {
////                    val isSuccess = response.getString("success")
////                    object : CountDownTimer(3000, 1000) {
////                        override fun onTick(l: Long) {
////                            dialog.show()
////                        }
////
////                        override fun onFinish() {
////                            dialog.dismiss()
////                            if (isSuccess == AppEventsConstants.EVENT_PARAM_VALUE_YES) {
////                                Toast.makeText(this@LoginActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
////                                editor.putBoolean(Urls.is_login, true).commit()
////                                val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
////                                editor.putString("username", email.text.toString()).commit()
////                                startActivity(intent)
////                                finish()
////                            } else {
////                                Toast.makeText(this@LoginActivity, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
////                            }
////                        }
////                    }.start()
////                } catch (e: JSONException) {
////                    throw RuntimeException(e)
////                }
////            }
////
////            override fun onFailure(statusCode: Int, headers: Array<Header>, throwable: Throwable, errorResponse: JSONObject?) {
////                super.onFailure(statusCode, headers, throwable, errorResponse)
////                Toast.makeText(this@LoginActivity, "Server Error", Toast.LENGTH_SHORT).show()
////            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Headers?,
//                response: String?,
//                throwable: Throwable?
//            ) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
//                TODO("Not yet implemented")
//            }
//        })
    }

//    private fun signIn() {
//        dialog.dismiss()
//        startActivityForResult(gsc.signInIntent, 1000)
//    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        dialog.show()
        if (requestCode == 1000) {
            dialog.dismiss()
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                dialog.dismiss()
//                task.getResult(ApiException::class.java)
//                emailLogin()
//            } catch (e: ApiException) {
//                dialog.dismiss()
//                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
//            }
        }
    }

//    private fun emailLogin() {
////        val googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this)
//        googleSignInAccount?.let {
//            params.put("email_google", googleSignInAccount.email.toString())
//            params.put("photo_url", googleSignInAccount.photoUrl.toString())
////            client.post(Urls.email, params, object : JsonHttpResponseHandler() {
//////                override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
//////                    super.onSuccess(statusCode, headers, response)
//////                    try {
//////                        val isSuccess = response.getString("success")
//////                        if (isSuccess == AppEventsConstants.EVENT_PARAM_VALUE_YES) {
//////                            val editor1 = getSharedPreferences(is_login, 0).edit()
//////                            editor1.putBoolean(Urls.is_login, true).commit()
//////                            val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
//////                            editor.putString("username", it.email).commit()
//////                            startActivity(intent)
//////                            finish()
//////                        } else if (isSuccess == "2D") {
//////                            showDialog()
//////                        } else {
//////                            Toast.makeText(this@LoginActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
//////                        }
//////                    } catch (e: JSONException) {
//////                        throw RuntimeException(e)
//////                    }
//////                }
////
//////                override fun onFailure(statusCode: Int, headers: Array<Header>, responseString: String?, throwable: Throwable) {
//////                    super.onFailure(statusCode, headers, responseString, throwable)
//////                    Toast.makeText(this@LoginActivity, "Server Error", Toast.LENGTH_SHORT).show()
//////                }
////
////                override fun onFailure(
////                    statusCode: Int,
////                    headers: Headers?,
////                    response: String?,
////                    throwable: Throwable?
////                ) {
////                    TODO("Not yet implemented")
////                }
////
////                override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
////                    TODO("Not yet implemented")
////                }
////            })
//        }
//    }

    private fun showDialog() {
        val view1 = layoutInflater.inflate(
            R.layout.no_internet_2,
            findViewById<LinearLayout>(R.id.bottomsheet_dialog_no_internet)
        )
        val btnVpnRefresh = view1.findViewById<Button>(R.id.btn_no_internet2)
        view1.findViewById<TextView>(R.id.tv1).text = "Please SignUp User Not Found"
        view1.findViewById<TextView>(R.id.tv2).text = "Create Account"
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
                    startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
                }
            }.start()
        }
    }
}
