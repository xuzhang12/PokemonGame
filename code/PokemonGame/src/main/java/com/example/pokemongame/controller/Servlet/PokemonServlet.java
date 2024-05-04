package com.example.pokemongame.controller.Servlet;

import com.example.pokemongame.domain.Pokemon;
import com.example.pokemongame.domain.User;
import com.example.pokemongame.service.ManagerService;
import com.example.pokemongame.service.UserService;
import com.example.pokemongame.service.impl.ManagerServiceimpl;
import com.example.pokemongame.service.impl.UserServiceimpl;
import com.example.pokemongame.util.UpgradeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonServlet extends BaseServlet {
    ManagerService managerService = new ManagerServiceimpl();
    UserService userService = new UserServiceimpl();

    //获取对应的宠物，如果参数object是pokemon，那么说明是与宠物园中的宠物对战，根据名字和等级获取该宠物，放入List集合
    //如果参数object是otheruser，则说明是与其他用户对战，则通过用户名查询其宠物，放入List集合
    protected void getOpponents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pokemon> opponents = null;
        if (req.getParameter("object").equals("pokemon")) {//参数object是pokemon，说明是与宠物园中的宠物对战
            String name = req.getParameter("name");
            int grade = Integer.parseInt(req.getParameter("grade"));
            Pokemon opponent = managerService.queryPokemonByNameAndGrade(name, grade);//根据名字和等级获取该宠物
            opponents = new ArrayList<>();
            opponents.add(opponent);//放入List集合
            req.getSession().setAttribute("object", "pokemon");//在session域中设置，方便后续使用
            req.getSession().setAttribute("opponents", opponents);
            resp.sendRedirect(req.getContextPath() + "/pages/pokemon/main.jsp");
        } else if (req.getParameter("object").equals("otheruser")) {//参数object是otheruser，说明是与其他用户对战
            String otherUsername = req.getParameter("otherUsername");
            opponents = userService.queryPokemonsByMaster(otherUsername);//则通过用户名查询其宠物
            if (opponents.size() != 0) {//该用户有宠物时才允许对战
                req.getSession().setAttribute("object", "otheruser");//在session域中设置，方便后续使用
                req.getSession().setAttribute("oppoName", otherUsername);
                req.getSession().setAttribute("opponents", opponents);
                resp.sendRedirect(req.getContextPath() + "/pages/pokemon/main.jsp");
            } else {
                req.getSession().setAttribute("msg", "不能对没有宠物的玩家发起挑战");
                req.getRequestDispatcher("/userServlet?action=otherUserList").forward(req, resp);
            }
        }
    }

    //宠物对战时的方法
    protected void battle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        boolean gameFinished = false;//游戏结束标志，用于前端
        Pokemon opponent = (Pokemon) session.getAttribute("opponent");//对方场上的宠物
        Pokemon mypokemon = (Pokemon) session.getAttribute("mypokemon");//我方场上的宠物
        String skillname = req.getParameter("skillname");//我方宠物使用的技能
        Random random = new Random();//随机数，用于让对方宠物使用随机技能
        String opposkill = null;//对方宠物使用的技能，初始为null
        int mydamage = 0;//我方宠物造成的伤害
        int oppodamage = 0;//对方宠物造成的伤害
        String attackInfo = null;//我方攻击对方的战斗信息
        String attackedInfo = null;//对方攻击我方的战斗信息
        int count = 1;//回合数，初始为1
        //session域中已经有回合数记录，则直接使用
        if (session.getAttribute("count") != null) count = (int) session.getAttribute("count");
        //计算伤害，如果技能伤害大于对方宠物血量，则技能伤害等于对方宠物血量，不然等于技能本来的伤害值
        if (mypokemon.damage(skillname) > opponent.getBlood())
            mydamage = opponent.getBlood();
        else mydamage = mypokemon.damage(skillname);
        //调用宠物的攻击方法
        mypokemon.attackPokemon(opponent, skillname);
        //编辑我方攻击对方的战斗信息，用于前端显示
        attackInfo = "第" + count + "回合，我方的【" + mypokemon.getName() + "】vs敌方的【" + opponent.getName() + "】，我方的【" + mypokemon.getName() + "】对敌方的【"
                + opponent.getName() + "】使用" + skillname + " 造成了 " + mydamage + " 点伤害，敌方的【" + opponent.getName() + "】还剩 "
                + opponent.getBlood() + " 点血";
        //回合数增加
        count++;
        //如果对方宠物还存活（血量大于0），则轮到对方宠物攻击
        if (opponent.getBlood() > 0) {
            //获取随机技能
            opposkill = opponent.randomSkill(random);
            //计算伤害，进行攻击，编辑对战信息，回合数增加，与上面相同
            if (opponent.damage(opposkill) > mypokemon.getBlood())
                oppodamage = mypokemon.getBlood();
            else oppodamage = opponent.damage(opposkill);
            opponent.attackPokemon(mypokemon, opposkill);
            attackedInfo = "第" + count + "回合，敌方的【" + opponent.getName() + "】vs我方的【" + mypokemon.getName() + "】，敌方的【" + opponent.getName() + "】对我方的【"
                    + mypokemon.getName() + "】使用" + opposkill + " 造成了 " + oppodamage + " 点伤害，我方的【" + mypokemon.getName() + "】还剩 "
                    + mypokemon.getBlood() + " 点血";
            count++;
        }
        //如果有宠物被击败（血量为0），则要对游戏结果进行判断
        if (mypokemon.getBlood() == 0 || opponent.getBlood() == 0) {
            //我方宠物存活，对方宠物被击败
            if (mypokemon.getBlood() > 0) {
                //编辑当前对战结果，用于前端显示
                String nowResult = "我方的【" + mypokemon.getName() + "】击败了敌方的【" + opponent.getName() + "】";
                session.setAttribute("nowResult", nowResult);
                //获取对方宠物集合，如果所有宠物都被击败（血量为0），则游戏结束，我方胜利
                //设置gameFinished为true，finalResult为1（表示胜利），并放入session域中，用于前端显示信息
                List<Pokemon> opponents = (List<Pokemon>) session.getAttribute("opponents");
                int index;
                for (index = 0; index < opponents.size(); index++) {
                    if (opponents.get(index).getBlood() > 0)
                        break;
                }
                if (index == opponents.size()) {
                    int finalResult = 1;
                    gameFinished = true;
                    session.setAttribute("finalResult", finalResult);
                    session.setAttribute("gameFinished", gameFinished);
                } else {//对方宠物还有宠物存活，将其放入session域，编辑信息oppoChangeInfo，用于前端显示
                    session.setAttribute("opponent", opponents.get(index));
                    String oppoName = (String) session.getAttribute("oppoName");
                    session.setAttribute("oppoChangeInfo", oppoName + "派出了" + opponents.get(index).getName());
                }
            } else {//对方宠物存活，我方宠物被击败，逻辑同上
                String nowResult = "敌方的【" + opponent.getName() + "】击败了我方的【" + mypokemon.getName() + "】";
                session.setAttribute("nowResult", nowResult);
                List<Pokemon> pokemons = (List<Pokemon>) session.getAttribute("pokemons");
                int index;
                for (index = 0; index < pokemons.size(); index++) {
                    if (pokemons.get(index).getBlood() > 0)
                        break;
                }
                if (index == pokemons.size()) {
                    int finalResult = 0;
                    gameFinished = true;
                    session.setAttribute("finalResult", finalResult);
                    session.setAttribute("gameFinished", gameFinished);
                }
            }
        } else session.setAttribute("nowResult", null);//当前回合没有宠物被击败，设置nowResult为null
        session.setAttribute("gameFinished", gameFinished);
        session.setAttribute("attackedInfo", attackedInfo);
        session.setAttribute("attackInfo", attackInfo);
        session.setAttribute("count", count);
        resp.sendRedirect(req.getContextPath() + "/pages/pokemon/main.jsp");
    }

    //战斗结束之后的方法，如果战斗结果是失败，所有宠物不获取经验，但是刷新血量
    //如果战斗结果是胜利（击败对方所有宠物/捕获宠物园中的宠物），参战宠物获取经验并刷新血量
    //不管结果如何，都将作战时在session域中设置的属性移除（调用deleteSessionDate(req, resp)方法）
    protected void afterBattle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Pokemon> opponents = (List<Pokemon>) session.getAttribute("opponents");//对方宠物集合
        List<Pokemon> participant = (List<Pokemon>) session.getAttribute("participant");//我方参战宠物集合
        if (req.getParameter("finalResult").equals("win")) {//战斗结果是胜利（前端已经将 1 转成win，0 转成 lose）
            double sumExp = 0;//总经验
            for (Pokemon opponent : opponents) {
                sumExp += opponent.getExpSlot() * UpgradeUtils.multiplier;//总经验等于对方每只宠物经验槽*管理员设置的倍数之和
            }
            int avgExp = (int) (sumExp / participant.size());//平均经验=总经验/参战宠物总数
            for (Pokemon pokemon : participant) {
                pokemon.updateAttribute(avgExp);//更新宠物属性，不升级就刷新血量和增加经验值，升级就还要更新各种属性
            }
        } else {//战斗结果是失败，则只刷新血量
            for (Pokemon pokemon : participant) {
                pokemon.updateAttribute(0);
            }
        }
        //根据对战的对象的不同，跳回原来发起对战的页面
        if (session.getAttribute("object").equals("pokemon")) {
            deleteSessionDate(req, resp);
            resp.sendRedirect(req.getContextPath() + "/pokemonServlet?action=pokemon_field_list");
        } else if (session.getAttribute("object").equals("otheruser")) {
            deleteSessionDate(req, resp);
            resp.sendRedirect(req.getContextPath() + "/userServlet?action=otherUserList");
        }
    }

    //捕获方法，如果对方是宠物园中的宠物，并且自己没有，则允许捕获，宠物血量大于10不容易捕获，小于10捕获概率较大
    //如果对方是其他用户（即玩家），或者对方是宠物园中的宠物，但是自己已经有该宠物了，则都不允许捕获
    protected void capture(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String object = (String) session.getAttribute("object");
        if (object.equals("pokemon")) {//对方是宠物园中的宠物，判断是否已经有了相同宠物
            Pokemon opponent = (Pokemon) session.getAttribute("opponent");
            List<Pokemon> pokemons = (List<Pokemon>) session.getAttribute("pokemons");
            boolean cuptureAllow = true;
            for (Pokemon pokemon : pokemons) {
                if (opponent.getName().equals(pokemon.getName())) {//有相同宠物，设置提示信息，跳转页面
                    String msg = "你的背包里已经有同名宠物，不允许捕捉";
                    cuptureAllow = false;
                    req.setAttribute("msg", msg);
                    req.getRequestDispatcher("/pages/pokemon/main.jsp").forward(req, resp);
                }
            }
            if (cuptureAllow) {//没有相同宠物，允许捕获
                int finalResult = 0;
                Random random = new Random();
                String nowResult;//当前回合结果
                String captureInfo;//捕获信息
                boolean gameFinished = false;
                double captureRate;//捕获率
                String attackedInfo;//对方宠物攻击我方宠物信息
                int count;//回合数
                if (session.getAttribute("count") != null)
                    count = (int) session.getAttribute("count");
                else count = 1;
                captureInfo = "第" + count + "回合,你使用了精灵球,";
                count++;//捕获也算一个回合
                if (opponent.getBlood() < 10) {
                    captureRate = random.nextDouble() + 0.1;
                } else {
                    captureRate = random.nextDouble() - 0.4;
                }
                if (captureRate > 0.5) {//如果捕获率大于0.5，则捕获成功
                    captureInfo += "捕获成功！";
                    session.setAttribute("captureInfo", captureInfo);//设置捕获信息
                    User user = (User) session.getAttribute("user");
                    opponent.updateAttribute(0);//刷新该宠物血量
                    opponent.setMaster(user.getName());//设置其主人
                    opponent.setQueue(pokemons.size() + 1);//设置其出战位序（刚捕获时在最后）
                    pokemons.add(opponent);//添加到List集合中，此时还没有存入数据库，需要用户手动存档或者在宠物背包中保存修改才会存入数据库
                    gameFinished = true;
                    finalResult = 1;
                    session.setAttribute("finalResult", finalResult);
                    session.setAttribute("gameFinished", gameFinished);
                    resp.sendRedirect(req.getContextPath() + "/pages/pokemon/main.jsp");
                } else {//捕获失败
                    captureInfo += "捕获失败！";
                    session.setAttribute("captureInfo", captureInfo);//设置捕获信息
                    Pokemon mypokemon = (Pokemon) session.getAttribute("mypokemon");
                    //我方宠物挨打，跟对战时对方宠物攻击我方宠物是一样的
                    int oppodamage;
                    String opposkill = opponent.randomSkill(random);
                    if (opponent.damage(opposkill) > mypokemon.getBlood())
                        oppodamage = mypokemon.getBlood();
                    else oppodamage = opponent.damage(opposkill);
                    opponent.attackPokemon(mypokemon, opposkill);
                    attackedInfo = "第" + count + "回合，敌方的【" + opponent.getName() + "】vs我方的【" + mypokemon.getName() + "】，敌方的【" + opponent.getName() + "】对我方的【"
                            + mypokemon.getName() + "】使用" + opposkill + " 造成了 " + oppodamage + " 点伤害，我方的【" + mypokemon.getName() + "】还剩 "
                            + mypokemon.getBlood() + " 点血";
                    count++;
                    session.setAttribute("attackedInfo", attackedInfo);
                    session.setAttribute("count", count);
                    if (mypokemon.getBlood() == 0) {//因为挨打，所以可能会被击倒
                        nowResult = "敌方的【" + opponent.getName() + "】击败了我方的【" + mypokemon.getName() + "】";
                        session.setAttribute("nowResult", nowResult);
                        int i;
                        for (i = 0; i < pokemons.size(); i++) {
                            if (pokemons.get(i).getBlood() > 0)
                                break;
                        }
                        if (i == pokemons.size()) {
                            gameFinished = true;
                            finalResult = 0;
                            session.setAttribute("finalResult", finalResult);
                        }
                    }
                    session.setAttribute("gameFinished", gameFinished);
                    resp.sendRedirect(req.getContextPath() + "/pages/pokemon/main.jsp");
                }
            }
        } else if (object.equals("otheruser")) {//对方是其他玩家，不可以捕获其宠物
            req.setAttribute("msg", "玩家之间的战斗，不允许捕捉对方的宠物");
            req.getRequestDispatcher("/pages/pokemon/main.jsp").forward(req, resp);
        }
    }
    //逃跑方法，如果对方是宠物园中的宠物，则允许逃跑，如果是其他玩家，则不允许逃跑（玩家之间的战斗是严肃的！！！）
    protected void escape(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("object").equals("pokemon")) {
            List<Pokemon> participant = (List<Pokemon>) session.getAttribute("participant");
            for (Pokemon pokemon : participant) {
                pokemon.updateAttribute(0);
            }
            deleteSessionDate(req, resp);
            resp.sendRedirect(req.getContextPath() + "/pokemonServlet?action=pokemon_field_list");
        } else if (session.getAttribute("object").equals("otheruser")) {
            req.setAttribute("msg", "玩家之间的战斗，不允许逃跑");
            req.getRequestDispatcher("/pages/pokemon/main.jsp").forward(req, resp);
        }
    }
    //更换宠物方法，前端已经将血量为 0 的宠物拦截，这里主要是实现了是否需要增加参战宠物和挨打的程序
    protected void changeMyPokemon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String pokemonName = req.getParameter("pokemonName");//获取要更换上场的宠物名
        List<Pokemon> pokemons = (List<Pokemon>) session.getAttribute("pokemons");//获取全部宠物列表
        List<Pokemon> participant = (List<Pokemon>) session.getAttribute("participant");//获取参战宠物列表
        Pokemon mypokemon = null;
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getName().equals(pokemonName)) {
                mypokemon = pokemon;
                break;
            }
        }
        session.setAttribute("mypokemon", mypokemon);//更新session域中的mypokemon属性
        int index;
        //查看该宠物是否已在参战列表中
        for (index = 0; index < participant.size(); index++) {
            if (pokemonName.equals(participant.get(index).getName()))
                break;
        }
        if (index == participant.size()) participant.add(mypokemon);//不在，则添加
        String changeInfo;//更换宠物信息
        int count = 1;
        if (session.getAttribute("count") != null) count = (int) session.getAttribute("count");
        changeInfo = "第" + count + "回合，你派出了【" + mypokemon.getName() + "】";
        count++;//更换宠物也是一个回合
        session.setAttribute("changeInfo", changeInfo);
        //挨打
        Pokemon opponent = (Pokemon) session.getAttribute("opponent");
        Random random = new Random();
        int oppodamage;
        String opposkill = opponent.randomSkill(random);
        if (opponent.damage(opposkill) > mypokemon.getBlood())
            oppodamage = mypokemon.getBlood();
        else oppodamage = opponent.damage(opposkill);
        opponent.attackPokemon(mypokemon, opposkill);
        String attackedInfo = null;
        attackedInfo = "第" + count + "回合，敌方的【" + opponent.getName() + "】vs我方的【" + mypokemon.getName() + "】，敌方的【" + opponent.getName() + "】对我方的【"
                + mypokemon.getName() + "】使用" + opposkill + " 造成了 " + oppodamage + " 点伤害，我方的【" + mypokemon.getName() + "】还剩 "
                + mypokemon.getBlood() + " 点血";
        count++;
        session.setAttribute("attackedInfo", attackedInfo);
        session.setAttribute("count", count);
        String nowResult = null;
        boolean gameFinished = false;
        int finalResult = 1;
        //我方宠物可能被击败
        if (mypokemon.getBlood() == 0) {
            nowResult = "敌方的【" + opponent.getName() + "】击败了我方的【" + mypokemon.getName() + "】";
            session.setAttribute("nowResult", nowResult);
            int j;
            for (j = 0; j < pokemons.size(); j++) {
                if (pokemons.get(j).getBlood() > 0)
                    break;
            }
            if (j == pokemons.size()) {//我方宠物可能全部被击败
                gameFinished = true;
                finalResult = 0;
            }
        }
        session.setAttribute("gameFinished", gameFinished);
        session.setAttribute("finalResult", finalResult);
        resp.sendRedirect(req.getContextPath() + "/pages/pokemon/main.jsp");
    }
    //删除对战时设置的各种属性
    protected void deleteSessionDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("opponents");
        session.removeAttribute("opponent");
        session.removeAttribute("mypokemon");
        session.removeAttribute("participant");
        session.removeAttribute("nowResult");
        session.removeAttribute("gameFinished");
        session.removeAttribute("attackedInfo");
        session.removeAttribute("attackInfo");
        session.removeAttribute("count");
        session.removeAttribute("object");
        session.removeAttribute("oppoName");
    }
    //获取宠物园中的宠物
    protected void pokemon_field_list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Pokemon> pokemonlist = managerService.queryPokemonInField();
        req.setAttribute("pokemonlist", pokemonlist);
        req.getRequestDispatcher("/pages/pokemon/pokemon_field.jsp").forward(req, resp);
    }
}
