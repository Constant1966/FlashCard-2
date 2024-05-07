package com.example.flashcard_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        val editTextField =findViewById<EditText>(R.id.editTextText)
        val editTextField1 =findViewById<EditText>(R.id.editTextText2)
        val editTextField2 =findViewById<EditText>(R.id.editTextText3)
        val editTextField3 =findViewById<EditText>(R.id.editTextText4)

        val ShowingAnswers = findViewById<ImageView>(R.id.imageView4)
        val SaveAnswers = findViewById<ImageView>(R.id.imageView5)

        ShowingAnswers.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val question = intent.getStringExtra("question")
        val answer = intent.getStringExtra("answer")
        val option1 = intent.getStringExtra("option1")
        val option2 = intent.getStringExtra("option2")

        // Mettre à jour les EditText avec les données existantes
        editTextField.setText(question)
        editTextField1.setText(answer)
        editTextField2.setText(option1)
        editTextField3.setText(option2)



        SaveAnswers.setOnClickListener {
            val question = editTextField.text.toString()
            val answer = editTextField1.text.toString()
            val option1 = editTextField2.text.toString()
            val option2 = editTextField3.text.toString()


            if (question.isBlank() || answer.isBlank() || option1.isBlank() || option2.isBlank()) {
                // Afficher un message d'erreur avec Snackbar si l'un des champs est vide
                Snackbar.make(findViewById(R.id.imageView5), "Veuillez remplir tous les champs", Snackbar.LENGTH_SHORT).show()
            } else {
                // Les champs sont remplis, continuer avec la sauvegarde des données
                Snackbar.make(findViewById(R.id.imageView5), "Card succesful Created", Snackbar.LENGTH_SHORT).show()
                val data = Intent()
                data.putExtra("question", question)
                data.putExtra("answer", answer)
                data.putExtra("option1", option1)
                data.putExtra("option2", option2)
                setResult(Activity.RESULT_OK, data)
                finish()
            }
        }
    }
}