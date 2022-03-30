package edu.uga.cs.jobstrackersqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * This class is an activity to create a new job lead.
 */
public class NewJobLeadActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "NewJobLeadActivity";

    private EditText companyNameView;
    private EditText phoneView;
    private EditText urlView;
    private EditText commentsView;

    private JobLeadsData jobLeadsData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job_lead);

        companyNameView = findViewById( R.id.editText1 );
        phoneView = findViewById( R.id.editText2 );
        urlView = findViewById( R.id.editText3 );
        commentsView = findViewById( R.id.editText4 );
        Button saveButton = findViewById(R.id.button);

        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activites may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        jobLeadsData = new JobLeadsData( this );

        saveButton.setOnClickListener( new ButtonClickListener()) ;
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String companyName = companyNameView.getText().toString();
            String phone = phoneView.getText().toString();
            String url = urlView.getText().toString();
            String comments = commentsView.getText().toString();
            JobLead jobLead = new JobLead( companyName, phone, url, comments );

            // Store this new job lead in the database asynchronously,
            // without blocking the UI thread.
            new JobLeadDBWriter().execute( jobLead );
        }
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB writing of a job lead, asynchronously.
    public class JobLeadDBWriter extends AsyncTask<JobLead, JobLead> {

        // This method will run as a background process to write into db.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onClick listener of the Save button.
        @Override
        protected JobLead doInBackground( JobLead... jobLeads ) {
            jobLeadsData.storeJobLead( jobLeads[0] );
            return jobLeads[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.  Note that doInBackground returns a JobLead object.
        // That object will be passed as argument to onPostExecute.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( JobLead jobLead ) {
            // Show a quick confirmation message
            Toast.makeText( getApplicationContext(), "Job lead created for " + jobLead.getCompanyName(),
                    Toast.LENGTH_SHORT).show();

            // Clear the EditTexts for next use.
            companyNameView.setText( "" );
            phoneView.setText( "" );
            urlView.setText( "" );
            commentsView.setText( "" );

            Log.d( DEBUG_TAG, "Job lead saved: " + jobLead );
        }
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "NewJobLeadActivity.onResume()" );
        // open the database in onResume
        if( jobLeadsData != null )
            jobLeadsData.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "NewJobLeadActivity.onPause()" );
        // close the database in onPause
        if( jobLeadsData != null )
            jobLeadsData.close();
        super.onPause();
    }

    // The following activity callback methods are not needed and are for
    // educational purposes only.
    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "NewJobLeadActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "NewJobLeadActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "NewJobLeadActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "NewJobLeadActivity.onRestart()" );
        super.onRestart();
    }
}
