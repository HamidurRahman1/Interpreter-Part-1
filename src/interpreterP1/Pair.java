
package interpreterP1;

import java.util.ArrayList;
import java.util.Map;

public class Pair extends FunExp
{
    public Pair(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "pair";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        ArrayList<Val> pv = new ArrayList<>();
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: pair operator missing arguments");
            return null;
        }
        NonEmptyExpList ne = (NonEmptyExpList)expList;
        while (ne.expList != null)
        {
            Val v = ne.exp.Eval(valMap);
            if(v != null)
            {
                pv.add(v);
            }
            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        for(int i = 0; i < pv.size(); i+=2)
        {
            try
            {
                valMap.put(getFunOp(), new PairVal(pv.get(i), pv.get(i+1)));
            }
            catch (Exception e)
            {
                System.out.println("Error: pair operator missing 2nd argument");
                return null;
            }
        }
        return valMap.get(getFunOp());
    }
}