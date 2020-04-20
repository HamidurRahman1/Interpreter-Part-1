
package interpreterP1;

import java.util.Map;

class Second extends FunExp
{
    Second(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "second";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        NonEmptyExpList ne = (NonEmptyExpList) expList;

        while (ne.expList != null)
        {
            PairVal v = (PairVal)ne.exp.Eval(map);
            map.put(getFunOp(), v.second);

            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return map.get(getFunOp());
    }
}