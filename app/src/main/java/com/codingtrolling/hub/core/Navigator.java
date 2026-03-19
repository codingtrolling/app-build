package com.codingtrolling.hub.core;

import android.content.Context;
import android.content.Intent;
import com.codingtrolling.hub.tools.*;

public class Navigator {
    private final Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void openAndroidLink() {
        context.startActivity(new Intent(context, AndroidLinkActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void openBozinAI() {
        context.startActivity(new Intent(context, BozinAiActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void openVmMatrix() {
        context.startActivity(new Intent(context, VmMatrixActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void openPannyPainting() {
        context.startActivity(new Intent(context, PannyPaintingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void openPdfReader() {
        context.startActivity(new Intent(context, PdfReaderActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void openMediaReader() {
        context.startActivity(new Intent(context, MediaReaderActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
