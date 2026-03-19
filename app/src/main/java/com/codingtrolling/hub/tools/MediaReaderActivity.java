package com.codingtrolling.hub.tools;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import com.codingtrolling.hub.R;
import com.codingtrolling.hub.utils.Logger;

public class MediaReaderActivity extends AppCompatActivity {
    private Logger mediaLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_reader);

        mediaLog = new Logger(findViewById(R.id.txt_media_status));
        mediaLog.log("MEDIA_VAULT: SCANNING STORAGE...");
        
        // Future: Integration with ContentResolver to pull real photos
    }
}
