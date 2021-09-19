import java.util.ArrayList;

public class entity extends thing {
    public double health;
    public double maxHealth;
    public boolean mortal;
    public boolean blinded;
    public boolean charmed;
    public boolean deafened;
    public boolean frightened;
    public boolean grappled;
    public boolean incapacitated;
    public boolean invisible;
    public boolean paralyzed;
    public boolean petrified;
    public boolean poisoned;
    public boolean prone;
    public boolean restrained;
    public boolean stunned;
    public boolean unconcious;
    public String race;
    public String size;
    public int speed;
    public String speedBonus;
    public int exhuastion;
    public ArrayList<String> resistances;
    public ArrayList<String> vulnerabilities;
    public ArrayList<String> immunities;
    public ArrayList<String> languages;
    public String languagesBonus;

    public entity(double mH, String n, boolean ren, String r) {
        super(n, ren);
        race = r;
        health = mH;
        maxHealth = mH;
        mortal = true;
        blinded = false;
        charmed = false;
        deafened = false;
        frightened = false;
        grappled = false;
        incapacitated = false;
        invisible = false;
        paralyzed = false;
        petrified = false;
        poisoned = false;
        prone = false;
        restrained = false;
        stunned = false;
        unconcious = false;
        exhuastion = 0;
    }

    public entity(double mH, boolean m, String n, boolean ren, String r) {
        this(mH, n, ren, r);
        mortal = m;
    }

    // returns whether or not the entity took the full damage (died).
    // returns true if alive
    // dead otherwise
    public boolean takeDamage(double damage) {
        health -= damage;
        return isDead();
    }

    public boolean takeDamage(double damage, String type)
    {
        for(int i = 0; i < resistances.size(); i++)
        {
            if(type.compareTo(resistances.get(i)) == 0)
            {
                return takeDamage(damage/2);
            }
        }
        for(int i = 0; i < vulnerabilities.size(); i++)
        {
            if(type.compareTo(vulnerabilities.get(i)) == 0)
            {
                return takeDamage(damage/2);
            }
        }
        for(int i = 0; i < immunities.size(); i++)
        {
            if(type.compareTo(immunities.get(i)) == 0)
            {
                return isDead();
            }
        }
        return takeDamage(damage);
    }

    public void setMaxHealth(int h) {
        maxHealth = h;
    }

    public boolean isDead() {
        return (health <= 0);
    }

    public boolean isMortal() {
        return mortal;
    }

    public void setMortal(boolean mortal) {
        this.mortal = mortal;
    }

    public boolean isBlinded() {
        return blinded;
    }

    public void setBlinded(boolean blinded) {
        this.blinded = blinded;
    }

    public boolean isCharmed() {
        return charmed;
    }

    public void setCharmed(boolean charmed) {
        this.charmed = charmed;
    }

    public boolean isDeafened() {
        return deafened;
    }

    public void setDeafened(boolean deafened) {
        this.deafened = deafened;
    }

    public boolean isFrightened() {
        return frightened;
    }

    public void setFrightened(boolean frightened) {
        this.frightened = frightened;
    }

    public boolean isGrappled() {
        return grappled;
    }

    public void setGrappled(boolean grappled) {
        this.grappled = grappled;
    }

    public boolean isIncapacitated() {
        return incapacitated;
    }

    public void setIncapacitated(boolean incapacitated) {
        this.incapacitated = incapacitated;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isParalyzed() {
        return paralyzed;
    }

    public void setParalyzed(boolean paralyzed) {
        this.paralyzed = paralyzed;
    }

    public boolean isPetrified() {
        return petrified;
    }

    public void setPetrified(boolean petrified) {
        this.petrified = petrified;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    public boolean isProne() {
        return prone;
    }

    public void setProne(boolean prone) {
        this.prone = prone;
    }

    public boolean isRestrained() {
        return restrained;
    }

    public void setRestrained(boolean restrained) {
        this.restrained = restrained;
    }

    public boolean isStunned() {
        return stunned;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }

    public boolean isUnconcious() {
        return unconcious;
    }

    public void setUnconcious(boolean unconcious) {
        this.unconcious = unconcious;
    }

    public int getExhuastion() {
        return exhuastion;
    }

    public void setExhuastion(int exhuastion) {
        this.exhuastion = exhuastion;
    }
}