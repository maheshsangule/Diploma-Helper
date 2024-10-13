package com.developermaheshsofttechltd.diplomahelper.views.activities

//import com.facebook.appevents.AppEventsConstants
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.loopj.android.http.AsyncHttpClient
//import com.loopj.android.http.JsonHttpResponseHandler
//import com.loopj.android.http.RequestParams
//import cz.msebera.android.httpclient.Header
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.developermaheshsofttechltd.diplomahelper.R
import com.developermaheshsofttechltd.diplomahelper.databinding.ActivitySignupBinding
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog

class SignupActivity : AppCompatActivity() {

    //    private val client = AsyncHttpClient()
    private lateinit var customErrorDrawable: Drawable

    //    private lateinit var dialog: Dialog
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var preferences: SharedPreferences

    //    private lateinit var params: RequestParams
    private lateinit var googleSignInAccount: GoogleSignInAccount

    //    private lateinit var gsc: GoogleSignInClient
    private lateinit var gso: GoogleSignInOptions
    private lateinit var sheetDialog: BottomSheetDialog

//    private lateinit var etName: EditText
//    private lateinit var etUsername: EditText
//    private lateinit var etMobileNumber: EditText
//    private lateinit var etEmail: EditText
//    private lateinit var etPassword: EditText
//    private lateinit var etConfirmPassword: EditText
//    private lateinit var rbGroupGender: RadioGroup
//    private lateinit var rbMale: RadioButton
//    private lateinit var rbFemale: RadioButton
//    private lateinit var tvLogin: TextView
//    private lateinit var btnSignup: Button
//    private lateinit var ivEtPassword: ImageView
//    private lateinit var ivEtConfirmPassword: ImageView
//    private lateinit var imgGoogleLogin: ImageView
//    private lateinit var imgFacebookLogin: ImageView
//    private lateinit var setError: TextView

    private var gender: String = ""
    private var id: Int = 0
    private var rbGetGender: RadioButton? = null
    private var strShowDialogMsg1: String? = null
    private var strShowDialogMsg2: String? = null


