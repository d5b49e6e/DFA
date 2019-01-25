package com.qprofit.dfa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Francis
 */
public class DFA8 {
       
    private final Transitions transitions;
    private final int startState;
    private final Set<Integer> acceptingStates;

    public DFA8(Transitions transitions,int startState,Set<Integer> acceptingStates) {
        this.transitions = Objects.requireNonNull(transitions,
                                       "Transition function is null.");
        this.startState = startState;
        this.acceptingStates = Objects.requireNonNull(acceptingStates,
                                       "Accepting state set is null.");
    }

    public boolean matches(String text) {
        return transitions.processAll(startState,text.toCharArray())
                .map(acceptingStates::contains)
                .orElse(false)
                ;        
    }
}
