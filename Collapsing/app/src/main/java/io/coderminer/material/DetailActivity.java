package io.coderminer.material;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public  static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);

        int pos = getIntent().getIntExtra(EXTRA_POSITION,0);
        Resources resources = getResources();
        String[] places = resources.getStringArray(R.array.places);
        collapsingToolbarLayout.setTitle(places[pos % places.length]);

        String[] details = resources.getStringArray(R.array.place_details);
        TextView detailText = (TextView)findViewById(R.id.place_detail);
        detailText.setText(details[pos % details.length]);

        String[] location = resources.getStringArray(R.array.place_locations);
        TextView locationText = (TextView)findViewById(R.id.location);
        locationText.setText(location[pos % location.length]);

        TypedArray pics = resources.obtainTypedArray(R.array.places_picture);
        ImageView imageView = (ImageView)findViewById(R.id.image);
        imageView.setImageDrawable(pics.getDrawable(pos % pics.length()));
    }

}
