package com.practice.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner

    private lateinit var ed1: EditText
    private lateinit var ed2: EditText

    var currencies = arrayOf<String?>("Indian Rupees",
        "US Dollar",
        "Australian Dollar",
        "Canadian Dollar",
        "Euro",
        "UAE Dirham",
        "Kuwaiti Dinar",
        "Bahraini Dinar",
        "Rial",
        "peso",
        "Rand",
        "Swiss Franc",
        "Pound",
        "Japanese Yen",
        "Russian Ruble")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)

        ed1 = findViewById(R.id.ed1)
        ed2 = findViewById(R.id.ed2)

        spinner1.onItemSelectedListener = this
        spinner2.onItemSelectedListener = this

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            currencies)
        val ad2: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            currencies)

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        ad2.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner1.adapter = ad
        spinner2.adapter = ad2


        ed1.doOnTextChanged { _, _, _, _ ->

            if (ed1.isFocused) {
                val amt = if (ed1.text.isEmpty()) 0.0 else ed1.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner1.selectedItem.toString(),
                    spinner2.selectedItem.toString()
                )

                ed2.setText(convertedCurrency.toString())
            }
        }

        ed2.doOnTextChanged { _, _, _, _ ->

            if (ed2.isFocused) {
                val amt = if (ed2.text.isEmpty()) 0.0 else ed2.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner2.selectedItem.toString(),
                    spinner1.selectedItem.toString()
                )

                ed1.setText(convertedCurrency.toString())
            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent!!.id){
            R.id.spinner1 -> {
                val amt = if (ed1.text.isEmpty()) 0.0 else ed1.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner1.selectedItem.toString(),
                    spinner2.selectedItem.toString())
                ed2.setText(convertedCurrency.toString())

            }
            R.id.spinner2 -> {
                val amt = if (ed2.text.isEmpty()) 0.0 else ed2.text.toString().toDouble()
                val convertedCurrency = convertCurrency(
                    amt,
                    spinner2.selectedItem.toString(),
                    spinner1.selectedItem.toString())
                ed1.setText(convertedCurrency.toString())

            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun convertCurrency(amt: Double, firstCurrency: String, secondCurrency: String): Double{
        val indianRupee = convertOtherToIndianCurrency(amt,firstCurrency)
        return convertIndianToOtherCurrency(indianRupee,secondCurrency)
    }

    private fun convertIndianToOtherCurrency(indianRupee: Double, secondCurrency: String): Double {
        return indianRupee * when (secondCurrency){

            "Indian Rupees" -> 1.0
            "US Dollar" -> 0.012
            "Australian Dollar" -> 0.019
            "Canadian Dollar" -> 0.017
            "Euro" -> 0.011
            "UAE Dirham" -> 0.044
            "Kuwaiti Dinar" -> 0.0037
            "Bahraini Dinar" -> 0.0045
            "Saudi Riyal" -> 0.045
            "Rial" -> 3.01
            "peso" -> 0.68
            "Rand" -> 0.23
            "Swiss Franc" -> 0.011
            "Uk Pound" -> 0.0099
            "Japanese Yen" -> 1.60
            "Russian Ruble" -> 0.93
            else -> 0.0

        }
    }

    private fun convertOtherToIndianCurrency(amt: Double, firstCurrency: String): Double {
        return amt * when (firstCurrency){

            "Indian Rupees" -> 1.0
            "US Dollar" -> 83.27
            "Australian Dollar" -> 53.12
            "Canadian Dollar" -> 60.15
            "Euro" -> 88.47
            "UAE Dirham" -> 22.67
            "Kuwaiti Dinar" -> 269.45
            "Bahraini Dinar" -> 220.87
            "Saudi Riyal" ->22.20
            "Rial" -> 0.33
            "peso" -> 1.47
            "Rand" -> 4.44
            "Swiss Franc" -> 92.37
            "UK Pound" -> 101.27
            "Japanese Yen" -> 0.62
            "Russian Ruble" -> 1.07
            else -> 0.0

        }
    }

}