package com.example.pokemongame.dao.impl;

import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.domain.User;
import com.example.pokemongame.dao.BaseDao;
import com.example.pokemongame.dao.UserDao;

import java.util.List;

public class UserDaoimpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from user where name=?";
        return queryForone(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where name=? and password=?";
        return queryForone(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user(name,password,email,online,nickname,signature,birthday,open) values(?,?,?,?,?,?,?,?)";
        return update(sql, user.getName(), user.getPassword(), user.getEmail(), user.isonline(), user.getNickname(), user.getSignature(), user.getBirthday(),user.getOpen());
    }

    @Override
    public boolean updateUserInfo(User user) {
        String sql = "update user set online=?,nickname=?,signature=?,birthday=?,open=? where name=?";
        return update(sql,user.getOnline(), user.getNickname(), user.getSignature(), user.getBirthday(),user.getOpen(), user.getName())!=-1;
    }

    @Override
    public List<User> qureyAllUser() {
        String sql = "select * from user where name!=?";
        return queryForList(User.class, sql, "admin");
    }


    @Override
    public List<User> queryOnlineUser() {
        String sql = "select * from user where name!=? and online=?";
        return queryForList(User.class, sql, "admin",1);
    }

    @Override
    public List<User> queryOfflineUser() {
        String sql = "select * from user where name!=? and online!=?";
        return queryForList(User.class, sql, "admin",1);
    }

    @Override
    public List<Pokemon> queryPokemonsByMaster(String master) {
        String sql="select * from pokemon where master=? order by queue asc";
        return queryForList(Pokemon.class,sql,master);
    }
    public boolean updatePokemon(Pokemon pokemon)
    {
        String sql = "update Pokemon set blood=?,grade=?,skillvalue_1=?,skillvalue_2=?,skillvalue_3=?,skillvalue_4=?," +
                "experience=?,expSlot=?,queue=? where master=? and name=?";
        return (update(sql,pokemon.getBlood(),pokemon.getGrade(),pokemon.getSkillvalue_1(),pokemon.getSkillvalue_2(),
                pokemon.getSkillvalue_3(),pokemon.getSkillvalue_4(),pokemon.getExperience(),pokemon.getExpSlot(),
                pokemon.getQueue(),pokemon.getMaster(),pokemon.getName())!=-1);
    }
    public boolean addPokemon(Pokemon pokemon)
    {
        String sql="insert into pokemon values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return (update(sql,pokemon.getName(),pokemon.getBlood(),pokemon.getGrade(),pokemon.getRarity(),
                        pokemon.getSkillname_1(),pokemon.getSkillvalue_1(),pokemon.getSkillname_2(),pokemon.getSkillvalue_2(),
                        pokemon.getSkillname_3(),pokemon.getSkillvalue_3(),pokemon.getSkillname_4(),pokemon.getSkillvalue_4(),
                        pokemon.getExperience(),pokemon.getExpSlot(),pokemon.getImg_path(),pokemon.getQueue(),pokemon.getMaster())!=-1);
    }

    @Override
    public int queryIdByUsername(String username) {
        String sql="select id from user where name=?";
        return (int) queryForSingleValue(sql, username);
    }

    @Override
    public List<User> queryOtherUser(String myname) {
        String sql="select * from user where name!=? and name!=? and open=?";
        return queryForList(User.class,sql,"admin",myname,1);
    }

    @Override
    public List<Announcement> queryAnnouncement() {
        String sql="select * from announcement order by day desc";
        return queryForList(Announcement.class,sql);
    }
}
