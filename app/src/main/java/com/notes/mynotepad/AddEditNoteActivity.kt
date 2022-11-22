package com.notes.mynotepad

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ImageSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.notes.mynotepadbeta10.R
import kotlinx.android.synthetic.main.activity_add_edit_note.*
import java.text.SimpleDateFormat
import java.util.*
import javax.annotation.Nullable


class AddEditNoteActivity : AppCompatActivity() {

    lateinit var iv_mic: ImageView
    lateinit var tv_Speech_to_text: TextView
    private val REQUEST_CODE_SPEECH_INPUT = 1

    var selectedColor = "#2196F3"

    lateinit var noteTitleEdit: EditText
    lateinit var noteDescriptionEdit: EditText
    //lateinit var addUpdateBtn: Button
    lateinit var viewModel: NoteViewModel
    lateinit var tickUpdate: ImageView
    var noteID = -1

    var relativeBG = 1

    lateinit var fNote1: FrameLayout
    lateinit var fNote2: FrameLayout
    lateinit var fNote3: FrameLayout
    lateinit var fNote4: FrameLayout
    lateinit var fNote5: FrameLayout
    lateinit var fNote6: FrameLayout
    lateinit var fNote7: FrameLayout

    lateinit var mainL: RelativeLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)


        mainL = findViewById(R.id.mainL) //mainL.setBackgroundResource(R.drawable.relative_teal)
        mainL.setBackgroundResource(R.drawable.relative_blueg)

        fNote1 = findViewById(R.id.fNote1)
        fNote2 = findViewById(R.id.fNote2)
        fNote3 = findViewById(R.id.fNote3)
        fNote4 = findViewById(R.id.fNote4)
        fNote5 = findViewById(R.id.fNote5)
        fNote6 = findViewById(R.id.fNote6)
        fNote7 = findViewById(R.id.fNote7)

        noteTitleEdit = findViewById(R.id.idEdtNoteTitle)
        noteDescriptionEdit = findViewById(R.id.idEdtNoteDescription)
        tickUpdate = findViewById(R.id.tickUpdate)
        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        //addUpdateBtn = findViewById(R.id.idBtnAddUpdate)

        val noteType = intent.getStringExtra("noteType")

        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")

            relativeBG = intent.getIntExtra("relativeBG", 1)
            noteID = intent.getIntExtra("noteID", -1)

            //addUpdateBtn.setText("Update Note")
            noteTitleEdit.setText(noteTitle)
            noteDescriptionEdit.setText(noteDesc)

            when (relativeBG) {
                1 -> {
                    mainL.setBackgroundResource(R.drawable.relative_blueg)
                    imgNote1.setImageResource(R.drawable.ic_tick)
                    imgNote2.setImageResource(0)
                    imgNote3.setImageResource(0)
                    imgNote4.setImageResource(0)
                    imgNote5.setImageResource(0)
                    imgNote6.setImageResource(0)
                    imgNote7.setImageResource(0)
                    imgNote8.setImageResource(0)
                }
                2 -> {
                    mainL.setBackgroundResource(R.drawable.relative_blue)
                    imgNote1.setImageResource(0)
                    imgNote2.setImageResource(R.drawable.ic_tick)
                    imgNote3.setImageResource(0)
                    imgNote4.setImageResource(0)
                    imgNote5.setImageResource(0)
                    imgNote6.setImageResource(0)
                    imgNote7.setImageResource(0)
                    imgNote8.setImageResource(0)
                }
                3 -> {
                    mainL.setBackgroundResource(R.drawable.relative_yell)
                    imgNote1.setImageResource(0)
                    imgNote2.setImageResource(0)
                    imgNote3.setImageResource(R.drawable.ic_tick)
                    imgNote4.setImageResource(0)
                    imgNote5.setImageResource(0)
                    imgNote6.setImageResource(0)
                    imgNote7.setImageResource(0)
                    imgNote8.setImageResource(0)
                }
                4 -> {
                    mainL.setBackgroundResource(R.drawable.relative_purp)
                    imgNote1.setImageResource(0)
                    imgNote2.setImageResource(0)
                    imgNote3.setImageResource(0)
                    imgNote4.setImageResource(R.drawable.ic_tick)
                    imgNote5.setImageResource(0)
                    imgNote6.setImageResource(0)
                    imgNote7.setImageResource(0)
                    imgNote8.setImageResource(0)
                }
                5 -> {
                    mainL.setBackgroundResource(R.drawable.relative_green)
                    imgNote1.setImageResource(0)
                    imgNote2.setImageResource(0)
                    imgNote3.setImageResource(0)
                    imgNote4.setImageResource(0)
                    imgNote5.setImageResource(R.drawable.ic_tick)
                    imgNote6.setImageResource(0)
                    imgNote7.setImageResource(0)
                    imgNote8.setImageResource(0)
                }
                6 -> {
                    mainL.setBackgroundResource(R.drawable.relative_teal)
                    imgNote1.setImageResource(0)
                    imgNote2.setImageResource(0)
                    imgNote3.setImageResource(0)
                    imgNote4.setImageResource(0)
                    imgNote5.setImageResource(0)
                    imgNote6.setImageResource(R.drawable.ic_tick)
                    imgNote7.setImageResource(0)
                    imgNote8.setImageResource(0)
                }
                7 -> {
                    mainL.setBackgroundResource(R.drawable.relative_orange)
                    imgNote1.setImageResource(0)
                    imgNote2.setImageResource(0)
                    imgNote3.setImageResource(0)
                    imgNote4.setImageResource(0)
                    imgNote5.setImageResource(0)
                    imgNote6.setImageResource(0)
                    imgNote7.setImageResource(R.drawable.ic_tick)
                    imgNote8.setImageResource(0)
                }
                8 -> {
                    mainL.setBackgroundResource(R.drawable.relative_white)
                    imgNote1.setImageResource(0)
                    imgNote2.setImageResource(0)
                    imgNote3.setImageResource(0)
                    imgNote4.setImageResource(0)
                    imgNote5.setImageResource(0)
                    imgNote6.setImageResource(0)
                    imgNote7.setImageResource(0)
                    imgNote8.setImageResource(R.drawable.ic_tick)
                }
                else -> {
                    mainL.setBackgroundResource(R.drawable.relative_blueg)
                    imgNote1.setImageResource(R.drawable.ic_tick)
                    imgNote2.setImageResource(0)
                    imgNote3.setImageResource(0)
                    imgNote4.setImageResource(0)
                    imgNote5.setImageResource(0)
                    imgNote6.setImageResource(0)
                    imgNote7.setImageResource(0)
                    imgNote8.setImageResource(0)
                }
            }

        }else{
            imgNote1.setImageResource(R.drawable.ic_tick)
            imgNote2.setImageResource(0)
            imgNote3.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            imgNote8.setImageResource(0)
        }

        //Previous save button works well
        /*addUpdateBtn.setOnClickListener {
            val noteTitle = noteTitleEdit.text.toString()
            val noteDescription = noteDescriptionEdit.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDescription, currentDate)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated...", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle, noteDescription, currentDate))
                    Toast.makeText(this, "Note Added...", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }*/

        idEdtNoteDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(e: Editable) {

            }
            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {

            }

            var n = true
            override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, lengthAfter: Int) {

            }
        })

        //half way working
        alignLeft.setOnClickListener {
            idEdtNoteDescription.textAlignment = View.TEXT_ALIGNMENT_TEXT_START

            val spannable = SpannableStringBuilder(idEdtNoteDescription.text)
            idEdtNoteDescription.text = spannable
        }

        //half way working
        alignCenter.setOnClickListener {
            idEdtNoteDescription.textAlignment = View.TEXT_ALIGNMENT_CENTER

            val startSelection: Int = idEdtNoteDescription.selectionStart
            val endSelection: Int = idEdtNoteDescription.selectionEnd
            //val selectedText: String = idEdtNoteDescription.getText().substring(startSelection, endSelection)

            val spannable = SpannableStringBuilder(idEdtNoteDescription.text.substring(startSelection, endSelection))
            idEdtNoteDescription.text = spannable

            idEdtNoteDescription
        }

        //half way working
        alignRight.setOnClickListener {
            idEdtNoteDescription.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            val spannable = SpannableStringBuilder(idEdtNoteDescription.text)
            idEdtNoteDescription.text = spannable
        }

        //half way working
        noFormat.setOnClickListener {
            idEdtNoteDescription.text.toString()

            val spannable = SpannableStringBuilder(idEdtNoteDescription.text)
            idEdtNoteDescription.text = spannable
        }

        //Bold not working
        bold.setOnClickListener {

            /*var toRemoveSpans =
                et.getSpans(0, et.getText().length(), ForegroundColorSpan::class.java)
            for (i in 0 until toRemoveSpans.length) et.removeSpan(toRemoveSpans.get(i))*/




            //val text = Html.fromHtml("This mixes <b>bold</b> and <i>italic</i> stuff")
            //idEdtNoteDescription.setText(text)



            //idEdtNoteDescription.setTypeface(Typeface.create(idEdtNoteDescription.getTypeface(), Typeface.NORMAL), Typeface.NORMAL);

            val bold = SpannableStringBuilder(idEdtNoteDescription.text)
            bold.setSpan(StyleSpan(Typeface.BOLD),
                idEdtNoteDescription.selectionStart,
                idEdtNoteDescription.selectionEnd,
                0)
            idEdtNoteDescription.text = bold
        }

        //Bold not working
        bold.setOnClickListener {
            //Toast.makeText(this, "Bold Clicked", Toast.LENGTH_LONG).show()
            /*var startSelection: Int = idEdtNoteDescription.getSelectionStart()
            var endSelection: Int = idEdtNoteDescription.getSelectionEnd()
            if (startSelection > endSelection) {
                startSelection = idEdtNoteDescription.getSelectionEnd()
                endSelection = idEdtNoteDescription.getSelectionStart()
            }
            val s: Spannable = idEdtNoteDescription.getText()
            s.setSpan(StyleSpan(Typeface.BOLD), startSelection, endSelection, 0)
            var builder = SpannableStringBuilder(s)
            idEdtNoteDescription.setText(builder)
            idEdtNoteDescription.setSelection(startSelection, endSelection)*/


        }

        //Italic not working
        italic.setOnClickListener {
            //Toast.makeText(this, "Italic Clicked",Toast.LENGTH_LONG).show()
            val boldSpan = StyleSpan(Typeface.ITALIC)
            val start: Int = idEdtNoteDescription.selectionStart
            val end: Int = idEdtNoteDescription.selectionEnd
            val flag = Spannable.SPAN_INCLUSIVE_INCLUSIVE
            idEdtNoteDescription.text.setSpan(boldSpan, start, end, flag)
        }

        //Link not working
        link.setOnClickListener {
            //Toast.makeText(this, "Link Clicked",Toast.LENGTH_LONG).show()
            /*var text = text
                if (lengthAfter > lengthBefore) {
                    if (text.toString().length == 1) {
                        text = "◎ $text"
                        idEdtNoteDescription.setText(text)
                        idEdtNoteDescription.setSelection(idEdtNoteDescription.getText().length)
                    }
                    if (text.toString().endsWith("\n")) {
                        text = text.toString().replace("\n", "\n◎ ")
                        text = text.toString().replace("◎ ◎", "◎")
                        idEdtNoteDescription.setText(text)
                        idEdtNoteDescription.setSelection(idEdtNoteDescription.getText().length)
                    }
                }*/
        }

        //AddImage not working
        addImage.setOnClickListener {
            //Toast.makeText(this, "Add Image Clicked",Toast.LENGTH_LONG).show()
            /*if(idEdtNoteDescription.equals(Typeface.ITALIC)){
                val `is` = ImageSpan(ad'#d, resId)
                text.setSpan(`is`, index, index + strLength, 0)*/

                val ssb = SpannableStringBuilder(" Hello world!")

                ssb.setSpan(
                    ImageSpan(applicationContext, R.drawable.ic_tick),
                    0,
                    1,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                idEdtNoteDescription.setText(ssb, TextView.BufferType.SPANNABLE)
        }


        //The color pickers are working well
        fNote1.setOnClickListener {
            mainL.setBackgroundResource(R.drawable.relative_blueg)
            relativeBG = 1
            imgNote1.setImageResource(R.drawable.ic_tick)
            imgNote2.setImageResource(0)
            imgNote3.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            imgNote8.setImageResource(0)
        }

        fNote2.setOnClickListener {
            mainL.setBackgroundResource(R.drawable.relative_blue)
            relativeBG = 2

            imgNote1.setImageResource(0)
            imgNote2.setImageResource(R.drawable.ic_tick)
            imgNote3.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            imgNote8.setImageResource(0)
        }

        fNote3.setOnClickListener {
            mainL.setBackgroundResource(R.drawable.relative_yell)
            relativeBG = 3

            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote3.setImageResource(R.drawable.ic_tick)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            imgNote8.setImageResource(0)
        }

        fNote4.setOnClickListener {
            mainL.setBackgroundResource(R.drawable.relative_purp)
            relativeBG = 4

            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote3.setImageResource(0)
            imgNote4.setImageResource(R.drawable.ic_tick)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            imgNote8.setImageResource(0)
        }

        fNote5.setOnClickListener {
            mainL.setBackgroundResource(R.drawable.relative_green)
            relativeBG = 5

            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote3.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(R.drawable.ic_tick)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            imgNote8.setImageResource(0)
        }

        fNote6.setOnClickListener {
            mainL.setBackgroundResource(R.drawable.relative_teal)
            relativeBG = 6

            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote3.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(R.drawable.ic_tick)
            imgNote7.setImageResource(0)
            imgNote8.setImageResource(0)

        }

        fNote7.setOnClickListener {
            mainL.setBackgroundResource(R.drawable.relative_orange)
            relativeBG = 7

            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote3.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(R.drawable.ic_tick)
            imgNote8.setImageResource(0)
        }

        fNote8.setOnClickListener {
            mainL.setBackgroundResource(R.drawable.relative_white)
            relativeBG = 8
            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote3.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            imgNote8.setImageResource(R.drawable.ic_tick)
        }


        //Save button/tick works well and saves the note
        tickUpdate.setOnClickListener {

            val noteTitle = noteTitleEdit.text.toString()
            val noteDescription = noteDescriptionEdit.text.toString()
            val pin = false

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDescription, currentDate, relativeBG, pin)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated...", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle, noteDescription, currentDate, relativeBG, pin))
                    Toast.makeText(this, "Note Added...", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

        iv_mic = findViewById(R.id.iv_mic)
        tv_Speech_to_text = findViewById(R.id.idEdtNoteDescription)
        //var initial = noteDescriptionEdit.text.toString()
        //tv_Speech_to_text.setSelection(tv_Speech_to_text.getText().length)

        //noteDescriptionEdit.setSelection(noteDescriptionEdit.length())

        iv_mic.setOnClickListener(View.OnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                //Toast.makeText(this@AddEditNoteActivity, " " + e.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val initial = "noteDescriptionEdit.getText().toString()"

        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )

                val oriContent: String = noteDescriptionEdit.text.toString()
                val index = noteDescriptionEdit.length()
                val sBuilder = StringBuilder("$oriContent ")
                sBuilder.insert(index, Objects.requireNonNull(result)!![0])
                tv_Speech_to_text.text = sBuilder.toString()
                //tv_Speech_to_text.text = (index + Objects.requireNonNull(result)!![0].length).toString()

            }
        }

    }


    //When Back action pressed saves the note when triggered
    override fun onBackPressed() {
        super.onBackPressed()

        val noteType = intent.getStringExtra("noteType")
        val noteTitle = noteTitleEdit.text.toString()
        val noteDescription = noteDescriptionEdit.text.toString()
        val pin = false

        if (noteType.equals("Edit")) {

            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                val currentDate: String = sdf.format(Date())
                val updateNote = Note(noteTitle, noteDescription, currentDate, relativeBG, pin)
                updateNote.id = noteID
                viewModel.updateNote(updateNote)
                Toast.makeText(this, "Note Updated...", Toast.LENGTH_LONG).show()
            }
        } else {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {

                val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                val currentDate: String = sdf.format(Date())
                viewModel.addNote(Note(noteTitle, noteDescription, currentDate, relativeBG, pin))
                Toast.makeText(this, "Note Added...", Toast.LENGTH_LONG).show()
            }
        }
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()

    }


}

