
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
            try {
                PairVal v = (PairVal)ne.exp.Eval(map);
                map.put(getFunOp(), v.second);
            }
            catch (Exception e)
            {
                System.out.println("Error: second operator cannot be applied to "+ne.exp.Eval(map));
                return null;
            }

            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return map.get(getFunOp());
    }
}