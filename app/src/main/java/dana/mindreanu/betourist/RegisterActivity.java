package dana.mindreanu.betourist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DialogFragment;

public class RegisterActivity extends FragmentActivity implements
        DatePickerDialog.OnDateSetListener
{

    private EditText regDateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText regUserName = (EditText) findViewById(R.id.registerUserName);
        final EditText regEmail = (EditText) findViewById(R.id.registerEmail);
        final EditText regCity = (EditText) findViewById(R.id.registerCity);
        final EditText regCountry = (EditText) findViewById(R.id.registerCountry);
        final EditText regPassword = (EditText) findViewById(R.id.registerPassword);
        regDateOfBirth = (EditText) findViewById(R.id.register_birthDate);
        final Button bRegister = (Button) findViewById(R.id.button_registerActivity);
    }

    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        String date = Integer.toString(dayOfMonth) + "-"
                + Integer.toString(month) + "-" + Integer.toString(year);
        regDateOfBirth.setText(date);
    }
}
