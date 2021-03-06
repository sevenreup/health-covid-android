package com.skybox.seven.covid.ui.share

import android.content.ClipData
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.DialogPreventionViewBinding
import com.skybox.seven.covid.ui.MainViewModel
import com.skybox.seven.covid.util.getLocalBitmapUri
import com.skybox.seven.covid.util.lerp
import com.skybox.seven.covid.util.lerpArgb
import com.skybox.seven.covid.util.toImage


class ShareFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: DialogPreventionViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogPreventionViewBinding.inflate(inflater, container, false).apply {
            val behavior = BottomSheetBehavior.from(sheet)

            close.setOnClickListener { behavior.state = BottomSheetBehavior.STATE_COLLAPSED }

            val backCallback =
                    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, false) {
                        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }

            val sheetStartColor = ContextCompat.getColor(sheet.context, R.color.color_primary_variant)
            val sheetEndColor = ContextCompat.getColor(sheet.context, R.color.color_primary)

            val sheetBackground = MaterialShapeDrawable(
                    ShapeAppearanceModel.builder(
                            sheet.context,
                            R.style.ShapeAppearance_Covid_MinimizedSheet,
                            0
                    ).build()
            ).apply {
                fillColor = ColorStateList.valueOf(sheetStartColor)
            }
            sheet.background = sheetBackground
            sheet.also {
                val peek = behavior.peekHeight
                val maxTranslationX = (it.width - peek).toFloat()
                sheet.translationX = (sheet.width - peek).toFloat()

                behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        backCallback.isEnabled = newState == BottomSheetBehavior.STATE_EXPANDED

                        if (newState == BottomSheetBehavior.STATE_COLLAPSED)
                            viewModel.hideBottom.value = false
                        else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                            viewModel.open.value = false
                            viewModel.hideBottom.value = true
                        }

                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        sheet.translationX =
                                lerp(maxTranslationX, 0f, 0f, 0.15f, slideOffset)

                        sheetBackground.interpolation = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                        sheetBackground.fillColor = ColorStateList.valueOf(
                                lerpArgb(
                                        sheetStartColor,
                                        sheetEndColor,
                                        0f,
                                        0.3f,
                                        slideOffset
                                )
                        )

                        close.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        share.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                        sharable.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                    }
                })
            }

            viewModel.open.observe(viewLifecycleOwner, Observer {
                if (it) behavior.state = BottomSheetBehavior.STATE_EXPANDED
            })
            share.setOnClickListener { shareAdvice() }
        }

        viewModel.myth.observe(viewLifecycleOwner, Observer {
            binding.preventionRVCardTitle.text = it.title
            binding.preventionRVCardDescription.text = it.paragraph
            binding.title.text = getString(R.string.myth_advice)
            binding.cardIcon.setImageResource(R.drawable.ic_myth)
        })

        viewModel.advice.observe(viewLifecycleOwner, Observer {
            binding.preventionRVCardTitle.text = it.title
            binding.preventionRVCardDescription.text = it.advice
            binding.title.text = getString(R.string.prevention)
            binding.cardIcon.setImageResource(R.drawable.ic_prevention)
        })



        return binding.root
    }

    override fun onDestroy() {
        viewModel.hideBottom.value = false
        viewModel.open.value = false
        super.onDestroy()
    }

    private fun shareAdvice() {
        val image = binding.sharable.toImage()
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/jpeg"
            putExtra(Intent.EXTRA_TITLE, binding.preventionRVCardTitle.text)
            if (image != null) {
                val uri = image.getLocalBitmapUri(requireContext())
                putExtra(Intent.EXTRA_STREAM, uri)
                clipData = ClipData.newUri(context?.contentResolver, context?.getString(R.string.app_name), uri)
            }
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context?.startActivity(Intent.createChooser(intent, null))
    }
}