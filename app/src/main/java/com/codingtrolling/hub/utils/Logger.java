package com.codingtrolling.hub.utils;

import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {
    private final TextView console;
    private final SimpleDateFormat dateFormat;

    public Logger(TextView textView) {
        this.console = textView;
        this.dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    }

    public void log(String message) {
        String time = dateFormat.format(new Date());
        String entry = "\n[" + time + "] " + message;
        console.append(entry);
    }

    public void critical(String message) {
        log("CRITICAL: " + message.toUpperCase());
    }
}
