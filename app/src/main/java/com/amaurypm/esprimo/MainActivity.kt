package com.amaurypm.esprimo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.amaurypm.esprimo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.opc_darkmode -> {
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }


    }

    fun clickBoton(view: View) {


        with(binding) {
            if (!etNumero.text.isEmpty()) {
                val numero = etNumero.text.toString().toInt()
                if (esPrimo(numero)) tvResultado.text = "El número $numero sí es primo"
                else tvResultado.text = "El número $numero no es primo"
            } else {
                Toast.makeText(this@MainActivity, "Por favor ingresa un número", Toast.LENGTH_LONG).show()
                etNumero.error = "Se requiere un número"
            }
        }

    }

    fun esPrimo(numero: Int): Boolean{
        if(numero<=1) return false
        else{
            for(i in 2 until numero){
                if(numero % i == 0) return false
            }
        }
        return true
    }
}