
package interpreterP1;

import java.util.Map;

public class Second extends FunExp
{
    public Second(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "second";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
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