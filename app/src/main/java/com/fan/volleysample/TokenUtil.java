package com.fan.volleysample;

import java.lang.reflect.Type;

//封装getType()操作
    public class TokenUtil{
        public static <E> Type getType(){
            return new MyTypeToken<E>() {}.getType();
        }
    }
