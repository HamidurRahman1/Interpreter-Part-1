
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
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: first operator missing arguments");
            return null;
        }
        NonEmptyExpList ne = (NonEmptyExpList) expList;

        while (ne.expList != null)
        {
            try
            {
                PairVal v = (PairVal)ne.exp.Eval(map);
                map.put(getFunOp(), v.first);
            }
            catch (Exception e)
            {
                System.out.println("Error: first operator cannot be applied to "+ne.exp.Eval(map));
                return null;
            }

            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return map.get(getFunOp());
    }
}