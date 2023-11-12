package es.studium.myzodiac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFecha;
    Button btnAceptar;
    LocalDate now = LocalDate.now();
    LocalDate userBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(this);
        editTextFecha = findViewById(R.id.inputFecha);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnAceptar.getId()) {
            // si la fecha está vacía
            String fecha = editTextFecha.getText().toString();
            if (fecha.isEmpty()) {
                Toast.makeText(this, "Introduce la fecha.", Toast.LENGTH_SHORT).show();
            }
            else {
                // comprobar la validez de la fecha
                if (checkDateValidity(fecha)) {
                    // convertir la cadena en la fecha
                    userBirthday = convertToDate(fecha);
                    // calcular la edad
                    int edad = calculateAge(userBirthday);
                    // comprobar el zodiaco del usuario
                    String zodiaco = checkZodiacSign(userBirthday);
                    // enviar un intent explícito -> pasar a la actividad secundaria
                    Intent intentSec = new Intent(this, Secundaria.class);
                    // mandar información a la actividad secundaria
                    intentSec.putExtra("edad", edad);
                    intentSec.putExtra("zodiaco", zodiaco);
                    // lanzar la actividad secundaria
                    startActivity(intentSec);
                    recreate();
                }
                else {
                    Toast.makeText(this, "Fecha incorrecta.", Toast.LENGTH_SHORT).show();
                }
            }
            editTextFecha.getText().clear();
        }
    }

    private String checkZodiacSign(LocalDate userBirthday) {
        String zodiac;
        // obtener el día de la fecha del usuario
        int day = userBirthday.getDayOfMonth();
        // obtener el mes de la fecha del usuario
        int month = userBirthday.getMonthValue();
        switch (month) {
            case 1: // enero
                zodiac = day <= 20 ? "capricornio" : "acuario"; // los días hasta veinte incluso -> capricornio
                break;
            case 2:
                zodiac = day <= 19 ? "acuario" : "piscis";
                break;
            case 3:
                zodiac = day <= 20 ? "piscis" : "aries";
                break;
            case 4:
                zodiac = day <= 19 ? "aries" : "tauro";
                break;
            case 5:
                zodiac = day <= 20 ? "tauro" : "geminis";
                break;
            case 6:
                zodiac = day <= 20 ? "geminis" : "cancer";
                break;
            case 7:
                zodiac = day <= 22 ? "cancer" : "leo";
                break;
            case 8:
                zodiac = day <= 22 ? "leo" : "virgo";
                break;
            case 9:
                zodiac = day <= 22 ? "virgo" : "libra";
                break;
            case 10:
                zodiac = day <= 22 ? "libra" : "escorpio";
                break;
            case 11:
                zodiac = day <= 21 ? "escorpio" : "sagitario";
                break;
            case 12:
                zodiac = day <= 21 ? "sagitario" : "capricornio";
                break;
            default:
                zodiac = "";
        }
        return zodiac;
    }

    private int calculateAge(LocalDate userBirthday) {
        // calcular el periodo entre la fecha del usuario y la fecha actual y obtener años completos
        int age = Period.between(userBirthday, now).getYears();
        return age;
    }


    private LocalDate convertToDate(String fecha) {
        try {
            // dividir la fecha en día, mes y año
            String[] dateSplit = fecha.split("/");
            int day = Integer.parseInt(dateSplit[0]);
            int month = Integer.parseInt(dateSplit[1]);
            int year = Integer.parseInt(dateSplit[2]);
            // crear una fecha utilizando el día, mes y año
            userBirthday = LocalDate.of(year, month, day);
        } catch (Exception ex) {}
        return userBirthday;
    }

    private boolean checkDateValidity(String date) {
        boolean valid = false;
        try {
            // convertir String en LocalDate
            userBirthday = convertToDate(date);
            // comprobar si la fecha es antes de la fecha actual
            valid = userBirthday.isBefore(now) ? true : false;
        } catch (Exception ex) {}
        return valid;
    }
}