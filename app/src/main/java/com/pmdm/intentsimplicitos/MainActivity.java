package com.pmdm.intentsimplicitos;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        String ac=intent.getAction();
        String textRecibido;

        if(ac.equals(Intent.ACTION_SEND)) {
            EditText edRecibido=(EditText)findViewById(R.id.edRecibido);
            textRecibido = intent.getStringExtra(Intent.EXTRA_TEXT);
            if(textRecibido!=null)
                edRecibido.setText(textRecibido);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void abrir(View v){
        Intent i=new Intent();
        Intent chooser=null;
        switch(v.getId()){
            case R.id.btnWeb:
                EditText edURL=(EditText)findViewById(R.id.edURL);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(edURL.getText().toString()));
                chooser=i.createChooser(i,"Elige un navegador");
                startActivity(chooser);
                Toast.makeText(this.getApplicationContext(),"Acceso a web!",Toast.LENGTH_LONG).show();
                break;

            case R.id.btnMapa:
                EditText edLongitud=(EditText)findViewById(R.id.edLongitud);
                EditText edLatitud=(EditText)findViewById(R.id.edLatitud);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:" + edLatitud.getText().toString() + "," + edLongitud.getText().toString()));
                chooser=i.createChooser(i,"Lanzar Maps");
                startActivity(chooser);
                Toast.makeText(this.getApplicationContext(),"Acceso a mapas!",Toast.LENGTH_LONG).show();
                break;

            case R.id.btnEnviar:
                EditText edEmail=(EditText)findViewById(R.id.edEmail);
                i.setAction(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                String para[]={edEmail.getText().toString(),"kkk@lll.es","otro@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL,para);
                i.putExtra(Intent.EXTRA_SUBJECT,"Saludos desde Android");
                i.putExtra(Intent.EXTRA_TEXT,"Hola!. Este es nuestro primer email!!");
                i.setType("message/rfc822");
                chooser=i.createChooser(i,"Enviar email");
                startActivity(chooser);
                Toast.makeText(this.getApplicationContext(),"Envía el email!!",Toast.LENGTH_LONG).show();
                break;

        }
    }
}
