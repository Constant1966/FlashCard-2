package com.example.flashcard_2

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextViews for question and answers
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardReponse = findViewById<TextView>(R.id.flashcard_answer)
        val flashcardReponse2 = findViewById<TextView>(R.id.flashcard_answer_2)
        val flashcardReponse3 = findViewById<TextView>(R.id.flashcard_answer_3)

        // Initialize buttons
        val isShowingAnswers = findViewById<View>(R.id.imageButton3)
        val editButton   = findViewById<View>(R.id.imageButton)

        // Initialize result launcher for starting AddCardActivity
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data: Intent? = result.data
            if (result.resultCode == Activity.RESULT_OK && data != null) {
                val question = data.getStringExtra("question")
                val answer = data.getStringExtra("answer")
                val option1 = data.getStringExtra("option1")
                val option2 = data.getStringExtra("option2")

                // Update TextViews in MainActivity with new data
                flashcardQuestion.text = question
                flashcardReponse.text = answer
                flashcardReponse2.text = option1
                flashcardReponse3.text = option2
            } else {
                Log.i("AddCardActivity", "Save operation cancelled or no data returned")
            }
        }

        // Click listener for showing/hiding the question
        flashcardReponse.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            findViewById<View>(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.my_green, null))
        }

        // Click listener for the second answer option
        flashcardReponse2.setOnClickListener {
            findViewById<View>(R.id.flashcard_answer_2).setBackgroundColor(getResources().getColor(R.color.my_red_color, null))
            findViewById<View>(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.my_green, null))
        }

        // Click listener for the third answer option
        flashcardReponse3.setOnClickListener {
            findViewById<View>(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.my_green, null))
            findViewById<View>(R.id.flashcard_answer_3).setBackgroundColor(getResources().getColor(R.color.my_red_color, null))
        }

        // Click listener for the edit button
        editButton.setOnClickListener {
            val question = findViewById<TextView>(R.id.flashcard_question).text.toString()
            val answer = findViewById<TextView>(R.id.flashcard_answer).text.toString()
            val option1 = findViewById<TextView>(R.id.flashcard_answer_2).text.toString()
            val option2 = findViewById<TextView>(R.id.flashcard_answer_3).text.toString()

            // Start AddCardActivity to edit the flashcard
            val intent = Intent(this, AddCardActivity::class.java)
            intent.putExtra("question", question)
            intent.putExtra("answer", answer)
            intent.putExtra("option1", option1)
            intent.putExtra("option2", option2)
            resultLauncher.launch(intent)
        }

        // Click listener for showing the answers
        isShowingAnswers.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}
