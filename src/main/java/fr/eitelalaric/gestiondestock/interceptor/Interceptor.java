package fr.eitelalaric.gestiondestock.interceptor;

import org.hibernate.EmptyInterceptor;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {

        //TODO  insert the company ID in the sql before the execution
        return super.onPrepareStatement(sql);
    }
}
