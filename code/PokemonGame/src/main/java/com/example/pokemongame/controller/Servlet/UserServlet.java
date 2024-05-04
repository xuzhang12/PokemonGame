package com.example.pokemongame.controller.Servlet;

import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.domain.User;
import com.example.pokemongame.service.ManagerService;
import com.example.pokemongame.service.UserService;
import com.example.pokemongame.service.impl.ManagerServiceimpl;
import com.example.pokemongame.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceimpl();
    ManagerService managerService = new ManagerServiceimpl();
    //用户退出方法，在数据库中更新为离线状态，并使session会话结束，跳转到游戏首页
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        user.setonline(0);
        userService.updateUserInfo(user);
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }
    //用户更新基本信息方法
    protected void user_info_update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");//昵称
        String signature = req.getParameter("signature");//签名
        String year = req.getParameter("year");//生日的年份
        String month = req.getParameter("month");//生日的月份
        String day = req.getParameter("day");//生日的日期
        int open = Integer.parseInt(req.getParameter("open"));//是否公开
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthday = sdf.parse(year + "-" + month + "-" + day);
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            //更新为更改后的状态，并存入数据库
            user.setNickname(nickname);
            user.setSignature(signature);
            user.setBirthday(birthday);
            user.setOpen(open);
            session.setAttribute("user", user);
            userService.updateUserInfo(user);
            session.setAttribute("birthday", year + "-" + month + "-" + day);
            resp.sendRedirect(req.getContextPath() + "/pages/user/user_info_list.jsp");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //存档方法，调用save(req,resp)，保存到数据库
    protected void saveDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        save(req,resp);
        req.setAttribute("msg", "存档成功");
        req.getRequestDispatcher("/pages/user/main.jsp").forward(req, resp);
    }
    //获取宠物园宠物列表方法
    protected void pokemon_field_list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pokemon> pokemonlist = managerService.queryPokemonInField();
        req.setAttribute("pokemonlist", pokemonlist);
        req.getRequestDispatcher("/pages/user/pokemon_field.jsp").forward(req, resp);
    }
    //签到方法
    protected void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        LocalDate localDate = LocalDate.now();
        boolean isSignIn = userService.isSignIn(user.getId(), localDate);
        int continuousSignInDays = 0;
        int maxContinuousCount = 0;
        if (isSignIn) {//今天已经签到过了
            continuousSignInDays=userService.countContinuousSignInDays(user.getId(), localDate);
            maxContinuousCount=userService.maxContinuousCount(user.getId(), localDate);
            req.setAttribute("msg", "您已经签到过了，已连续签到"+continuousSignInDays+"天，最大连续签到天数："+maxContinuousCount);
            req.getRequestDispatcher("/userServlet?action=signInRecord").forward(req, resp);
        } else {//今天还没有签到
            if (userService.signIn(user.getId(), localDate)) {
                continuousSignInDays=userService.countContinuousSignInDays(user.getId(), localDate);
                maxContinuousCount=userService.maxContinuousCount(user.getId(), localDate);
                req.setAttribute("msg", "签到成功！已连续签到"+continuousSignInDays+"天，最大连续签到天数："+maxContinuousCount);
                req.getRequestDispatcher("/userServlet?action=signInRecord").forward(req, resp);
            } else {
                req.setAttribute("msg", "签到失败，请稍后再试");
                req.getRequestDispatcher("/userServlet?action=signInRecord").forward(req, resp);
            }
        }
    }
    //获取签到记录表方法，用于用户查看签到记录
    protected void signInRecord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        LocalDate now = LocalDate.now();
        Map<Integer, Boolean> signInRecord = userService.querySignInRecord(user.getId(), now);
        session.setAttribute("signInRecord", signInRecord);
        req.getRequestDispatcher("/pages/user/signInRecord.jsp").forward(req, resp);
    }
    //调整宠物出战位序方法
    protected void queueUpPokemon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Pokemon> pokemons = (List<Pokemon>) session.getAttribute("pokemons");
        int queue = Integer.parseInt(req.getParameter("queue"));
        //先更改宠物位序，再更新List中的顺序，还没有保存到数据库
        pokemons.get(queue - 2).setQueue(queue);
        pokemons.get(queue - 1).setQueue(queue - 1);
        Pokemon pokemon = pokemons.get(queue - 1);
        pokemons.set(queue - 1, pokemons.get(queue - 2));
        pokemons.set(queue - 2, pokemon);
        resp.sendRedirect(req.getContextPath() + "/pages/user/pokemon_in_bag.jsp");
    }
    //保存修改后的位序方法，调用save(req,resp)，保存到数据库
    protected void saveQueue(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        save(req,resp);
        req.setAttribute("msg", "保存成功");
        req.getRequestDispatcher("/pages/user/pokemon_in_bag.jsp").forward(req, resp);
    }
    //获取除自己外的公开的玩家列表
    protected void otherUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<User> otherUsers = userService.queryOtherUser(user.getName());
        session.setAttribute("otherUsers", otherUsers);
        req.getRequestDispatcher("/pages/user/otheruser.jsp").forward(req, resp);
    }
    //获取具体某一个其他玩家的宠物信息
    protected void otherUserPokemon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otherUsername = req.getParameter("otherUsername");
        List<Pokemon> otherUserPokemons = userService.queryPokemonsByMaster(otherUsername);
        req.setAttribute("otherUserPokemons", otherUserPokemons);
        req.getRequestDispatcher("/pages/user/otheruser_pokemon_list.jsp").forward(req, resp);
    }
    //将数据保存到数据库方法，不存在就添加，存在就修改
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Pokemon> pokemons = (List<Pokemon>) session.getAttribute("pokemons");
        User user = (User) session.getAttribute("user");
        List<Pokemon> pastPokemons = userService.queryPokemonsByMaster(user.getName());
        boolean exist = false;
        int i, j;
        for (i = 0; i < pokemons.size(); i++) {
            exist = false;
            for (j = 0; j < pastPokemons.size(); j++) {
                if (pokemons.get(i).getName().equals(pastPokemons.get(j).getName())) exist = true;
            }
            if (!exist) userService.addPokemon(pokemons.get(i));
            else userService.updatePokemon(pokemons.get(i));
        }
    }
}
