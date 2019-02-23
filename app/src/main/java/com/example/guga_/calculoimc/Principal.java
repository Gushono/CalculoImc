package com.example.guga_.calculoimc;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    @Override public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
             return true;
        }

        return super.onKeyDown(keyCode, event); }

    FloatingActionButton fabBotao;
    CardView cvPesoAtual;
    CardView cvImc;
    ImageView imgCalculadora, imgBalanca, imgGrafico;
    ProgressBar barraProgresso;
    TextView tvpeso, tvImc, tvrestante;

    String[] listaPeso = {"63,00 kg", "65,00 kg"};
    String[] listaImc  = {"24,7", "25, 8"};
    String[] listaPesoDiferenca = {"+ 1,5 kg", "+ 0,5 kg"};
    String[] listaData = {"13/12/2000", "15/12/2000"};
    ListView lista;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    animaCVPesoAcende();
                    animaCVImcAcende();
                    animaApagaListView();
                    animaApagaImgBalanca();
                    animaApagaImgCalculadora();
                    animaApagaImgGrafico();
                    animaApagatvPeso();
                    animaApagatvImc();
                    animaApagatvRestante();

                    cvPesoAtual.setVisibility(View.VISIBLE);
                    cvImc.setVisibility(View.VISIBLE);
                    lista.setVisibility(View.INVISIBLE);
                    imgCalculadora.setVisibility(View.INVISIBLE);
                    imgBalanca.setVisibility(View.INVISIBLE);
                    imgGrafico.setVisibility(View.INVISIBLE);
                    tvpeso.setVisibility(View.INVISIBLE);
                    tvImc.setVisibility(View.INVISIBLE);
                    tvrestante.setVisibility(View.INVISIBLE);
//                    barraProgresso.setVisibility(View.VISIBLE);


                    return true;
                case R.id.navigation_dashboard:





                    BancoDeDados bd = new BancoDeDados(Principal.this, 1);
                    listaCustomizada listacustomizada = new listaCustomizada(bd.buscaHistorico(), Principal.this);
                    lista.setAdapter(listacustomizada);

                    lista.setVisibility(View.VISIBLE);
                    imgBalanca.setVisibility(View.VISIBLE);
                    imgCalculadora.setVisibility(View.VISIBLE);
                    imgGrafico.setVisibility(View.VISIBLE);
                    tvpeso.setVisibility(View.VISIBLE);
                    tvImc.setVisibility(View.VISIBLE);
                    tvrestante.setVisibility(View.VISIBLE);

                    animaCVPesoApaga();
                    animaCVImcApaga();
                    animaAcendeListView();
                    animaAcendeImgBalanca();
                    animaAcendeImgCalculadora();
                    animaAcendeImgGrafico();
                    animaAcendetvPeso();
                    animaAcendetvImc();
                    animaAcendetvRestante();




//                    barraProgresso.setVisibility(View.INVISIBLE);

                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        getSupportActionBar().setTitle("Seus dados");

        fabBotao = (FloatingActionButton) findViewById(R.id.fabAdicionar);
        cvPesoAtual = (CardView) findViewById(R.id.cvInformações);
        cvImc = (CardView) findViewById(R.id.cvImc);
        imgCalculadora = (ImageView) findViewById(R.id.imgCalculadora);
        imgBalanca = (ImageView) findViewById(R.id.imgBalanca);
        imgGrafico = (ImageView) findViewById(R.id.imgGrafico);
        tvpeso = (TextView) findViewById(R.id.tvPesoAtuaLista);
        tvImc = (TextView) findViewById(R.id.tvImcLista);
        tvrestante = (TextView) findViewById(R.id.tvPesoRestanteLista);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        lista = (ListView) findViewById(R.id.listaHistorico);


        animaCVPesoAcende();
        animaCVImcAcende();
        animaApagaListView();
        animaApagaImgBalanca();
        animaApagaImgCalculadora();
        animaApagaImgGrafico();



        //COLOCAR DADOS AQUI DENTRO DE CADA TEXTVIEW


        fabBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(Principal.this, telaAdicionarDados.class);
                startActivity(it);

            }
        });



    }


    public void animaCVPesoApaga() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cvPesoAtual, View.ALPHA, 1, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(300);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                cvPesoAtual.setVisibility(View.INVISIBLE);

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

    public void animaCVPesoAcende() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cvPesoAtual, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(300);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaCVImcApaga() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cvImc, View.ALPHA, 1, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(300);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                cvImc.setVisibility(View.INVISIBLE);
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

    public void animaCVImcAcende() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cvImc, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(300);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaAcendeListView() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(lista, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


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

    public void animaApagaListView() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(lista, View.ALPHA, 1, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


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

    public void animaAcendeImgBalanca() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgBalanca, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaApagaImgBalanca() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgBalanca, View.ALPHA, 1, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaAcendeImgCalculadora() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgCalculadora, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaApagaImgCalculadora() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgCalculadora, View.ALPHA, 1, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaApagaImgGrafico() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgGrafico, View.ALPHA, 1, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaAcendeImgGrafico() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imgGrafico, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaAcendetvPeso() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvpeso, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaApagatvPeso() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvpeso, View.ALPHA, 1, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaAcendetvImc() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvImc, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaApagatvImc() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvImc, View.ALPHA, 1, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaAcendetvRestante() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvrestante, View.ALPHA, 0, 1);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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

    public void animaApagatvRestante() {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvrestante, View.ALPHA, 1, 0);
        //Tempo, em milisegundos, da sua animação. Caso não coloque nenhum, o default é 300.
        objectAnimator.setDuration(300);
        /*Aqui esta a mágica. Você define o tempo (em milisegundos) para sua animação começar.
         * Ou seja, depois de 2 segundos, sua ImageView ira começar a desaparecer
         */
        objectAnimator.setStartDelay(600);

        //Caso você queira um Listener para o termino da animação
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

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
