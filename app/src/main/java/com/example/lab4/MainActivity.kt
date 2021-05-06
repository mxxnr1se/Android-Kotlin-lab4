package com.example.lab4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import net.alhazmy13.mediapicker.Video.VideoPicker


class MainActivity : AppCompatActivity() {
	private var videoPlayer: VideoView? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	fun pick(view: View?) {
		VideoPicker.Builder(this@MainActivity).mode(VideoPicker.Mode.GALLERY).directory("/storage/emulated/0/Movies")
			.extension(VideoPicker.Extension.MP4).enableDebuggingMode(true).build()
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
			val mPaths: List<String>? = data!!.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH)
			//videoPlayer = view.findViewById<VideoView>(R.id.video_view)
			videoPlayer = findViewById(R.id.video_view)
			videoPlayer?.setVideoURI(Uri.parse(mPaths!![0]))
			val mediaController = MediaController(this)
			videoPlayer?.setMediaController(mediaController)
			mediaController.setAnchorView(videoPlayer)
		}
	}

	fun find(view: View?) {
		videoPlayer = findViewById(R.id.video_view)
		val videoUrl = findViewById<EditText>(R.id.video_url)
		videoPlayer?.setVideoURI(Uri.parse(videoUrl.text.toString()))
		val mediaController = MediaController(this)
		videoPlayer?.setMediaController(mediaController)
		mediaController.setAnchorView(videoPlayer)
	}

	fun play(view: View?) {
		if (videoPlayer != null)
			videoPlayer!!.start()
	}

	fun pause(view: View?) {
		if (videoPlayer != null)
			videoPlayer!!.pause()
	}

	fun stop(view: View?) {
		if (videoPlayer != null)
		{
			videoPlayer!!.stopPlayback()
			videoPlayer!!.resume()
		}
	}
}