package com.example.pokemongame.controller.Servlet;

import com.example.pokemongame.domain.Announcement;
import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.domain.User;
import com.example.pokemongame.service.ManagerService;
import com.example.pokemongame.service.UserService;
import com.example.pokemongame.service.impl.ManagerServiceimpl;
import com.example.pokemongame.service.impl.UserServiceimpl;
import com.example.pokemongame.util.DBUtils;
import com.example.pokemongame.util.UpgradeUtils;
import com.example.pokemongame.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 管理员登录之后的servlet程序
 */
public class ManagerServlet extends BaseServlet {
    private ManagerService managerService = new ManagerServiceimpl();
    private UserService userService = new UserServiceimpl();
    //查看宠物图鉴，实际上等价于查看用户名为admin的宠物
    protected void pokemon_list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pokemon> pokemons = managerService.queryAllPokemon();
        req.setAttribute("pokemons", pokemons);
        req.getRequestDispatcher("/pages/manager/pokemon_list.jsp").forward(req, resp);
    }
    //更新宠物
    protected void pokemon_update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pokemon pokemon = WebUtils.copyParamToBean(req.getParameterMap(), new Pokemon());
        String pastname = req.getParameter("pastname");//因为有可能改到宠物名，所以需要原来的宠物名
        managerService.updatePokemonInTable(pokemon, pastname);//修改宠物表中的宠物（实际上是修改admin的宠物）
        managerService.updatePokemonOfUser(pokemon, pastname);//修改用户的宠物
        DBUtils.commitAndClose();
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=pokemon_list");
    }
    //删除宠物
    protected void pokemon_delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        managerService.deletePokemonByName(req.getParameter("name"));//删除所有该宠物出现的地方
        DBUtils.commitAndClose();
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=pokemon_list");
    }
    //添加宠物
    protected void pokemon_add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Pokemon pokemon = WebUtils.copyParamToBean(req.getParameterMap(), new Pokemon());
        managerService.addPokemon(pokemon);//添加宠物，并同时在pokemon_field（管理员维护的宠物园）中添加一些该宠物
        DBUtils.commitAndClose();
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=pokemon_list");
    }
    //管理员查看宠物升级规则
    protected void pokemon_upgrade_rule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("power", UpgradeUtils.power);
        req.setAttribute("baseExp", UpgradeUtils.baseExp);
        req.setAttribute("baseAttribute", UpgradeUtils.baseAttribute);
        req.setAttribute("multiplier", UpgradeUtils.multiplier);
        req.getRequestDispatcher("/pages/manager/pokemon_upgrade_rule.jsp").forward(req, resp);
    }
    //查看所有用户
    protected void user_list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<User> users = userService.queryAllUser();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/pages/manager/user_list.jsp").forward(req, resp);
    }
    //获取某只宠物的信息
    protected void getPokemon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pokemon pokemon = managerService.queryOnePokemonByname(req.getParameter("name"));
        req.setAttribute("pokemon", pokemon);
        req.getRequestDispatcher("/pages/manager/pokemon_edit.jsp").forward(req, resp);
    }
    //查询某个用户的宠物详情
    protected void user_pokemon_list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pokemon> pokemonList = managerService.queryPokemonByMaster(req.getParameter("username"));
        req.setAttribute("pokemonlist", pokemonList);
        req.getRequestDispatcher("/pages/manager/user_pokemon_list.jsp").forward(req, resp);
    }
    //查看在线用户
    protected void user_online(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.queryOnlineUser();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/pages/manager/user_online.jsp").forward(req, resp);
    }
    //查看离线用户
    protected void user_offline(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.queryOfflineUser();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/pages/manager/user_offline.jsp").forward(req, resp);
    }
    //查看宠物园中的宠物
    protected void pokemon_field_list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pokemon> pokemonlist = managerService.queryPokemonInField();
        req.setAttribute("pokemonlist", pokemonlist);
        req.getRequestDispatcher("/pages/manager/pokemon_field.jsp").forward(req, resp);
    }
    //更改宠物时要进行的规则判断，后面不用了，直接在前端进行判断
    protected boolean upgradeJudge(Pokemon pokemon) {
        return pokemon.getName() != null && !pokemon.getName().equals("") && pokemon.getBlood() != 0
                && pokemon.getRarity() != null && !pokemon.getRarity().equals("")
                && pokemon.getSkillname_1() != null && !pokemon.getSkillname_1().equals("")
                && pokemon.getSkillname_2() != null && !pokemon.getSkillname_2().equals("")
                && pokemon.getSkillname_3() != null && !pokemon.getSkillname_3().equals("")
                && pokemon.getSkillname_4() != null && !pokemon.getSkillname_4().equals("");
    }
    //管理员退出方法
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("manager");//移除session中的manager属性
        resp.sendRedirect(req.getContextPath() + "/index.jsp");//跳转到首页
    }
    //编辑公告方法
    protected void editAnnouncement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        HttpSession session = req.getSession();
        if (managerService.editAnnouncement(message)) {//存入数据库
            session.setAttribute("msg", "发布成功");
        }else session.setAttribute("msg","发布失败，请稍后再试");
        resp.sendRedirect(req.getContextPath() + "/managerServlet?action=announcement_list");
    }
    //查看公告
    protected void announcement_list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Announcement> announcements = managerService.queryAllAnnouncement();//从数据库中查询
        session.setAttribute("announcements",announcements);
        req.getRequestDispatcher("/pages/manager/announcement_edit.jsp").forward(req,resp);
    }
}
