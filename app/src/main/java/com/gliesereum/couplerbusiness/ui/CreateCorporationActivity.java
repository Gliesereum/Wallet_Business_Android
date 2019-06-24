package com.gliesereum.couplerbusiness.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gliesereum.couplerbusiness.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CreateCorporationActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private MaterialButton createCorporationBtn;
    private MaterialButton cancelCreateCorporationBtn;
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
    private TextView textView;
    private ScrollView scrollView2;
    private TextView textView2;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_corporation);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        createCorporationBtn = findViewById(R.id.createCorporationBtn);
        cancelCreateCorporationBtn = findViewById(R.id.cancelCreateCorporationBtn);
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
        scrollView2 = findViewById(R.id.scrollView2);
        backBtn = findViewById(R.id.backBtn);
        createCorporationBtn.setOnClickListener(this);
        cancelCreateCorporationBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createCorporationBtn:
                createCorporation();
                break;
            case R.id.cancelCreateCorporationBtn:
                onBackPressed();
                break;
            case R.id.backBtn:
                onBackPressed();
                break;
        }

    }

    private void createCorporation() {

    }
}
