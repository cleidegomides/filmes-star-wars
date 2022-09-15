package com.nttdata.desafiostarwars.exception;

public class FilmNotFoundException extends Exception {

    public FilmNotFoundException(Long id) {
        super(String.format("Filme não encontrado para o Id", id));
    }
}
