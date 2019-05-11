public class SoldierFactory
{
    public Soldier produceSoldier(int x,int y,int dir,int camp,String name)
    {
        if(name.equals("男拳頭士兵"))
        {
            return new Boy(x,y,dir,camp,new Boxing());
        }
        else if(name.equals("女拳頭士兵"))
        {
            return new Girl(x,y,dir,camp,new Boxing());
        }
        return null;
    }
}
