package com.gliesereum.couplerbusiness.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.couplerbusiness.util.Constants.ACCESS_TOKEN;
import static com.gliesereum.couplerbusiness.util.Constants.CORPORATION_OBJECT;
import static com.gliesereum.couplerbusiness.util.Constants.DELETE_CORPORATIION_LIST;
import static com.gliesereum.couplerbusiness.util.Constants.REFRESH_CORPORATIION_LIST;
import static com.gliesereum.couplerbusiness.util.Constants.UPDATE_CORPORATIION_LIST;

public class CorporationActivity extends AppCompatActivity implements View.OnClickListener {


    private Toolbar toolbar;
    private APIInterface API;
    private CustomCallback customCallback;
    private RecyclerView recyclerView;
    private CorporationAdapter corporationAdapter;
    private FloatingActionButton createCorporationBtn;
    private TextView label01;
    private TextView label02;
    private ImageView noneImg;
    private TextView label03;
    private MaterialButton noneCreateBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        getAllCorporation();

    }

    private void initData() {
        FastSave.init(getApplicationContext());
        FastSave.getInstance().deleteValue(REFRESH_CORPORATIION_LIST);
        FastSave.getInstance().deleteValue(UPDATE_CORPORATIION_LIST);
        FastSave.getInstance().deleteValue(DELETE_CORPORATIION_LIST);
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

        corporationAdapter = new CorporationAdapter(this, this);
        recyclerView.setAdapter(corporationAdapter);

        createCorporationBtn = findViewById(R.id.createCorporationBtn);
        createCorporationBtn.setOnClickListener(this);
        label01 = findViewById(R.id.label01);
        label02 = findViewById(R.id.label02);
        noneImg = findViewById(R.id.noneImg);
        label03 = findViewById(R.id.label03);
        noneCreateBtn = findViewById(R.id.noneCreateBtn);
        noneCreateBtn.setOnClickListener(this);

    }

    private void getAllCorporation() {
        API.getAllCorporation(FastSave.getInstance().getString(ACCESS_TOKEN, ""))
                .enqueue(customCallback.getResponseWithProgress(new CustomCallback.ResponseCallback<List<CorporationResponse>>() {
                    @Override
                    public void onSuccessful(Call<List<CorporationResponse>> call, Response<List<CorporationResponse>> response) {
                        hideNoneLabel();
                        corporationAdapter.setItems(response.body());
                    }

                    @Override
                    public void onEmpty(Call<List<CorporationResponse>> call, Response<List<CorporationResponse>> response) {
                        showNoneLabel();
                    }
                }));
    }

    public void showNoneLabel() {
        label01.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        createCorporationBtn.hide();
        label02.setVisibility(View.VISIBLE);
        noneImg.setVisibility(View.VISIBLE);
        label03.setVisibility(View.VISIBLE);
        noneCreateBtn.setVisibility(View.VISIBLE);
    }

    private void hideNoneLabel() {
        label01.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        createCorporationBtn.show();
        label02.setVisibility(View.GONE);
        noneImg.setVisibility(View.GONE);
        label03.setVisibility(View.GONE);
        noneCreateBtn.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.createCorporationBtn:
                startActivity(new Intent(CorporationActivity.this, CreateCorporationActivity.class));
                break;
            case R.id.noneCreateBtn:
                startActivity(new Intent(CorporationActivity.this, CreateCorporationActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (FastSave.getInstance().getBoolean(REFRESH_CORPORATIION_LIST, false)) {
            hideNoneLabel();
            corporationAdapter.addItems(FastSave.getInstance().getObject(CORPORATION_OBJECT, CorporationResponse.class));
            FastSave.getInstance().deleteValue(REFRESH_CORPORATIION_LIST);
        }
        if (FastSave.getInstance().getBoolean(UPDATE_CORPORATIION_LIST, false)) {
            corporationAdapter.replaceItems(FastSave.getInstance().getObject(CORPORATION_OBJECT, CorporationResponse.class));
            FastSave.getInstance().deleteValue(UPDATE_CORPORATIION_LIST);
        }
    }

    public CorporationAdapter getCorporationAdapter() {
        return corporationAdapter;
    }
}
