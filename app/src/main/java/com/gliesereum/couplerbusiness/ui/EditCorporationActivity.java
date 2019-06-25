package com.gliesereum.couplerbusiness.ui;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gliesereum.couplerbusiness.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditCorporationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MaterialButton editCorporationBtn;
    private ScrollView scrollView2;
    private TextInputLayout nameCorporationTextInputLayout;
    private TextInputEditText nameCorporationTextInputEditText;
    private TextInputLayout phoneCorporationTextInputLayout;
    private TextInputEditText phoneCorporationTextInputEditText;
    private TextInputLayout countryCorporationTextInputLayout;
    private TextInputEditText countryCorporationTextInputEditText;
    private TextInputLayout cityCorporationTextInputLayout;
    private TextInputEditText cityCorporationTextInputEditText;
    private TextInputLayout streetCorporationTextInputLayout;
    private TextInputEditText streetCorporationTextInputEditText;
    private TextInputLayout buildCorporationTextInputLayout;
    private TextInputEditText buildCorporationTextInputEditText;
    private TextInputLayout descriptionCorporationTextInputLayout;
    private TextInputEditText descriptionCorporationTextInputEditText;
    private ImageButton backBtn;
    private ImageButton editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_corporation);
        initData();
        initView();
    }

    private void initData() {
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        editCorporationBtn = findViewById(R.id.editCorporationBtn);
        scrollView2 = findViewById(R.id.scrollView2);
        nameCorporationTextInputLayout = findViewById(R.id.nameCorporationTextInputLayout);
        nameCorporationTextInputEditText = findViewById(R.id.nameCorporationTextInputEditText);
        phoneCorporationTextInputLayout = findViewById(R.id.phoneCorporationTextInputLayout);
        phoneCorporationTextInputEditText = findViewById(R.id.phoneCorporationTextInputEditText);
        countryCorporationTextInputLayout = findViewById(R.id.countryCorporationTextInputLayout);
        countryCorporationTextInputEditText = findViewById(R.id.countryCorporationTextInputEditText);
        cityCorporationTextInputLayout = findViewById(R.id.cityCorporationTextInputLayout);
        cityCorporationTextInputEditText = findViewById(R.id.cityCorporationTextInputEditText);
        streetCorporationTextInputLayout = findViewById(R.id.streetCorporationTextInputLayout);
        streetCorporationTextInputEditText = findViewById(R.id.streetCorporationTextInputEditText);
        buildCorporationTextInputLayout = findViewById(R.id.buildCorporationTextInputLayout);
        buildCorporationTextInputEditText = findViewById(R.id.buildCorporationTextInputEditText);
        descriptionCorporationTextInputLayout = findViewById(R.id.descriptionCorporationTextInputLayout);
        descriptionCorporationTextInputEditText = findViewById(R.id.descriptionCorporationTextInputEditText);
        backBtn = findViewById(R.id.backBtn);
        editBtn = findViewById(R.id.editBtn);
    }
}
