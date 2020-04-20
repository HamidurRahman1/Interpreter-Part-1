
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
    Val Eval(Map<String, Val> map)
    {
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: not operator missing arguments");
            return null;
        }
        NonEmptyExpList ne = (NonEmptyExpList)expList;

        while (ne.exp != null)
        {
            map.put(getFunOp(), ne.exp.Eval(map));
            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        map.replace(getFunOp(), new BoolVal(!Boolean.valueOf(map.get(getFunOp()).toString())));

        return map.get(getFunOp());
    }
}