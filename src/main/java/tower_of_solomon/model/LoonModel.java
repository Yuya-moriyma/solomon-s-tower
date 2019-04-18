package tower_of_solomon.model;

public class LoonModel {
    private int level;
    private int type;
    private int value;
    private SkillModel skill;
    private int exp;
    private int nextExp;

    public LoonModel(int level, int type, int value, SkillModel skill, int exp, int nextExp) {
        this.level = level;
        this.type = type;
        this.value = value;
        this.skill = skill;
        this.exp = exp;
        this.nextExp = nextExp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public SkillModel getSkill() {
        return skill;
    }

    public void setSkill(SkillModel skill) {
        this.skill = skill;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNextExp() {
        return nextExp;
    }

    public void setNextExp(int nextExp) {
        this.nextExp = nextExp;
    }
}
