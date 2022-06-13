package com.l_es.communityrecipes;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.l_es.communityrecipes.Adapters.RVAddRecipeIngredientsAdapter;
import com.l_es.communityrecipes.Adapters.RVAddRecipePreparationAdapter;
import com.l_es.communityrecipes.Dialogs.ImageDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import soup.neumorphism.NeumorphButton;

@SuppressWarnings("FieldCanBeLocal")
public class AddRecipePageActivity extends AppCompatActivity implements ImageDialog.ImageDialogListener {

    private NumberPicker numberPickerHours, numberPickerMinutes;
    private NeumorphButton uploadImageButton, discardButton, submitRecipeButton;
    private static List<String> recipe_preparation_items = new ArrayList<>();
    private static List<String> recipe_ingredients_names = new ArrayList<>();
    private static List<String> recipe_ingredients_amounts = new ArrayList<>();
    private Button buttonEnterIngredient;
    private EditText editTextIngredientName, editTextIngredientAmount;
    private ImageView imageViewRecipeImage;
    private Bitmap imageBitmap;
    private EditText editTextRecipeName;
    private Spinner spinnerCategories;
    private Context context;
    private static List<String> category_cuisine = new ArrayList<>();
    private static List<String> category_meal = new ArrayList<>();
    private static List<String> category_occasion = new ArrayList<>();
    private static List<String> categories = new ArrayList<>();
    private String selected_category_type, general_recipe_type = "";
    private EditText editTextAddPreparation;
    private Button buttonEnterPreparation;
    private AppCompatButton buttonLoginOrRegister;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference imageRef;
    private FirebaseAuth mAuth;
    // RecyclerViews related
    private RecyclerView recyclerViewIngredients, recyclerViewPreparations;
    private RVAddRecipeIngredientsAdapter rvAddRecipeIngredientsAdapter;
    private RVAddRecipePreparationAdapter rvAddRecipePreparationAdapter;
    private LinearLayoutManager linearLayoutManagerIngredients, linearLayoutManagerPreparations;

    private Uri outputFileUri;


    @Override
    protected void onResume() {
        super.onResume();
        updateUI_BasedOnUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_page);

        context = this;

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        mAuth = FirebaseAuth.getInstance();

