package com.example.hw3_nav_rannumgen_dice.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.hw3_nav_rannumgen_dice.R
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

    private lateinit var editText: EditText

    override fun onCreateView(
                                    inflater: LayoutInflater,
                                    container: ViewGroup?,
                                    savedInstanceState: Bundle?
                                ): View? {

        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        editText = root.findViewById(R.id.edit_text)

        var num: Double = 0.0

        val radioGroup: RadioGroup = root.findViewById(R.id.radioGroup)
        lateinit var selectedRadioButton: RadioButton
        lateinit var calculateButton: Button
        var radioID: Int = 0


        calculateButton = root.findViewById(R.id.calculate_button)
        calculateButton.setOnClickListener {

            num = ( editText.text.toString() ).toDouble()

            radioID = radioGroup.checkedRadioButtonId

            if ( radioID != -1 ){

                selectedRadioButton = root.findViewById(radioID)


                if ( (selectedRadioButton.text.toString()).equals("15%")){

                    result_tip.text = (num*0.15).toString()
                    result_total.text = (num+(num*0.15)).toString()

                }
                else if ( (selectedRadioButton.text.toString()).equals("18%")){

                    result_tip.text = (num*0.18).toString()
                    result_total.text = (num+(num*0.18)).toString()
                }
                else if ( (selectedRadioButton.text.toString()).equals("20%")){

                    result_tip.text = (num*0.20).toString()
                    result_total.text = (num+(num*0.20)).toString()
                }

            }else{

                    result_tip.text = "0"
                    result_total.text = (num).toString()
                    Toast.makeText(getActivity(),"nothing selected", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

}