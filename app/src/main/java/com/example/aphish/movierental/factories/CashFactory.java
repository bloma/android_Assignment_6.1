package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Cash;
import com.example.aphish.movierental.domain.Credit;
import com.example.aphish.movierental.domain.PaymentType;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CashFactory {
    private  static CashFactory factory = null;

    public CashFactory(){}

    public static CashFactory getInstance(){
        if (factory == null){
            factory = new CashFactory();
        }
        return factory;
    }

    public static Cash createCash(Long id,double money,String date){
        Cash cash = new Cash
                .Builder()
                .id(id)
                .cashPayed(money)
                .date(date)
                .build();
        return cash;
    }

}
