package com.devix.www.multipleselecteditemsinlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String tag = MainActivity.this.getClass().getSimpleName();
    public static final String[] countyName = new String[]{"A", "B", "C", "D", "E"};
    public static final String[] countyCode = new String[]{"01", "02", "03", "04", "05"};

    public static final Integer[] images = {R.drawable.leon, R.drawable.leon, R.drawable.leon, R.drawable.leon, R.drawable.leon,};
    ListView listView;
    List<ListRowModel> listRowItems;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hola Mundo");
        setContentView(R.layout.activity_main);

        listRowItems = new ArrayList<ListRowModel>();
        for (int i = 0; i < countyName.length; i++) {
            ListRowModel item = new ListRowModel(images[i], countyName[i], countyCode[i]);
            listRowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.listView);
        customAdapter = new CustomAdapter(this, R.layout.list_item_row, listRowItems);
        listView.setAdapter(customAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                final int checkedCount = listView.getCheckedItemCount();
                mode.setTitle(checkedCount + " Selected");
                customAdapter.toggleSelection(position);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.delete_menu_option, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        SparseBooleanArray selected = customAdapter.getSelectedIds();
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                ListRowModel selectedListItem = customAdapter.getItem(selected.keyAt(i));
                                customAdapter.remove(selectedListItem);
                            }
                        }
                        mode.finish();
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                customAdapter.removeSelection();
            }
        });
    }
}
