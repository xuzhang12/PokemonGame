package com.example.pokemongame.service;

import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Manager;
import com.example.pokemongame.domain.Pokemon;

import java.util.List;

public interface ManagerService {
    /**
     *
     * @param name 管理员名
     * @param password 管理员密码
     * @return 查询成功返回对应的Manager对象，否则返回null
     */
    public Manager login(String name, String password);
    /**
     *
     * @param master 宠物拥有者
     * @return  查看该用户拥有的宠物的信息，返回值是其宠物的集合
     */
    public List<Pokemon> queryPokemonByMaster(String master);
    /**
     *
     * @param pokemon 要更新资料的宠物
     * @param pastname 宠物原来的名字（因为可能会修改到宠物名，所以需要原来的宠物名）
     * @return 返回影响的行数
     */
    public int updatePokemonInTable(Pokemon pokemon,String pastname);
    /**
     *
     * @param pokemon 要更新资料的宠物
     * @param pastname 宠物原来的名字（因为可能会修改到宠物名，所以需要原来的宠物名）
     */
    public void updatePokemonOfUser(Pokemon pokemon,String pastname);
    /**
     *
     * @param pokemon 要保存资料的宠物
     * @return 返回影响的行数
     */
    public int addPokemon(Pokemon pokemon);

    /**
     *
     * @param name 要删除的宠物的名字
     * @return 返回影响的行数
     */
    public int deletePokemonByName(String name);

    /**
     *
     * @param name 要查询的宠物名
     * @return 返回查询到的宠物集合
     */
    public List<Pokemon> queryPokemonByname(String name);
    /**
     *
     * @param name 宠物在宠物表中的宠物名
     * @return 返回单个宠物
     */
    public Pokemon queryOnePokemonByname(String name);

    /**
     *
     * @return 返回所有宠物图鉴
     */
    public List<Pokemon> queryAllPokemon();

    /**
     *
     * @return 返回在宠物园中的宠物的集合
     */
    public List<Pokemon> queryPokemonInField();

    /**
     *
     * @param name 宠物名
     * @param grade 宠物等级
     * @return 返回宠物园中对应宠物名对应等级的单个宠物
     */
    public Pokemon queryPokemonByNameAndGrade(String name,int grade);

    /**
     *
     * @param message 要发布的公告消息
     * @return 发布成功返回true，失败返回false
     */
    public boolean editAnnouncement(String message);

    /**
     *
     * @return 返回所有已发布的公告
     */
    public List<Announcement> queryAllAnnouncement();
}
