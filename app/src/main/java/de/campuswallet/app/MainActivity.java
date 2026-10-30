package de.campuswallet.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class MainActivity extends Activity {

    // Pseudodaten – erfundene Hochschule und erfundene Person
    private static final String UNIVERSITY = "Aurelion Fernhochschule";
    private static final String ADDRESS = "Aurelion Fernhochschule\nAm Wissenspark 8\n34567 Lindenhain";
    private static final String FIRST_NAME = "Jonas";
    private static final String LAST_NAME = "Feldberg";
    private static final String BIRTHDAY = "14.03.1999";
    private static final String MATRIKEL = "AFH-2026-31415";
    private static final String PROGRAM = "Medieninformatik (B.Sc.)";
    private static final String SEMESTER = "Fernstudium · WiSe 2026/27";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setText(R.id.greeting, FIRST_NAME);
        setText(R.id.university, UNIVERSITY);
        setText(R.id.avatar, "" + FIRST_NAME.charAt(0) + LAST_NAME.charAt(0));
        setText(R.id.name, FIRST_NAME + " " + LAST_NAME);
        setText(R.id.birthday, BIRTHDAY);
        setText(R.id.matrikel, MATRIKEL);
        setText(R.id.program, PROGRAM);
        setText(R.id.semester, SEMESTER);
        setText(R.id.barcodeText, MATRIKEL);
        setText(R.id.address, ADDRESS);

        String qrContent = UNIVERSITY + "|" + FIRST_NAME + " " + LAST_NAME + "|" + MATRIKEL;
        setCode(R.id.qr, qrContent, BarcodeFormat.QR_CODE, 600, 600);
        setCode(R.id.barcode, MATRIKEL, BarcodeFormat.CODE_128, 900, 180);
    }

    private void setText(int id, String text) {
        ((TextView) findViewById(id)).setText(text);
    }

    private void setCode(int id, String content, BarcodeFormat format, int width, int height) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(content, format, width, height);
            int w = matrix.getWidth();
            int h = matrix.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    pixels[y * w + x] = matrix.get(x, y) ? Color.BLACK : Color.WHITE;
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(pixels, w, h, Bitmap.Config.ARGB_8888);
            ((ImageView) findViewById(id)).setImageBitmap(bitmap);
        } catch (WriterException e) {
            // Code kann nicht erzeugt werden – Feld bleibt leer
        }
    }
}
