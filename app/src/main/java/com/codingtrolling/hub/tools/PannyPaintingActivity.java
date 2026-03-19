package com.codingtrolling.hub.tools;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.codingtrolling.hub.R;
import com.codingtrolling.hub.views.PannyCanvas;

public class PannyPaintingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panny_painting);

        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            PannyCanvas canvas = findViewById(R.id.panny_view);
            canvas.clearCanvas();
        });
    }
}
