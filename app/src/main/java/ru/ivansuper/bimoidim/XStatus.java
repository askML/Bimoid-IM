package ru.ivansuper.bimoidim;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Vector;

public class XStatus {
    public static Vector<Bitmap> list = new Vector<Bitmap>();
    private static boolean initialized;

    public static void init() {
        if (initialized) return;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScreenDensity = 240;
        options.inTargetDensity =  240;
        options.inDensity = 240;

        final Bitmap source = BitmapFactory.decodeResource(resources.res, R.drawable.xstatuses, options);
        final int sample_size = source.getHeight();

        Matrix matrix = new Matrix();
        matrix.postScale(resources.dm.scaledDensity, resources.dm.scaledDensity);

        for (int i = 0; i < 56; i++) {
            Bitmap sts = Bitmap.createBitmap(source, i * sample_size, 0, sample_size, sample_size, matrix, true);
            list.add(sts);
        }
        initialized = true;
    }

    public static Drawable getIcon(int index) {
        return new BitmapDrawable(list.get(index));
    }
}
