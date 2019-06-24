package com.gliesereum.couplerbusiness.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gliesereum.couplerbusiness.R;
import com.gliesereum.couplerbusiness.adapter.CorporationAdapter;
import com.gliesereum.couplerbusiness.data.json.corporation.CorporationResponse;
import com.gliesereum.couplerbusiness.data.network.APIClient;
import com.gliesereum.couplerbusiness.data.network.APIInterface;
import com.gliesereum.couplerbusiness.data.network.CustomCallback;
import com.gliesereum.couplerbusiness.util.FastSave;
import com.gliesereum.couplerbusiness.util.Util;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.couplerbusiness.util.Constants.ACCESS_TOKEN;

public class CorporationActivity extends AppCompatActivity implements View.OnClickListener {


    private Toolbar toolbar;
    private APIInterface API;
    private CustomCallback customCallback;
    private RecyclerView recyclerView;
    private CorporationAdapter corporationAdapter;
    private MaterialButton createCorporationBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        getBusinessCategory();

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
        new Util(this, toolbar, 1).addNavigation();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        corporationAdapter = new CorporationAdapter(CorporationActivity.this);
        recyclerView.setAdapter(corporationAdapter);

        createCorporationBtn = findViewById(R.id.createCorporationBtn);
        createCorporationBtn.setOnClickListener(this);
    }

    private void getBusinessCategory() {
        API.getAllCorporation(FastSave.getInstance().getString(ACCESS_TOKEN, ""))
                .enqueue(customCallback.getResponseWithProgress(new CustomCallback.ResponseCallback<List<CorporationResponse>>() {
                    @Override
                    public void onSuccessful(Call<List<CorporationResponse>> call, Response<List<CorporationResponse>> response) {
                        corporationAdapter.setItems(response.body());
                    }

                    @Override
                    public void onEmpty(Call<List<CorporationResponse>> call, Response<List<CorporationResponse>> response) {

                    }
                }));
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.createCorporationBtn:
                startActivity(new Intent(CorporationActivity.this, CreateCorporationActivity.class));
                break;
        }
    }
}
