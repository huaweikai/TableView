/*
 * MIT License
 *
 * Copyright (c) 2021 Evren Coşkun, 2023 k3b
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
package com.evrencoskun.tableviewutil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evrencoskun.tableview.model.Cell;
import com.evrencoskun.tableview.model.ColumnDefinition;
import com.evrencoskun.tableview.model.IColumnValueProvider;
import com.evrencoskun.tableview.model.IModelWithId;
import com.evrencoskun.tableview.model.RowHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic TableViewModel that defines RowHeader, ColumnHeader and Cells of the Table.
 */
public class TableViewModel<POJO extends IModelWithId>  {
    private final List<ColumnDefinition<POJO>> columnDefinitions;
    protected final List<POJO> pojos;

    public TableViewModel(List<ColumnDefinition<POJO>> columnDefinitions, List<POJO> pojos) {
        this.columnDefinitions = columnDefinitions;
        this.pojos = pojos;
    }

    @NonNull
    public List<List<Cell<POJO>>> getCellList() {
        return getCellList(null);
    }
    @NonNull
    public List<List<Cell<POJO>>> getCellList(@Nullable String filter) {
        List<List<Cell<POJO>>> list = new ArrayList<>();
        int numberOfColumns = columnDefinitions.size();
        for (POJO pojo : pojos) {
            boolean match = false;
            List<Cell<POJO>> cellList = new ArrayList<>();
            for (int colId = 0; colId < numberOfColumns; colId++) {
                ColumnDefinition<POJO> columnDefinition = columnDefinitions.get(colId);
                Cell<POJO> cell = new Cell(pojo, columnDefinition);
                cellList.add(cell);
                if (!match) match = isMatch(cell.getContent(), filter);
            }

            if (match) {
                list.add(cellList);
            }
        }

        return list;
    }

    protected boolean isMatch(Object content, String filter) {
        if (filter == null || filter.isEmpty()) return true;
        return content.toString().contains(filter);
    }
    @NonNull
    public List<ColumnDefinition<POJO>> getColumnHeaderList() {
        return columnDefinitions;
    }


    @NonNull
    public List<RowHeader> getRowHeaderList() {
        List<RowHeader> list = new ArrayList<>();
        for (POJO pojo : pojos) {
            RowHeader header = new RowHeader(pojo);
            list.add(header);
        }

        return list;
    }

    public List<ColumnDefinition<POJO>> getColumnDefinitions() {
        return columnDefinitions;
    }
}
