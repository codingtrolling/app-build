package com.codingtrolling.hub.tools;

import android.media.MediaCodec;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class RemoteStreamThread extends Thread {
    private final InputStream inputStream;
    private final MediaCodec decoder;
    private boolean isRunning = true;

    public RemoteStreamThread(InputStream inputStream, MediaCodec decoder) {
        this.inputStream = inputStream;
        this.decoder = decoder;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024 * 1024]; // 1MB buffer
        MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();

        while (isRunning) {
            try {
                int length = inputStream.read(buffer);
                if (length > 0) {
                    // 1. Get an input buffer from the decoder
                    int inputBufferId = decoder.dequeueInputBuffer(10000);
                    if (inputBufferId >= 0) {
                        ByteBuffer inputBuffer = decoder.getInputBuffer(inputBufferId);
                        inputBuffer.clear();
                        inputBuffer.put(buffer, 0, length);
                        decoder.queueInputBuffer(inputBufferId, 0, length, System.currentTimeMillis(), 0);
                    }

                    // 2. Tell the decoder to render the frame to the Surface
                    int outputBufferId = decoder.dequeueOutputBuffer(info, 10000);
                    if (outputBufferId >= 0) {
                        decoder.releaseOutputBuffer(outputBufferId, true); // true = render to surface
                    }
                }
            } catch (Exception e) {
                isRunning = false;
            }
        }
    }

    public void stopStream() {
        isRunning = false;
    }
}
