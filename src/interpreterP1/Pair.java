
package interpreterP1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Pair extends FunExp
{
    Pair(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "pair";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        List<Val> pv = new LinkedList<>();

        NonEmptyExpList ne = (NonEmptyExpList)expList;
        while (ne.expList != null)
        {
            Val v = ne.exp.Eval(map);
            if(v != null)
            {
                pv.add(v);
            }
            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }
        for(int i = 0; i < pv.size(); i+=2)
        {
            map.put(getFunOp(), new PairVal(pv.get(i), pv.get(i+1)));
        }
        return map.get(getFunOp());
    }
}