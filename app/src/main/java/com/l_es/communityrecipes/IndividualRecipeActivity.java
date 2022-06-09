package com.l_es.communityrecipes;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.l_es.communityrecipes.Adapters.RVRecipeIngredientsAdapter;
import com.l_es.communityrecipes.Adapters.RVRecipePreparationAdapter;
import com.l_es.communityrecipes.Dialogs.RateRecipeDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("FieldCanBeLocal")
public class IndividualRecipeActivity extends AppCompatActivity {

    private TextView textViewHours, textViewMinutes;
    private ImageView imageViewRecipe;
    private final String[] avg_times = new String[2];
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SharedPreferences prefs;
    private Context context;
    private RecyclerView recyclerViewPreparation, recyclerViewIngredients;
    private RVRecipePreparationAdapter rvRecipePreparationAdapter;
    private RVRecipeIngredientsAdapter rvRecipeIngredientsAdapter;
    private List<String> preparations = new ArrayList<>();
    private Map<String, String> ingredients = new HashMap<>();
    private String category_type, recipe_type, recipe_name, recipe_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_recipe);

        context = this;

        prefs = getSharedPreferences(Utilities.SP_RECIPES_TYPE, Context.MODE_PRIVATE);
        category_type  = prefs.getString(Utilities.SP_CATEGORY_TYPE, Utilities.NULL);
        recipe_type  = prefs.getString(Utilities.SP_RECIPES_TYPE, Utilities.NULL);
        recipe_name  = prefs.getString(Utilities.SP_RECIPE_NAME, Utilities.NULL);
        recipe_image  = prefs.getString(Utilities.SP_RECIPE_IMAGE, Utilities.NULL);

        setUniqueToolBar();
        assignIDs();

        getRecipeDataFromDB(category_type, recipe_type, recipe_name);

        if(recipe_image.equalsIgnoreCase(Utilities.NULL)){
            Toast.makeText(context, getResources().getString(R.string.recipe_image_load_fail_msg), Toast.LENGTH_LONG).show();
        }else{
            Glide.with(context).load(Uri.parse(recipe_image)).into(imageViewRecipe);
        }

        setupRecyclerViews();

    }

    public void setupRecyclerViews(){
        rvRecipePreparationAdapter = new RVRecipePreparationAdapter(this, preparations);
        LinearLayoutManager linearLayoutManagerPreparations = new LinearLayoutManager(this);
        recyclerViewPreparation.setLayoutManager(linearLayoutManagerPreparations);
        recyclerViewPreparation.setAdapter(rvRecipePreparationAdapter);

        rvRecipeIngredientsAdapter = new RVRecipeIngredientsAdapter(this, ingredients);
        FlexboxLayoutManager flexboxLayoutManagerIngredients = new FlexboxLayoutManager(this);
        flexboxLayoutManagerIngredients.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManagerIngredients.setJustifyContent(JustifyContent.FLEX_START);
        flexboxLayoutManagerIngredients.setFlexWrap(FlexWrap.WRAP);
        recyclerViewIngredients.setLayoutManager(flexboxLayoutManagerIngredients);
        recyclerViewIngredients.setAdapter(rvRecipeIngredientsAdapter);
    }

    private void assignIDs(){
        textViewHours = findViewById(R.id.text_view_avg_hours);
        textViewMinutes = findViewById(R.id.text_view_avg_minutes);
        imageViewRecipe = findViewById(R.id.imageViewRecipe);
        recyclerViewPreparation = findViewById(R.id.recycler_view_individual_recipe_preparation);
        recyclerViewIngredients = findViewById(R.id.recycler_view_individual_recipe_ingredients);
    }

    /**
     * :avg_times[0]: Hours
     * :avg_times[1]: Minutes
     */
    @SuppressWarnings("unchecked")
    private void getRecipeDataFromDB(String _category_type, String _recipe_type, String _recipe_name) {
        db.collection(Utilities.RECIPES)
                .document(_category_type)
                .collection(_recipe_type)
                .document(_recipe_name)
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        DocumentSnapshot document = task.getResult();
                        String avg_h = document.getString(Utilities.AVG_HOURS);
                        String avg_m = document.getString(Utilities.AVG_MINUTES);
                        preparations =  (List<String>) document.get(Utilities.PREPARATION);
                        ingredients = (Map<String, String>) document.get(Utilities.INGREDIENTS);
                        try {
                            ingredients = Utilities.sortMapByKeyLength(ingredients, 1, -1);
                        }catch (Exception ignored) { }
                        avg_times[0] = avg_h;
                        avg_times[1] = avg_m;
                        textViewHours.setText(avg_times[0]);
                        textViewMinutes.setText(avg_times[1]);
                        try {
                            rvRecipePreparationAdapter = new RVRecipePreparationAdapter(context, preparations);
                            recyclerViewPreparation.setAdapter(rvRecipePreparationAdapter);
                            rvRecipeIngredientsAdapter = new RVRecipeIngredientsAdapter(context, ingredients);
                            recyclerViewIngredients.setAdapter(rvRecipeIngredientsAdapter);
                        }catch (Exception ignored) { }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settings_menu_extra, menu);
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
        Toolbar toolbar = findViewById(R.id.toolbar_individual_recipe);
        toolbar.setTitle(recipe_name);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarAppearance);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utilities.useBungee(this, LoginOrRegisterActivity.class, Utilities.ANIMATION_FADE, false);
    }
}