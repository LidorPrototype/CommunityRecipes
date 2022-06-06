package com.l_es.communityrecipes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.l_es.communityrecipes.Adapters.RVRecipesAdapter;
import com.l_es.communityrecipes.Dialogs.RateRecipeDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("FieldCanBeLocal")
public class RecipesActivity extends AppCompatActivity {

    private static List<String> categoryRecipesNames = new ArrayList<>(), categoryRecipesImages = new ArrayList<>();
    private RecyclerView recipesRecyclerView;
    private RVRecipesAdapter rvRecipesAdapter;
    private TextView textViewError;
    private FirebaseFirestore db;
    private SharedPreferences prefs;
    private Editor editor;
    private String recipe_type;
    private String category_type;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        context = this;
        db = FirebaseFirestore.getInstance();

        prefs = getSharedPreferences(Utilities.SP_RECIPES_TYPE, Context.MODE_PRIVATE);
        editor = prefs.edit();
        category_type = prefs.getString(Utilities.SP_CATEGORY_TYPE, Utilities.NULL);
        recipe_type = prefs.getString(Utilities.SP_RECIPES_TYPE, Utilities.NULL);

        setUniqueToolBar();
        assignIDs();
        fillRecipesDataFireStore(category_type, recipe_type);
        setupRecyclerViews();

    }

    public void setupRecyclerViews(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getWindow().getContext(), 2);
        recipesRecyclerView.setLayoutManager(gridLayoutManager);

        rvRecipesAdapter = new RVRecipesAdapter(this, categoryRecipesNames, categoryRecipesImages);

        rvRecipesAdapter.setOnItemClickListener(position -> {
            /*
             * In order to get to the recipe attributes, this is the path:
             * 1) Recipes       ->  Utilities.RECIPES
             * 2) Category Type ->  category_type
             * 3) Recipe Type   ->  recipe_type
             * 4) Recipe Name   ->  recipe_name
             * 5) Recipe Image  ->  ???
             */
            String recipe_name = categoryRecipesNames.get(position);
            String recipe_image;
            try {
                recipe_image = categoryRecipesImages.get(position);
            }catch (Exception e){
                recipe_image = Utilities.NULL;
            }
            editor.putString(Utilities.SP_CATEGORY_TYPE, category_type);
            editor.putString(Utilities.SP_RECIPES_TYPE, recipe_type);
            editor.putString(Utilities.SP_RECIPE_NAME, recipe_name);
            editor.putString(Utilities.SP_RECIPE_IMAGE, recipe_image);
            editor.apply();
            Utilities.useBungee(context, IndividualRecipeActivity.class, Utilities.ANIMATION_FADE, true);
        });

        recipesRecyclerView.setAdapter(rvRecipesAdapter);
    }

    private void assignIDs(){
        textViewError = findViewById(R.id.textViewError);
        recipesRecyclerView = findViewById(R.id.recycler_view_category_recipes);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fillRecipesDataFireStore(String _category_type, String _recipe_type) {
        categoryRecipesNames = new ArrayList<>();
        categoryRecipesImages = new ArrayList<>();
        // Get Recipes list from FireBase
        db.collection(Utilities.RECIPES)
                .document(_category_type)
                .collection(_recipe_type)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String _id = document.getId();
                            String _image_link;
                            try{
                                _image_link = document.getString(Utilities.IMAGE);
                            }catch (Exception e){
                                _image_link = Utilities.NULL;
                            }
                            categoryRecipesNames.add(_id);
                            categoryRecipesImages.add(_image_link);
                        }
                        rvRecipesAdapter.notifyDataSetChanged();
                    }
                    else{
                        String err_msg = getResources().getString(R.string._error_) + task;
                        textViewError.setText(err_msg);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.rate_recipe){
            RateRecipeDialog rateRecipeDialog = new RateRecipeDialog();
            rateRecipeDialog.show(getSupportFragmentManager(), "rate recipe dialog");
            return true;
        }else if(item.getItemId() == R.id.item_add_recipe){
            Utilities.useBungee(context, AddRecipePageActivity.class, Utilities.ANIMATION_ZOOM, true);
            return true;
        }else if (item.getItemId() == R.id.item_settings){
            Utilities.useBungee(context, SettingsActivity.class, Utilities.ANIMATION_FADE, true);
            return true;
        }else if (item.getItemId() == R.id.item_login_or_register){
            Utilities.useBungee(context, LoginOrRegisterActivity.class, Utilities.ANIMATION_SLIDE_LEFT, true);
            return true;
        }else if (item.getItemId() == R.id.item_about){
            Utilities.createAboutDialog(this);
            return true;
        }else if (item.getItemId() == R.id.item_exit){
            this.finishAffinity();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void setUniqueToolBar(){
        String test = Utilities.getLanguageBasedCategory(category_type) + " : " + Translations.translateFromEnglishCategory(recipe_type);
        Toolbar toolbar = findViewById(R.id.toolbar_recipes);
        toolbar.setTitle(test);
        setSupportActionBar(toolbar);
        try {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException ignored){ }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utilities.useBungee(this, LoginOrRegisterActivity.class, Utilities.ANIMATION_FADE, false);
    }

}


























