package com.example.viewbindingtest3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewbindingtest3.databinding.FragmentSampleBinding;

public class FragmentSample extends Fragment {
    private FragmentSampleBinding binding;
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentSampleBinding.inflate(inflater,container,false);
        binding.btnFragment.setOnClickListener(view -> {
            binding.txtFragment.setText("Fragment 버튼이 눌렸습니다");
        });
        return binding.getRoot();
    }
}
