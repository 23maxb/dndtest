import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StatusEffect {
    public String name;
    public int durationTurns;
    public int durationHours;
    public boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationTurns() {
        return durationTurns;
    }

    public void setDurationTurns(int durationTurns) {
        this.durationTurns = durationTurns;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ArrayList<String> getBonus() {
        return bonus;
    }

    public void setBonus(ArrayList<String> bonus) {
        this.bonus = bonus;
    }

    public String source;
    public ArrayList<String> bonus;
    public int turnsActive;
    public int hoursActive;

    public StatusEffect(String name, int durationTurns, int durationHours, String source, String bonus) {
        this.name = name;
        this.durationTurns = durationTurns;
        this.bonus = new ArrayList<String>(Collections.singleton(bonus));
        this.source = source;
        update();
    }

    public StatusEffect(String name, int durationTurns, int durationHours, String source, String @NotNull ... bonus) {
        this.name = name;
        this.durationTurns = durationTurns;
        this.bonus = new ArrayList<String>();
        this.bonus.addAll(Arrays.asList(bonus));
        this.source = source;
        update();
    }

    public void hourTick(int hours) {
        hoursActive += hours;
        update();
    }

    public void hourTick() {
        hourTick(1);

    }

    //returns whether or not the effect is worn off.
    //returns true if the effect is still active
    public boolean update() {
        if ((hoursLeft() <= 0 || turnsLeft() <= 0))
            active = false;
        return (hoursLeft() <= 0 || turnsLeft() <= 0);
    }

    public void turnTick() {
        turnsActive++;
        update();
    }

    public void turnTick(int turns) {
        turnsActive += turns;
        update();
    }

    public boolean isHourly() {
        return durationHours != -1;
    }

    public boolean isTurnBased() {
        return durationTurns != -1;
    }

    public boolean isInfinite() {
        return !(isHourly() || isTurnBased());
    }

    public int turnsLeft() {
        if (isTurnBased())
            return turnsActive - durationTurns;
        return Integer.MAX_VALUE;
    }

    public int hoursLeft() {
        return isHourly() ? hoursActive - durationHours : Integer.MAX_VALUE;
    }
}
