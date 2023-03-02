/*
 * MIT License
 *
 * Copyright (c) 2021 Evren Coşkun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.evrencoskun.tableview.sort;

import androidx.annotation.NonNull;

import com.evrencoskun.tableview.IRow;

import java.util.Comparator;
import java.util.List;

/**
 * Created by cedricferry on 14/2/18.
 */

public class RowHeaderForCellSortComparator implements Comparator<List<ISortableModel>> {
    @NonNull
    private final List<ISortableModel> mReferenceList;
    @NonNull
    private final List<IRow<ISortableModel>> mColumnList;
    @NonNull
    private final SortState mSortState;
    @NonNull
    private final RowHeaderSortComparator mRowHeaderSortComparator;

    public RowHeaderForCellSortComparator(@NonNull List<ISortableModel> referenceList,
                                          @NonNull List<IRow<ISortableModel>> columnList,
                                          @NonNull SortState sortState) {
        this.mReferenceList = referenceList;
        this.mColumnList = columnList;
        this.mSortState = sortState;
        this.mRowHeaderSortComparator = new RowHeaderSortComparator(sortState);
    }

    @Override
    public int compare(List<ISortableModel> l1, List<ISortableModel> l2) {
        int colL1 = this.mColumnList.indexOf(l1);
        int colL2 = this.mColumnList.indexOf(l2);
        Object o1 = mReferenceList.get(colL1).getContent(colL1);
        Object o2 = mReferenceList.get(colL2).getContent(colL2);
        if (mSortState == SortState.DESCENDING) {
            return mRowHeaderSortComparator.compareContent(o2, o1);
        } else {
            return mRowHeaderSortComparator.compareContent(o1, o2);
        }
    }
}
