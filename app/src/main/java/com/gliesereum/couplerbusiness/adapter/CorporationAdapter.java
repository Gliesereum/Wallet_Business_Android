package com.gliesereum.couplerbusiness.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gliesereum.couplerbusiness.R;
import com.gliesereum.couplerbusiness.data.json.corporation.CorporationDeleteResponse;
import com.gliesereum.couplerbusiness.data.json.corporation.CorporationResponse;
import com.gliesereum.couplerbusiness.data.network.APIClient;
import com.gliesereum.couplerbusiness.data.network.APIInterface;
import com.gliesereum.couplerbusiness.data.network.CustomCallback;
import com.gliesereum.couplerbusiness.ui.BusinessActivity;
import com.gliesereum.couplerbusiness.ui.CorporationActivity;
import com.gliesereum.couplerbusiness.ui.EditCorporationActivity;
import com.gliesereum.couplerbusiness.util.FastSave;
import com.gliesereum.couplerbusiness.util.IconPowerMenuItem;
import com.labters.lottiealertdialoglibrary.ClickListener;
import com.labters.lottiealertdialoglibrary.DialogTypes;
import com.labters.lottiealertdialoglibrary.LottieAlertDialog;
import com.skydoves.powermenu.CustomPowerMenu;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.couplerbusiness.util.Constants.ACCESS_TOKEN;
import static com.gliesereum.couplerbusiness.util.Constants.CORPORATION_OBJECT;

public class CorporationAdapter extends RecyclerView.Adapter<CorporationAdapter.ViewHolder> {

    private List<CorporationResponse> corporationList = new ArrayList<>();
    private Context context;
    private CustomPowerMenu powerMenu;
    private String corporationStringId;
    private LottieAlertDialog alertDialog;
    private APIInterface API;
    private CustomCallback customCallback;
    private CorporationActivity activity;


    @NonNull
    @Override
    public CorporationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.corporation_item, parent, false);
        view.setOnClickListener(v -> {
            CorporationResponse corporationResponse = corporationList.get(corporationList.indexOf(new CorporationResponse(((TextView) v.findViewById(R.id.corporationId)).getText().toString())));
            FastSave.getInstance().saveObject(CORPORATION_OBJECT, corporationResponse);
            context.startActivity(new Intent(context, BusinessActivity.class));


        });
        return new CorporationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CorporationAdapter.ViewHolder holder, int position) {
        holder.bind(corporationList.get(position));
    }

    @Override
    public int getItemCount() {
        return corporationList.size();

    }

    public void setItems(List<CorporationResponse> businessCategory) {
        corporationList.addAll(businessCategory);
        notifyDataSetChanged();
    }

    public void addItems(CorporationResponse businessCategory) {
        corporationList.add(businessCategory);
        notifyDataSetChanged();
    }

    public void replaceItems(CorporationResponse businessCategory) {
        int index = corporationList.indexOf(businessCategory);
        corporationList.remove(index);
        corporationList.add(index, businessCategory);
        notifyDataSetChanged();
    }

    public void deleteItems(CorporationResponse businessCategory) {
        corporationList.remove(businessCategory);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameCorporation;
        private TextView corporationId;
        private ImageButton moreBtn;


        public ViewHolder(View itemView) {
            super(itemView);
            nameCorporation = itemView.findViewById(R.id.nameCorporation);
            corporationId = itemView.findViewById(R.id.corporationId);
            moreBtn = itemView.findViewById(R.id.moreBtn);
        }

        public void bind(CorporationResponse businesCategoryResponse) {
            if (businesCategoryResponse.getName() != null) {
                nameCorporation.setText(businesCategoryResponse.getName());
            } else {
                nameCorporation.setText("");
            }
            if (businesCategoryResponse.getId() != null) {
                corporationId.setText(businesCategoryResponse.getId());
            } else {
                corporationId.setText("");
            }

            moreBtn.setTag(businesCategoryResponse.getId());
            moreBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.moreBtn:
                    corporationStringId = (String) view.getTag();
                    powerMenu = new CustomPowerMenu.Builder<>(context, new IconMenuAdapter())
                            .addItem(new IconPowerMenuItem("Редактировать", corporationStringId, R.drawable.ic_mode_edit_24dp))
                            .addItem(new IconPowerMenuItem("Удалить", corporationStringId, R.drawable.ic_delete_forever_24dp))
                            .setOnMenuItemClickListener(onIconMenuItemClickListener)
                            .setAnimation(MenuAnimation.SHOWUP_TOP_RIGHT)
                            .setMenuRadius(10f)
                            .setAutoDismiss(true)
                            .setMenuShadow(10f)
                            .setWidth(800)
                            .build();
                    powerMenu.showAsDropDown(view);
                    break;
            }
        }
    }

    private OnMenuItemClickListener<IconPowerMenuItem> onIconMenuItemClickListener = new OnMenuItemClickListener<IconPowerMenuItem>() {
        @Override
        public void onItemClick(int position, IconPowerMenuItem item) {
            switch (position) {
                case 0:
                    CorporationResponse corporationResponse = corporationList.get(corporationList.indexOf(new CorporationResponse(item.getId())));
                    FastSave.getInstance().saveObject(CORPORATION_OBJECT, corporationResponse);
                    context.startActivity(new Intent(context, EditCorporationActivity.class));
                    break;
                case 1:
                    openDialog(item.getId());
                    break;
            }
        }
    };

    private void openDialog(String id) {
        alertDialog = new LottieAlertDialog.Builder(context, DialogTypes.TYPE_QUESTION)
                .setTitle("Удаление компании")
                .setDescription("Вы действительно хотите удалить компанию?\n(Эту операцию нельзя отменить)")
                .setPositiveText("Да")
                .setNegativeText("Нет")
                .setPositiveButtonColor(context.getResources().getColor(R.color.accent))
                .setPositiveListener(new ClickListener() {
                    @Override
                    public void onClick(@NotNull LottieAlertDialog lottieAlertDialog) {
                        deleteCorporation(id);
                        alertDialog.dismiss();
                    }
                })
                .setNegativeListener(new ClickListener() {
                    @Override
                    public void onClick(@NotNull LottieAlertDialog lottieAlertDialog) {
                        alertDialog.dismiss();
                    }
                })
                .build();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void deleteCorporation(String id) {
        API.deleteCorporation(FastSave.getInstance().getString(ACCESS_TOKEN, ""), id)
                .enqueue(customCallback.getResponseWithProgress(new CustomCallback.ResponseCallback<CorporationDeleteResponse>() {
                    @Override
                    public void onSuccessful(Call<CorporationDeleteResponse> call, Response<CorporationDeleteResponse> response) {
                        Toast.makeText(context, "Компания удаленя", Toast.LENGTH_SHORT).show();
                        activity.getCorporationAdapter().deleteItems(new CorporationResponse(id));
//                        FastSave.getInstance().saveObject(CORPORATION_OBJECT, new CorporationResponse(id));
//                        FastSave.getInstance().saveBoolean(DELETE_CORPORATIION_LIST, true);
                    }

                    @Override
                    public void onEmpty(Call<CorporationDeleteResponse> call, Response<CorporationDeleteResponse> response) {

                    }
                }));

    }

    public CorporationAdapter(Context context, CorporationActivity activity) {
        this.context = context;
        this.activity = activity;
        FastSave.init(context);
        API = APIClient.getClient().create(APIInterface.class);
        customCallback = new CustomCallback(context, activity);
    }

    public CorporationAdapter(List<CorporationResponse> corporationList, Context context) {
        this.corporationList = corporationList;
        this.context = context;
    }
}
