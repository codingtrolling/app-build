package com.codingtrolling.hub.tools;

import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.codingtrolling.hub.R;
import com.codingtrolling.hub.utils.Logger;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class AndroidLinkActivity extends AppCompatActivity {
    private Logger toolLog;
    private MediaCodec decoder;
    private RemoteStreamThread streamThread;
    private OutputStream remoteControlStream;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_link);

        toolLog = new Logger(findViewById(R.id.txt_status));
        SurfaceView surfaceView = findViewById(R.id.remote_surface);
        EditText ipInput = findViewById(R.id.edt_target_ip);
        
        // TOUCH INJECTION LOGIC
        surfaceView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN && remoteControlStream != null) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                sendRemoteCommand("input tap " + x + " " + y);
            }
            return true;
        });

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) { setupDecoder(holder); }
            @Override public void surfaceChanged(@NonNull SurfaceHolder holder, int f, int w, int h) {}
            @Override public void surfaceDestroyed(@NonNull SurfaceHolder holder) { cleanup(); }
        });

        findViewById(R.id.btn_mirror).setOnClickListener(v -> startRemoteFeed(ipInput.getText().toString()));
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }

    private void setupDecoder(SurfaceHolder holder) {
        try {
            decoder = MediaCodec.createDecoderByType(MediaFormat.MIMETYPE_VIDEO_AVC);
            MediaFormat format = MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, 1280, 720);
            decoder.configure(format, holder.getSurface(), null, 0);
            decoder.start();
        } catch (IOException e) { toolLog.log("DECODER_ERR"); }
    }

    private void startRemoteFeed(String ip) {
        new Thread(() -> {
            try {
                Socket socket = new Socket(ip.split(":")[0], Integer.parseInt(ip.split(":")[1]));
                remoteControlStream = socket.getOutputStream();
                streamThread = new RemoteStreamThread(socket.getInputStream(), decoder);
                streamThread.start();
                runOnUiThread(() -> toolLog.log("REMOTE CONTROL ACTIVE."));
            } catch (Exception e) {
                runOnUiThread(() -> toolLog.log("LINK FAILED."));
            }
        }).start();
    }

    private void sendRemoteCommand(String cmd) {
        new Thread(() -> {
            try {
                if (remoteControlStream != null) {
                    remoteControlStream.write((cmd + "\n").getBytes());
                    remoteControlStream.flush();
                }
            } catch (IOException e) { e.printStackTrace(); }
        }).start();
    }

    private void cleanup() {
        if (streamThread != null) streamThread.stopStream();
        if (decoder != null) { decoder.stop(); decoder.release(); }
    }
}
