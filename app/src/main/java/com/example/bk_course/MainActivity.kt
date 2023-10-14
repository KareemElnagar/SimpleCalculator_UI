package com.example.bk_course

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bk_course.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var historyNum: Double = 0.0
    var currentOperation: Operation? = null

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addCallbacks()

    }

    fun addCallbacks() {
        binding.clearIcon.setOnClickListener {
            clearAll()
        }
        binding.plusIcon.setOnClickListener {
            prepareOperation(Operation.plus)

        }
        binding.minusIcon.setOnClickListener {
            prepareOperation(Operation.minus)
        }
        binding.multiplyIcon.setOnClickListener {
            prepareOperation(Operation.multiply)
        }
        binding.divideIcon.setOnClickListener {
            prepareOperation(Operation.divide)
        }
        binding.modules.setOnClickListener {
            prepareOperation(Operation.modules)
        }

        binding.equalIcon.setOnClickListener {
            val result = doCurrentOperation()
            binding.textNum.text = result.toString()
        }
    }

    private fun doCurrentOperation(): Double {
        val currentNum = binding.textNum.text.toString().toDouble()
        return when (currentOperation) {
            Operation.plus -> historyNum + currentNum
            Operation.minus -> historyNum - currentNum
            Operation.multiply -> historyNum * currentNum
            Operation.divide -> historyNum / currentNum
            Operation.modules -> historyNum % currentNum
            null -> 0.0
        }
    }

    private fun prepareOperation(operation: Operation) {
        historyNum = binding.textNum.text.toString().toDouble()
        clearText()
        currentOperation = operation
    }


    private fun clearText() {
        binding.textNum.text = ""
    }

    private fun clearAll() {
        binding.textNum.text = ""
        historyNum = 0.0
    }

    fun onClickNum(v: View) {
        val newDigit = (v as Button).text.toString()
        val oldDigit = binding.textNum.text.toString()
        val newTextNum = oldDigit + newDigit
        binding.textNum.text = newTextNum


    }
}