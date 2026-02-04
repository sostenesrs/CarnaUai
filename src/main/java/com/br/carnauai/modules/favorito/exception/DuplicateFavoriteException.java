package com.br.carnauai.modules.favorito.exception;

public class DuplicateFavoriteException extends RuntimeException {
    public DuplicateFavoriteException() { super(); }
    public DuplicateFavoriteException(String message) { super(message); }
}
