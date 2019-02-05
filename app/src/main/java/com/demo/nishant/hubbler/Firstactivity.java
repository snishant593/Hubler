package com.demo.nishant.hubbler;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.demo.nishant.hubbler.databinding.ActivityFirstBinding;

import java.util.ArrayList;

import Adapter.Showdata;
import Pojo.UserDetail;

public class Firstactivity extends AppCompatActivity {

    ActivityFirstBinding binding;
    Showdata adapter;
   // ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first);
        binding.setFirstactivity(this);


        binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(0), false));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
      //  arrayList = Singleton.getInstance().getArray();

        adapter = new Showdata(Singleton.getInstance().getArray(),getApplicationContext());
        binding.recyclerView.setAdapter(adapter);

        Log.e("xx", "" + Singleton.getInstance().getArray());

//        Intent intent = getIntent();
//
//        Parcableuserdetail parcableuserdetail = (Parcableuserdetail) intent
//                .getParcelableExtra("userdetail");
//        UserDetail userDetail = parcableuserdetail.getUserDetail();
//        display(userDetail);


    }

    public void onButtonclick() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void display(UserDetail userDetail) {
        String desc = userDetail.getName() + ": " + userDetail.getAddress() + "\n"
                + userDetail.getAge();

        Log.e("Data Transfered", desc);

        // binding.name.setText(userDetail.getName());
        // binding.age.setText(userDetail.getAge());
        // descTxt.setText(desc);
        // imageView.setImageBitmap(laptop.getImageBitmap());
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

        private int dpToPx(int dp) {
            Resources r = getResources();
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
        }
    }




