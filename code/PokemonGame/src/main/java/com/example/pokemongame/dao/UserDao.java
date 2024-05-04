package com.example.pokemongame.dao;

import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.domain.User;

import java.util.List;

public interface UserDao {
    /**
     *
     * @param username
     * @return 返回匹配的User对象，否则返回null
     */
    public User queryUserByUsername(String username);
    /**
     *
     * @param username
     * @param password
     * @return 返回匹配的User对象，否则返回null
     */
    public User queryUserByUsernameAndPassword(String username,String password);
    /**
     *
     * @param user
     * @return 返回影响的行数，否则返回-1
     */
    public int saveUser(User user);
    /**
     *
     * @param user 更新后的用户信息
     * @return 返回true修改成功，false失败
     */
    public boolean updateUserInfo(User user);

    /**
     *
     * @return 返回所有已经注册的用户
     */
    public List<User> qureyAllUser();

    /**
     *
     * @return 返回所有在线的用户
     */
    public List<User> queryOnlineUser();

    /**
     *
     * @return 返回所有离线的用户
     */
    public List<User> queryOfflineUser();

    /**
     *
     * @param master 根据宠物拥有者查询其宠物
     * @return 返回该用户的宠物
     */
    public List<Pokemon> queryPokemonsByMaster(String master);

    /**
     *
     * @param pokemon 属性变更后的宠物（名字不变）
     * @return 更新成功返回true，否则返回false
     */
    public boolean updatePokemon(Pokemon pokemon);

    /**
     *
     * @param pokemon 新获得的宠物
     * @return 插入数据成功返回true，否则返回false
     */
    public boolean addPokemon(Pokemon pokemon);

    /**
     *
     * @param username 用户名
     * @return 返回该用户的id
     */
    public int queryIdByUsername(String username);

    /**
     *
     * @param myname 登录的用户的用户名
     * @return 返回除该用户和admin之外的其他公开的用户
     */
    public List<User> queryOtherUser(String myname);

    /**
     *
     * @return 返回公告的List集合（按日期从新到久排序）
     */
    public List<Announcement> queryAnnouncement();
}
