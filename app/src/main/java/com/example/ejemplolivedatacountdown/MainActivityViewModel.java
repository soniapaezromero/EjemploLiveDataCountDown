package com.example.ejemplolivedatacountdown;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.net.ConnectException;
import java.net.HttpCookie;

public class MainActivityViewModel extends ViewModel {

    public MutableLiveData <Integer> seconds= new MutableLiveData();
    public MutableLiveData <Long>timerValue=new MutableLiveData();
    public MutableLiveData <Boolean> finish=new MutableLiveData();
    public CountDownTimer contador;

    public LiveData getSeconds() {
        return(LiveData)this.seconds;
    }

    public void setSeconds(MutableLiveData<Integer> seconds) {
        this.seconds = seconds;
    }

    public MutableLiveData<Long> getTimerValue() {
        return timerValue;
    }

    public void setTimerValue(MutableLiveData<Long> timerValue) {
        this.timerValue = timerValue;
    }

    public LiveData getFinish() {

        return (LiveData)this.finish;
    }

    public void setFinish(MutableLiveData<Boolean> finish) {
        this.finish = finish;
    }
   // Creamos el contador
    public final void empezarContador() {
        contador= new CountDownTimer(timerValue.getValue().longValue(),1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long timeLeft = millisUntilFinished / 1000;
                seconds.setValue((int)timeLeft);
            }

            @Override
            public void onFinish() {
                seconds.setValue(0);
                finish.setValue(true);
            }
        }.start();
        this.contador=contador;
    }
    //Terminamos  el contador
    public final void finalizarContador() {
        contador = this.contador;
        this.contador.cancel();
    }

}

