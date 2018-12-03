package com.stoka.libra.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stoka.libra.AccelView;
import com.stoka.libra.R;

public class CalibrateFragment extends Fragment {
    onViewCreateDoneListener listener;

    public interface onViewCreateDoneListener{
        void onCreateComplete();
    }
    public void setOnViewCreateDoneListener(onViewCreateDoneListener onViewCreateDoneListener){
        listener = onViewCreateDoneListener;
    }
    AccelView accelView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calibrate,container,false);
        accelView = view.findViewById(R.id.accelView);
        accelView.setOnCalibrateListener(new AccelView.onCalibrateCompleteListener() {
            @Override
            public void onCalibrateComplete() {
                listener.onCreateComplete();
            }
        });
        return view;
    }

    public AccelView getAccelView(){
        return accelView;
    }
}
