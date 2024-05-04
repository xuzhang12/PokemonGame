package com.example.pokemongame.dao;

import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Manager;
import com.example.pokemongame.domain.Pokemon;

import java.util.Date;
import java.util.List;

public interface ManagerDao {
    /**
     *
     * @param adminName 管理员名
     * @param password 管理员密码
     * @return 查询成功返回对应的Manager对象，否则返回null
     */
    public Manager queryByAdminNameAndPassword(String adminName, String password);
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
     * @param pastname 更新信息前的宠物名
     * @param pokemon 要更新资料的宠物
     * @return 返回影响的行数
     */
    public int updatePokemonInTableAndField(Pokemon pokemon,String pastname);

    /**
     *
     * @param pokemon 更新了属性之后的宠物
     * @param pastname 因为可能更改了宠物名，为了修改对应的数据，需要用到原来的名字
     */
    public void updatePokemonOfUser(Pokemon pokemon,String pastname);
    /**
     *
     * @param name 要查询的宠物名
     * @return 返回查询到的数量
     */
    public List<Pokemon> queryPokemonByname(String name);

    /**
     *
     * @param name 宠物在宠物表中的宠物名
     * @return 返回单个宠物
     */
    public Pokemon queryOnePokemonByname(String name);

    /**
     *  我在pokemon表中添加了名为“admin”的用户，其宠物属性都是基础的，作为宠物图鉴
     * @return 返回所有宠物图鉴
     */
    public List<Pokemon> queryAllPokemon();
    /**
     *
     * @param master 主人
     * @return  通过master（宠物拥有者）查看其宠物的信息，返回值是其宠物的集合
     */
    public List<Pokemon> queryPokemonByMaster(String master);

    /**
     *
     * @return 返回在宠物园中的所有宠物
     */
    public List<Pokemon> queryPokemonInField();

    /**
     *
     * @param name 宠物名
     * @param grade 宠物等级
     * @return 返回值是宠物园中对应的宠物
     */
    public Pokemon queryPokeomnByNameAndGrade(String name,int grade);

    /**
     *
     * @param day 公告发布日期
     * @param message 公告消息
     * @return 发布成功返回true，否则返回false
     */
    public boolean editAnnouncement(Date day,String message);

    /**
     *
     * @return 返回所有公告
     */
    public List<Announcement> queryAllAnnouncement();
}
