package de.uit.campus;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int PRIMARY = Color.parseColor("#0F766E");
    private static final int ACCENT = Color.parseColor("#F97316");
    private static final int TEXT = Color.parseColor("#0F172A");
    private static final int MUTED = Color.parseColor("#64748B");
    private static final int DIVIDER = Color.parseColor("#E2E8F0");

    private static final String[] TAB_TITLES = {"Start", "Plan", "Noten", "News", "Kontakt"};
    private static final String[] TAB_ICONS = {"⌂", "▦", "★", "✉", "☎"};
    private static final int PROFILE_TAB = 99;

    private LinearLayout content;
    private ScrollView scroll;
    private TextView headerTitle;
    private LinearLayout header;
    private LinearLayout nav;
    private final TextView[] tabs = new TextView[TAB_TITLES.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.parseColor("#F1F5F9"));

        header = buildHeader();
        root.addView(header);

        scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(16), dp(8), dp(16), dp(24));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));

        nav = buildBottomNav();
        root.addView(nav);
        setContentView(root);

        // Ab Android 15 zeichnet die App unter Status- und Navigationsleiste –
        // Abstand einfügen, damit das Menü unten nicht verdeckt wird.
        root.setOnApplyWindowInsetsListener((v, insets) -> {
            int top;
            int bottom;
            if (android.os.Build.VERSION.SDK_INT >= 30) {
                android.graphics.Insets bars = insets.getInsets(WindowInsets.Type.systemBars());
                top = bars.top;
                bottom = bars.bottom;
            } else {
                top = insets.getSystemWindowInsetTop();
                bottom = insets.getSystemWindowInsetBottom();
            }
            header.setPadding(dp(20), top + dp(18), dp(20), dp(18));
            nav.setPadding(0, dp(6), 0, bottom + dp(6));
            return insets;
        });

        showTab(0);
    }

    @Override
    public void onBackPressed() {
        if (tabs[0].isSelected()) {
            super.onBackPressed();
        } else {
            showTab(0);
        }
    }

    // ---------- Grundgerüst ----------

    private LinearLayout buildHeader() {
        LinearLayout bar = new LinearLayout(this);
        bar.setOrientation(LinearLayout.HORIZONTAL);
        bar.setGravity(Gravity.CENTER_VERTICAL);
        bar.setBackgroundColor(PRIMARY);
        bar.setPadding(dp(20), dp(18), dp(20), dp(18));

        LinearLayout titles = new LinearLayout(this);
        titles.setOrientation(LinearLayout.VERTICAL);
        TextView logo = text(DemoData.UNI_SHORT, 26, Color.WHITE, true);
        logo.setLetterSpacing(0.15f);
        titles.addView(logo);
        headerTitle = text("", 14, Color.parseColor("#CCFBF1"), false);
        titles.addView(headerTitle);
        bar.addView(titles, new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        TextView profileButton = avatar(40, 16);
        profileButton.setOnClickListener(v -> showTab(PROFILE_TAB));
        bar.addView(profileButton);
        return bar;
    }

    private LinearLayout buildBottomNav() {
        LinearLayout nav = new LinearLayout(this);
        nav.setOrientation(LinearLayout.HORIZONTAL);
        nav.setBackgroundColor(Color.WHITE);
        nav.setElevation(dp(8));
        nav.setPadding(0, dp(6), 0, dp(6));

        for (int i = 0; i < TAB_TITLES.length; i++) {
            final int index = i;
            TextView tab = text(TAB_ICONS[i] + "\n" + TAB_TITLES[i], 12, MUTED, false);
            tab.setGravity(Gravity.CENTER);
            tab.setPadding(0, dp(8), 0, dp(8));
            tab.setMinHeight(dp(56));
            tab.setClickable(true);
            tab.setOnClickListener(v -> showTab(index));
            tabs[i] = tab;
            nav.addView(tab, new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        }
        return nav;
    }

    private void showTab(int index) {
        for (int i = 0; i < tabs.length; i++) {
            boolean selected = i == index;
            tabs[i].setSelected(selected);
            tabs[i].setTextColor(selected ? PRIMARY : MUTED);
            tabs[i].setTypeface(null, selected ? Typeface.BOLD : Typeface.NORMAL);
        }
        content.removeAllViews();
        switch (index) {
            case 0: buildHome(); break;
            case 1: buildSchedule(); break;
            case 2: buildGrades(); break;
            case 3: buildNews(); break;
            case PROFILE_TAB: buildProfile(); break;
            default: buildContacts(); break;
        }
        scroll.scrollTo(0, 0);
    }

    // ---------- Seiten ----------

    private void buildHome() {
        headerTitle.setText(DemoData.UNI_SLOGAN);

        LinearLayout greeting = new LinearLayout(this);
        greeting.setGravity(Gravity.CENTER_VERTICAL);
        greeting.setPadding(0, dp(12), 0, 0);

        ImageView photo = new ImageView(this);
        photo.setImageResource(R.drawable.profile_photo);
        photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GradientDrawable ring = new GradientDrawable();
        ring.setShape(GradientDrawable.OVAL);
        ring.setStroke(dp(3), ACCENT);
        photo.setBackground(ring);
        photo.setPadding(dp(3), dp(3), dp(3), dp(3));
        photo.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
        photo.setClipToOutline(true);
        greeting.addView(photo, new LinearLayout.LayoutParams(dp(96), dp(96)));

        LinearLayout greetingText = new LinearLayout(this);
        greetingText.setOrientation(LinearLayout.VERTICAL);
        greetingText.setPadding(dp(16), 0, 0, 0);
        greetingText.addView(text("Hallo, " + DemoData.STUDENT_FIRST_NAME + "! 👋", 24, TEXT, true));
        greetingText.addView(text(DemoData.PROGRAM + "\n" + DemoData.SEMESTER, 14, MUTED, false));
        greeting.addView(greetingText, new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        content.addView(greeting);

        // Fortschritt
        LinearLayout progress = card();
        progress.addView(label("Studienfortschritt"));
        LinearLayout row = new LinearLayout(this);
        row.setPadding(0, dp(8), 0, dp(8));
        row.addView(stat(DemoData.ECTS_DONE + " / " + DemoData.ECTS_TOTAL, "ECTS"),
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        row.addView(stat(DemoData.GRADE_AVERAGE, "Notenschnitt"),
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        progress.addView(row);
        ProgressBar bar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        bar.setMax(DemoData.ECTS_TOTAL);
        bar.setProgress(DemoData.ECTS_DONE);
        bar.setProgressTintList(android.content.res.ColorStateList.valueOf(ACCENT));
        progress.addView(bar);
        content.addView(progress);

        // Nächste Veranstaltung
        String[] next = DemoData.SCHEDULE[0];
        LinearLayout nextCard = card();
        nextCard.addView(label("Nächste Veranstaltung"));
        nextCard.addView(text(next[2], 18, TEXT, true));
        nextCard.addView(text(next[0] + ", " + next[1] + "\n" + next[3], 14, MUTED, false));
        content.addView(nextCard);

        // Termine
        LinearLayout events = card();
        events.addView(label("Termine"));
        for (String[] e : DemoData.EVENTS) {
            events.addView(listRow(e[0], e[1]));
        }
        content.addView(events);

        // Neueste Meldung
        String[] news = DemoData.NEWS[0];
        LinearLayout newsCard = card();
        newsCard.addView(label("Aktuelles"));
        newsCard.addView(text(news[1], 16, TEXT, true));
        newsCard.addView(text(news[2], 14, MUTED, false));
        TextView more = text("Alle News →", 14, PRIMARY, true);
        more.setPadding(0, dp(10), 0, 0);
        more.setOnClickListener(v -> showTab(3));
        newsCard.addView(more);
        content.addView(newsCard);
    }

    private void buildSchedule() {
        headerTitle.setText("Stundenplan · diese Woche");
        String currentDay = "";
        LinearLayout dayCard = null;
        for (String[] s : DemoData.SCHEDULE) {
            if (!s[0].equals(currentDay)) {
                currentDay = s[0];
                dayCard = card();
                dayCard.addView(label(currentDay));
                content.addView(dayCard);
            }
            TextView time = text(s[1], 13, ACCENT, true);
            time.setPadding(0, dp(8), 0, 0);
            dayCard.addView(time);
            dayCard.addView(text(s[2], 16, TEXT, true));
            dayCard.addView(text(s[3], 13, MUTED, false));
        }
    }

    private void buildGrades() {
        headerTitle.setText("Notenübersicht");
        LinearLayout summary = card();
        LinearLayout row = new LinearLayout(this);
        row.addView(stat(DemoData.GRADE_AVERAGE, "Durchschnitt"),
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        row.addView(stat(String.valueOf(DemoData.ECTS_DONE), "ECTS erreicht"),
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        summary.addView(row);
        content.addView(summary);

        LinearLayout list = card();
        list.addView(label("Module"));
        for (String[] g : DemoData.GRADES) {
            LinearLayout line = new LinearLayout(this);
            line.setGravity(Gravity.CENTER_VERTICAL);
            line.setPadding(0, dp(10), 0, dp(10));

            LinearLayout left = new LinearLayout(this);
            left.setOrientation(LinearLayout.VERTICAL);
            left.addView(text(g[0], 15, TEXT, false));
            left.addView(text(g[2] + " ECTS", 12, MUTED, false));
            line.addView(left, new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            boolean open = g[1].equals("–");
            TextView grade = text(open ? "offen" : g[1], 16, open ? MUTED : PRIMARY, true);
            line.addView(grade);
            list.addView(line);
            list.addView(divider());
        }
        content.addView(list);
    }

    private void buildNews() {
        headerTitle.setText("Neuigkeiten");
        for (String[] n : DemoData.NEWS) {
            LinearLayout c = card();
            c.addView(text(n[0], 12, ACCENT, true));
            c.addView(text(n[1], 17, TEXT, true));
            TextView body = text(n[2], 14, MUTED, false);
            body.setPadding(0, dp(4), 0, 0);
            c.addView(body);
            content.addView(c);
        }
    }

    private void buildProfile() {
        headerTitle.setText("Mein Profil");

        LinearLayout top = card();
        top.setGravity(Gravity.CENTER_HORIZONTAL);
        top.addView(avatar(88, 32));
        TextView name = text(DemoData.STUDENT_FIRST_NAME + " " + DemoData.STUDENT_LAST_NAME, 22, TEXT, true);
        name.setPadding(0, dp(12), 0, 0);
        top.addView(name);
        top.addView(text(DemoData.PROGRAM, 14, MUTED, false));
        content.addView(top);

        LinearLayout personal = card();
        personal.addView(label("Persönliche Daten"));
        for (String[] row : DemoData.PROFILE_PERSONAL) {
            personal.addView(listRow(row[0], row[1]));
        }
        content.addView(personal);

        LinearLayout study = card();
        study.addView(label("Studium"));
        for (String[] row : DemoData.PROFILE_STUDY) {
            study.addView(listRow(row[0], row[1]));
        }
        content.addView(study);

        LinearLayout contact = card();
        contact.addView(label("Kontaktdaten"));
        for (String[] row : DemoData.PROFILE_CONTACT) {
            contact.addView(listRow(row[0], row[1]));
        }
        content.addView(contact);

        TextView demo = text("Demo-Profil · Studiendaten fiktiv", 11, MUTED, false);
        demo.setGravity(Gravity.CENTER);
        demo.setPadding(0, dp(18), 0, 0);
        content.addView(demo, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    private void buildContacts() {
        headerTitle.setText("Kontakt & Service");
        for (String[] k : DemoData.CONTACTS) {
            LinearLayout c = card();
            c.addView(label(k[0]));
            TextView body = text(k[1], 15, TEXT, false);
            body.setLineSpacing(dp(2), 1f);
            c.addView(body);
            content.addView(c);
        }

        LinearLayout info = card();
        info.addView(label("Impressum & Hinweis"));
        info.addView(text(DemoData.DEMO_NOTICE, 15, TEXT, true));
        content.addView(info);
    }

    // ---------- Bausteine ----------

    private LinearLayout card() {
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(dp(18), dp(16), dp(18), dp(16));
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.WHITE);
        bg.setCornerRadius(dp(18));
        c.setBackground(bg);
        c.setElevation(dp(2));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.topMargin = dp(14);
        c.setLayoutParams(lp);
        return c;
    }

    private TextView label(String s) {
        TextView t = text(s.toUpperCase(), 12, PRIMARY, true);
        t.setLetterSpacing(0.08f);
        t.setPadding(0, 0, 0, dp(4));
        return t;
    }

    private View stat(String value, String caption) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.addView(text(value, 22, TEXT, true));
        box.addView(text(caption, 12, MUTED, false));
        return box;
    }

    private View listRow(String left, String right) {
        LinearLayout row = new LinearLayout(this);
        row.setPadding(0, dp(6), 0, dp(6));
        TextView date = text(left, 14, ACCENT, true);
        row.addView(date, new LinearLayout.LayoutParams(dp(110),
                LinearLayout.LayoutParams.WRAP_CONTENT));
        row.addView(text(right, 14, TEXT, false), new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        return row;
    }

    private TextView avatar(int sizeDp, int textSp) {
        TextView a = text("" + DemoData.STUDENT_FIRST_NAME.charAt(0)
                + DemoData.STUDENT_LAST_NAME.charAt(0), textSp, Color.WHITE, true);
        a.setGravity(Gravity.CENTER);
        GradientDrawable bg = new GradientDrawable();
        bg.setShape(GradientDrawable.OVAL);
        bg.setColor(ACCENT);
        bg.setStroke(dp(2), Color.WHITE);
        a.setBackground(bg);
        a.setLayoutParams(new LinearLayout.LayoutParams(dp(sizeDp), dp(sizeDp)));
        return a;
    }

    private View divider() {
        View v = new View(this);
        v.setBackgroundColor(DIVIDER);
        v.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(1)));
        return v;
    }

    private TextView text(String s, int sp, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(sp);
        t.setTextColor(color);
        if (bold) {
            t.setTypeface(null, Typeface.BOLD);
        }
        return t;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
