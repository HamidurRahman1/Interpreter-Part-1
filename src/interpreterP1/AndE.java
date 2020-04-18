
package interpreterP1;

import java.util.Map;

public class AndE extends FunExp
{
    AndE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "and";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        map.put(getFunOp(), new BoolVal(true));

        if(expList instanceof EmptyExpList)
        {
            System.out.println("single and");
            return map.get(getFunOp());
        }

        NonEmptyExpList ne = (NonEmptyExpList)expList;
        boolean isTrue = true;

        while(ne.expList != null)
        {
            Val val = ne.exp.Eval(map);
            if(val instanceof BoolVal)
            {
                if(!((BoolVal) val).val)
                {
                    System.out.println(((BoolVal) val).val);
                    isTrue = false;
                }
            }
            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }
        return new BoolVal(isTrue);
    }
}