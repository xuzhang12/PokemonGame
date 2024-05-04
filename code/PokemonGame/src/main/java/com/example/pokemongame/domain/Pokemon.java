package com.example.pokemongame.domain;
import com.example.pokemongame.service.impl.ManagerServiceimpl;
import com.example.pokemongame.util.UpgradeUtils;
import com.example.pokemongame.service.ManagerService;
import java.util.Random;

/**
 * 这是宠物类
 */
public class Pokemon {
    private String name;
    private Integer blood;//血量，战斗过后自动恢复为当前等级和当前稀有度下的最大生命值
    private Integer grade=1;//等级，初始为1
    private String rarity;//稀有度
    private String skillname_1="";//技能名，后面一样
    private Integer skillvalue_1=0;//技能伤害，后面一样
    private String skillname_2="";
    private Integer skillvalue_2=0;
    private String skillname_3="";
    private Integer skillvalue_3=0;
    private String skillname_4="";
    private Integer skillvalue_4=0;
    private Integer experience=0;//当前经验值
    private Integer expSlot=0;//升级到下一级所需的总经验，当experience>exSlot时，宠物升级，刷新experience=experience-exSlot
    private String img_path="static/img/封面.jsp";//宠物图片，没有时间实现
    private int queue=1;//宠物出战位序
    private String master="admin";//宠物主人，默认为admin，根据具体情况赋值

    public Pokemon(String name, Integer blood, Integer grade, String rarity, String skillname_1, Integer skillvalue_1,
                   String skillname_2, Integer skillvalue_2, String skillname_3, Integer skillvalue_3,
                   String skillname_4, Integer skillvalue_4, Integer experience,Integer expSlot,String img_path,
                   int queue,String master) {
        this.name = name;
        this.blood = blood;
        if(grade!=null)this.grade = grade;
        this.rarity = rarity;
        this.skillname_1 = skillname_1;
        this.skillvalue_1 = skillvalue_1;
        this.skillname_2 = skillname_2;
        this.skillvalue_2 = skillvalue_2;
        this.skillname_3 = skillname_3;
        this.skillvalue_3 = skillvalue_3;
        this.skillname_4 = skillname_4;
        this.skillvalue_4 = skillvalue_4;
        if(experience!=null)this.experience = experience;
        if(expSlot!=null) this.expSlot=expSlot;
        if(img_path!=null&&!img_path.equals("")) this.img_path=img_path;
        if(queue!=0)this.queue=queue;
        if(master!=null&&!master.equals("")) this.master = master;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        if(master!=null&&!master.equals("")) this.master = master;
    }

