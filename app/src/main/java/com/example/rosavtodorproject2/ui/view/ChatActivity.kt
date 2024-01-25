package com.example.rosavtodorproject2.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.example.rosavtodorproject2.R

class ChatActivity : AppCompatActivity() {
    companion object {
        const val user_id_key = "user_id_key"
        const val user_name_key = "user_name_key"
        const val user_picture_key = "user_picture_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val userId = intent.getIntExtra(user_id_key, 0)

        val userName = intent.getStringExtra(user_name_key)
        val userPictureResourceId: Int = intent.getIntExtra(user_picture_key,0)

        val collocutorName = findViewById<TextView>(R.id.collocutor_name)

        collocutorName.text = userName

        val collocutorPicture = findViewById<ImageView>(R.id.collocutor_picture)
        collocutorPicture.setImageBitmap(
            AppCompatResources.getDrawable( baseContext,userPictureResourceId)!!
            .toBitmap(baseContext.toPx(70).toInt(), baseContext.toPx(70).toInt()))

        val backToChatsButton = findViewById<ImageButton>(R.id.back_to_chats_panel_button)
        backToChatsButton.setOnClickListener {
            val intentToOpenMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToOpenMainActivity)
        }
    }
    fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    )
}