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
import com.gliesereum.couplerbusiness.data.json.corporation.CorporationResponse;
import com.gliesereum.couplerbusiness.ui.BusinessActivity;
import com.gliesereum.couplerbusiness.util.FastSave;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.gliesereum.couplerbusiness.util.Constants.CORPORATION_OBJECT;

public class CorporationAdapter extends RecyclerView.Adapter<CorporationAdapter.ViewHolder> {

    private List<CorporationResponse> corporationList = new ArrayList<>();
    private Context context;
    private PowerMenu powerMenu;
    private String corporationStringId;

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
                    powerMenu = new PowerMenu.Builder(context)
                            .addItem(new PowerMenuItem("Редактировать", R.drawable.ic_arrow_back_24dp))
                            .addItem(new PowerMenuItem("Удалить", R.drawable.ic_arrow_back_24dp))
                            .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
                            .setMenuRadius(10f)
                            .setMenuShadow(10f)
                            .setOnMenuItemClickListener(onMenuItemClickListener)
                            .build();
                    powerMenu.showAsDropDown(view);
                    break;
            }
        }
    }

    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {
            Toast.makeText(context, corporationStringId, Toast.LENGTH_SHORT).show();
            powerMenu.setSelectedPosition(position); // change selected item
            powerMenu.dismiss();
        }
    };

//    private OnMenuItemClickListener<IconPowerMenuItem> onIconMenuItemClickListener = new OnMenuItemClickListener<IconPowerMenuItem>() {
//        @Override
//        public void onItemClick(int position, IconPowerMenuItem item) {
//            switch (position) {
//                case 0:
//                    editCorporation(item.getId());
//                    break;
//                case 1:
//                    deleteCorporation(item.getId());
//                    break;
//            }
//        }
//    };

    private void deleteCorporation(String id) {
        Toast.makeText(context, "deleteCorporation", Toast.LENGTH_SHORT).show();
    }

    private void editCorporation(String id) {
        Toast.makeText(context, "editCorporation", Toast.LENGTH_SHORT).show();
    }

    public CorporationAdapter(Context context) {
        this.context = context;
    }

    public CorporationAdapter(List<CorporationResponse> corporationList, Context context) {
        this.corporationList = corporationList;
        this.context = context;
    }
}