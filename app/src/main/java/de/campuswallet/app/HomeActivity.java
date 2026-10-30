package de.campuswallet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((TextView) findViewById(R.id.university)).setText(CardActivity.UNIVERSITY);
        ((TextView) findViewById(R.id.welcome)).setText("Willkommen, " + CardActivity.FIRST_NAME + "!");
        ((TextView) findViewById(R.id.semester)).setText(CardActivity.PROGRAM + " · WiSe 2026/27");

        findViewById(R.id.openCard).setOnClickListener(
                v -> startActivity(new Intent(this, CardActivity.class)));
    }
}
