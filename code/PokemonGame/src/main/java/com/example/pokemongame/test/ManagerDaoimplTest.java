package com.example.pokemongame.test;

import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.dao.ManagerDao;
import com.example.pokemongame.dao.impl.ManagerDaoimpl;
import org.junit.Test;

import java.util.List;

public class ManagerDaoimplTest {
    ManagerDao managerDao=new ManagerDaoimpl();
    @Test
    public void queryPokemonByMaster() {
        List<Pokemon> pokemonList = managerDao.queryPokemonByMaster("Tom");
        System.out.println(pokemonList);
    }

    @Test
    public void updatePokemon() {
        String[]skillnames=new String[4];
        int[]skillvalues=new int[4];
        skillnames[0]="水枪";
        skillvalues[0]=10;
        skillnames[1]="咬咬";
        skillvalues[1]=10;
        skillnames[2]="铁尾";
        skillvalues[2]=5;
        skillnames[3]="守住";
        skillvalues[3]=0;
        int i=managerDao.updatePokemonInTableAndField(new Pokemon("杰尼龟", 100, 1, "稀有", skillnames[0], skillvalues[0],
                skillnames[1],skillvalues[1],skillnames[2],skillvalues[2],skillnames[3],skillvalues[3],
                0,6,null,1,"Tom"),"杰尼龟");
        System.out.println(i);
    }

    @Test
    public void addPokemon() {
        String[]skillnames=new String[4];
        int[]skillvalues=new int[4];
        skillnames[0]="手里剑";
        skillvalues[0]=10;
        skillnames[1]="水枪";
        skillvalues[1]=10;
        skillnames[2]="影子分身";
        skillvalues[2]=0;
        skillnames[3]="电光一闪";
        skillvalues[3]=10;
        int i = managerDao.addPokemon(new Pokemon("呱呱泡蛙", 100, 1, "稀有",skillnames[0], skillvalues[0],
                skillnames[1],skillvalues[1],skillnames[2],skillvalues[2],skillnames[3],skillvalues[3], 0, 6,null,1,"admin"));
        System.out.println(i);
    }
    @Test
    public void deletePokemonByName(){
        System.out.println(managerDao.deletePokemonByName("小锯鳄"));
    }
    @Test
    public void queryPokemonByname(){
        System.out.println(managerDao.queryPokemonByname("小火龙"));
    }
    @Test
    public void queryAllPokemon(){
        System.out.println(managerDao.queryAllPokemon());
    }

}