package com.example.miprimercontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miprimercontrol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun esAlfanumerico(texto: String): Boolean {
            return texto.matches(Regex("^[a-zA-Z0-9\\s]+$"))
        }

        fun mostrarDosDecimales(num: Double): String {
            return "S/%.2f".format(num)
        }

        binding.btnCalcular.setOnClickListener {
            binding.txtNombreCliente.error = null

            // Obteniendo valores
            val cliente = binding.txtNombreCliente.editText?.text.toString()
            val producto = binding.txtProducto.editText?.text.toString()

            // Inicializando variables
            var esCliente = ""
            var precio = 0.0
            var descuento = 0.0
            var total = 0.0

            // Validando si la casilla nomCliente esta vacia
            if (cliente.isEmpty()) {
                binding.txtNombreCliente.error = resources.getString(R.string.error_campo_requerido)
                return@setOnClickListener
            }

            // Validando que la casilla nomCliente solo acepte textos alfanumericos
            if (!esAlfanumerico(cliente)) {
                binding.txtNombreCliente.error =
                    resources.getString(R.string.error_campo_alfanumerico)
                return@setOnClickListener
            }

            // Obteniendo precios segun producto
            when (producto) {
                "Producto A" -> precio = 15.0
                "Producto B" -> precio = 20.0
                "Producto C" -> precio = 35.5
                "Producto D" -> precio = 39.9
            }

            // Validando si es cliente
            if (binding.rgOpcion.checkedRadioButtonId == binding.rbSiCliente.id) {
                esCliente = "SI"

                // Realizando calculos
                when (producto) {
                    "Producto A" -> {
                        descuento = precio * 0.10
                        total = precio - descuento
                    }
                    "Producto B" -> {
                        descuento = precio * 0.15
                        total = precio - descuento
                    }
                    "Producto C" -> {
                        descuento = precio * 0.12
                        total = precio - descuento
                    }
                    "Producto D" -> {
                        descuento = precio * 0.13
                        total = precio - descuento
                    }
                }
            } else {
                esCliente = "NO"
                total = precio - descuento
            }

            // Mostrando resultados
            binding.lblResultado.text =
                resources.getString(
                    R.string.resultado_,
                    cliente,
                    producto,
                    esCliente,
                    mostrarDosDecimales(precio),
                    mostrarDosDecimales(descuento),
                    mostrarDosDecimales(total),
                )
        }
    }
}