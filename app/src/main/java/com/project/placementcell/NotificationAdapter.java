package com.project.placementcell;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;
import static com.project.placementcell.LoginActivity.UID;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    private List<ListData3> listData;

    public NotificationAdapter(List<ListData3> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listdata3,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListData3 ld=listData.get(position);
        holder.txtname.setText(ld.getName());
        holder.descriptiontxt.setText(ld.getDescription());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtid,txtname,descriptiontxt;
        public ViewHolder(View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.nametxt);
            descriptiontxt=(TextView)itemView.findViewById(R.id.descriptiontxt);
            CardView company=itemView.findViewById(R.id.company);
            company.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(),CompanyDetails.class).putExtra("company",txtname.getText().toString());
                    v.getContext().startActivity(intent);

                }
            });
        }
    }
}
