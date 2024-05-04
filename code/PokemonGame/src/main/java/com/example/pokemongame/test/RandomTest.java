package com.example.pokemongame.test;

import com.example.pokemongame.domain.Pokemon;
import org.junit.Test;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Pokemon pokemon=new Pokemon("小火龙", 26, 2, "稀有", "火花", 6, "大字爆", 6, "喷射火焰", 6, "龙之怒", 6, 0, 24, null,1,"Tom");
        Random random=new Random();
        System.out.println(pokemon.randomSkill(random));
    }
    @Test
    public void Test(){
        Random random=new Random();
        System.out.println(random.nextDouble());
    }
}
