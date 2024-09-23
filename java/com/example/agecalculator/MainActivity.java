package com.example.agecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText birthdateInput;
    private TextView ageOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        birthdateInput = findViewById(R.id.birthdate_input);
        ageOutput = findViewById(R.id.age_output);
        Button calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAge();
            }
        });
    }

    private void calculateAge() {
        String birthdateStr = birthdateInput.getText().toString();
        if (birthdateStr.isEmpty()) {
            ageOutput.setText("Please enter your birthdate.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthdate = dateFormat.parse(birthdateStr);
            Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.setTime(birthdate);

            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

            if (today.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
                    (today.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                            today.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
                age--;
            }

            ageOutput.setText("Your age is: " + age);
        } catch (ParseException e) {
            ageOutput.setText("Invalid date format. Please use yyyy-mm-dd.");
        }
    }
}
