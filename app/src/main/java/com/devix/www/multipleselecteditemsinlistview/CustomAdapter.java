package com.devix.www.multipleselecteditemsinlistview;
/*
 *Created by Carlos Anguiano on 23/04/17.
 *For more info contact : c.joseanguiano@gmail.com
 */

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<ListRowModel> {

    Context context;
    private SparseBooleanArray selectedListItemsIds;
    List multipleSelectionList;

    public CustomAdapter(Context context, int resourceId, List item) {
        super(context, resourceId, item);
        this.context = context;
        selectedListItemsIds = new SparseBooleanArray();
        this.multipleSelectionList = item;
    }

    private class ViewHolder {
        ImageView imgCountryFlag;
        TextView txtCountryName;
        TextView txtCountryCode;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ListRowModel rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_row, null);
            holder = new ViewHolder();
            holder.txtCountryCode = (TextView) convertView.findViewById(R.id.txtCountryCode);
            holder.txtCountryName = (TextView) convertView.findViewById(R.id.txtCountryName);
            holder.imgCountryFlag = (ImageView) convertView.findViewById(R.id.imgCountryFlag);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        holder.txtCountryCode.setText(rowItem.getCountryCode());
        holder.txtCountryName.setText(rowItem.getCountryName());
        holder.imgCountryFlag.setImageResource(rowItem.getImageId());
        return convertView;
    }

    @Override
    public void remove(ListRowModel object) {
        multipleSelectionList.remove(object);
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !selectedListItemsIds.get(position));
    }

    public void removeSelection() {
        selectedListItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    private void selectView(int position, boolean value) {
        if (value) {
            selectedListItemsIds.put(position, value);
        } else {
            selectedListItemsIds.delete(position);
            notifyDataSetChanged();
        }
    }

    public int getSelectedCount() {
        return selectedListItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return selectedListItemsIds;
    }


}
