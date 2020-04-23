
package interpreterPart1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeE extends FunExp
{
    public LeE(ExpList leList)
    {
        expList = leList;
    }

    public String getFunOp()
    {
        return "<=";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList instanceof EmptyExpList) return new BoolVal(true);
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
            List<Val> leList = new LinkedList<>();

            while (nonEmptyExpList.expList != null)
            {
                leList.add(nonEmptyExpList.exp.Eval(valMap));
                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }

            if(leList.size() <= 1) return new BoolVal(true);
            else
            {
                FloatVal le1;

                if(leList.get(0) instanceof IntVal) le1 = new FloatVal(((IntVal)leList.get(0)).val);
                else if(leList.get(0) instanceof FloatVal)le1 = (FloatVal) leList.get(0);
                else
                {
                    System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + leList.get(0));
                    return null;
                }

                boolean leFlag = true;

                for(int i = 1; i < leList.size(); i++)
                {
                    if(leList.get(i) instanceof IntVal || leList.get(i) instanceof FloatVal)
                    {
                        FloatVal next;

                        if(leList.get(i) instanceof FloatVal) next = new FloatVal(((FloatVal)leList.get(i)).val);
                        else next = new FloatVal(((IntVal)leList.get(i)).val);

                        if(le1.val <= next.val)
                        {
                            le1 = next;
                            leFlag = true;
                        }
                        else
                        {
                            le1 = next;
                            leFlag = false;
                        }
                    }
                    else
                    {
                        System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + leList.get(i));
                        return null;
                    }
                }
                if(leFlag) return new BoolVal(true);
                else return new BoolVal(false);
            }
        }
    }
}