package com.gliesereum.couplerbusiness.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gliesereum.couplerbusiness.R;
import com.gliesereum.couplerbusiness.adapter.BusinessAdapter;
import com.gliesereum.couplerbusiness.data.json.business.BusinessResponse;
import com.gliesereum.couplerbusiness.data.json.corporation.CorporationResponse;
import com.gliesereum.couplerbusiness.data.network.APIClient;
import com.gliesereum.couplerbusiness.data.network.APIInterface;
import com.gliesereum.couplerbusiness.data.network.CustomCallback;
import com.gliesereum.couplerbusiness.util.FastSave;
import com.gliesereum.couplerbusiness.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.couplerbusiness.util.Constants.ACCESS_TOKEN;
import static com.gliesereum.couplerbusiness.util.Constants.CORPORATION_OBJECT;

public class BusinessActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private CorporationResponse corporation;
    private APIInterface API;
    private CustomCallback customCallback;
    private BusinessAdapter businessAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        iniData();
        initView();
        getAllBusiness();
    }

    private void getAllBusiness() {
        API.getAllBusiness(FastSave.getInstance().getString(ACCESS_TOKEN, ""), corporation.getId())
                .enqueue(customCallback.getResponseWithProgress(new CustomCallback.ResponseCallback<List<BusinessResponse>>() {
                    @Override
                    public void onSuccessful(Call<List<BusinessResponse>> call, Response<List<BusinessResponse>> response) {
                        businessAdapter.setItems(response.body());
                    }

                    @Override
                    public void onEmpty(Call<List<BusinessResponse>> call, Response<List<BusinessResponse>> response) {

                    }
                }));
    }

    private void iniData() {
        FastSave.init(getApplicationContext());
        corporation = FastSave.getInstance().getObject(CORPORATION_OBJECT, CorporationResponse.class);
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

        businessAdapter = new BusinessAdapter(BusinessActivity.this);
        recyclerView.setAdapter(businessAdapter);
    }
}
