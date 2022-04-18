package com.example.newsapp.ui.webview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentWebviewBinding
import android.webkit.WebChromeClient

class WebViewFragment : Fragment() {

    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentWebviewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_webview,
            container,
            false
        )
        progressBar = binding.progressBar
        progressBar.progress

        var args = arguments?.let { WebViewFragmentArgs.fromBundle(it) }

        // inflate Web View
        val webView = binding.webView
        webView.webViewClient = WebViewClient()
        webView.settings.apply {
            allowFileAccess = true
            javaScriptEnabled = true
            domStorageEnabled = true
            domStorageEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            builtInZoomControls = true
            displayZoomControls = false
        }
        webView.loadUrl("${args?.url}")

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                progressBar.progress = progress
            }
        }

        webView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == MotionEvent.ACTION_UP && webView.canGoBack()) {
                webView.goBack()
                return@OnKeyListener true
            }
            false
        })

        return binding.root
    }

    inner class WebViewClient : android.webkit.WebViewClient() {

        // ProgressBar will disappear once page is loaded
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }
}