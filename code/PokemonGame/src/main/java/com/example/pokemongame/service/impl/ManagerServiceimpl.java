package com.example.pokemongame.service.impl;

import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Manager;
import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.dao.ManagerDao;
import com.example.pokemongame.dao.impl.ManagerDaoimpl;
import com.example.pokemongame.service.ManagerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ManagerServiceimpl implements ManagerService {
    ManagerDao managerDao=new ManagerDaoimpl();

    @Override
    public Manager login(String name, String password) {
        return managerDao.queryByAdminNameAndPassword(name, password);
    }

    @Override
    public List<Pokemon> queryPokemonByMaster(String master) {
        return managerDao.queryPokemonByMaster(master);
    }

    @Override
    public int updatePokemonInTable(Pokemon pokemon,String pastname) {
        return managerDao.updatePokemonInTableAndField(pokemon,pastname);
    }

    @Override
    public void updatePokemonOfUser(Pokemon pokemon, String pastname) {
        managerDao.updatePokemonOfUser(pokemon,pastname);
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        return managerDao.addPokemon(pokemon);
    }

    @Override
    public int deletePokemonByName(String name) {
        return managerDao.deletePokemonByName(name);
    }

    @Override
    public List<Pokemon> queryPokemonByname(String name) {
        return managerDao.queryPokemonByname(name);
    }

    @Override
    public Pokemon queryOnePokemonByname(String name) {
        return managerDao.queryOnePokemonByname(name);
    }

    @Override
    public List<Pokemon> queryAllPokemon() {
        return managerDao.queryAllPokemon();
    }

    @Override
    public List<Pokemon> queryPokemonInField() {
        return managerDao.queryPokemonInField();
    }

    @Override
    public Pokemon queryPokemonByNameAndGrade(String name,int grade) {
        return managerDao.queryPokeomnByNameAndGrade(name,grade);
    }

    @Override
    public boolean editAnnouncement(String message) {
        int year = LocalDate.now().getYear();
        int monthValue = LocalDate.now().getMonthValue();
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = sdf.parse(year+"-"+monthValue+"-"+dayOfMonth);
            return managerDao.editAnnouncement(date,message);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Announcement> queryAllAnnouncement() {
        return managerDao.queryAllAnnouncement();
    }

//    @Override
//    public List<User_Pokemon> queryUserAndPokemon() {
//        return managerDao.queryUserAndPokemon();
//    }
}
