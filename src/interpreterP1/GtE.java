
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
        valMap.put(getFunOp(), new BoolVal(true));

        if(expList instanceof EmptyExpList) return valMap.get(getFunOp());

        NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
        List<Val> gtList = new LinkedList<>();

        while (nonEmptyExpList.expList != null)
        {
            gtList.add(nonEmptyExpList.exp.Eval(valMap));
            if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
            else break;
        }

        if(gtList.size() <= 1) return valMap.get(getFunOp());
        else
        {
            FloatVal fG1;
            if(gtList.get(0) instanceof IntVal) fG1 = new FloatVal(((IntVal)gtList.get(0)).val);
            else if(gtList.get(0) instanceof FloatVal) fG1 = (FloatVal) gtList.get(0);
            else
            {
                System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + gtList.get(0));
                return null;
            }

            boolean gFlag = true;

            for(int i = 1; i < gtList.size(); i++)
            {
                if(gtList.get(i) instanceof IntVal || gtList.get(i) instanceof FloatVal)
                {
                    FloatVal next;
                    if(gtList.get(i) instanceof FloatVal) next = new FloatVal(((FloatVal)gtList.get(i)).val);
                    else if(gtList.get(i) instanceof IntVal) next = new FloatVal(((IntVal)gtList.get(i)).val);
                    else
                    {
                        System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + gtList.get(0));
                        return null;
                    }
                    if(fG1.val > next.val)
                    {
                        fG1 = next;
                        gFlag = true;
                    }
                    else
                    {
                        fG1 = next;
                        gFlag = false;
                    }
                }
            }
            if(gFlag) return valMap.replace(getFunOp(), new BoolVal(true));
            return valMap.replace(getFunOp(), new BoolVal(false));
        }
    }
}