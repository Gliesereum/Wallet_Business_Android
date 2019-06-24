package com.gliesereum.couplerbusiness.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gliesereum.couplerbusiness.R;
import com.gliesereum.couplerbusiness.data.json.business.BusinessResponse;

import java.util.ArrayList;
import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder> {

    private List<BusinessResponse> businessList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public BusinessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.corporation_item, parent, false);
        view.setOnClickListener(v -> {
//            BusinessResponse businessResponse = businessList.get(businessList.indexOf(new BusinessResponse(((TextView) v.findViewById(R.id.corporationId)).getText().toString())));
//            FastSave.getInstance().saveObject(CORPORATION_OBJECT, businessResponse);
//            context.startActivity(new Intent(context, BusinessActivity.class));


        });
        return new BusinessAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessAdapter.ViewHolder holder, int position) {
        holder.bind(businessList.get(position));
    }

    @Override
    public int getItemCount() {
        return businessList.size();

    }

    public void setItems(List<BusinessResponse> businessCategory) {
        businessList.addAll(businessCategory);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCorporation;
        private TextView addressCorporation;
        private TextView corporationId;


        public ViewHolder(View itemView) {
            super(itemView);
            nameCorporation = itemView.findViewById(R.id.nameCorporation);
            corporationId = itemView.findViewById(R.id.corporationId);
        }

        public void bind(BusinessResponse businessResponse) {
            if (businessResponse.getName() != null) {
                nameCorporation.setText(businessResponse.getName());
            } else {
                nameCorporation.setText("");
            }
            if (businessResponse.getAddress() != null) {
                addressCorporation.setText(businessResponse.getAddress());
            } else {
                addressCorporation.setText("");
            }
            if (businessResponse.getId() != null) {
                corporationId.setText(businessResponse.getId());
            } else {
                corporationId.setText("");
            }
        }
    }

    public BusinessAdapter(Context context) {
        this.context = context;
    }

    public BusinessAdapter(List<BusinessResponse> businessList, Context context) {
        this.businessList = businessList;
        this.context = context;
    }
}
