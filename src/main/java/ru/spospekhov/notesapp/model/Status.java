package ru.spospekhov.notesapp.model;

/**
 * ========== ItCorp v. 1.0 class library ==========
 * <p/>
 * http://www.it.ru/
 * <p/>
 * &copy; Copyright 1990-2017, by ItCorp.
 * <p/>
 * ========== Status.java ==========
 * <p/>
 * $Revision:  $<br/>
 * $Author:  $<br/>
 * $HeadURL:  $<br/>
 * $Id:  $
 * <p/>
 * 19.10.2017 17:30: Original version (spospekhov)<br/>
 */
public enum  Status {
    COMPLETE("Выполнена"),
    NOT_COMPLETE("Не выполнена"),
    ALL("Все");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
