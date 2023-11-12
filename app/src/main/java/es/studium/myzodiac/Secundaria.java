package es.studium.myzodiac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Secundaria extends AppCompatActivity{

    TextView txtEdad;
    TextView txtZodiaco;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        txtEdad = findViewById(R.id.txtEdad);
        txtZodiaco = findViewById(R.id.txtZodiaco);
        img = findViewById(R.id.imgX);

        // recibir información de la actividad principal
        Bundle info = getIntent().getExtras();
        if (info != null) {
            // obtener la edad del usuario
            int edad = info.getInt("edad");
            // mostrar la edad
            txtEdad.setText("Tienes " + edad + " años.");

            // obtener el zodiaco del usuario
            String zodiaco = info.getString("zodiaco");
            // obtener el identificador del recurso en strings.xml según el valor de la variable zodiaco
            int idZodiacString = getResources().getIdentifier(zodiaco, "string", getPackageName());
            // mostrar el zodiaco del usuario
            txtZodiaco.setText(getString(idZodiacString));

            // obtener el identificador del recurso en drawable según el valor de la variable zodiaco
            int idZodiacDrawable = getResources().getIdentifier(zodiaco, "drawable", getPackageName());
            // si el recurso con este id existe
            if (idZodiacDrawable != 0) {
                // obtener el recurso según su id
                Drawable drawable = ContextCompat.getDrawable(this, idZodiacDrawable);
                // mostrar la imagen del zodiaco del usuario
                img.setImageDrawable(drawable);
            }

        }

    }
}