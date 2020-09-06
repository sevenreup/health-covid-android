package com.skybox.seven.covid.ui.survey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skybox.seven.covid.databinding.ActivitySelfTestQuestionareBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelfTestQuestionareActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySelfTestQuestionareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}