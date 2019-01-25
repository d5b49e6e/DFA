package com.qprofit.dfa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 *
 * @author Francis
 */
public class Transitions {
    private final Map<Integer, Map<Character, Integer>> function = 
          new HashMap<>();
    private final Map<Integer,Boolean> saveMap = new HashMap();
    
    private StringBuilder sb = new StringBuilder();
    
    

    public void addTransition(Integer startState, 
                              Integer goalState,
                              String characters,
                              Boolean save
                              
    )
    {        
        
        for(char c:characters.toCharArray()){
            
            
            saveMap.put(startState,save) ;
            
            function.computeIfAbsent(startState,k -> new HashMap<>())
                    .put(c, goalState);
        }
    }

    public Optional<Integer> process(Integer startState, char character) {        
        if(saveMap.get(startState)){
            sb.append(character);
        }
        return Optional.ofNullable(
                function.getOrDefault(startState,Collections.emptyMap())
                    .get(character)
                
        );
    }
    
    public Optional<Integer> processAll(Integer startState,char[] characters){
        sb = new StringBuilder();
        
        return IntStream.range(0,characters.length)
                .boxed()
                .reduce(                        
                        Optional.of(startState),
                        (o,i) -> o.flatMap(s->process(s,characters[i])),
                        (o1,o2) -> o1.map(Optional::of).orElse(o2)                        
                );
    }

    public StringBuilder getSb() {
        return sb;
    }
    
    
}
