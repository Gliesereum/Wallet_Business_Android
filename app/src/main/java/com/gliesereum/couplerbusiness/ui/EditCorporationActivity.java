package com.gliesereum.couplerbusiness.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gliesereum.couplerbusiness.R;
import com.gliesereum.couplerbusiness.data.json.corporation.CorporationResponse;
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
import static com.gliesereum.couplerbusiness.util.Constants.UPDATE_CORPORATIION_LIST;

public class EditCorporationActivity extends AppCompatActivity implements View.OnClickListener {

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
    private boolean editFlag;
    private APIInterface API;
    private CustomCallback customCallback;
    private CorporationResponse corporation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_corporation);
        initData();
        initView();
        fillFields();
    }

    private void initData() {
        FastSave.init(getApplicationContext());
        corporation = FastSave.getInstance().getObject(CORPORATION_OBJECT, CorporationResponse.class);
        API = APIClient.getClient().create(APIInterface.class);
        customCallback = new CustomCallback(this, this);
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
        backBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);
        editCorporationBtn.setOnClickListener(this);
    }

    private void fillFields() {
        nameCorporationTextInputEditText.setText(corporation.getName());
        phoneCorporationTextInputEditText.setText(corporation.getPhone());
        countryCorporationTextInputEditText.setText(corporation.getCountry());
        cityCorporationTextInputEditText.setText(corporation.getCity());
        streetCorporationTextInputEditText.setText(corporation.getStreet());
        buildCorporationTextInputEditText.setText(corporation.getBuildingNumber());
        descriptionCorporationTextInputEditText.setText(corporation.getDescription());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editBtn:
                if (!editFlag) {
                    editCorporationBtn.setText("Сохранить");
                    nameCorporationTextInputEditText.setEnabled(true);
                    phoneCorporationTextInputEditText.setEnabled(true);
                    countryCorporationTextInputEditText.setEnabled(true);
                    cityCorporationTextInputEditText.setEnabled(true);
                    streetCorporationTextInputEditText.setEnabled(true);
                    buildCorporationTextInputEditText.setEnabled(true);
                    descriptionCorporationTextInputEditText.setEnabled(true);
                    editFlag = true;
                }
                break;
            case R.id.editCorporationBtn:
                if (!editFlag) {
                    editCorporationBtn.setText("Сохранить");
                    nameCorporationTextInputEditText.setEnabled(true);
                    phoneCorporationTextInputEditText.setEnabled(true);
                    countryCorporationTextInputEditText.setEnabled(true);
                    cityCorporationTextInputEditText.setEnabled(true);
                    streetCorporationTextInputEditText.setEnabled(true);
                    buildCorporationTextInputEditText.setEnabled(true);
                    descriptionCorporationTextInputEditText.setEnabled(true);
                    editFlag = true;
                } else {
                    editCorporation();
                }
                break;
            case R.id.backBtn:
                onBackPressed();
                break;
        }

    }

    private void editCorporation() {
        corporation.setName(nameCorporationTextInputEditText.getText().toString());
        corporation.setPhone(phoneCorporationTextInputEditText.getText().toString());
        corporation.setCountry(countryCorporationTextInputEditText.getText().toString());
        corporation.setCity(cityCorporationTextInputEditText.getText().toString());
        corporation.setStreet(streetCorporationTextInputEditText.getText().toString());
        corporation.setBuildingNumber(buildCorporationTextInputEditText.getText().toString());
        corporation.setDescription(descriptionCorporationTextInputEditText.getText().toString());
        API.updateCorporation(FastSave.getInstance().getString(ACCESS_TOKEN, ""), corporation)
                .enqueue(customCallback.getResponseWithProgress(new CustomCallback.ResponseCallback<CorporationResponse>() {
                    @Override
                    public void onSuccessful(Call<CorporationResponse> call, Response<CorporationResponse> response) {
//                        editCorporationBtn.setText("Изменить");
//                        nameCorporationTextInputEditText.setEnabled(false);
//                        phoneCorporationTextInputEditText.setEnabled(false);
//                        countryCorporationTextInputEditText.setEnabled(false);
//                        cityCorporationTextInputEditText.setEnabled(false);
//                        streetCorporationTextInputEditText.setEnabled(false);
//                        buildCorporationTextInputEditText.setEnabled(false);
//                        descriptionCorporationTextInputEditText.setEnabled(false);
//                        editFlag = false;
                        Toast.makeText(EditCorporationActivity.this, "Изменения сохраненны", Toast.LENGTH_SHORT).show();
                        FastSave.getInstance().saveObject(CORPORATION_OBJECT, response.body());
                        FastSave.getInstance().saveBoolean(UPDATE_CORPORATIION_LIST, true);
                        finish();
                    }

                    @Override
                    public void onEmpty(Call<CorporationResponse> call, Response<CorporationResponse> response) {

                    }
                }));
    }
}
