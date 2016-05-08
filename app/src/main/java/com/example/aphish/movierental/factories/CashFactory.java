package com.example.aphish.movierental.factories;

import com.example.aphish.movierental.domain.Cash;
import com.example.aphish.movierental.domain.Credit;
import com.example.aphish.movierental.domain.PaymentType;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CashFactory {
    private  static CashFactory factory;

    private CashFactory(){}

    public static CashFactory getInstance(){
        if (factory == null){
            factory = new CashFactory();
        }
        return factory;
    }
    public static PaymentType getPaymentType(String value, double money, String name, String cardNumber){
        if ("Cash".equalsIgnoreCase(value)){
            return new Cash
                    .Builder()
                    .cashPayed(money)
                    .build();
        }else {
            return new Credit
                    .Builder()
                    .cardHolderName(name)
                    .cardNumber(cardNumber)
                    .Amount(money)
                    .build();
        }
    }

    public static Cash createCash(double money,String date){
        Cash cash = new Cash
                .Builder()
                .cashPayed(money)
                .date(date)
                .build();
        return cash;
    }

}
