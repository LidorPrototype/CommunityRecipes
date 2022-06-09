package com.l_es.communityrecipes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.l_es.communityrecipes.Adapters.RVCategoriesAdapter;
import com.l_es.communityrecipes.Dialogs.RateRecipeDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainRecipesCategoriesActivity extends AppCompatActivity {

    private static List<String> category_cuisine = new ArrayList<>();
    private static List<String> category_meal = new ArrayList<>();
    private static List<String> category_occasion = new ArrayList<>();
    private TextView c_1, c_2, c_3;
    private RecyclerView categoriesRecyclerView_1, categoriesRecyclerView_2, categoriesRecyclerView_3;
    private RVCategoriesAdapter rvCategoriesAdapterCuisine, rvCategoriesAdapterMeal, rvCategoriesAdapterOccasion;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_categories_main);

        context = this;

        setUniqueToolBar();
        assignIDs();

        SharedPreferences prefs = getSharedPreferences(Utilities.SP_CATEGORIES, Context.MODE_PRIVATE);
        category_cuisine = new ArrayList<>(prefs.getStringSet(Utilities.CATEGORY_CUISINE, new HashSet<>()));
        category_meal = new ArrayList<>(prefs.getStringSet(Utilities.CATEGORY_MEAL, new HashSet<>()));
        category_occasion = new ArrayList<>(prefs.getStringSet(Utilities.CATEGORY_OCCASION, new HashSet<>()));

        setCategoryLanguageBased();

        rvCategoriesAdapterCuisine = new RVCategoriesAdapter(this, category_cuisine);
        rvCategoriesAdapterMeal = new RVCategoriesAdapter(this, category_meal);
        rvCategoriesAdapterOccasion = new RVCategoriesAdapter(this, category_occasion);

        setOnClicks();
        setupRecyclerViews();

    }

    private void setCategoryLanguageBased() {
        String deviceLanguage = Utilities.getDeviceLanguage();
        switch (deviceLanguage) {
            case Utilities.LANGUAGE_ENGLISH:
                c_1.setText(Utilities.CATEGORY_CUISINE_ENGLISH);
                c_2.setText(Utilities.CATEGORY_MEAL_ENGLISH);
                c_3.setText(Utilities.CATEGORY_OCCASION_ENGLISH);
                break;
            case Utilities.LANGUAGE_HEBREW:
                c_1.setText(Utilities.CATEGORY_CUISINE_HEBREW);
                c_2.setText(Utilities.CATEGORY_MEAL_HEBREW);
                c_3.setText(Utilities.CATEGORY_OCCASION_HEBREW);
                break;
            case Utilities.LANGUAGE_RUSSIAN:
                c_1.setText(Utilities.CATEGORY_CUISINE_RUSSIAN);
                c_2.setText(Utilities.CATEGORY_MEAL_RUSSIAN);
                c_3.setText(Utilities.CATEGORY_OCCASION_RUSSIAN);
                break;
        }
    }

    private void setupRecyclerViews(){
        FlexboxLayoutManager layoutManagerCuisine = new FlexboxLayoutManager(this);
        FlexboxLayoutManager layoutManagerMeal = new FlexboxLayoutManager(this);
        FlexboxLayoutManager layoutManagerOccasion = new FlexboxLayoutManager(this);
        layoutManagerCuisine.setFlexDirection(FlexDirection.ROW);
        layoutManagerMeal.setFlexDirection(FlexDirection.ROW);
        layoutManagerOccasion.setFlexDirection(FlexDirection.ROW);
        layoutManagerCuisine.setJustifyContent(JustifyContent.FLEX_START);
        layoutManagerMeal.setJustifyContent(JustifyContent.FLEX_START);
        layoutManagerOccasion.setJustifyContent(JustifyContent.FLEX_START);
        layoutManagerCuisine.setFlexWrap(FlexWrap.WRAP);
        layoutManagerMeal.setFlexWrap(FlexWrap.WRAP);
        layoutManagerOccasion.setFlexWrap(FlexWrap.WRAP);
        categoriesRecyclerView_1.setLayoutManager(layoutManagerCuisine);
        categoriesRecyclerView_2.setLayoutManager(layoutManagerMeal);
        categoriesRecyclerView_3.setLayoutManager(layoutManagerOccasion);

        categoriesRecyclerView_1.setAdapter(rvCategoriesAdapterCuisine);
        categoriesRecyclerView_2.setAdapter(rvCategoriesAdapterMeal);
        categoriesRecyclerView_3.setAdapter(rvCategoriesAdapterOccasion);
    }

    private void assignIDs(){
        c_1 = findViewById(R.id.category_1);
        c_2 = findViewById(R.id.category_2);
        c_3 = findViewById(R.id.category_3);
        categoriesRecyclerView_1 = findViewById(R.id.recycler_view_category_1);
        categoriesRecyclerView_2 = findViewById(R.id.recycler_view_category_2);
        categoriesRecyclerView_3 = findViewById(R.id.recycler_view_category_3);
    }

    private void setOnClicks(){
        rvCategoriesAdapterCuisine.setOnItemClickListener(position -> move_to_recipes_page(category_cuisine.get(position), Utilities.CATEGORY_CUISINE));
        rvCategoriesAdapterMeal.setOnItemClickListener(position -> move_to_recipes_page(category_meal.get(position), Utilities.CATEGORY_MEAL));
        rvCategoriesAdapterOccasion.setOnItemClickListener(position -> move_to_recipes_page(category_occasion.get(position), Utilities.CATEGORY_OCCASION));

    }

    private void move_to_recipes_page(String _category_language, String _type) {
        SharedPreferences prefs = getSharedPreferences(Utilities.SP_RECIPES_TYPE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Utilities.SP_RECIPES_TYPE, _category_language);
        editor.putString(Utilities.SP_CATEGORY_TYPE, _type);
        editor.apply();
        Utilities.useBungee(this, RecipesActivity.class, Utilities.ANIMATION_FADE, true);
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
        Toolbar toolbar = findViewById(R.id.toolbar_recipes_categories);
        toolbar.setTitle(getResources().getString(R.string.recipes_categories));
        toolbar.setTitleTextAppearance(this, R.style.ToolbarAppearance);
        setSupportActionBar(toolbar);
    }

}































