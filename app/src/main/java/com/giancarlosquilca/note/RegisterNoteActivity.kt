package com.giancarlosquilca.note

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

import com.giancarlosquilca.note.databinding.ActivityRegisterNoteBinding
import com.giancarlosquilca.note.entity.Nota
import com.giancarlosquilca.note.viewModels.MainActivityVM
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class RegisterNoteActivity : AppCompatActivity() {


    private val bottomSheetDialogFragment = BottomSheetDialogFragment()
    // Get the view model
    private val model: MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_note)
        val binding = ActivityRegisterNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // INTENTS
        val bundle = intent.extras
        val id = bundle?.getString("id")
        val descripcion = bundle?.getString("descripcion")
        val title = bundle?.getString("titulo")
        val idColor = bundle?.getString("idColor")

        binding.edtNota.setText(descripcion)
        binding.edTitulo.setText(title)


        // ESTABLECE MARCA SEGUN  COLOR ASIGNADO
        lifecycleScope.launch {
            val rButton: RadioButton? = findViewById(idColor?.toInt() ?: 1)
            rButton?.isChecked=true
        }

        //Insertar nota
        binding.btnBack.setOnClickListener{
            try {
                val radioId = binding.rdgColores.checkedRadioButtonId
                val radioButton: RadioButton = findViewById(radioId)
                lifecycleScope.launch {
                    val myDate = Date()
                    if (id != null) {
                        model.updateNota(
                            Nota(id.toInt(),binding.edTitulo.text.toString() ,binding.edtNota.text.toString(),
                            SimpleDateFormat("dd-MM-yyyy").format(myDate),radioButton.text.toString(),radioId)
                        )
                    }
                    else{
                        model.insertNota(Nota(0,binding.edTitulo.text.toString() ,binding.edtNota.text.toString(),
                            SimpleDateFormat("dd-MM-yyyy").format(myDate),radioButton.text.toString(),radioId))
                    }
                }
            } catch (e: NullPointerException) {}

          finish()
        }

        //Eliminar nota
        binding.btnEliminarNota.setOnClickListener {

            lifecycleScope.launch {

             model.deleteNota(id.toString())
            }
            finish()
        }


        binding.btnOpcionesImagenes.setOnClickListener{
            bottomSheetDialogFragment.show(supportFragmentManager,"BottomSheetDialog")
        }







        //SELECCIONAR OPCIONES IMAGENES
        /*binding.btnOpcionesImagenes.setOnClickListener{
            val btnsheet = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(btnsheet)
            btnsheet.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }*/


    }


}