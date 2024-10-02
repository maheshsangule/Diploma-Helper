package com.developermaheshsofttechltd.diplomahelper.views.fragment

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codepath.asynchttpclient.RequestParams
import com.developermaheshsofttechltd.diplomahelper.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import org.asynchttpclient.AsyncHttpClient


class ProfileFragment : Fragment() {

    private var auth: FirebaseAuth? = null
    private lateinit var binding: FragmentProfileBinding
    private var client: AsyncHttpClient? = null
    private var dialog: Dialog? = null
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var loginUsername: String? = null
    private var mobileNumber: String? = null
    private var params: RequestParams = RequestParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

//    private fun getMyInfo() {
//        params["username"] = loginUsername
//        params["mobile_number"] = mobileNumber
//
//        client.post(Urls.urlGetMyDetails, params, object : TextHttpResponseHandler() {
//
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Array<Header>,
//                responseString: String,
//                throwable: Throwable
//            ) {
//                // Handle failure
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Headers?,
//                errorResponse: String?,
//                throwable: Throwable?
//            ) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
}
