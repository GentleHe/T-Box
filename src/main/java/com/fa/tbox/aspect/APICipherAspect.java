package com.fa.tbox.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Level;
import java.util.logging.Logger;

//@Component
@Aspect
public class APICipherAspect {

    private static Logger logger = Logger.getLogger(APICipherAspect.class.getSimpleName());

    @Around(value = "execution(* com.fa.tbox.controller.* (..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println();

        Object object = pjp.proceed();

        logger.log(Level.INFO, "");


        return object;
    }

}
