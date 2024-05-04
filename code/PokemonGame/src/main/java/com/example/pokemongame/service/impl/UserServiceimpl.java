package com.example.pokemongame.service.impl;

import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.domain.User;
import com.example.pokemongame.dao.UserDao;
import com.example.pokemongame.dao.impl.UserDaoimpl;
import com.example.pokemongame.service.UserService;
import com.example.pokemongame.util.JedisUtils;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceimpl implements UserService {
    UserDao userDao = new UserDaoimpl();

    @Override
    public boolean regist(User user) {
        return userDao.saveUser(user) == 1;
    }

    @Override
    public User login(String username, String password) {
        return userDao.queryUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean exitsUser(String username) {
        return userDao.queryUserByUsername(username) != null;
    }

    @Override
    public boolean updateUserInfo(User user) {
        return userDao.updateUserInfo(user);
    }

    @Override
    public List<User> queryAllUser() {
        return userDao.qureyAllUser();
    }

    @Override
    public List<User> queryOnlineUser() {
        return userDao.queryOnlineUser();
    }

    @Override
    public List<User> queryOfflineUser() {
        return userDao.queryOfflineUser();
    }

    @Override
    public List<Pokemon> queryPokemonsByMaster(String master) {
        return userDao.queryPokemonsByMaster(master);
    }

    @Override
    public boolean updatePokemon(Pokemon pokemon) {
        return userDao.updatePokemon(pokemon);
    }

    public boolean addPokemon(Pokemon pokemon) {
        return userDao.addPokemon(pokemon);
    }

    @Override
    public boolean signIn(int userId, LocalDate date) {
        Jedis jedis = JedisUtils.getJedis();
        int year = date.getYear();
        int monthValue = date.getMonthValue();
        int dayOfMouth = date.getDayOfMonth();//用于计算偏移量
        String key = JedisUtils.signKey + userId + ":" + year + "-" + monthValue;
        jedis.setbit(key, dayOfMouth - 1, true);
        boolean isSignIn = jedis.getbit(key, dayOfMouth - 1);
        jedis.close();
        return isSignIn;
    }
    public boolean isSignIn(int userId, LocalDate date) {
        Jedis jedis = JedisUtils.getJedis();
        int year = date.getYear();
        int monthValue = date.getMonthValue();
        String key = JedisUtils.signKey + userId + ":" + year + "-" + monthValue;
        int dayOfMonth = date.getDayOfMonth();
        boolean isSignIn = jedis.getbit(key, dayOfMonth - 1);
        jedis.close();
        return isSignIn;
    }

    public int countContinuousSignInDays(int userId, LocalDate endDate) {
        Jedis jedis = JedisUtils.getJedis();
        int year = endDate.getYear();
        int monthValue = endDate.getMonthValue();
        int offset = endDate.getDayOfMonth() - 1;
        String key = JedisUtils.signKey + userId + ":" + year + "-" + monthValue;
        int count = 0;
        while (offset >= 0 && jedis.getbit(key, offset)) {
            count++;
            offset--;
        }
        return count;
    }

    public int maxContinuousCount(int userId, LocalDate endDate) {
        Jedis jedis = JedisUtils.getJedis();
        int year = endDate.getYear();
        int monthValue = endDate.getMonthValue();
        int offset = endDate.getDayOfMonth() - 1;
        String key = JedisUtils.signKey + userId + ":" + year + "-" + monthValue;
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i <= offset; i++) {
            if(jedis.getbit(key,i)){
                count++;
                if(count>maxCount)maxCount=count;
            }else count=0;
        }
        return maxCount;
    }

    public Map<Integer, Boolean> querySignInRecord(int userId, LocalDate endDate) {
        Jedis jedis = JedisUtils.getJedis();
        int year = endDate.getYear();
        int monthValue = endDate.getMonthValue();
        int offset = endDate.getDayOfMonth() - 1;
        String key = JedisUtils.signKey + userId + ":" + year + "-" + monthValue;
        Map<Integer, Boolean> signInRecord = new HashMap<>();
        for (int i = 0; i <= offset; i++) {
            boolean getbit = jedis.getbit(key, i);
            signInRecord.put(i + 1, getbit);
        }
        return signInRecord;
    }

    @Override
    public List<Announcement> queryAnnouncement() {
        return userDao.queryAnnouncement();
    }

    @Override
    public int queryIdByUsername(String username) {
        return userDao.queryIdByUsername(username);
    }

    @Override
    public void updatePokemonQueue(List<Pokemon> pokemons) {
        for (Pokemon pokemon : pokemons) {
            userDao.updatePokemon(pokemon);
        }
    }

    @Override
    public List<User> queryOtherUser(String myname) {
        return userDao.queryOtherUser(myname);
    }

}
