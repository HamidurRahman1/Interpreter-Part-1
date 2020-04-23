
package interpreterP1;

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
        valMap.put(getFunOp(), new BoolVal(true));

        if(expList instanceof EmptyExpList) return valMap.get(getFunOp());
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;

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
                        valMap.replace(getFunOp(), new BoolVal(((BoolVal) val).val));
                }

                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }
            return valMap.get(getFunOp());
        }
    }
}