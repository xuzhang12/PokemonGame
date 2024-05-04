package com.example.pokemongame.controller.Servlet;

import com.example.pokemongame.domain.Manager;
import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.domain.User;
import com.example.pokemongame.service.ManagerService;
import com.example.pokemongame.service.UserService;
import com.example.pokemongame.service.impl.ManagerServiceimpl;
import com.example.pokemongame.service.impl.UserServiceimpl;
import com.example.pokemongame.util.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 这里是控制用户和管理员登录或注册的Servlet，没有设置filter过滤器，即没有权限拦截
 */

public class LoginOrRegistServlet extends BaseServlet {
    UserService userService = new UserServiceimpl();
    ManagerService managerService = new ManagerServiceimpl();

    /**
     *用户登录的方法
     */

    protected void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");//获取用户名
        String originalpwd = req.getParameter("password");//获取原始密码
        String finalpwd = MD5Utils.md5Password(originalpwd);//获取加密后的密码
        User user = userService.login(username, finalpwd);//查询有无该用户
        req.setAttribute("username", username);
        req.setAttribute("password", finalpwd);
        if (user != null) {//查询到了用户
            user.setonline(1);
            userService.updateUserInfo(user);//更新数据库，修改用户为在线状态
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            req.getSession().setAttribute("user", user);//session域设置user属性
            if (user.getBirthday() != null) req.getSession().setAttribute("birthday", sdf.format(user.getBirthday()));
            List<Pokemon> pokemons = userService.queryPokemonsByMaster(username);
            req.getSession().setAttribute("pokemons", pokemons);//session域设置pokemons属性
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {//查询不到，回到登录界面
            req.setAttribute("msg", "用户名或密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }
    /**
     *用户注册的方法
     */
    protected void userRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        req.getSession().setAttribute("username", username);
        req.getSession().setAttribute("email", email);
        req.getSession().setAttribute("password", password);
        //判断该用户名是否可用
        if (!userService.exitsUser(username)) {
            req.getRequestDispatcher("/pages/user/first_login.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名已存在");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
    /**
     *  用户首次登录的方法，因为考虑到首次登录的用户要送宠物，所以单独写了
     *  在该servlet中才真正注册用户和用户首个宠物
     */
    protected void userFirstLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String firstPokemonName = req.getParameter("firstPokemon");
        String username = (String) req.getSession().getAttribute("username");
        //存入数据库前对密码进行加密
        String originalpwd = (String) req.getSession().getAttribute("password");
        String finalpwd = MD5Utils.md5Password(originalpwd);
        String email = (String) req.getSession().getAttribute("email");
        Pokemon pokemon = managerService.queryOnePokemonByname(firstPokemonName);
        pokemon.setMaster(username);
        pokemon.setExpSlot(2);
        User user = new User(0, username, finalpwd, email, 1, "", "", new Date(),1);
        if (!userService.exitsUser(username)) {
            userService.regist(user);
            userService.addPokemon(pokemon);
        }
        user.setonline(1);
        //获取id作为redis签到的唯一标识
        int userId = userService.queryIdByUsername(username);
        System.out.println(userId);
        user.setId(userId);
        userService.updateUserInfo(user);//更新用户为登录状态
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        req.getSession().setAttribute("user", user);//session域设置user属性
        req.getSession().setAttribute("birthday", sdf.format(user.getBirthday()));
        List<Pokemon> pokemons = userService.queryPokemonsByMaster(username);
        req.getSession().setAttribute("pokemons", pokemons);//session域设置pokemons属性
        req.getRequestDispatcher("/pages/user/main.jsp").forward(req, resp);
    }
    /**
     *管理员登录的方法
     */
    protected void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String adminname = req.getParameter("adminname");
        String password = req.getParameter("password");
        String finalpwd = MD5Utils.md5Password(password);
        //判断有无该管理员
        Manager manager = managerService.login(adminname, finalpwd);
        if (manager!=null) {
            req.getSession().setAttribute("manager", manager);
            req.getRequestDispatcher("/pages/manager/login_success.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "账号或者密码错误！");
            req.getRequestDispatcher("/pages/manager/login.jsp").forward(req, resp);
        }
    }
}
