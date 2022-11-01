package com.dmt.dangtus.learnandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dmt.dangtus.learnandroid.adapter.ComputerAdapter;
import com.dmt.dangtus.learnandroid.model.Category;
import com.dmt.dangtus.learnandroid.model.Computer;
import com.dmt.dangtus.learnandroid.sqlite.DanhMucDao;
import com.dmt.dangtus.learnandroid.sqlite.MayTinhDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvComputer;
    private MayTinhDao mayTinhDao;
    private ComputerAdapter computerAdapter;
    private Button btnThem;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String> categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        getItemListView();

        getItemCategory();
    }

    private void getItemCategory() {
        DanhMucDao danhMucDao = new DanhMucDao(this);
        List<Category> categoryList = danhMucDao.getAll();

        // lay ten danh muc dua vao combobox
        List<String> tenDanhMucList = new ArrayList<>();
        for (Category category: categoryList) {
            tenDanhMucList.add(category.getName());
        }

        categoryAdapter = new ArrayAdapter<String>(this, R.layout.item_combobox, tenDanhMucList);
        autoCompleteTextView.setAdapter(categoryAdapter);

        //bat su kien khi chon
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getItemListView() {
        mayTinhDao = new MayTinhDao(this);
        List<Computer> computerList = mayTinhDao.getAll();

        computerAdapter = new ComputerAdapter(this, R.layout.item_computer, computerList);
        lvComputer.setAdapter(computerAdapter);
    }

    private void anhXa() {
        lvComputer = findViewById(R.id.computerListView);
        btnThem = findViewById(R.id.themMoiButton);
        autoCompleteTextView = findViewById(R.id.danhMucCombobox);
    }
}