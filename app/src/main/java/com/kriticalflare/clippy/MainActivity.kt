package com.kriticalflare.clippy

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (intent?.action == Intent.ACTION_SEND && "text/plain" == intent.type) {
            handleIncomingText(intent, this)
            finish()
        }
    }
}

private fun handleIncomingText(intent: Intent, context: Context) {
    intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("copied text", text)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, "Text Copied to Clipboard", Toast.LENGTH_SHORT).show()
    }
}