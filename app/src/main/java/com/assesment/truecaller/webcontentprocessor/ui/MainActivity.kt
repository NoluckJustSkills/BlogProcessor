package com.assesment.truecaller.webcontentprocessor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.assesment.truecaller.webcontentprocessor.R
import com.assesment.truecaller.webcontentprocessor.databinding.ActivityMainBinding
import com.assesment.truecaller.webcontentprocessor.presentation.WebContentViewModel
import com.assesment.truecaller.webcontentprocessor.ui.utils.TextViewScrollingExtensions.makeTextViewScrollableInsideScrollView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var tenthCharacterTextView: TextView
    private lateinit var everyTenthCharacterTextView: TextView
    private lateinit var wordCountTextView: TextView
    private lateinit var loadButton: Button
    private lateinit var binding: ActivityMainBinding

    private val viewModel: WebContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.dataBindingViewModel = viewModel
        initializeUI(viewModel)
    }

    private fun initializeUI(viewModel: WebContentViewModel) {
        tenthCharacterTextView = findViewById(R.id.tenth_char_content)
        tenthCharacterTextView.makeTextViewScrollableInsideScrollView()
        everyTenthCharacterTextView = findViewById(R.id.every_tenth_char_content)
        everyTenthCharacterTextView.makeTextViewScrollableInsideScrollView()
        wordCountTextView = findViewById(R.id.words_count_content)
        wordCountTextView.makeTextViewScrollableInsideScrollView()
        loadButton = findViewById(R.id.load_content)
        loadButton.setOnClickListener {
            viewModel.getEveryTenthCharacter()
            viewModel.getTenthCharacter()
            viewModel.getWordCount()
        }
    }
}