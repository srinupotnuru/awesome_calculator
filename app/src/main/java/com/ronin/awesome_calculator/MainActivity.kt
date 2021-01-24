package com.ronin.awesome_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private fun eval(exp:String):String {

        return try {
            val result =  ExpressionBuilder(exp).build().evaluate()
            result.toString()
        }catch (e:IllegalArgumentException) {
            "Illegal Expression"
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var expression = ""
        val solutionText = findViewById<TextView>(R.id.solution)

        for(i in 0..19){
            val id = "button$i"
            val tempButton = findViewById<Button>(resources.getIdentifier(id,"id",packageName))
            tempButton.setOnClickListener {
                (it as Button)
                when(it.text){
                    "AC" -> {
                        expression = ""
                    }
                    "DEL" -> {
                        expression = expression.substring(0,expression.length - 1)
                    }
                    "=" -> {
                        expression = eval(expression)
                    }
                    else -> {
                        expression += it.text
                    }
                }
                solutionText.text = expression
            }
        }
    }
}