
package interpreterP1;

import java.util.Map;

public class OrE extends FunExp
{
    OrE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "or";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        map.put(getFunOp(), new BoolVal(false));

        if(expList instanceof EmptyExpList)
        {
            return map.get(getFunOp());
        }

        NonEmptyExpList ne = (NonEmptyExpList)expList;
        boolean isFalse = false;

        while(ne.expList != null)
        {
            Val val = ne.exp.Eval(map);
            if(val instanceof BoolVal)
            {
                if(((BoolVal) val).val) isFalse = true;
            }
            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return new BoolVal(isFalse);
    }
}