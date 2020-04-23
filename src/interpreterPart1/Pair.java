
package interpreterPart1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Pair extends FunExp
{
    public Pair(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "pair";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        List<Val> pairList = new LinkedList<>();
        if(expList instanceof EmptyExpList)
        {
            System.out.println("Error: "+getFunOp()+" operator missing arguments");
            return null;
        }
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;

            while (nonEmptyExpList.expList != null)
            {
                Val val = nonEmptyExpList.exp.Eval(valMap);
                if(val != null) pairList.add(val);

                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }

            for(int i = 0; i < pairList.size(); i+=2)
            {
                try
                {
                    valMap.put(getFunOp(), new PairVal(pairList.get(i), pairList.get(i+1)));
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println("Error: "+getFunOp()+" operator missing 2nd argument");
                    return null;
                }
            }
            return valMap.get(getFunOp());
        }
    }
}