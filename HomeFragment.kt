package com.example.hw3_nav_rannumgen_dice.ui.home

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
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    var isChecked: Boolean = false
    var numbersList: ArrayList<Int> = ArrayList<Int>()

    override fun onCreateView(
                                    inflater: LayoutInflater,
                                    container: ViewGroup?,
                                    savedInstanceState: Bundle?
                                ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val gotoRolldiceButton: Button = root.findViewById(R.id.goto_rolldice_button)
        gotoRolldiceButton.setOnClickListener {
            handleGotoRolldice(it)
        }


        var msg: String?=null

        editText1 = root.findViewById(R.id.edit_text1)
        editText2 = root.findViewById(R.id.edit_text2)

        val checkBox: CheckBox = root.findViewById(R.id.checkBox)

        checkBox?.setOnCheckedChangeListener { Button, isTheBoxChecked ->

            if(isTheBoxChecked) {
                isChecked = true
                msg = "The numbers without a replacement"
                editText1.text.clear()
                editText2.text.clear()
                result_text.text = " "
            }
            else {
                isChecked = false
                msg = "The numbers with replacement"
                editText1.text.clear()
                editText2.text.clear()
                result_text.text =" "
            }
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show()
        }

        val rollButton: Button = root.findViewById(R.id.roll_button)
        rollButton.setOnClickListener{

            rollDice(it, editText1, editText2 )
        }

        return root
    }

    private fun handleGotoRolldice(view: View) {

        view.findNavController().navigate(R.id.action_nav_home_to_nav_gallery)
    }

    private fun rollDice(view: View, editText1: EditText, editText2: EditText) {

        var num1: Int = 0
        var num2: Int = 0
        var numbersRangeLimit: Int = 0
        var randomInt: Int = 0
        var diffNumFound: Boolean = false
        var sameNumFound: Boolean = false

        num1 = Integer.parseInt( editText1.text.toString() )
        num2 = Integer.parseInt( editText2.text.toString() )

        numbersRangeLimit = (num2-num1)+1

        diffNumFound = false

        if ( (num1>=1) && (num2<=30) && (num1<=num2) ) {
            if (!isChecked) {

                while (!diffNumFound) {
                    randomInt = Random().nextInt(30) + 1

                    if ((randomInt >= num1) && (randomInt <= num2)) {
                        diffNumFound = true
                        numbersList.clear()
                    }
                }
            } else {

                while (!diffNumFound) {

                    if(numbersList.size != numbersRangeLimit) {

                        randomInt = Random().nextInt(30) + 1

                        if ((randomInt >= num1) && (randomInt <= num2)) {

                            if (numbersList.size == 0) {

                                diffNumFound = true
                                numbersList.add(randomInt)
                            } else {
                                sameNumFound = false

                                for (i in numbersList)
                                    if (i == randomInt)
                                        sameNumFound = true

                                if (!sameNumFound) {

                                    diffNumFound = true
                                    numbersList.add(randomInt)
                                }
                            }
                        }
                    }else{
                        diffNumFound=true
                        Toast.makeText(getActivity(), "All the repetitive numbers were generated", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            result_text.text = randomInt.toString()

            //else out of range
        }else{
            Toast.makeText(getActivity(), "Wrong numbers Entered!!\n Min: 1, Max: 30", Toast.LENGTH_SHORT).show()
        }
    }
}