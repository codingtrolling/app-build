package com.codingtrolling.hub.tools;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.codingtrolling.hub.R;
import com.codingtrolling.hub.utils.Logger;

public class BozinAiActivity extends AppCompatActivity {
    private Logger aiLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bozin_ai);

        aiLog = new Logger(findViewById(R.id.txt_ai_console));
        EditText input = findViewById(R.id.edt_ai_input);
        Button btnSend = findViewById(R.id.btn_send);
        
        aiLog.log("BOZIN NEURAL INTERFACE v3.0");
        aiLog.log("STATUS: CONNECTED TO CLUSTER");

        btnSend.setOnClickListener(v -> {
            String text = input.getText().toString();
            if (!text.isEmpty()) {
                aiLog.log("USER: " + text);
                processAiResponse(text);
                input.setText("");
            }
        });
    }

    private void processAiResponse(String query) {
        // Simple local logic for now - we can add API calls later
        aiLog.log("BOZIN: Processing packet '" + query + "'...");
        if (query.toLowerCase().contains("status")) {
            aiLog.log("BOZIN: All systems nominal.");
        } else {
            aiLog.log("BOZIN: Data acknowledged.");
        }
    }
}
