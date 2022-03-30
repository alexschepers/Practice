package edu.uga.cs.jobstrackersqlite;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The main activity class.  It just sets listeners for the two buttons.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newLeadButton = findViewById(R.id.button1);
        Button reviewLeadsButton = findViewById(R.id.button2);

        newLeadButton.setOnClickListener(new NewLeadButtonClickListener());
        reviewLeadsButton.setOnClickListener(new ReviewLeadsButtonClickListener());
    }

    private static class NewLeadButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), NewJobLeadActivity.class);
            view.getContext().startActivity( intent );
        }
    }

    private static class ReviewLeadsButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ReviewJobLeadsActivity.class);
            view.getContext().startActivity(intent);
        }
    }
}
