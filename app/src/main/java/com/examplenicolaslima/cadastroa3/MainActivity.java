package com.examplenicolaslima.cadastroa3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import static com.examplenicolaslima.cadastroa3.R.id.lProdutos;

public class MainActivity<mDatabase> extends AppCompatActivity {

    private ListView lProdutos;
    private List<Produto> listaProdutos;
    private  FirebaseDatabase database;
    private DatabaseReference reference;
    private ChildEventListener childEventListener;
    private Query query;
    private ArrayAdapter<Produto> adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lProdutos = findViewById(R.id.lProdutos);

        listaProdutos = new ArrayList<>();
        adapter = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1, listaProdutos);
        lProdutos.setAdapter( adapter );


    }


    @Override
    public void onStart() {
        super.onStart();

        listaProdutos.clear();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        query = reference.child("produto");
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Produto p = new Produto();
                p.id = dataSnapshot.getKey();
                p.nome = dataSnapshot.child("nome").getValue(String.class);
                p.preco = dataSnapshot.child("preco").getValue(String.class);

                listaProdutos.add(p);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        query.addChildEventListener( childEventListener);
    }


    @Override
    protected void onStop() {
        super.onStop();

        query.removeEventListener( childEventListener );
    }

    public void startcadActicity(View view){

        Intent cadActivity = new Intent(this, com.examplenicolaslima.cadastroa3.cadActivity.class);
        startActivity(cadActivity);

    }
}
