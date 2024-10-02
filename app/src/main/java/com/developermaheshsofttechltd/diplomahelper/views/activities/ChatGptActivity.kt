package com.developermaheshsofttechltd.diplomahelper.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developermaheshsofttechltd.diplomahelper.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ChatGptActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private var linearLayout: LinearLayout? = null
    private var messageAdapter: MessageAdapter? = null
    private var messageList = mutableListOf<Message>()
    private lateinit var msgEt: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var sendBtn: ImageButton
    private lateinit var welcomeTv: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_gpt)

        supportActionBar?.hide()

        recyclerView = findViewById(R.id.activity_chat_gpt_recycler_view)
        welcomeTv = findViewById(R.id.activity_chat_gpt_tv_welcome_text)
        sendBtn = findViewById(R.id.activity_chat_gpt_send_btn)
        msgEt = findViewById(R.id.activity_chat_gpt_et_input)

        messageAdapter = MessageAdapter(messageList)
        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }

        sendBtn.setOnClickListener {
            val question = msgEt.text.toString().trim()
            if (question.isNotEmpty()) {
                addToChat(question, Message.SEND_BY_ME)
                msgEt.setText("")
                callApi(question)
                welcomeTv.visibility = View.GONE
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addToChat(message: String, sentBy: String) {
        runOnUiThread {
            messageList.add(Message(message, sentBy))
            messageAdapter?.notifyDataSetChanged()
            recyclerView.smoothScrollToPosition(messageAdapter?.itemCount ?: 0)
        }
    }

    private fun addResponse(response: String) {
        addToChat(response, Message.SEND_BY_BOT)
    }

    private fun callApi(question: String) {
        val jsonObject = JSONObject().apply {
            put("model", "text-davinci-003")
            put("prompt", question)
            put("max_tokens", 4000)
            put("temperature", 0)
        }

        val requestBody = jsonObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val request = Request.Builder()
            .url("https://api.openai.com/v1/completions")
            .header("Authorization", "Bearer YOUR_API_KEY_HERE")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                addResponse("Failed to load response due to ${e.message}")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    try {
                        val responseBody = response.body?.string() ?: ""
                        val responseJson = JSONObject(responseBody)
                        val choices = responseJson.getJSONArray("choices")
                        val choice = choices.getJSONObject(0)
                        val text = choice.getString("text").trim()
                        addResponse(text)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        addResponse("Failed to parse response")
                    }
                } else {
                    addResponse("Failed to load due to ${response.message}")
                }
            }
        })
    }

    companion object {
        private val JSON: MediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
    }
}
