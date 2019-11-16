package com.example.hw3_nav_rannumgen_dice.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.hw3_nav_rannumgen_dice.R
import kotlinx.android.synthetic.main.fragment_gallery.*
import java.util.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    lateinit var diceImage: ImageView

    override fun onCreateView(
                                    inflater: LayoutInflater,
                                    container: ViewGroup?,
                                    savedInstanceState: Bundle?
                                ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)

        val gotoTipcalButton: Button = root.findViewById(R.id.goto_tipcal_button)
        gotoTipcalButton.setOnClickListener {
            handleGotoTipcal(it)
        }

        diceImage = root.findViewById(R.id.dice_image)

        val rollButton: Button = root.findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice(it, diceImage)
        }
        return root
    }

    private fun handleGotoTipcal(view: View) {

        view.findNavController().navigate(R.id.action_nav_gallery_to_nav_slideshow)
    }

    private fun rollDice(view: View, diceImage: ImageView) {

        var randomInt = Random().nextInt(6)

        val drawableResource = when(randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)


    }
}