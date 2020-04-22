
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
    Val Eval(Map<String, Val> valMap)
    {
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: second operator missing arguments");
            return null;
        }
        NonEmptyExpList ne = (NonEmptyExpList) expList;

        while (ne.expList != null)
        {
            try {
                PairVal v = (PairVal)ne.exp.Eval(valMap);
                valMap.put(getFunOp(), v.second);
            }
            catch (Exception e)
            {
                System.out.println("Error: second operator cannot be applied to "+ne.exp.Eval(valMap));
                return null;
            }

            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return valMap.get(getFunOp());
    }
}