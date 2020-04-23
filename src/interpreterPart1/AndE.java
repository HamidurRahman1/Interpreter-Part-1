
package interpreterPart1;

import java.util.Map;

public class AndE extends FunExp
{
    public AndE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "and";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList instanceof EmptyExpList) return new BoolVal(true);
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
            boolean isTrue = true;

            while(nonEmptyExpList.expList != null)
            {
                Val val = nonEmptyExpList.exp.Eval(valMap);

                if(val == null) return null;
                if(!(val instanceof BoolVal))
                {
                    System.out.println("Error: "+ getFunOp() +" operator cannot be applied to "+val);
                    return null;
                }
                if(val instanceof BoolVal)
                {
                    if(!((BoolVal) val).val)
                        isTrue = false;
                }

                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }

            valMap.put(getFunOp(), new BoolVal(isTrue));
            return valMap.get(getFunOp());
        }
    }
}