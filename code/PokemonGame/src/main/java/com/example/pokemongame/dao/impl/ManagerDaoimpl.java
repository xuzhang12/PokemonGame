package com.example.pokemongame.dao.impl;

import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Manager;
import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.dao.BaseDao;
import com.example.pokemongame.dao.ManagerDao;

import java.util.Date;
import java.util.List;

public class ManagerDaoimpl extends BaseDao implements ManagerDao {
    @Override
    public List<Pokemon> queryPokemonByMaster(String master) {
        String sql = "select * from Pokemon where master=? order by queue asc";
        return queryForList(Pokemon.class, sql, master);
    }

    @Override
    public List<Pokemon> queryPokemonInField() {
        String sql = "select * from pokemon_field order by name,grade asc";
        return queryForList(Pokemon.class, sql);
    }

    @Override
    public Pokemon queryPokeomnByNameAndGrade(String name, int grade) {
        String sql = "select * from pokemon_field where name=? and grade=?";
        return queryForone(Pokemon.class, sql, name, grade);
    }

    @Override
    public boolean editAnnouncement(Date day,String message) {
        String sql="insert into announcement values(?,?)";
        if (day!=null)return update(sql,day,message)!=-1;
        else return false;
    }

    @Override
    public List<Announcement> queryAllAnnouncement() {
        String sql="select * from announcement order by day desc";
        return queryForList(Announcement.class,sql);
    }

    @Override
    public int updatePokemonInTableAndField(Pokemon pokemon, String pastname) {
        String updatesql1 = "update Pokemon set name=?,blood=?,grade=?,rarity=?,skillname_1=?,skillvalue_1=?," +
                "skillname_2=?,skillvalue_2=?,skillname_3=?,skillvalue_3=?,skillname_4=?,skillvalue_4=?," +
                "experience=?,expSlot=?,img_path=?,queue=? where master=? and name=?";
        String deletesql = "delete from pokemon_field where name=?";
        String updatesql2 = "insert into pokemon_field values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String skillname_1 = pokemon.getSkillname_1();
        int skillvalue_1 = pokemon.getSkillvalue_1();
        String skillname_2 = pokemon.getSkillname_2();
        int skillvalue_2 = pokemon.getSkillvalue_2();
        String skillname_3 = pokemon.getSkillname_3();
        int skillvalue_3 = pokemon.getSkillvalue_3();
        String skillname_4 = pokemon.getSkillname_4();
        int skillvalue_4 = pokemon.getSkillvalue_4();
        pokemon.updateExpSlot(pokemon.getRarity(), pokemon.getGrade());
        update(updatesql1, pokemon.getName(), pokemon.getBlood(), pokemon.getGrade(), pokemon.getRarity(),
                skillname_1, skillvalue_1, skillname_2, skillvalue_2, skillname_3, skillvalue_3,
                skillname_4, skillvalue_4, pokemon.getExperience(), pokemon.getExpSlot(), pokemon.getImg_path(),
                pokemon.getQueue(), "admin", pastname);
        update(deletesql, pastname);
        for (int i = 1; i <= 5; i++) {
            pokemon.setGrade(i);
            pokemon.updateExpSlot(pokemon.getRarity(), pokemon.getGrade());
            pokemon.updateBlood(pokemon.getName(), pokemon.getGrade());
            pokemon.updateAttack(pokemon.getName(), pokemon.getGrade());
            update(updatesql2, pokemon.getName(), pokemon.getBlood(), pokemon.getGrade(), pokemon.getRarity(),
                    pokemon.getSkillname_1(), pokemon.getSkillvalue_1(), pokemon.getSkillname_2(), pokemon.getSkillvalue_2(),
                    pokemon.getSkillname_3(), pokemon.getSkillvalue_3(), pokemon.getSkillname_4(), pokemon.getSkillvalue_4(),
                    pokemon.getExperience(), pokemon.getExpSlot(), pokemon.getImg_path(), pokemon.getQueue(), pokemon.getMaster());
        }
        return 1;
    }

