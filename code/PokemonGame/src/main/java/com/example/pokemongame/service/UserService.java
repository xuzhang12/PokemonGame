package com.example.pokemongame.service;

import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     *
     * @param user 要注册的用户
     * @return 返回true表示注册成功，false表示该用户名已被使用
     */
    public boolean regist(User user);

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回User表示登录成功，否则返回null
     */
    public User login(String username,String password);

    /**
     *
     * @param username 查询该用户名是否存在
     * @return 返回true表示存在，false表示不存在
     */
    public boolean exitsUser(String username);

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
    public List<User> queryAllUser();

    /**
     *
     * @return 返回在线的用户
     */
    public List<User> queryOnlineUser();

    /**
     *
     * @return 返回离线的用户
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
     * @param userId 用户id
     * @param date 日期
     * @return 签到成功返回true，否则返回false
     */
    public boolean signIn(int userId, LocalDate date);

    /**
     *
     * @param userId 用户id
     * @param date 日期
     * @return 已经签到返回true，否则返回false
     */
    public boolean isSignIn(int userId,LocalDate date);

    /**
     *
     * @param userId 用户id
     * @param endDate 最后日期（其实就是今天）
     * @return 最后的日期往前算出的连续签到次数
     */
    public int countContinuousSignInDays(int userId,LocalDate endDate);

    /**
     *
     * @param username 用户名
     * @return 根据用户名查询id
     */
    public int queryIdByUsername(String username);

    /**
     * 该方法用于更新宠物出战队列
     * @param pokemons 要更新的宠物列表
     */
    public void updatePokemonQueue(List<Pokemon> pokemons);

    /**
     *
     * @param myname 登录的用户的用户名
     * @return 返回除该用户和admin之外的其他公开的用户
     */
    public List<User> queryOtherUser(String myname);

    /**
     *
     * @param userId 用户id
     * @param endDate 最后日期（其实就是今天）
     * @return 返回Map集合，key是当月几号，value为 1 表示当日已签，为 0 表示当日未签
     */
    public Map<Integer,Boolean> querySignInRecord(int userId, LocalDate endDate);

    /**
     * 该方法用于查询公告
     * @return 返回公告的集合
     */
    public List<Announcement> queryAnnouncement();
    public int maxContinuousCount(int userId, LocalDate endDate);
}
