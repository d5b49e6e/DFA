package com.qprofit.dfa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Francis
 */
public class Main {
    public static void main(String args[]){
        Transitions transitionFunction = new Transitions();

        transitionFunction.addTransition(0, 1, ".,",false);                
        transitionFunction.addTransition(0, 1, "0123456789",true);                
        transitionFunction.addTransition(1, 2, "0123456789",true);                        
        transitionFunction.addTransition(2, 3, "0123456789",true);
        transitionFunction.addTransition(2, 4, ",.",true);
        transitionFunction.addTransition(3, 4, ",.",true);
                
        transitionFunction.addTransition(4, 5, "0123456789",true);                        
        transitionFunction.addTransition(5, 6, "0123456789",true);                  
        transitionFunction.addTransition(6, 7, "0123456789",true);                          
        transitionFunction.addTransition(7, 4, ",.",false);
        

        Set<Integer> acceptingStates = new HashSet<>(Arrays.asList(4,5,6,7));
        DFA8 dfa = new DFA8(transitionFunction, 0, acceptingStates);

        String input = ",54.321,678";
        
        boolean res = dfa.matches(input);
        System.out.println(res);
        System.out.println(transitionFunction.getSb().reverse().toString());
        
    }
}