        setupSharedPreferences();
        setUniqueToolBar();
        assignIDs();
        setupNumberPicker();
        setupRecyclerViews();
        setupOnClicks();
        setupSpinner();
        updateUI_BasedOnUser();

    }

    private void updateUI_BasedOnUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            updateEnabled(false);
            findViewById(R.id.relativelayout_login_or_register_alert).setVisibility(View.VISIBLE);
        }else{
            updateEnabled(true);
            findViewById(R.id.relativelayout_login_or_register_alert).setVisibility(View.GONE);
        }
    }

    private void updateEnabled(boolean enableStat) {
        numberPickerHours.setEnabled(enableStat);
        numberPickerMinutes.setEnabled(enableStat);
        uploadImageButton.setEnabled(enableStat);
        editTextAddPreparation.setEnabled(enableStat);
        buttonEnterPreparation.setEnabled(enableStat);
        discardButton.setEnabled(enableStat);
        submitRecipeButton.setEnabled(enableStat);
        recyclerViewIngredients.setEnabled(enableStat);
        buttonEnterIngredient.setEnabled(enableStat);
        editTextIngredientName.setEnabled(enableStat);
        editTextIngredientAmount.setEnabled(enableStat);
        imageViewRecipeImage.setEnabled(enableStat);
        editTextRecipeName.setEnabled(enableStat);
        spinnerCategories.setEnabled(enableStat);
    }

    private void setupRecyclerViews() {
        recyclerViewIngredients.setHasFixedSize(true);
        linearLayoutManagerIngredients = new LinearLayoutManager(this);
        rvAddRecipeIngredientsAdapter = new RVAddRecipeIngredientsAdapter(this, recipe_ingredients_names, recipe_ingredients_amounts);
        recyclerViewIngredients.setLayoutManager(linearLayoutManagerIngredients);
        recyclerViewIngredients.setAdapter(rvAddRecipeIngredientsAdapter);

        recyclerViewPreparations.setHasFixedSize(true);
        linearLayoutManagerPreparations = new LinearLayoutManager(this);
        rvAddRecipePreparationAdapter = new RVAddRecipePreparationAdapter(this, recipe_preparation_items);
        recyclerViewPreparations.setLayoutManager(linearLayoutManagerPreparations);
        recyclerViewPreparations.setAdapter(rvAddRecipePreparationAdapter);
    }

    protected void setupSharedPreferences(){
        SharedPreferences prefs = context.getSharedPreferences(Utilities.SP_CATEGORIES, Context.MODE_PRIVATE);
        category_cuisine = new ArrayList<>(prefs.getStringSet(Utilities.CATEGORY_CUISINE, new HashSet<>()));
        category_meal = new ArrayList<>(prefs.getStringSet(Utilities.CATEGORY_MEAL, new HashSet<>()));
        category_occasion = new ArrayList<>(prefs.getStringSet(Utilities.CATEGORY_OCCASION, new HashSet<>()));
        List<String> categories_raw = new ArrayList<>();
        categories = new ArrayList<>();
        categories_raw.addAll(category_cuisine);
        categories_raw.addAll(category_meal);
        categories_raw.addAll(category_occasion);
        for (String raw_word : categories_raw) {
            categories.add(Translations.translateCategoryHelper(raw_word, false));
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void addPreparation(String preparation) {
        recipe_preparation_items.add(preparation);
        rvAddRecipePreparationAdapter = new RVAddRecipePreparationAdapter(this, recipe_preparation_items);
        recyclerViewPreparations.setAdapter(rvAddRecipePreparationAdapter);
        rvAddRecipePreparationAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void addIngredient(String ingredient_name, String ingredient_amount) {
        recipe_ingredients_names.add(ingredient_name);
        recipe_ingredients_amounts.add(ingredient_amount);
        rvAddRecipeIngredientsAdapter = new RVAddRecipeIngredientsAdapter(this, recipe_ingredients_names, recipe_ingredients_amounts);
        recyclerViewIngredients.setAdapter(rvAddRecipeIngredientsAdapter);
        rvAddRecipeIngredientsAdapter.notifyDataSetChanged();
    }

    private String checkSubmitVariables() {
        if(editTextRecipeName.getText().toString().isEmpty()){
            return getResources().getString(R.string.validation_warning_recipe_name);
        }else if(numberPickerHours.getValue() == 0 && numberPickerMinutes.getValue() == 0){
            return getResources().getString(R.string.validation_warning_avg_time);
        }else if(recipe_preparation_items.size() == 0){
            return getResources().getString(R.string.validation_warning_preparations);
        }else if(recipe_ingredients_names.size() == 0){
            return getResources().getString(R.string.validation_warning_ingredients);
        }else if(imageBitmap == null){
            imageBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.general_food);
        }
        return "";
    }

    private void setupSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);
        spinnerCategories.setSelection(0);
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d(Utilities.LOG_FLAG, "SELECTED: " + spinnerCategories.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d(Utilities.LOG_FLAG, " NOT SELECTED");
            }
        });
    }

    private void assignIDs() {
        numberPickerHours = findViewById(R.id.number_picker_hours);
        numberPickerMinutes = findViewById(R.id.number_picker_minutes);
        uploadImageButton = findViewById(R.id.button_upload_image);
        editTextAddPreparation = findViewById(R.id.edit_text_preparation);
        buttonEnterPreparation = findViewById(R.id.button_preparation_add);
        discardButton = findViewById(R.id.button_discard);
        submitRecipeButton = findViewById(R.id.button_submit_recipe);
        recyclerViewPreparations = findViewById(R.id.recycler_view_add_recipe_preparations);
        recyclerViewIngredients = findViewById(R.id.recycler_view_add_recipe_ingredients);
        buttonEnterIngredient = findViewById(R.id.button_ingredient_add);
        editTextIngredientName = findViewById(R.id.edit_text_add_ingredient_name);
        editTextIngredientAmount = findViewById(R.id.edit_text_add_ingredient_amount);
        imageViewRecipeImage = findViewById(R.id.image_view_recipe_image);
        editTextRecipeName = findViewById(R.id.edit_text_recipe_name);
        spinnerCategories = findViewById(R.id.spinner_categories);
        buttonLoginOrRegister = findViewById(R.id.button_login_or_register_alert);
    }

    private void setupNumberPicker() {
        numberPickerHours.setMinValue(0);
        numberPickerMinutes.setMinValue(0);
        numberPickerHours.setMaxValue(99);
        numberPickerMinutes.setMaxValue(59);
    }

    private void dispatchTakePictureIntent() {
        File outputFile = null;
        try {
            outputFile = File.createTempFile("tmp", ".jpg", getCacheDir());
        } catch (IOException pE) {
            pE.printStackTrace();
        }
        outputFileUri = Uri.fromFile(outputFile);

        // Camera.
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for(ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

        startActivityForResult(chooserIntent, 42);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 42) {
                Bitmap bmp = null;
                if (data.hasExtra("data")) {
                    Bundle extras = data.getExtras();
                    bmp = (Bitmap) extras.get("data");
                } else {
                    AssetFileDescriptor fd = null;
                    try {
                        fd = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                    } catch (FileNotFoundException pE) {
                        pE.printStackTrace();
                    }
                    bmp = BitmapFactory.decodeFileDescriptor(fd.getFileDescriptor());
                }
                try {
                    FileOutputStream out = new FileOutputStream(new File(outputFileUri.getPath()));
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loadFacePicture(outputFileUri);
            }
        }
    }

    private void loadFacePicture(Uri outputFileUri){
        try {
            imageBitmap = (Bitmap) MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputFileUri);
        } catch (IOException e) {
            imageBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.general_food);
        }
        imageViewRecipeImage.setImageBitmap(imageBitmap);
    }






















    @SuppressWarnings("UnnecessaryReturnStatement")
    private void setupOnClicks() {
        uploadImageButton.setOnClickListener(view -> dispatchTakePictureIntent());
        imageViewRecipeImage.setOnClickListener(view -> openImageDialog());
        buttonEnterPreparation.setOnClickListener(view -> {
            String preparation = editTextAddPreparation.getText().toString();
            if(preparation.length() == 0){
                return;
            }else{
                if (recipe_preparation_items.contains(preparation)){
                    Toast.makeText(context, "This preparation is already added.", Toast.LENGTH_SHORT).show();
                }else{
                    editTextAddPreparation.setText("");
                    addPreparation(preparation);
                }
            }
        });
        buttonEnterIngredient.setOnClickListener(view -> {
            String ingredient_name = editTextIngredientName.getText().toString();
            String ingredient_amount = editTextIngredientAmount.getText().toString();
            if(ingredient_name.length() == 0 || ingredient_amount.length() == 0){
                return;
            }else{
                if (recipe_ingredients_names.contains(ingredient_name)){
                    Toast.makeText(context, "This ingredient is already added.", Toast.LENGTH_SHORT).show();
                }else{
                    editTextIngredientName.setText("");
                    editTextIngredientAmount.setText("");
                    addIngredient(ingredient_name, ingredient_amount);
                }
            }
        });
        discardButton.setOnClickListener(view -> onBackPressed());
        submitRecipeButton.setOnClickListener(view -> {
            String checker_str = checkSubmitVariables();
            if(!checker_str.equals("")){
                Toast.makeText(context, checker_str, Toast.LENGTH_SHORT).show();
            }else{
                String recipe_name = editTextRecipeName.getText().toString();
                String recipe_hours = numberPickerHours.getValue() + "";
                String recipe_minutes = numberPickerMinutes.getValue() + "";
                List<String> recipe_preparations = new ArrayList<>();
                for (int i=0;i<rvAddRecipePreparationAdapter.getItemCount();i++){
                    recipe_preparations.add(rvAddRecipePreparationAdapter.getItemAt(i));
                }
                Map<String, String> recipe_ingredients = rvAddRecipeIngredientsAdapter.getIngredients();
                selected_category_type = Translations.translateCategoryHelper(spinnerCategories.getSelectedItem().toString(), true);
                general_recipe_type = getRecipeType(selected_category_type);
                imageRef = storageRef.child(Utilities.IMAGES_PATH + recipe_name + " _ " + UUID.randomUUID() + ".jpg");
                uploadStartEnd(0);
                uploadRecipeToFirebaseStorage(
                        imageBitmap,
                        recipe_name,
                        recipe_hours,
                        recipe_minutes,
                        recipe_preparations,
                        recipe_ingredients,
                        selected_category_type,
                        general_recipe_type
                );
                uploadStartEnd(1); // TODO
            }
        });
        buttonLoginOrRegister.setOnClickListener(view -> Utilities.useBungee(context, LoginOrRegisterActivity.class, Utilities.ANIMATION_SLIDE_LEFT, true));
    }

    private String getRecipeType(String category_type) {
        String _type_ = "";
        if (category_cuisine.contains(category_type)){
            _type_ = Utilities.CATEGORY_CUISINE;
        }else if (category_meal.contains(category_type)){
            _type_ = Utilities.CATEGORY_MEAL;
        }else if (category_occasion.contains(category_type)){
            _type_ = Utilities.CATEGORY_OCCASION;
        }
        return _type_;
    }

    private void uploadStartEnd(int flag) {
        switch (flag){
            case 0:
                // Upload started, disable everything
                break;
            case 1:
                // Upload finished, enable everything
                break;
        }
    }

    private void uploadRecipeToFirebaseStorage(Bitmap bitmap_, String name_, String hours_,
                                               String minutes_, List<String> preparations_,
                                               Map<String, String> ingredients_,
                                               String category_type_, String recipe_type_) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap_.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask
                .addOnFailureListener(exception -> Toast.makeText(AddRecipePageActivity.this, getResources().getString(R.string.upload_failed), Toast.LENGTH_SHORT).show())
                .addOnSuccessListener(AddRecipePageActivity.this, taskSnapshot -> {
            Toast.makeText(AddRecipePageActivity.this, getResources().getString(R.string.upload_success), Toast.LENGTH_SHORT).show();
            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String downloadUrl = uri.toString();
                uploadRecipeToFirebaseFireStore(downloadUrl, name_, hours_,
                        minutes_, preparations_, ingredients_, category_type_, recipe_type_);
            });
        });
    }

    private void uploadRecipeToFirebaseFireStore(String link_, String name_, String hours_,
                                                 String minutes_, List<String> preparations_,
                                                 Map<String, String> ingredients_,
                                                 String category_type, String recipe_type) {
        /*
         * - > Collection : "Recipes"
         * - - > Document <Category Type> : "Cuisine" || "Meal" || "Occasion"
         * - - - > Collection <Recipe Type> : ""
         * - - - - > Document <Recipe Name> : ""
         * - - - - - > Field <avg_hours> : ""
         * - - - - - > Field <avg_minutes> : ""
         * - - - - - > Field <image_link> : ""
         * - - - - - > Field <ingredients> : Map<String, String>
         * - - - - - > Field <preparation> : List<String>
         */
        Map<String, Object> recipe_data = new HashMap<>();
        recipe_data.put(Utilities.AVG_HOURS, hours_);
        recipe_data.put(Utilities.AVG_MINUTES, minutes_);
        recipe_data.put(Utilities.IMAGE, link_);
        recipe_data.put(Utilities.INGREDIENTS, ingredients_);
        recipe_data.put(Utilities.PREPARATION, preparations_);
        recipe_data.put(Utilities.LIKES, 0);
        recipe_data.put(Utilities.DISLIKES, 0);

        db.collection(Utilities.RECIPES).document(recipe_type)
            .collection(category_type).document(name_)
                .set(recipe_data)
                .addOnSuccessListener(aVoid -> {
                    resetAllFields();
                    Utilities.useBungee(context, MainActivityRecipesCategories.class, Utilities.ANIMATION_ZOOM, true);
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddRecipePageActivity.this, getResources().getString(R.string.recipe_creation_failed), Toast.LENGTH_SHORT).show());
    }

    public void openImageDialog(){
        if(imageBitmap == null){
            return;
        }
        ImageDialog imageDialog = new ImageDialog().newInstance(imageBitmap);
        imageDialog.show(getSupportFragmentManager(), "image dialog");
    }

    @Override
    public void onRetakeImage() { dispatchTakePictureIntent(); }

    public void setUniqueToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar_add_recipe);
        toolbar.setTitle(getResources().getString(R.string.submit_recipe));
        toolbar.setTitleTextAppearance(this, R.style.ToolbarAppearance);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        resetAllFields();
        super.onBackPressed();
        Utilities.useBungee(this, LoginOrRegisterActivity.class, Utilities.ANIMATION_ZOOM, false);
    }

    private void resetAllFields() {
        imageBitmap = null;
        editTextRecipeName.setText("");
        recipe_preparation_items = new ArrayList<>();
        recipe_ingredients_names = new ArrayList<>();
        recipe_ingredients_amounts = new ArrayList<>();
    }

}

















