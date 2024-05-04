package com.example.pokemongame.test;

import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.domain.User;
import com.example.pokemongame.dao.UserDao;
import com.example.pokemongame.dao.impl.UserDaoimpl;
import com.example.pokemongame.service.UserService;
import com.example.pokemongame.service.impl.UserServiceimpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceimplTest {
    UserService userService=new UserServiceimpl();
    UserDao userDao=new UserDaoimpl();
    @Test
    public void regist() {
        User user = new User(0,"彭于晏4", "123456", "123456@qq.com",1,"","",null,1);
        userService.regist(user);
    }

    @Test
    public void login() {
        System.out.println(userService.login("abc","abc"));
    }

    @Test
    public void exitsUser() {
        if(userService.exitsUser("Tom"))
            System.out.println("用户已存在");
        else System.out.println("用户不存在");
    }
    @Test
    public void TestQueue()
    {
        List<Pokemon> pokemons = userDao.queryPokemonsByMaster("testbbbb");
        System.out.println(pokemons);
    }
}