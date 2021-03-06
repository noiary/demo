package com.maodq.demo.internal

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.maodq.demo.R
import com.maodq.demo.base.BActivity
import okhttp3.*
import java.io.IOException

class OkHttpDemoActivity : BActivity() {
    var tv: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http_demo)
        tv = findViewById(R.id.tv)

        demo()
    }

    private fun demo() {
        // 创建OkHttpClient对象，内部默认创建一系列成员变量对象
        val okHttpClient = OkHttpClient()
        // 创建Request，默认get请求，设置url
        val request = Request.Builder().url("http://www.jd.com").build()
        // 创建call对象，把okHttpClient自己装进call对象中,此时并没有进行网络连接
        val call = okHttpClient.newCall(request)
        // 异步联网，通过OkHttpClient中的成员dispatcher中的线程池来启动工作线程，然后进行联网操作，
        // onResponse 和 onFailure回调并没有在主线程，需要自己手动切换线程
        // TODO 联网的具体细节待分析
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null && response.isSuccessful) {
                    val responseStr = response.body()?.string()
                    val fromHtml = Html.fromHtml(responseStr)
                    runOnUiThread { tv?.text = fromHtml }
                }
            }
        })
    }
}
