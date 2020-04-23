
package interpreterP1;

import java.util.Map;

public class OrE extends FunExp
{
    public OrE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "or";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList instanceof EmptyExpList) return new BoolVal(false);

        NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
        boolean isFalse = false;

        while(nonEmptyExpList.expList != null)
        {
            Val val = nonEmptyExpList.exp.Eval(valMap);

            if(val == null) return null;
            if(!(val instanceof BoolVal))
            {
                System.out.println("Error: " + getFunOp() + " operator cannot be be applied to "+val);
                return null;
            }
            if(val instanceof BoolVal)
            {
                if(((BoolVal) val).val)
                    isFalse = true;
            }
            if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
            else break;
        }

        valMap.put(getFunOp(), new BoolVal(isFalse));
        return valMap.get(getFunOp());
    }
}