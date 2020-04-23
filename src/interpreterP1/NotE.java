
package interpreterP1;

import java.util.Map;

public class NotE extends FunExp
{
    public NotE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "not";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList instanceof EmptyExpList)
        {
            System.out.println("Error: " + getFunOp() + " operator missing argument");
            return null;
        }
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;

            while (nonEmptyExpList.exp != null)
            {
                valMap.put(getFunOp(), nonEmptyExpList.exp.Eval(valMap));

                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }

            valMap.replace(getFunOp(), new BoolVal(!Boolean.valueOf(valMap.get(getFunOp()).toString())));

            return valMap.get(getFunOp());
        }
    }
}