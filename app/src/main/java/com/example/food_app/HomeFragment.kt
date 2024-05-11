package com.example.food_app


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recipesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Sample data with local drawable resources and URLs for recipes
        val recipes = listOf(
            Recipe("Spaghetti Carbonara", R.drawable.spaghetti_carbonara, "Creamy Italian classic.", "https://www.recipetineats.com/carbonara/"),
            Recipe("Chicken Parmesan", R.drawable.chicken_parmesan, "Deliciously breaded chicken with cheese.", "https://www.allrecipes.com/recipe/223042/chicken-parmesan/"),
            Recipe("Vegetable Stir Fry", R.drawable.vegetable_stir_fry, "Quick and healthy.", "https://therecipecritic.com/vegetable-stir-fry/"),
            Recipe("Beef Stew", R.drawable.beef_stew, "Rich and hearty.", "https://www.spendwithpennies.com/beef-stew-recipe/"),
            Recipe("Cheese Cake", R.drawable.cheese_cake, "Creamy and rich dessert.", "https://sugarspunrun.com/best-cheesecake-recipe/"),
            Recipe("Pancakes", R.drawable.pancakes, "Fluffy and delicious.", "https://www.allrecipes.com/recipe/21014/good-old-fashioned-pancakes/"),
            Recipe("Moussaka", R.drawable.moussaka, "A Greek classic.", "https://www.recipetineats.com/moussaka-greek-eggplant-beef-bake/"),
            Recipe("Peking Duck", R.drawable.peking_duck, "Crispy skin and tender meat.", "https://redhousespice.com/peking-duck/"),
            Recipe("Confit de Canard", R.drawable.confit_de_canard, "Slow-cooked to perfection.", "https://www.recipetineats.com/duck-confit/")
        )

        // Initialize the adapter and pass the context to it
        recipeAdapter = RecipeAdapter(recipes, requireContext())
        recyclerView.adapter = recipeAdapter
    }
}
