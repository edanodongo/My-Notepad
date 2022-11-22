package com.notes.mynotepad

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.notes.mynotepadbeta10.R
import java.util.*
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(),
    NoteClickListener,
    PopupMenu.OnMenuItemClickListener,
    SearchView.OnQueryTextListener {

    lateinit var addNoteText: TextView
    lateinit var addFAB: FloatingActionButton
    lateinit var fab: FloatingActionButton
    lateinit var idRVNotes: RecyclerView
    lateinit var viewModel: NoteViewModel

    var noteRVAdapter = NoteRVAdapter(this, this, this, this)
    lateinit var sv: SearchView


    lateinit var selectedNote: Note

    private val rotate_fwrd: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_fwrd) }
    private val rotate_bwrd: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_bwrd) }
    private val fab_open: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.fab_open) }
    private val fab_close: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.fab_close) }

    lateinit var outer: ConstraintLayout

    private var staggered by Delegates.notNull<Boolean>()
    private var isOpen = false

    //lateinit var binding: ActivityMainBinding

    lateinit var toolBar: Toolbar

    //For reviewing
    lateinit var reviewInfo: ReviewInfo
    lateinit var manager: ReviewManager

    private var i by Delegates.notNull<Int>()
    private val lang = Locale.getDefault().language

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContentView(R.layout.activity_main)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        //toolBar.setSupportA

        val actionBar: ActionBar? = supportActionBar;
        actionBar?.title = resources.getString(R.string.app_name)

        toolBar = findViewById(R.id.toolbar)
        //toolBar.setTitle("Search here")
        setSupportActionBar(toolBar)

        //notesRV = findViewById(R.id.idRVNotes)
        addFAB = findViewById(R.id.idFABAddNote)
        addNoteText = findViewById(R.id.addNoteText)
        idRVNotes = findViewById(R.id.idRVNotes)

        staggered = true
        if(!staggered){
            idRVNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            staggered = true
        }else if (staggered){
            idRVNotes.layoutManager = LinearLayoutManager(this)
            staggered = false
        }else if(idRVNotes.layoutManager == null){
            idRVNotes.layoutManager = LinearLayoutManager(this)
            staggered = false
        }else{
            idRVNotes.layoutManager = LinearLayoutManager(this)
            staggered = false
        }


        /*for rating
        activateReviewInfo()*/

        idRVNotes.adapter = noteRVAdapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel ::class.java)
        viewModel.allNotes.observe(this, Observer{ list->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })

        outer = findViewById(R.id.outer)
        fab = findViewById(R.id.fab)
        addNoteText.isClickable = false
        fab.isClickable = false

        addNoteText.setOnClickListener{
            intent.putExtra("noteType", "new")
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        addFAB.setOnClickListener {
            if (isOpen){
                addNoteText.isClickable = false
                fab.isClickable = false
                addFAB.startAnimation(rotate_fwrd)
                addNoteText.startAnimation(fab_close)
                fab.startAnimation(fab_close)
                //addNoteText.startAnimation(fab_open)

                addNoteText.visibility = View.INVISIBLE
                fab.visibility = View.INVISIBLE

                idRVNotes.startAnimation(notOpaq());

                isOpen = false
            }else{
                addNoteText.isClickable = true
                fab.isClickable = true
                addFAB.startAnimation(rotate_bwrd)
                addNoteText.startAnimation(fab_open)
                fab.startAnimation(fab_open)
                //addNoteText.startAnimation(fab_close)

                addNoteText.visibility = View.VISIBLE
                fab.visibility = View.VISIBLE

                idRVNotes.startAnimation(opaq())

                isOpen = true
            }

        }


        /*OpenAlertDialog.setOnClickListener(View.OnClickListener { // AlertDialog builder instance to build the alert dialog
            val alertDialog = AlertDialog.Builder(this@MainActivity)

            // set the custom icon to the alert dialog
            alertDialog.setIcon(R.drawable.image_logo)

            // title of the alert dialog
            alertDialog.setTitle("Choose an Item")

            // list of the items to be displayed to
            // the user in the form of list
            // so that user can select the item from
            val listItems = arrayOf("Android Development", "Web Development", "Machine Learning")

            // the function setSingleChoiceItems is the function which builds
            // the alert dialog with the single item selection
            alertDialog.setSingleChoiceItems(
                listItems, checkedItem[0]
            ) { dialog, which -> // update the selected item which is selected by the user
                // so that it should be selected when user opens the dialog next time
                // and pass the instance to setSingleChoiceItems method
                checkedItem[0] = which

                // now also update the TextView which previews the selected item
                tvSelectedItemPreview.setText("Selected Item is : " + listItems[which])

                // when selected an item the dialog should be closed with the dismiss method
                dialog.dismiss()
            }

            // set the negative button if the user
            // is not interested to select or change
            // already selected item
            alertDialog.setNegativeButton(
                "Cancel"
            ) { dialog, which -> }

            // create and build the AlertDialog instance
            // with the AlertDialog builder instance
            val customAlertDialog = alertDialog.create()

            // show the alert dialog when the button is clicked
            customAlertDialog.show()
        })*/
    }



    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} deleted", Toast.LENGTH_LONG).show()
    }


    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("relativeBG", note.relativeBG)
        intent.putExtra("noteID", note.id)
        //Toast.makeText(this, "" + note.relativeBG, Toast.LENGTH_LONG).show()
        startActivity(intent)
        this.finish()
    }

    override fun onNoteLongClicked(note: Note, cardView: CardView) {
        selectedNote = note
        showPopup(cardView)
    }


    private fun searchDatabase(query: String){
        //val noteRVAdapter = NoteRVAdapter(this, this, this)
        val searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery).observe(this) { list ->
            list.let {
                noteRVAdapter.updateList(it)
            }
        }
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }


    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)

        val search = menu.findItem(R.id.menuSearch)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            //Experimental

            /*R.id.grid -> {
                Toast.makeText(this, "Grid clicked", Toast.LENGTH_LONG).show()
                if(!staggered){
                    idRVNotes.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    staggered = true
                }
                return true
            }R.id.line -> {
                Toast.makeText(this, "Grid clicked", Toast.LENGTH_LONG).show()
                if (staggered){
                    idRVNotes.layoutManager = LinearLayoutManager(this)
                    staggered = false
                }
                return true
            }*/R.id.help -> {
                //Toast.makeText(this, "about clicked", Toast.LENGTH_LONG).show()
                return true
            }R.id.rate -> {
                //Toast.makeText(this, "rate clicked", Toast.LENGTH_LONG).show()
                //val intent = Intent(Intent.ACTION_VIEW)
                //intent.data = Uri.parse("market://details?id=com.test(This is the package name)")
                //startActivity(intent)

                /*if (reviewInfo != null){
                    var flow: Task<Void> = manager.launchReviewFlow(this, reviewInfo)
                    flow.addOnCompleteListener { task ->
                        Toast.makeText(this, "rating is complete", Toast.LENGTH_LONG).show()
                    }
                }

                 */

                return true
            }R.id.share -> {
                //Toast.makeText(this, "share clicked", Toast.LENGTH_LONG).show()
                shareIt();
                return true
            }

            R.id.lang -> {
                Toast.makeText(this, "Change language", Toast.LENGTH_SHORT).show()

                val mBuilder = AlertDialog.Builder(this@MainActivity)

                // title of the alert dialog
                mBuilder.setTitle("Choose language...")

                // list of the items to be displayed to
                // the user in the form of list
                // so that user can select the item from
                val listItems = arrayOf( "Kiswahili", "French", "English")

                i = when (lang) {
                    "sw" -> {
                        0
                    }
                    "fr" -> {
                        1
                    }
                    "en" -> {
                        2
                    }
                    else -> {
                        2
                    }
                }
                // the function setSingleChoiceItems is the function which builds
                // the alert dialog with the single item selection
                mBuilder.setSingleChoiceItems(
                    listItems, i
                ) { dialog, which -> // update the selected item which is selected by the user
                    // so that it should be selected when user opens the dialog next time
                    // and pass the instance to setSingleChoiceItems method

                    when (which) {
                        0 -> {
                            //Kiswahili
                            setLocale("sw")
                            //Toast.makeText(this, "Kiswahili Selected", Toast.LENGTH_LONG).show()
                            restart()
                            recreate()
                        }
                        1 -> {
                            //French
                            setLocale("fr")
                            //Toast.makeText(this, "French Selected", Toast.LENGTH_LONG).show()
                            restart()
                            recreate()
                        }
                        2 -> {
                            //English
                            setLocale("en")
                            //Toast.makeText(this, "English Selected", Toast.LENGTH_LONG).show()
                            restart()
                            recreate()
                        }
                    }
                    dialog.dismiss()
                }

                val mDialog: AlertDialog = mBuilder.create()
                mDialog.show()

                /**https://www.youtube.com/watch?v=zILw5eV9QBQ**/

                return true
            }

            R.id.privacy -> {
                //Toast.makeText(this, "Privacy Policy clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, Privacy::class.java)
                startActivity(intent)
                return true
            }R.id.terms -> {
                //Toast.makeText(this, "Terms of Service clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, Terms::class.java)
                startActivity(intent)
                return true
            }else -> super.onOptionsItemSelected(item)

        }

    }

    private fun setLocale(lang: String) {
        if (lang.equals("", ignoreCase = true)) return
        val myLocale: Locale = Locale(lang)
        Locale.setDefault(myLocale)
        val config = Configuration()
        config.locale = myLocale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }

    //Load language saved in shared preferences
    private fun loadLocale(){
        val prefs = getSharedPreferences("setting", MODE_PRIVATE)
        val language: String = prefs.getString("My_lang", "").toString()
        setLocale(language)
        }

    private fun shareIt() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain";
        val shareBody = "Here is the share content body"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

        /*val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
        )
        sendIntent.type = "text/plain"
        startActivity(sendIntent)*/
    }

    private fun activateReviewInfo() {
        manager = ReviewManagerFactory.create(this)
        var managerInfoTask: Task<ReviewInfo> = manager.requestReviewFlow()
        managerInfoTask.addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                // We got the ReviewInfo object
                reviewInfo = task.result!!
            } else {
                // There was some problem, log or handle the error code.
                Toast.makeText(this, "failed to start", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun opaq(): AlphaAnimation {
        val alphaAnimation = AlphaAnimation(1.0f, 0.3f)
        alphaAnimation.fillAfter = true

        return alphaAnimation
    }

    fun notOpaq(): AlphaAnimation {
        val alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation.fillAfter = true

        return alphaAnimation
    }



    private fun showPopup(cardView: CardView) {
        val popupMenu: PopupMenu = PopupMenu(this, cardView)
        popupMenu.setOnMenuItemClickListener(this)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item != null) {
            return when(item.itemId){
                R.id.pinningNote -> {
                    if (selectedNote.notePinned){
                        viewModel.updateNote(Note(selectedNote.noteTitle,
                            selectedNote.noteDescription,
                            selectedNote.timeStamp ,
                            selectedNote.relativeBG, false))
                        viewModel.updateNote(selectedNote)
                        noteRVAdapter.notifyDataSetChanged()
                        Toast.makeText(this, "Unpinned!", Toast.LENGTH_LONG).show()
                    }else{
                        viewModel.updateNote(Note(selectedNote.noteTitle,
                            selectedNote.noteDescription,
                            selectedNote.timeStamp ,
                            selectedNote.relativeBG, true))
                        viewModel.updateNote(selectedNote)
                        noteRVAdapter.notifyDataSetChanged()
                        Toast.makeText(this, "Pinned!", Toast.LENGTH_LONG).show()
                    }

                    viewModel.allNotes.observe(this, Observer{ list->
                        list?.let {

                            noteRVAdapter.updateList(it)
                        }
                    })
                    return true
                }R.id.deleteNote -> {
                    onDeleteIconClick(selectedNote)
                    return true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    fun showChangeLanguageDialog() {
        val listItems = listOf<String>("English", "Kiswahili")
        //final String[] listItems = {"English", "Kiswahili"};
        val mBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        mBuilder.setTitle("Choose language...")
    }

    fun triggerRestart(context: Context, nextIntent: Intent?) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("KEY_RESTART_INTENT", nextIntent)
        context.startActivity(intent)
        if (context is Activity) {
            (context as Activity).finish()
        }
        Runtime.getRuntime().exit(0)
    }

    private fun restart() {
        val i = Intent(this@MainActivity, MainActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this@MainActivity.startActivity(i)

    }

}