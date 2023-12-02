package com.example.glowhub.ui.appointment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.glowhub.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentBooking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentBooking extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText etAppointmentTitle, etSelectedDate; // Declare other EditText fields for appointment details
    private Calendar selectedDateCalendar;

    public AppointmentBooking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentBooking.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentBooking newInstance(String param1, String param2) {
        AppointmentBooking fragment = new AppointmentBooking();
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
        View view = inflater.inflate(R.layout.fragment_appointment_booking, container, false);

        etAppointmentTitle = view.findViewById(R.id.etAppointmentTitle);
        etSelectedDate = view.findViewById(R.id.etSelectedDate);
        Button btnSelectDate = view.findViewById(R.id.btnSelectDate);
        Button btnBookAppointment = view.findViewById(R.id.btnBookAppointment);

        selectedDateCalendar = Calendar.getInstance();

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve input data and book the appointment
                String appointmentTitle = etAppointmentTitle.getText().toString();
                // Get other appointment details

                // Validate data and perform booking logic
                // Example: Send appointment details to a database or perform necessary actions
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDateCalendar.set(Calendar.YEAR, year);
                        selectedDateCalendar.set(Calendar.MONTH, month);
                        selectedDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        updateSelectedDateText();
                    }
                },
                selectedDateCalendar.get(Calendar.YEAR),
                selectedDateCalendar.get(Calendar.MONTH),
                selectedDateCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateSelectedDateText() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String formattedDate = sdf.format(selectedDateCalendar.getTime());
        etSelectedDate.setText(formattedDate);
    }
}