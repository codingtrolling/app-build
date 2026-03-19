package com.codingtrolling.hub;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.codingtrolling.hub.core.Navigator;
import com.codingtrolling.hub.utils.Logger;

public class MainActivity extends AppCompatActivity {
    private Logger sysLog;
    private Navigator nav;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Engines
        sysLog = new Logger(findViewById(R.id.txt_console));
        nav = new Navigator(this);

        // Cyber Boot Sequence
        sysLog.log("CODINGTROLLING_OS LOADING...");
        handler.postDelayed(() -> sysLog.log("NEURAL_MODULES: ACTIVE"), 500);
        handler.postDelayed(() -> sysLog.log("HUB_V1.0: ONLINE"), 1000);

        // Tool 1: Android Link (Remote Mirroring)
        findViewById(R.id.btn_link).setOnClickListener(v -> {
            sysLog.log("EXECUTING: REMOTE_NEURAL_LINK.EXE");
            nav.openAndroidLink();
        });

        // Tool 2: Bozin AI
        findViewById(R.id.btn_ai).setOnClickListener(v -> {
            sysLog.log("CONNECTING TO BOZIN_CLUSTER...");
            nav.openBozinAI();
        });

        // Tool 3: VM Matrix
        findViewById(R.id.btn_vm).setOnClickListener(v -> {
            sysLog.log("MOUNTING VIRTUAL_MATRIX...");
            nav.openVmMatrix();
        });
    }
}
