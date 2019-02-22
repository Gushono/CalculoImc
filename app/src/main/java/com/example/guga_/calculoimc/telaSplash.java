package com.example.guga_.calculoimc;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class telaSplash extends AppCompatActivity {

    ImageView imgLogo;
    int valorinicial;
    public static Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);

        contexto = getApplicationContext();

        imgLogo = findViewById(R.id.imgLogo);


        animaLogo();


    }




    public void animaLogo(){

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgLogo, View.ALPHA, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(1000);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(2000);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                SharedPreferences sharedPreferencess = telaSplash.contexto.getSharedPreferences("dados", Context.MODE_PRIVATE);

                String acesso = sharedPreferencess.getString("valorAcesso", "");

                if(acesso.equals(""))
                {

                    Intent it = new Intent(telaSplash.this, telaPrimeiroAcesso.class);
                    startActivity(it);
                    telaSplash.this.finish();

                }else{
                    Intent it = new Intent(telaSplash.this, Principal.class);
                    startActivity(it);
                    telaSplash.this.finish();
                }





//                Intent it = new Intent(telaSplash.this, telaPrimeiroAcesso.class);
//                startActivity(it);
//                telaSplash.this.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();


    }



}
