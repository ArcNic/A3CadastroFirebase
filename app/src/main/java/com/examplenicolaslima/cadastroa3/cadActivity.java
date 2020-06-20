package com.examplenicolaslima.cadastroa3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadActivity extends AppCompatActivity {

    private EditText editProduto, editPreco;
    private Button cadButton;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        editProduto = findViewById(R.id.editProduto);
        editPreco = findViewById(R.id.editPreco);
        cadButton = findViewById(R.id.cadButton);

        cadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaCadastro();
            }
        });
    }

    public void salvaCadastro(){
        String nProduto = editProduto.getText().toString();
        String preco = editPreco.getText().toString();


        if(!nProduto.isEmpty() && !preco.isEmpty()){

            Produto produto = new Produto();
            produto.nome = nProduto;
            produto.preco = preco;

            mDatabase = FirebaseDatabase.getInstance();
            mReference = mDatabase.getReference();
            mReference.child("produto").push().setValue( produto );
            finish();

        }

    }

}
