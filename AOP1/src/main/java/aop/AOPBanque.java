package aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPBanque {

    @Pointcut("execution(* metier.MetierBanqueImplementation.*(..))")
    public void pc1(){}

    @Pointcut("execution(metier.Compte.new(..))")
    public void pc2(){}

    @Around("pc2()")
    public void onInit(){
        System.out.println("new compte");
    }

    @Before("pc1()")
    public void before(){
        System.out.println("---------------------------------------------");
        System.out.println("Before");
        System.out.println("---------------------------------------------");
    }

}
