package com.gliesereum.couplerbusiness.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gliesereum.couplerbusiness.R;
import com.gliesereum.couplerbusiness.util.IconPowerMenuItem;
import com.skydoves.powermenu.MenuBaseAdapter;

public class IconMenuAdapter extends MenuBaseAdapter<IconPowerMenuItem> {

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_icon_menu, viewGroup, false);
        }

        IconPowerMenuItem item = (IconPowerMenuItem) getItem(index);
        TextView titlteTextView = view.findViewById(R.id.titlteTextView);
        ImageView iconImg = view.findViewById(R.id.iconImg);

        titlteTextView.setText(item.getTitle());
        iconImg.setImageResource(item.getIc_logo());

        return super.getView(index, view, viewGroup);
    }

}
