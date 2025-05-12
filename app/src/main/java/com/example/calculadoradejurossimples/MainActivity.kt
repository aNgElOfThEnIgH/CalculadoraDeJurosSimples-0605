package com.example.calculadoradejurossimples

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            val rbtCapital = findViewById<RadioButton>(R.id.rbtCapital)
            val rbtTaxa = findViewById<RadioButton>(R.id.rbtTaxa)
            val rbtPrazo = findViewById<RadioButton>(R.id.rbtPrazo)
            val rbtJuros = findViewById<RadioButton>(R.id.rbtJuros)
            val txtCapital = findViewById<EditText>(R.id.txtCapital)
            val txtTaxa = findViewById<EditText>(R.id.txtTaxa)
            val txtPrazo = findViewById<EditText>(R.id.txtPrazo)
            val txtJuros = findViewById<EditText>(R.id.txtJuros)
            val btnCalcular = findViewById<Button>(R.id.btnCalcular)
            val btnLimpar = findViewById<Button>(R.id.btnLimpar)

            // Marcar "Juros" como padrão
            rbtJuros.isChecked = true

            // Função para atualizar campos
            fun atualizarCampos() {
                txtCapital.isEnabled = rbtCapital.isChecked.not()
                txtTaxa.isEnabled = rbtTaxa.isChecked.not()
                txtPrazo.isEnabled = rbtPrazo.isChecked.not()
                txtJuros.isEnabled = rbtJuros.isChecked.not()
            }

            // Atualizar campos inicialmente
            atualizarCampos()

            // Quando qualquer RadioButton for clicado
            rbtJuros.setOnClickListener { atualizarCampos() }
            rbtCapital.setOnClickListener { atualizarCampos() }
            rbtTaxa.setOnClickListener { atualizarCampos() }
            rbtPrazo.setOnClickListener { atualizarCampos() }

            // Zerbinati daqui pra baixo, a Zerbinati esteve aqui

            btnLimpar.setOnClickListener {
                txtJuros.text.clear()
                txtCapital.text.clear()
                txtTaxa.text.clear()
                txtPrazo.text.clear()
                rbtJuros.isChecked = true
                atualizarCampos()
                txtCapital.requestFocus()
            }
            //Vitinho daqui pra baixo

            btnCalcular.setOnClickListener {
                val cap = txtCapital.text.toString().toDoubleOrNull() ?: 0.00
                val tax = txtTaxa.text.toString().toDoubleOrNull() ?: 0.00
                val prz = txtPrazo.text.toString().toIntOrNull() ?: 0
                val juros = txtJuros.text.toString().toDoubleOrNull() ?: 0.00
                var calcC = 0.00
                var calcI = 0.00
                var calcN = 0
                var calcJ = 0.00
                //Kauã daqui para baixo

                if (rbtJuros.isChecked) {
                    calcJ = (cap * tax * prz) / 100
                    txtJuros.setText(String.format("%.2f", calcJ))
                } else if (rbtCapital.isChecked) {
                    calcC = juros / (tax / 100 * prz)
                    txtCapital.setText(String.format("%.2f", calcC))
                } else if (rbtTaxa.isChecked) {
                    calcI = (juros / (cap * prz)) * 100
                    txtTaxa.setText(String.format("%.2f", calcI))
                } else if (rbtPrazo.isChecked) {
                    calcN = (juros / ((cap * tax) / 100)).toInt()
                    txtPrazo.setText(calcN.toString())
                }
            }
        }
    }
