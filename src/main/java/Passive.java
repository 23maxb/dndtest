public class Passive {
    public String desc;
    public String name;
    public String statIncrease;
    //format stat:modifier
    //example strength:+5
    //example animalhandling:*2

    public Passive(String desc, String name, String statIncrease) {
        this.desc = desc;
        this.name = name;
        this.statIncrease = statIncrease;
    }

    public String toString()
    {
        return name + ": " + statIncrease;
    }

    public String statToIncrease()
    {
        return statIncrease.substring(0, statIncrease.indexOf(":"));
    }
    public String increase()
    {
        return statIncrease.substring(statIncrease.indexOf(":"));
    }
}