    private val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }
    private val activity = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {


            customErrorDrawable = resources.getDrawable(R.drawable.icon_seterror2, null).apply {
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            }

            sheetDialog = BottomSheetDialog(activity, R.style.BottomSheetStyle)
            preferences = PreferenceManager.getDefaultSharedPreferences(activity)
            editor = preferences.edit()

//        ivEtPassword = findViewById(R.id.activity_signup_iv_et_password)
//        ivEtConfirmPassword = findViewById(R.id.activity_signup_iv_et_confirm_password)
//        tvLogin = findViewById(R.id.tv_login)
//        imgGoogleLogin = findViewById(R.id.activity_signup_google_login_img)
//        imgFacebookLogin = findViewById(R.id.activity_signup_facebook_login_img)
//        setError = findViewById(R.id.activity_signup_tv_error)
//        etName = findViewById(R.id.activity_signup_et_name)
//        etUsername = findViewById(R.id.activity_signup_et_username)
//        etMobileNumber = findViewById(R.id.activity_signup_et_mobile_number)
//        rbGroupGender = findViewById(R.id.activity_signup_rb_group_gender)
//        rbMale = findViewById(R.id.activity_signup_rb_male)
//        rbFemale = findViewById(R.id.activity_signup_rb_female)
//        etEmail = findViewById(R.id.activity_signup_et_email)
//        etPassword = findViewById(R.id.activity_signup_et_password)
//        etConfirmPassword = findViewById(R.id.activity_signup_et_confirm_password)
//        btnSignup = findViewById(R.id.activity_signup_btn_signup)

//        dialog = Dialog(this).apply {
//            requestWindowFeature(1)
//            setContentView(R.layout.progressbar)
//            setCanceledOnTouchOutside(false)
//        }

            gso =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                    .build()

            etEyeConfirmPassword.setOnClickListener {
                togglePasswordVisibility(etConfirmPassword, etEyeConfirmPassword)
            }

            etEyePassword.setOnClickListener {
                togglePasswordVisibility(etPassword, etEyePassword)
            }

            ivFacebookLogin.setOnClickListener {
                Toast.makeText(activity, "Under Maintenance!", Toast.LENGTH_SHORT).show()
            }

            mbtnSignup.setOnClickListener {
                validateAndSignup(binding)
            }

            ivGoogleLogin.setOnClickListener {
//            CountDownTimer(2000, 1000).apply {
//                onTick = { dialog.show() }
//                onFinish = { signIn() }
//                start()
//            }
            }

            tvLogin.setOnClickListener {
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        }
    }

    private fun togglePasswordVisibility(editText: EditText, imageView: ImageView) {
        if (editText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.ic_eye_visible)
        } else {
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.ic_eye_invisible)
        }
    }

    private fun validateAndSignup(binding: ActivitySignupBinding) {
        binding.apply {
            when {
                etName.text.isNullOrEmpty() -> showError("Please Enter the Name...", etName)
                !etName.text.toString()
                    .matches("[a-zA-Z ]+".toRegex()) -> showError(
                    "Please Enter Alphabets Only",
                    etName
                )

                etName.text.length < 8 -> showError(
                    "Name should be of greater than 8 char..",
                    etName
                )

                etUsername.text.isNullOrEmpty() -> showError("Please Enter UserName...", etUsername)
                !etUsername.text.toString().matches("[a-z-_]+".toRegex()) -> showError(
                    "Please Enter Small letters Only",
                    etUsername
                )

                etUsername.text.length < 8 -> showError(
                    "Username must be of greater than 8 char..",
                    etUsername
                )

                etMobileNumber.text.isNullOrEmpty() -> showError(
                    "Please Enter Mobile Number...",
                    etMobileNumber
                )

                rbMale.isChecked.not() && rbFemale.isChecked.not() -> showError(
                    "Please Select Gender",
                    null
                )

                etEmail.text.isNullOrEmpty() -> showError("Please Enter Email...", etEmail)
                !etEmail.text.contains("@") || !etEmail.text.contains(".com") || !etEmail.text.contains(
                    "gmail"
                ) -> showError("Please Enter Valid Email Id", etEmail)

                etPassword.text.isNullOrEmpty() -> showError("Please Enter Password...", null)
                etPassword.text.length < 8 -> showError(
                    "Password must be of greater than 8 character",
                    null
                )

                etConfirmPassword.text.isNullOrEmpty() -> showError(
                    "Please Confirm the Password...",
                    null
                )

                etConfirmPassword.text.toString() != etPassword.text.toString() -> showError(
                    "Confirm the Password Properly",
                    null
                )

                else -> {
//                    setError.text = ""
                    addUserDetails()
                }
            }
        }

    }

    private fun showError(message: String, editText: EditText?) {
//        setError.text = message
        editText?.error = message
        editText?.setCompoundDrawablesWithIntrinsicBounds(customErrorDrawable, null, null, null)
    }

    private fun addUserDetails() {
//        params = RequestParams().apply {
//            id = rbGroupGender.checkedRadioButtonId
//            rbGetGender = findViewById(id)
//            put("name", etName.text.toString())
//            put("username", etUsername.text.toString())
//            put("mobile_number", etMobileNumber.text.toString())
//            put("spinner_gender", rbGetGender?.text.toString())
//            put("email", etEmail.text.toString())
//            put("password", etPassword.text.toString())
//            put("confirm_password", etConfirmPassword.text.toString())
//        }

//        client.post(Urls.urlRegisterUser, params, object : JsonHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
//                super.onSuccess(statusCode, headers, response)
//                try {
//                    if (response.getString("success") == AppEventsConstants.EVENT_PARAM_VALUE_YES) {
//                        Toast.makeText(
//                            this@SignupActivity,
//                            "Registration Done Successfully",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
//                    } else {
//                        Toast.makeText(
//                            this@SignupActivity,
//                            response.getString("error"),
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Array<Header>,
//                responseString: String,
//                throwable: Throwable
//            ) {
//                super.onFailure(statusCode, headers, responseString, throwable)
//                Toast.makeText(this@SignupActivity, "Registration failed", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        })
    }

    private fun signIn() {
//        val signInIntent = gsc.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RC_SIGN_IN) {
////            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
////            handleSignInResult(task)
//        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Toast.makeText(this, "Google Sign-In Successful", Toast.LENGTH_SHORT).show()
            // Handle successful sign-in here
        } catch (e: ApiException) {
            Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show()
        }
//        dialog.dismiss()
    }

//    companion object {
//        private const val RC_SIGN_IN = 9001
//    }
}
