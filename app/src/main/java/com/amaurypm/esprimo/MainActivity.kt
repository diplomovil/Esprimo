package com.amaurypm.esprimo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.amaurypm.esprimo.databinding.ActivityMainBinding
import com.amaurypm.esprimo.databinding.SwitchItemBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bindingSwitch: SwitchItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingSwitch = SwitchItemBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        /*binding.btnVisibilidad.setOnClickListener {
            binding.btnVerificar.visibility = View.GONE
            binding.etNumero.visibility = View.INVISIBLE
        }*/


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_toolbar, menu)

        if(menu!=null){
            val item = menu.findItem(R.id.opc_darkmode)
            val mySwitch = item.actionView.findViewById<SwitchMaterial>(R.id.switch_darkmode)

            //bindingSwitch.switchDarkmode es el mismo objeto que mySwitch

            //Si el SO ya está en modo oscuro, cambiamos el switch y la imagen. Si no, solamente la imagen
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                mySwitch.toggle()
                //bindingSwitch.switchDarkmode.toggle()
                binding.clMain.setBackgroundResource(R.drawable.back2dark)
            }else{
                binding.clMain.setBackgroundResource(R.drawable.back2)
            }

            /*bindingSwitch.switchDarkmode.setOnCheckedChangeListener { buttonView, isChecked ->

            }*/

            mySwitch.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

        }

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
                if (esPrimo(numero)) tvResultado.text = resources.getString(R.string.si_primo, numero,"!")
                //if (esPerfecto(numero)) tvResultado.text = resources.getString(R.string.si_primo, numero,"!")
                else tvResultado.text = getString(R.string.no_primo, numero)
            } else {
                Toast.makeText(this@MainActivity, getString(R.string.ingresa_valor), Toast.LENGTH_LONG).show()
                etNumero.error = getString(R.string.valor_requerido)
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

    fun esPerfecto(numero: Int): Boolean{

        var resultado: Int
        var suma = 0

        for (i in 1 until numero) {
            resultado = numero % i
            if (resultado == 0) { //si el resultado da 0 entonces se suma porque es divisor
                suma += i
            }
        }
        return suma == numero
    }
}