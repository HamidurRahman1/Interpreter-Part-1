
package interpreterP1;

import java.util.Map;

class First extends FunExp
{
    First(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "first";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        NonEmptyExpList ne = (NonEmptyExpList) expList;

        while (ne.expList != null)
        {
            PairVal v = (PairVal)ne.exp.Eval(map);
            map.put(getFunOp(), v.first);

            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return map.get(getFunOp());
    }
}