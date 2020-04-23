
package interpreterPart1;

import java.util.Map;

public class Second extends FunExp
{
    public Second(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "second";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList instanceof EmptyExpList)
        {
            System.out.println("Error: "+getFunOp()+" operator missing argument");
            return null;
        }
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;

            while (nonEmptyExpList.expList != null)
            {
                if(nonEmptyExpList.exp.Eval(valMap) instanceof PairVal)
                {
                    PairVal v = (PairVal)nonEmptyExpList.exp.Eval(valMap);
                    valMap.put(getFunOp(), v.second);
                }
                else
                {
                    System.out.println("Error: "+getFunOp()+" operator cannot be applied to "+nonEmptyExpList.exp.Eval(valMap));
                    return null;
                }

                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }

            return valMap.get(getFunOp());
        }
    }
}