package com.gliesereum.couplerbusiness.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gliesereum.couplerbusiness.R;
import com.gliesereum.couplerbusiness.data.json.corporation.CorporationResponse;
import com.gliesereum.couplerbusiness.data.json.corporation.CreateCorporationBody;
import com.gliesereum.couplerbusiness.data.network.APIClient;
import com.gliesereum.couplerbusiness.data.network.APIInterface;
import com.gliesereum.couplerbusiness.data.network.CustomCallback;
import com.gliesereum.couplerbusiness.util.FastSave;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.couplerbusiness.util.Constants.ACCESS_TOKEN;
import static com.gliesereum.couplerbusiness.util.Constants.CORPORATION_OBJECT;
import static com.gliesereum.couplerbusiness.util.Constants.REFRESH_CORPORATIION_LIST;

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
    private APIInterface API;
    private CustomCallback customCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_corporation);
        initData();
        initView();
    }

    private void initData() {
        FastSave.init(getApplicationContext());
        API = APIClient.getClient().create(APIInterface.class);
        customCallback = new CustomCallback(this, this);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        createCorporationBtn = findViewById(R.id.editCorporationBtn);
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
            case R.id.editCorporationBtn:
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
        CreateCorporationBody corporationBody = new CreateCorporationBody();
        corporationBody.setName(nameCorporationTextInputEditText.getText().toString());
        corporationBody.setPhone(phoneCorporationTextInputEditText.getText().toString());
        corporationBody.setCountry(countryCorporationTextInputEditText.getText().toString());
        corporationBody.setCity(cityCorporationTextInputEditText.getText().toString());
        corporationBody.setStreet(streetCorporationTextInputEditText.getText().toString());
        corporationBody.setBuildingNumber(buildCorporationTextInputEditText.getText().toString());
        corporationBody.setDescription(descriptionCorporationTextInputEditText.getText().toString());

        API.createCorporation(FastSave.getInstance().getString(ACCESS_TOKEN, ""), corporationBody)
                .enqueue(customCallback.getResponseWithProgress(new CustomCallback.ResponseCallback<CorporationResponse>() {
                    @Override
                    public void onSuccessful(Call<CorporationResponse> call, Response<CorporationResponse> response) {
                        FastSave.getInstance().saveObject(CORPORATION_OBJECT, response.body());
                        FastSave.getInstance().saveBoolean(REFRESH_CORPORATIION_LIST, true);
                        finish();
                    }

                    @Override
                    public void onEmpty(Call<CorporationResponse> call, Response<CorporationResponse> response) {

                    }
                }));







    }
}
