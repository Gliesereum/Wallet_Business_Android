package com.gliesereum.coupler_business.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gliesereum.coupler_business.R;
import com.gliesereum.coupler_business.data.network.APIClient;
import com.gliesereum.coupler_business.data.network.APIInterface;
import com.gliesereum.coupler_business.data.network.CustomCallback;
import com.gliesereum.coupler_business.util.FastSave;
import com.gliesereum.coupler_business.util.Util;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private APIInterface API;
    private CustomCallback customCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        new Util(this, toolbar, 1).addNavigation();

    }
}
