package com.project.placementcell;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private List<ListData2> listData;

    public MessageAdapter(List<ListData2> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data2,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListData2 ld=listData.get(position);
        holder.txtname.setText(ld.getMessageText());
     //   holder.descriptiontxt.setText(ld.getMessageUser());
        ViewGroup.MarginLayoutParams cardViewMargin= (ViewGroup.MarginLayoutParams) holder.msg.getLayoutParams();

        if(ld.getMessageUser().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            cardViewMargin.setMargins(400, 20, 10, 20);

        }else
            cardViewMargin.setMargins(10,20,300,20);
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtid,txtname,descriptiontxt;
        CardView msg;
        public ViewHolder(View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.nametxt);
   //         descriptiontxt=(TextView)itemView.findViewById(R.id.descriptiontxt);
           msg=itemView.findViewById(R.id.msg);
            msg.setCardBackgroundColor(Color.rgb(32,23,23));


        }
    }
}