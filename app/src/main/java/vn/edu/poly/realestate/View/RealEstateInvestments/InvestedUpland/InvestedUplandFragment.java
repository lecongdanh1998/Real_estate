package vn.edu.poly.realestate.View.RealEstateInvestments.InvestedUpland;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.poly.realestate.R;

public class InvestedUplandFragment extends Fragment {

    private View v;

    public InvestedUplandFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_investing_upland, container, false);
        return v;
    }
}
