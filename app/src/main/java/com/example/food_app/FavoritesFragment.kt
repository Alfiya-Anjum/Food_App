package com.example.food_app

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import java.io.*
import android.content.Context

class FavoritesFragment : Fragment() {
    private lateinit var userNameEditText: EditText
    private lateinit var recipesRadioGroup: RadioGroup
    private lateinit var submitButton: Button
    private lateinit var showSelectionsButton: Button
    private lateinit var selectionsTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        userNameEditText = view.findViewById(R.id.etUserName)
        recipesRadioGroup = view.findViewById(R.id.rgRecipes)
        submitButton = view.findViewById(R.id.btnSubmit)
        showSelectionsButton = view.findViewById(R.id.btnShowSelections)
        selectionsTextView = view.findViewById(R.id.tvSelections)

        val recipes = listOf("Spaghetti Carbonara", "Chicken Parmesan", "Vegetable Stir Fry", "Beef Stew",
            "Confit de Canard", "Peking Duck", "CheeseCake")
        populateRadioGroup(recipes)

        submitButton.setOnClickListener {
            val userName = userNameEditText.text.toString().trim()
            val selectedRadioButtonId = recipesRadioGroup.checkedRadioButtonId
            if (userName.isNotEmpty() && selectedRadioButtonId != -1) {
                val selectedRecipeRadioButton: RadioButton = view.findViewById(selectedRadioButtonId)
                saveRecipeSelection(userName, selectedRecipeRadioButton.text.toString())
            } else {
                Toast.makeText(context, "Please enter your name and select a recipe.", Toast.LENGTH_SHORT).show()
            }
        }

        showSelectionsButton.setOnClickListener {
            selectionsTextView.text = readRecipeSelections()
        }

        return view
    }

    private fun populateRadioGroup(recipes: List<String>) {
        recipes.forEach { recipe ->
            val radioButton = RadioButton(context).apply {
                text = recipe
                id = View.generateViewId()
            }
            recipesRadioGroup.addView(radioButton)
        }
    }

    private fun saveRecipeSelection(userName: String, recipe: String) {
        val selection = "$userName's favorite recipe is $recipe.\n"
        try {
            context?.openFileOutput("recipe_selections.txt", Context.MODE_APPEND)?.use { outputStream ->
                outputStream.write(selection.toByteArray())
            }
            Toast.makeText(context, "Your selection is saved", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "Failed to save your selection", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }


    private fun readRecipeSelections(): String {
        val fileName = "recipe_selections.txt"
        return try {
            context?.openFileInput(fileName)?.bufferedReader().use { it?.readText() } ?: "No selections made yet"
        } catch (e: IOException) {
            "Failed to read selections"
        }
    }
}
