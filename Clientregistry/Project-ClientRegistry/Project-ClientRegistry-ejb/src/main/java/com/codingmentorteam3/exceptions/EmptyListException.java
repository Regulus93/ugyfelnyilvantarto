package com.codingmentorteam3.exceptions;

/**
 *
 * @author Zsolt
 */
public class EmptyListException extends QueryException {

    public EmptyListException() {
//        constructor with no arguments
    }

    public EmptyListException(String message) {
        super(message);
    }

}
