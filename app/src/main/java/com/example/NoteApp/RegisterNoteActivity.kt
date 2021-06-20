package com.example.NoteApp

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.NoteApp.databinding.ActivityRegisterNoteBinding
import com.example.NoteApp.entity.Nota
import com.example.NoteApp.viewModels.MainActivityVM
import org.jetbrains.anko.doAsync
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
       doAsync {
           val rButton: RadioButton = findViewById(idColor!!.toInt())
           rButton.isChecked=true

       }



        //Insertar nota
        binding.btnBack.setOnClickListener{
            val radioId = binding.rdgColores.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(radioId)
            doAsync {
                val myDate = Date()
                if (id != null) {
                    model.updateNota(Nota(id.toInt(),binding.edTitulo.text.toString() ,binding.edtNota.text.toString(),
                        SimpleDateFormat("dd-MM-yyyy").format(myDate),radioButton.text.toString(),radioId))
                }
                else{
                    model.insertNota(Nota(0,binding.edTitulo.text.toString() ,binding.edtNota.text.toString(),
                        SimpleDateFormat("dd-MM-yyyy").format(myDate),radioButton.text.toString(),radioId))
                }
            }
          finish()
        }

        //Eliminar nota
        binding.btnEliminarNota.setOnClickListener {

            doAsync {

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