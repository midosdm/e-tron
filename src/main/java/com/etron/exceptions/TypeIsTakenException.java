package com.etron.exceptions;

import org.springframework.util.StringUtils;

public class TypeIsTakenException extends RuntimeException{
    public TypeIsTakenException(Class clazz, String type) {
        super(generateMessage(clazz.getSimpleName(), type));
    }

    private static String generateMessage(String entity, String type) {
        return " the type" + type +" already exists " + StringUtils.capitalize(entity);
    }
}

