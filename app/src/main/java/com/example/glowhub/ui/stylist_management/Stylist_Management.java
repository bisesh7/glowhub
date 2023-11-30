package com.example.glowhub.ui.stylist_management;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glowhub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stylist_Management#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stylist_Management extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Stylist_Management() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Stylist_Management.
     */
    // TODO: Rename and change types and number of parameters
    public static Stylist_Management newInstance(String param1, String param2) {
        Stylist_Management fragment = new Stylist_Management();
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

    private List<Stylist> stylistList;
    private StylistAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stylist__management, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_stylist);
        stylistList = new ArrayList<>(); // Initialize your stylist list

        // Create some initial stylists (example)
        stylistList.add(new Stylist("Stylist 1", "Hair Specialist"));
        stylistList.add(new Stylist("Stylist 2", "Nail Artist"));
        // ... add more stylists as needed

        adapter = new StylistAdapter(stylistList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Example: Adding a new stylist to the list (can be triggered by an event)
        view.findViewById(R.id.add_stylist_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new stylist and add it to the list
                Stylist newStylist = new Stylist("New Stylist", "Specialization");
                stylistList.add(newStylist);

                // Notify adapter of the new data addition
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}