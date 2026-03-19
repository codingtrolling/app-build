package com.codingtrolling.hub.tools;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.codingtrolling.hub.R;
import com.codingtrolling.hub.utils.Logger;

public class VmMatrixActivity extends AppCompatActivity {
    private Logger vmLog;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vm_matrix);

        vmLog = new Logger(findViewById(R.id.txt_vm_console));
        
        vmLog.log("VM_MATRIX_V1: INITIALIZING HYPERVISOR...");

        findViewById(R.id.btn_boot_vm).setOnClickListener(v -> {
            vmLog.log("ALLOCATING 2048MB RAM...");
            handler.postDelayed(() -> vmLog.log("MOUNTING /dev/sda1 (TrollOS)..."), 1000);
            handler.postDelayed(() -> vmLog.log("KERNEL BOOT SUCCESSFUL."), 2000);
        });

        findViewById(R.id.btn_vm_back).setOnClickListener(v -> finish());
    }
}
