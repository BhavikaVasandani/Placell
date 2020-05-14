
package com.project.placementcell;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileView extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, null);
        TextView logout = view.findViewById(R.id.settingsLogout);
        TextView editProfile = view.findViewById(R.id.editProfile);
       TextView notification=view.findViewById(R.id.notification);
       TextView report=view.findViewById(R.id.report);
            RelativeLayout display=view.findViewById(R.id.display);
            editProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), StudentInfo.class));
                }
            });

    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        });
           notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), Notification.class));
                }
            });
           display.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(getActivity(), DisplayStudent.class));
               }
           });
           report.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(getActivity(), Report.class));

               }
           });
       return view;
        }

}

