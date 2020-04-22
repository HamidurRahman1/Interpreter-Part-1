
package interpreterP1;

import java.util.Map;

class AndE extends FunExp
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
    Val Eval(Map<String, Val> valMap)
    {
        if(expList.getClass() == EmptyExpList.class) return new BoolVal(true);

        NonEmptyExpList ne = (NonEmptyExpList)expList;
        boolean isTrue = true;

        while(ne.expList != null)
        {
            Val val = ne.exp.Eval(valMap);
            if(val == null) return null;
            if(val.getClass() != BoolVal.class)
            {
                System.out.println("Error: and operator cannot be applied to "+val);
                return null;
            }
            if(val.getClass() == BoolVal.class)
            {
                if(!((BoolVal) val).val)
                {
                    isTrue = false;
                }
            }
            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }
        return new BoolVal(isTrue);
    }
}