public abstract class thing {
    public String name;
    public String status;
    public boolean renamable;

    public thing(String n, boolean ren) 
    {
        renamable = ren;
        name = n;
        status = "exists";
    }

    public String getName() {
        return name;
    }

    public boolean setName(String newName) {
        if (renamable)
            name = newName;
        return renamable;
    }

    public boolean isRenamable()
    {
        return renamable;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String stat)
    {
        status = stat;
    }
}
