package com.skybox.seven.covid.ui.share

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.Advice
import com.skybox.seven.covid.data.entities.Myth
import com.skybox.seven.covid.databinding.DialogPreventionViewBinding
import com.skybox.seven.covid.helpers.VisibilityListener
import com.skybox.seven.covid.util.getLocalBitmapUri
import com.skybox.seven.covid.util.toImage


class ShareDialogDialog : Fragment() {
    private val viewModel: ShareDialogViewModel by activityViewModels()

    private lateinit var binding: DialogPreventionViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogPreventionViewBinding.inflate(inflater, container, false)

        setUpBottomSheet()

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



//        binding.close.setOnClickListener { dismiss() }
        binding.share.setOnClickListener { shareAdvice() }
        binding.header.setVisibilityListener(object : VisibilityListener {
            override fun onVisibilityChange(visibility: Int) {
                if (visibility == View.VISIBLE) {
                    val image = binding.sharable.toImage()
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "image/jpeg"
//                        putExtra(Intent.EXTRA_TITLE, advice?.shortTitle)
                        if (image != null) {
                            val uri = image.getLocalBitmapUri(requireContext())
                            putExtra(Intent.EXTRA_STREAM, uri)
                            clipData = ClipData.newUri(context?.contentResolver, context?.getString(R.string.app_name), uri)
                        }
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    context?.startActivity(Intent.createChooser(intent, null))
//                    showContent()
                }
            }
        })

        return binding.root
    }

    private fun shareAdvice() {
        binding.header.visibility = View.VISIBLE
    }

    private fun setUpBottomSheet() {
        val behavior = BottomSheetBehavior.from(binding.sheet)

        viewModel.open.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", "setUpBottomSheet: clicked")
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        })
    }

    companion object {
        const val ADVICE = "ADVICE"
        private const val MYTH = "MYTH"
        private const val TYPE = "TYPE"

        @JvmStatic
        fun newInstance(advice: Advice?): ShareDialogDialog {
            val dialog = ShareDialogDialog()
            val args = Bundle()
            args.putSerializable(ADVICE, advice)
            args.putInt(TYPE, 0)
            dialog.arguments = args
            return dialog
        }

        @JvmStatic
        fun newInstance(myth: Myth): ShareDialogDialog {
            val dialog = ShareDialogDialog()
            val args = Bundle()
            args.putSerializable(MYTH, myth)
            args.putInt(TYPE, 1)
            dialog.arguments = args
            return dialog
        }
    }
}