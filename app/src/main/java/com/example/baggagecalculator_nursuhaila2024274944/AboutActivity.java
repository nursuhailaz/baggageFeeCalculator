package com.example.baggagecalculator_nursuhaila2024274944;

import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;

public class AboutActivity extends AppCompatActivity {

    TextView tvWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbarAbout);
        setSupportActionBar(toolbar);

        Drawable backArrow = AppCompatResources.getDrawable(
                this,
                androidx.appcompat.R.drawable.abc_ic_ab_back_material
        );

        if (backArrow != null) {
            backArrow = DrawableCompat.wrap(backArrow.mutate());
            DrawableCompat.setTint(backArrow, Color.WHITE);
            toolbar.setNavigationIcon(backArrow);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        tvWebsite = findViewById(R.id.tvWebsite);

        tvWebsite.setOnClickListener(v -> {
            String url = "https://github.com/nursuhailaz/baggageFeeCalculator";

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}
