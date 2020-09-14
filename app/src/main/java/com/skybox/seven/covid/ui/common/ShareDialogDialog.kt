package com.skybox.seven.covid.ui.common

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skybox.seven.covid.R
import com.skybox.seven.covid.data.entities.Advice
import com.skybox.seven.covid.data.entities.Myth
import com.skybox.seven.covid.databinding.DialogPreventionViewBinding
import com.skybox.seven.covid.util.getLocalBitmapUri
import com.skybox.seven.covid.util.toImage

class ShareDialogDialog : BottomSheetDialogFragment() {
    private var advice: Advice? = null
    private var myth: Myth? = null
    private var type = 0

    private lateinit var binding: DialogPreventionViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(requireArguments()) {
            advice = getSerializable(ADVICE) as Advice?
            myth = getSerializable(MYTH) as Myth?
            type = getInt(TYPE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogPreventionViewBinding.inflate(inflater, container, false)
        when(type) {
            0-> {
                binding.preventionRVCardTitle.text = advice!!.title
                binding.preventionRVCardDescription.text = advice!!.advice
            }
            else -> {
                binding.preventionRVCardTitle.text = myth!!.title
                binding.preventionRVCardDescription.text = myth!!.myth
            }
        }

        binding.close.setOnClickListener { dismiss() }
        binding.share.setOnClickListener { shareAdvice() }
        return binding.root
    }

    private fun shareAdvice() {
        hideContent()
        val image = binding.root.toImage()
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/jpeg"
            putExtra(Intent.EXTRA_TITLE, advice?.shortTitle)
            if (image != null) {
                val uri =  image.getLocalBitmapUri(requireContext())
                putExtra(Intent.EXTRA_STREAM, uri)
                clipData = ClipData.newUri(context?.contentResolver, context?.getString(R.string.app_name), uri)
            }
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context?.startActivity(Intent.createChooser(intent, null))
        showContent()
    }

    private fun hideContent() {
        binding.close.visibility = View.GONE
        binding.share.visibility = View.GONE
    }

    private fun showContent() {
        binding.close.visibility = View.VISIBLE
        binding.share.visibility = View.VISIBLE
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