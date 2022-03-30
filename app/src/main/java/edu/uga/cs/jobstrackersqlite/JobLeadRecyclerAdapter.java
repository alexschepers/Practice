package edu.uga.cs.jobstrackersqlite;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * This is an adapter class for the RecyclerView to show all job leads.
 */
public class JobLeadRecyclerAdapter extends RecyclerView.Adapter<JobLeadRecyclerAdapter.JobLeadHolder> {

    public static final String DEBUG_TAG = "JobLeadRecyclerAdapter";

    private List<JobLead> jobLeadList;

    public JobLeadRecyclerAdapter( List<JobLead> jobLeadList ) {
        this.jobLeadList = jobLeadList;
    }

    // The adapter must have a ViewHolder class to "hold" one item to show.
    class JobLeadHolder extends RecyclerView.ViewHolder {

        TextView companyName;
        TextView phone;
        TextView url;
        TextView comments;

        public JobLeadHolder(View itemView ) {
            super(itemView);

            companyName = (TextView) itemView.findViewById( R.id.companyName );
            phone = (TextView) itemView.findViewById( R.id.phone );
            url = (TextView) itemView.findViewById( R.id.url );
            comments = (TextView) itemView.findViewById( R.id.comments );
        }
    }

    @Override
    public JobLeadHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        // We need to make sure that all CardViews have the same, full width, allowed by the parent view.
        // This is a bit tricky, and we must provide the parent reference (the second param of inflate)
        // and false as the third parameter (don't attach to root).
        // Consequently, the parent view's (the RecyclerView) width will be used (match_parent).
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.job_lead, parent, false );
        return new JobLeadHolder( view );
    }

    // This method fills in the values of a holder to show a JobLead.
    // The position parameter indicates the position on the list of jobs list.
    @Override
    public void onBindViewHolder( JobLeadHolder holder, int position ) {
        JobLead jobLead = jobLeadList.get( position );

        Log.d( DEBUG_TAG, "onBindViewHolder: " + jobLead );

        holder.companyName.setText( jobLead.getCompanyName());
        holder.phone.setText( jobLead.getPhone() );
        holder.url.setText( jobLead.getUrl() );
        holder.comments.setText( jobLead.getComments() );
    }

    @Override
    public int getItemCount() {
        if( jobLeadList != null )
            return jobLeadList.size();
        else
            return 0;
    }
}
