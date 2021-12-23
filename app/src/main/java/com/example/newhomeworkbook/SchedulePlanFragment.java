package com.example.newhomeworkbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newhomeworkbook.databinding.FragmentSchedulePlanBinding;
import com.example.newhomeworkbook.databinding.FragmentWelcomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SchedulePlanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchedulePlanFragment extends Fragment {

    FragmentSchedulePlanBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SchedulePlanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchedulePlanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SchedulePlanFragment newInstance(String param1, String param2) {
        SchedulePlanFragment fragment = new SchedulePlanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return View inflate = inflater.inflate(R.layout.fragment_schedule_plan, container, false);
        binding = FragmentSchedulePlanBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        TextView test = binding.scheduleId;
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_schedulePlanFragment_to_schoolsubjectFragment);
            }
        });

        return view;
    }
}