package com.example.conversiones

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val btnCalcular: Button = findViewById(R.id.BtnCalcular)
        val txtQty1: EditText = findViewById(R.id.TxtQty1)
        val txtQty2: EditText = findViewById(R.id.TxtQty2)
        val txtOp: TextView = findViewById(R.id.LblOperation)
        val txtResult: TextView = findViewById(R.id.LblResultado)
        val btnSumar: Button = findViewById(R.id.BtnSumar)
        val btnRestar: Button = findViewById(R.id.BtnRestar)
        val btnMultiplicar: Button = findViewById(R.id.BtnMultiplicacion)
        val btnDividir: Button = findViewById(R.id.BtnDivision)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnCalcular.setOnClickListener {
            val strQty1: String = txtQty1.text.toString()
            val strQty2: String = txtQty2.text.toString()
            val strOp: String = txtOp.text.toString()
            if(isValidInput(strQty1, strQty2, strOp)){
                val qtyQty1: Float = strQty1.toFloat()
                val qtyQty2: Float = strQty2.toFloat()
                val qtyRespuesta: Float = operacion(qtyQty1, qtyQty2, strOp)
                txtResult.text = String.format(Locale.getDefault(), "%f", qtyRespuesta)
            }
        }

        btnSumar.setOnClickListener {
            txtOp.text = "+"
        }

        btnRestar.setOnClickListener {
            txtOp.text = "-"
        }

        btnMultiplicar.setOnClickListener {
            txtOp.text = "x"
        }

        btnDividir.setOnClickListener {
            txtOp.text = "/"
        }
    }

    private fun isValidInput(inputText1: String?, inputText2: String?, operation: String?): Boolean {

        if(inputText1.isNullOrEmpty() || inputText2.isNullOrEmpty() || operation.isNullOrEmpty()){
            val toast = Toast.makeText(
                this,
                "Datos incorrectos",
                Toast.LENGTH_SHORT)
            toast.show()

            return false
        }

        if(operation == "/" && inputText2.toFloat() == 0F){
            val toast = Toast.makeText(
                this,
                "No se puede dividir entre 0",
                Toast.LENGTH_LONG)
            toast.show()
            return false
        }
        return true
    }

    private fun operacion(var1: Float, var2: Float, simbolo: String): Float {
        val result: Float;
        when(simbolo){
            "+" -> result = var1 + var2
            "-" -> result = var1 - var2
            "x" -> result = var1 * var2
            "/" -> result = var1 / var2
            else -> result = var1 - var1
        }
        return result
    }
}