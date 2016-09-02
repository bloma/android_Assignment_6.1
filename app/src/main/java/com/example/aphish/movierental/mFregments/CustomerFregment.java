package com.example.aphish.movierental.mFregments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aphish.movierental.R;
import com.example.aphish.movierental.domain.Customers;
import com.example.aphish.movierental.services.CustomerService;
import com.example.aphish.movierental.services.impl.CustomerServiceImpl;

/**
 * Created by Aphish on 2016/09/01.
 */
public class CustomerFregment extends Fragment {

CustomerService service = new CustomerServiceImpl();
    Customers customer;
    EditText name;
    EditText surname;
    EditText age;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        // force connection to open
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final View rootView = inflater.inflate(R.layout.customer_fragment,container,false);
        Button save = (Button) rootView.findViewById(R.id.btnAddCustomer);
        Button edit = (Button) rootView.findViewById(R.id.btnEditCustomer);
        Button delete = (Button) rootView.findViewById(R.id.btnDeleteCustomer);

        //delete Customer
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

                alertDialog.setTitle("Confirm Delete....");

                alertDialog.setMessage("Are you sure you want to delete Customer ?");

                alertDialog.setPositiveButton("YES",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText id = (EditText) rootView.findViewById(R.id.txtAge);

                        if (!id.getText().toString().isEmpty()){

                            Customers customersdel = new Customers.Builder()
                                    .id(customer.getId())
                                    .customerName(customer.getName())
                                    .customeruSurname(customer.getSurname())
                                    .customerAge(customer.getAge())
                                    .build();

                            service.delete(customersdel);

                            Toast.makeText(getContext(),"Customer Deleted"+"\n"+
                            customersdel.getId()+customersdel.getName(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        //Edit Customer
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    EditText id = (EditText)rootView.findViewById(R.id.txtCustomerAge);
                    name = (EditText) rootView.findViewById(R.id.txtCustomerName);
                    surname = (EditText) rootView.findViewById(R.id.txtCustomerSurname);
                    age = (EditText) rootView.findViewById(R.id.txtCustomerAge);
                    Customers customers = service.findByID(Long.parseLong(id.getText().toString()));

                    if (!id.getText().toString().isEmpty()) {
                        name.setText(customers.getName());
                        surname.setText(customers.getSurname());
                        age.setText(customers.getAge());
                    }
                }catch(Exception e){
                    Toast.makeText(getContext(),"Customer does not exit.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Save Customer
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name = (EditText) rootView.findViewById(R.id.txtCustomerName);
                surname =(EditText) rootView.findViewById(R.id.txtCustomerSurname);
                age = (EditText) rootView.findViewById(R.id.txtCustomerAge);

                try{
                    Customers customer = new Customers.Builder()
                            .id(1L)
                            .customerName(name.getText().toString())
                            .customeruSurname(surname.getText().toString())
                            .customerAge(age.getText().toString())
                            .build();

                    String response = String.valueOf(service.save(customer));

                    name.getText().clear();
                    surname.getText().clear();
                    age.getText().clear();

                    Toast.makeText(getContext(),"Customer asved",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getContext(),"Could not save",Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }

}
