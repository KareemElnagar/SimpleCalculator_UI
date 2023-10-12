package com.example.bk_course

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var plusIcon: Button
    lateinit var minusIcon: Button
    lateinit var divIcon: Button
    lateinit var multiplyIcon: Button
    lateinit var resultIcon: Button
    lateinit var textNumber: TextView
    lateinit var clearIcon: Button
    lateinit var modulesIcon: Button


    var historyNum: Double = 0.0
    var currentOperation: Operation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        addCallbacks()

    }

    fun addCallbacks() {
        clearIcon.setOnClickListener {
            clearAll()
        }
        plusIcon.setOnClickListener {
            prepareOperation(Operation.plus)

        }
        minusIcon.setOnClickListener {
            prepareOperation(Operation.minus)
        }
        multiplyIcon.setOnClickListener {
            prepareOperation(Operation.multiply)
        }
        divIcon.setOnClickListener {
            prepareOperation(Operation.divide)
        }
        modulesIcon.setOnClickListener {
            prepareOperation(Operation.modules)
        }

        resultIcon.setOnClickListener {
            val result = doCurrentOperation()
            textNumber.text = result.toString()
        }
    }

    private fun doCurrentOperation(): Double {
        val currentNum = textNumber.text.toString().toDouble()
        return when (currentOperation) {
            Operation.plus -> historyNum + currentNum
            Operation.minus -> historyNum - currentNum
            Operation.multiply -> historyNum * currentNum
            Operation.divide -> historyNum / currentNum
            Operation.modules -> historyNum % currentNum
            null -> 0.0
        }
    }

    private fun initView() {
        plusIcon = findViewById(R.id.plusIcon)
        minusIcon = findViewById(R.id.minusIcon)
        divIcon = findViewById(R.id.divideIcon)
        multiplyIcon = findViewById(R.id.multiplyIcon)
        resultIcon = findViewById(R.id.equalIcon)
        textNumber = findViewById(R.id.text_num)
        clearIcon = findViewById(R.id.clearIcon)
        modulesIcon = findViewById(R.id.modules)
    }


    private fun prepareOperation(operation: Operation) {
        historyNum = textNumber.text.toString().toDouble()
        clearText()
        currentOperation = operation
    }


    private fun clearText() {
        textNumber.text = ""
    }

    private fun clearAll() {
        textNumber.text = ""
        historyNum = 0.0
    }

    fun onClickNum(v: View) {
        val newDigit = (v as Button).text.toString()
        val oldDigit = textNumber.text.toString()
        val newTextNum = oldDigit + newDigit
        textNumber.text = newTextNum


    }
}