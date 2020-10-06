package com.skybox.seven.covid.ui.details

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.skybox.seven.covid.R
import com.skybox.seven.covid.databinding.FragmentDetailsBinding
import com.skybox.seven.covid.helpers.TextFormatter
import com.skybox.seven.covid.helpers.UIHelpers

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this

        UIHelpers.setUpToolbar(binding.toolbarLayout.toolbar, findNavController(), R.menu.share_menu, Toolbar.OnMenuItemClickListener {
            if (it != null) {
                if (it.itemId == R.id.share)
                    shareDetails()
            }
            true
        })

        return binding.root

    }

    private fun shareDetails() {
    }

    fun whatsApp() {
        val pkg = activity?.packageManager
        val appInstalled: Boolean

        appInstalled = try {
            if (pkg != null) {
                pkg.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                true
            } else {
                false
            }
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

        if (appInstalled) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + getString(R.string.chatbot)
                    + "&text="))
            startActivity(intent)
        } else {
            Toast.makeText(context, "WhatsApp not installed on your Device", Toast.LENGTH_SHORT).show()
        }
    }

    fun callProvider(tnm: Boolean) {
        if (tnm) {
            val number1 = getString(R.string._352)
            val number2 = getString(R.string._929)

            createCallDialog(
                    arrayOf(Uri.parse("tel:${number1}").toString(), TextFormatter.ussdToCallableUri(number2).toString()),
                    arrayOf(number1, number2)
            )
        } else {
            val number1 = getString(R.string._54747)
            val number2 = getString(R.string._929)

            createCallDialog(
                    arrayOf(Uri.parse("tel:${number1}").toString(), TextFormatter.ussdToCallableUri(number2).toString()),
                    arrayOf(number1, number2)
            )
        }
    }

    fun facebook() {
        val url = "https://web.facebook.com/malawimoh"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun twitter() {
        val url = "https://twitter.com/health_malawi"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun website() {
        val url = "https://www.health.gov.mw/"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun createCallDialog(values: Array<String>, headers: Array<String>) {
        MaterialAlertDialogBuilder(requireContext())
                .setTitle("Choose Number")
                .setItems(headers) { _, which ->
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse(values[which])
                    startActivity(intent)
                }
                .show()
    }
}