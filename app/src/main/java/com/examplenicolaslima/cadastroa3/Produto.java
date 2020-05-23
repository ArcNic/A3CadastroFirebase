package com.examplenicolaslima.cadastroa3;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

public class Produto {

    public String id;
    public String nome;
    public String preco;

    @NonNull
    @Override
    public String toString() {
        return "Produto: "+ nome + "  Preço: " + preco;
    }
}