    public Pokemon() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        if(grade!=null)this.grade = grade;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }


    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        if(experience!=null)this.experience = experience;
    }

    public String getSkillname_1() {
        return skillname_1;
    }

    public void setSkillname_1(String skillname_1) {
        this.skillname_1 = skillname_1;
    }

    public Integer getSkillvalue_1() {
        return skillvalue_1;
    }

    public void setSkillvalue_1(Integer skillvalue_1) {
        this.skillvalue_1 = skillvalue_1;
    }

    public String getSkillname_2() {
        return skillname_2;
    }

    public void setSkillname_2(String skillname_2) {
        this.skillname_2 = skillname_2;
    }

    public Integer getSkillvalue_2() {
        return skillvalue_2;
    }

    public void setSkillvalue_2(Integer skillvalue_2) {
        this.skillvalue_2 = skillvalue_2;
    }

    public String getSkillname_3() {
        return skillname_3;
    }

    public void setSkillname_3(String skillname_3) {
        this.skillname_3 = skillname_3;
    }

    public Integer getSkillvalue_3() {
        return skillvalue_3;
    }

    public void setSkillvalue_3(Integer skillvalue_3) {
        this.skillvalue_3 = skillvalue_3;
    }

    public String getSkillname_4() {
        return skillname_4;
    }

    public void setSkillname_4(String skillname_4) {
        this.skillname_4 = skillname_4;
    }

    public Integer getSkillvalue_4() {
        return skillvalue_4;
    }

    public void setSkillvalue_4(Integer skillvalue_4) {
        this.skillvalue_4 = skillvalue_4;
    }

    public Integer getExpSlot() {
        return expSlot;
    }

    public void setExpSlot(Integer expSlot) {
        if(expSlot!=null) this.expSlot=expSlot;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        if(img_path!=null&&!img_path.equals("")) this.img_path=img_path;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        if (queue!=0) this.queue = queue;
    }

    /**
     *
     * @param getExperience 获取的经验
     * 该方法用于更新宠物信息，根据经验值让宠物升级（也可能不升级），并更新其血量和技能伤害，如果传入的经验值为0，则是恢复宠物血量
     */
    public void updateAttribute(int getExperience)
    {
        experience+=getExperience;
        while (experience>=expSlot)
        {
            experience-=expSlot;
            grade+=1;
            updateExpSlot(rarity,grade);
        }
        updateBlood(name,grade);
        updateAttack(name,grade);
    }

    /**
     *
     * @param rarity 宠物稀有度
     * @param grade 宠物当前等级
     *              该方法用于更新宠物升级后的经验槽expSlot
     */
    public void updateExpSlot(String rarity,int grade)
    {
        if(rarity.equals("普通"))
            expSlot= (int) (UpgradeUtils.baseExp[0]*Math.pow(grade,UpgradeUtils.power));
        else if(rarity.equals("稀有"))
            expSlot= (int) (UpgradeUtils.baseExp[1]*Math.pow(grade,UpgradeUtils.power));
        else expSlot= (int) (UpgradeUtils.baseExp[2]*Math.pow(grade,UpgradeUtils.power));
    }

    /**
     *
     * @param name 宠物名
     * @param grade 宠物当前等级
     *              根据宠物名从宠物图鉴中查询其稀有度，根据稀有度和等级更新其血量
     */
    public void updateBlood(String name,int grade)
    {
        ManagerService managerService=new ManagerServiceimpl();
        Pokemon pokemonFromTable = managerService.queryOnePokemonByname(name);
        int baseAttribute=0;
        if(pokemonFromTable.getRarity().equals("普通"))baseAttribute=UpgradeUtils.baseAttribute[0];
        else if(pokemonFromTable.getRarity().equals("稀有"))baseAttribute=UpgradeUtils.baseAttribute[1];
        else baseAttribute=UpgradeUtils.baseAttribute[2];
        blood=pokemonFromTable.getBlood()+3*(grade-1)+baseAttribute*(grade-1);
    }

    /**
     *
     * @param name 宠物名
     * @param grade 宠物当前等级
     *              根据宠物名从宠物图鉴中查询其稀有度，根据稀有度和等级更新其技能伤害
     */
    public void updateAttack(String name,int grade)
    {
        ManagerService managerService=new ManagerServiceimpl();
        Pokemon pokemonFromTable = managerService.queryOnePokemonByname(name);
        int baseAttribute=0;
        if(pokemonFromTable.getRarity().equals("普通"))baseAttribute=UpgradeUtils.baseAttribute[0];
        else if(pokemonFromTable.getRarity().equals("稀有"))baseAttribute=UpgradeUtils.baseAttribute[1];
        else baseAttribute=UpgradeUtils.baseAttribute[2];
        skillvalue_1=pokemonFromTable.getSkillvalue_1()+(grade-1)+baseAttribute/2*(grade-1);
        skillvalue_2=pokemonFromTable.getSkillvalue_2()+(grade-1)+baseAttribute/2*(grade-1);
        skillvalue_3=pokemonFromTable.getSkillvalue_3()+(grade-1)+baseAttribute/2*(grade-1);
        skillvalue_4=pokemonFromTable.getSkillvalue_4()+(grade-1)+baseAttribute/2*(grade-1);
    }

    /**
     * 我方宠物攻击敌方宠物
     * @param opponent 敌方宠物
     * @param skillname 我方宠物使用的技能
     *
     */
    public void attackPokemon(Pokemon opponent,String skillname)
    {
        if(skillname_1.equals(skillname))
            opponent.setBlood(Math.max(opponent.getBlood() - skillvalue_1, 0));
        else if(skillname_2.equals(skillname))
            opponent.setBlood(Math.max(opponent.getBlood() - skillvalue_2, 0));
        else if(skillname_3.equals(skillname))
            opponent.setBlood(Math.max(opponent.getBlood() - skillvalue_3, 0));
        else if(skillname_4.equals(skillname))
            opponent.setBlood(Math.max(opponent.getBlood() - skillvalue_4, 0));
    }

    /**
     *
     * @param random
     * @return 返回一个随机技能，对决时敌方宠物使用
     */
    public String randomSkill(Random random)
    {
        int num=random.nextInt(4);
        if(num==0)return skillname_1;
        if(num==1)return skillname_2;
        if(num==2)return skillname_3;
        else return skillname_4;
    }

    /**
     *
     * @param skillname 技能名
     * @return 返回技能名对应的伤害值
     */
    public int damage(String skillname)
    {
        if(skillname_1.equals(skillname))return skillvalue_1;
        if(skillname_2.equals(skillname))return skillvalue_2;
        if(skillname_3.equals(skillname))return skillvalue_3;
        else return skillvalue_4;
    }
    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", blood=" + blood +
                ", grade=" + grade +
                ", rarity='" + rarity + '\'' +
                ", skillname_1='" + skillname_1 + '\'' +
                ", skillvalue_1=" + skillvalue_1 +
                ", skillname_2='" + skillname_2 + '\'' +
                ", skillvalue_2=" + skillvalue_2 +
                ", skillname_3='" + skillname_3 + '\'' +
                ", skillvalue_3=" + skillvalue_3 +
                ", skillname_4='" + skillname_4 + '\'' +
                ", skillvalue_4=" + skillvalue_4 +
                ", experience=" + experience +
                ", expSlot=" + expSlot +
                ", queue=" + queue +
                ", img_path='" + img_path + '\'' +
                ", master='" + master + '\'' +
                '}';
    }
}