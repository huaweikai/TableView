package com.evrencoskun.tableview.adapter.recyclerview;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evrencoskun.tableview.ITableView;
import com.evrencoskun.tableview.adapter.recyclerview.CellRowRecyclerViewAdapter;
import com.evrencoskun.tableview.sort.ISortableModel;

public class CellRowPositionRecyclerViewAdapter<C extends ISortableModel> extends CellRowRecyclerViewAdapter<C> {

    private final RecyclerView.ViewHolder rowViewHolder;

    public CellRowPositionRecyclerViewAdapter(
            RecyclerView.ViewHolder rowViewHolder,
            @NonNull Context context,
            @NonNull ITableView tableView
    ) {
        super(context, tableView);
        this.rowViewHolder = rowViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        try {
            return mTableAdapter.getCellItemViewType(position, rowViewHolder.getBindingAdapterPosition());
        } catch (Exception e) {
            e.printStackTrace();
            return super.getItemViewType(position);
        }
    }
}
