
package interpreterP1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GtE extends FunExp
{
    public GtE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return ">";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList instanceof EmptyExpList) return new BoolVal(true);

        NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
        List<Val> gtList = new LinkedList<>();

        while (nonEmptyExpList.expList != null)
        {
            gtList.add(nonEmptyExpList.exp.Eval(valMap));
            if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
            else break;
        }

        if(gtList.size() <= 1) return new BoolVal(true);
        else
        {
            FloatVal gt1;

            if(gtList.get(0) instanceof IntVal) gt1 = new FloatVal(((IntVal)gtList.get(0)).val);
            else if(gtList.get(0) instanceof FloatVal) gt1 = (FloatVal) gtList.get(0);
            else
            {
                System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + gtList.get(0));
                return null;
            }

            boolean gtFlag = true;

            for(int i = 1; i < gtList.size(); i++)
            {
                if(gtList.get(i) instanceof IntVal || gtList.get(i) instanceof FloatVal)
                {
                    FloatVal next;

                    if(gtList.get(i) instanceof FloatVal) next = new FloatVal(((FloatVal)gtList.get(i)).val);
                    else next = new FloatVal(((IntVal)gtList.get(i)).val);

                    if(gt1.val > next.val)
                    {
                        gt1 = next;
                        gtFlag = true;
                    }
                    else
                    {
                        gt1 = next;
                        gtFlag = false;
                    }
                }
                else
                {
                    System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + gtList.get(i));
                    return null;
                }
            }
            if(gtFlag) return new BoolVal(true);
            else return new BoolVal(false);
        }
    }
}