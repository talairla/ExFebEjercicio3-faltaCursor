package com.example.teo.exfebejercicio3;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*  Valor total del ejercicio (45pt)
        Programar los botones siguientes:
        btnNotificacionCamara : Al pulsarlo debe crear una notificación con icono, título y texto.  (8pt)
                                La notificación, al pulsarla debe abrir la cámara de fotos.         (7pt)
        btnProgramarAlamra :    Al pulsarlo, debe programar una alarma para dentro de 1 minuto.     (5pt)
                                La alarma, al dispararse debe reproducir el sonido alojado en       (10pt)
                                 https://www.w3schools.com/html/horse.ogg
        btnToastContacto :      Al pulsarlo, debe mostrar un Toast con el primer nombre por orden alfabético
                                que contenga una letra n y tenga teléfono.                          (15pt)
    */
    Button btnNotif;
    Button btnAlarm;
    Button btnToast;
    int notifID = 1;
    public final static String tag="ExFeb3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNotif = (Button) findViewById(R.id.btnNotificacionCamara);
        btnNotif.setOnClickListener(this);
        btnAlarm = (Button) findViewById(R.id.btnProgramarAlarma);
        btnAlarm.setOnClickListener(this);
        btnToast = (Button) findViewById(R.id.btnToastContacto);
        btnToast.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNotificacionCamara:
                crearNotificacion();
                break;
            case R.id.btnProgramarAlarma:
                crearAlarma();
                break;
            case R.id.btnToastContacto:
                crearToast();
                break;
        }
    }


    private void crearNotificacion() {
        Log.i(tag,"Creando notificación");
        NotificationCompat.Builder constructor = new NotificationCompat.Builder(this);

        constructor.setSmallIcon(R.mipmap.ic_launcher);
        constructor.setContentTitle("Notificacion cámara");
        constructor.setContentText("Pulsa en la notificación para abrir la cámara");

        Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        PendingIntent intentNotif = PendingIntent.getActivity(this, 0, camara, 0);

        constructor.setContentIntent(intentNotif);

        NotificationManager gestorNotif = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        gestorNotif.notify(notifID, constructor.build() );
    }

    private void crearAlarma() {
        Log.i(tag,"Creando alarma");

        AlarmManager alarmManager;
        PendingIntent alarmIntent;

        alarmManager  = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime()+10*1000, alarmIntent);
    }


    private void crearToast() {
        Log.i(tag,"Creando Toast");
    }
}
