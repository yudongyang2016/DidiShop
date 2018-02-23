package com.shop.didi.didishop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.didi.didishop.R;

/**
 * Author: ydy
 * Created: 2017/3/23 16:14
 * Description:
 */

public class FragmentThree extends Fragment {

    private Context mContext;

    public static FragmentThree newInstance() {
        FragmentThree fragmentThree = new FragmentThree();
        return fragmentThree;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_three_view, container, false);
        return view;
    }

}
