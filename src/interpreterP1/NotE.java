
package interpreterP1;

import java.util.Map;

class NotE extends FunExp
{
    NotE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "not";
    }

    @Override
    Val Eval(Map<String, Val> valMap)
    {
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: not operator missing arguments");
            return null;
        }
        NonEmptyExpList ne = (NonEmptyExpList)expList;

        while (ne.exp != null)
        {
            valMap.put(getFunOp(), ne.exp.Eval(valMap));
            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        valMap.replace(getFunOp(), new BoolVal(!Boolean.valueOf(valMap.get(getFunOp()).toString())));

        return valMap.get(getFunOp());
    }
}