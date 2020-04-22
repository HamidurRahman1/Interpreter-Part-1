
package interpreterP1;

import java.util.Map;

class OrE extends FunExp
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
    Val Eval(Map<String, Val> valMap)
    {
        if(expList.getClass() == EmptyExpList.class) return new BoolVal(false);

        NonEmptyExpList ne = (NonEmptyExpList)expList;
        boolean isFalse = false;

        while(ne.expList != null)
        {
            Val val = ne.exp.Eval(valMap);
            if(val == null) return null;
            if(val.getClass() != BoolVal.class)
            {
                return null;
            }
            if(val.getClass() == BoolVal.class)
            {
                if(((BoolVal) val).val) isFalse = true;
            }
            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return new BoolVal(isFalse);
    }
}