    @Override
    public void updatePokemonOfUser(Pokemon pokemon, String pastname) {
        String querysql = "select * from pokemon where name=? and master!=?";
        String updatesql = "update Pokemon set name=?,blood=?,rarity=?,skillname_1=?,skillvalue_1=?," +
                "skillname_2=?,skillvalue_2=?,skillname_3=?,skillvalue_3=?,skillname_4=?,skillvalue_4=?," +
                "expSlot=?,img_path=?,queue=? where master=? and name=?";
        List<Pokemon> pokemonList = queryForList(Pokemon.class, querysql, pastname, "admin");
        for (Pokemon pokemon1 : pokemonList) {
            pokemon1.setName(pokemon.getName());
            pokemon1.setSkillname_1(pokemon.getSkillname_1());
            pokemon1.setSkillname_2(pokemon.getSkillname_2());
            pokemon1.setSkillname_3(pokemon.getSkillname_3());
            pokemon1.setSkillname_4(pokemon.getSkillname_4());
            pokemon1.setRarity(pokemon.getRarity());
            pokemon1.updateExpSlot(pokemon1.getRarity(), pokemon1.getGrade());
            pokemon1.updateBlood(pokemon1.getName(), pokemon1.getGrade());
            pokemon1.updateAttack(pokemon1.getName(), pokemon1.getGrade());
            update(updatesql, pokemon1.getName(), pokemon1.getBlood(), pokemon1.getRarity(), pokemon1.getSkillname_1(), pokemon1.getSkillvalue_1(),
                    pokemon1.getSkillname_2(), pokemon1.getSkillvalue_2(), pokemon1.getSkillname_3(), pokemon1.getSkillvalue_3(),
                    pokemon1.getSkillname_4(), pokemon1.getSkillvalue_4(), pokemon1.getExpSlot(), pokemon1.getImg_path(),
                    pokemon.getQueue(),pokemon1.getMaster(), pastname);
        }
    }

    @Override
    public List<Pokemon> queryPokemonByname(String name) {
        String sql = "select * from Pokemon where name=?";
        return queryForList(Pokemon.class, sql, name);
    }

    @Override
    public Pokemon queryOnePokemonByname(String name) {
        String sql = "select * from pokemon where master=? and name=?";
        return queryForone(Pokemon.class, sql, "admin", name);
    }

    @Override
    public List<Pokemon> queryAllPokemon() {
        String sql = "select * from Pokemon where master=?";
        return queryForList(Pokemon.class, sql, "admin");
    }

    @Override
    public Manager queryByAdminNameAndPassword(String name, String password) {
        String sql = "select * from manager where name=? and password=?";
        return queryForone(Manager.class, sql, name, password);
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        String sql1 = "insert into Pokemon values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String sql2 = "insert into pokemon_field values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String skillname_1 = pokemon.getSkillname_1();
        int skillvalue_1 = pokemon.getSkillvalue_1();
        String skillname_2 = pokemon.getSkillname_2();
        int skillvalue_2 = pokemon.getSkillvalue_2();
        String skillname_3 = pokemon.getSkillname_3();
        int skillvalue_3 = pokemon.getSkillvalue_3();
        String skillname_4 = pokemon.getSkillname_4();
        int skillvalue_4 = pokemon.getSkillvalue_4();
        pokemon.updateExpSlot(pokemon.getRarity(), pokemon.getGrade());
        update(sql1, pokemon.getName(), pokemon.getBlood(), pokemon.getGrade(), pokemon.getRarity(),
                skillname_1, skillvalue_1, skillname_2, skillvalue_2,
                skillname_3, skillvalue_3, skillname_4, skillvalue_4,
                pokemon.getExperience(), pokemon.getExpSlot(), pokemon.getImg_path(),pokemon.getQueue(), "admin");
        for (int i = 1; i <= 5; i++) {
            pokemon.setGrade(i);
            pokemon.updateExpSlot(pokemon.getRarity(), pokemon.getGrade());
            pokemon.updateBlood(pokemon.getName(), pokemon.getGrade());
            pokemon.updateAttack(pokemon.getName(), pokemon.getGrade());
            update(sql2, pokemon.getName(), pokemon.getBlood(), pokemon.getGrade(), pokemon.getRarity(),
                    pokemon.getSkillname_1(), pokemon.getSkillvalue_1(), pokemon.getSkillname_2(), pokemon.getSkillvalue_2(),
                    pokemon.getSkillname_3(), pokemon.getSkillvalue_3(), pokemon.getSkillname_4(), pokemon.getSkillvalue_4(),
                    pokemon.getExperience(), pokemon.getExpSlot(), pokemon.getImg_path(),pokemon.getQueue(), "admin");
        }
        return 1;
    }

    @Override
    public int deletePokemonByName(String name) {
        String sql1 = "delete from Pokemon where name=?";
        String sql2 = "delete from pokemon_field where name=?";
        return update(sql1, name) + update(sql2, name);
    }
}
