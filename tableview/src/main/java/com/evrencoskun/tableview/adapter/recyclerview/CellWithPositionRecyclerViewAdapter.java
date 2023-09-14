package com.evrencoskun.tableview.adapter.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evrencoskun.tableview.ITableView;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.evrencoskun.tableview.layoutmanager.ColumnLayoutManager;
import com.evrencoskun.tableview.listener.itemclick.CellRecyclerViewItemClickListener;

import java.util.List;

public class CellWithPositionRecyclerViewAdapter<C> extends CellRecyclerViewAdapter<C>{


    public CellWithPositionRecyclerViewAdapter(@NonNull Context context, @Nullable List<List<C>> itemList, @NonNull ITableView tableView) {
        super(context, itemList, tableView);
    }

    @NonNull
    @Override
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a RecyclerView as a Row of the CellRecyclerView
        CellRecyclerView recyclerView = new CellRecyclerView(mContext);

        // Use the same view pool
        recyclerView.setRecycledViewPool(mRecycledViewPool);

        if (mTableView.isShowHorizontalSeparators()) {
            // Add divider
            recyclerView.addItemDecoration(mTableView.getHorizontalItemDecoration());
        }

        // To get better performance for fixed size TableView
        recyclerView.setHasFixedSize(mTableView.hasFixedWidth());

        // set touch mHorizontalListener to scroll synchronously
        recyclerView.addOnItemTouchListener(mTableView.getHorizontalRecyclerViewListener());

        // Add Item click listener for cell views
        if (mTableView.isAllowClickInsideCell()) {
            recyclerView.addOnItemTouchListener(new CellRecyclerViewItemClickListener(recyclerView,
                    mTableView));
        }

        // Set the Column layout manager that helps the fit width of the cell and column header
        // and it also helps to locate the scroll position of the horizontal recyclerView
        // which is row recyclerView
        ColumnLayoutManager mColumnLayoutManager = new ColumnLayoutManager(mContext, mTableView);
        if (mTableView.getReverseLayout()) mColumnLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mColumnLayoutManager);
        CellRowViewHolder holder = new CellRowViewHolder(recyclerView);

        // This is for testing purpose to find out which recyclerView is displayed.
        recyclerView.setId(mRecyclerViewId);

        mRecyclerViewId++;
        // Create CellRow adapter
        recyclerView.setAdapter(new CellRowPositionRecyclerViewAdapter<>(holder, mContext, mTableView));


        return holder;
    }
}
