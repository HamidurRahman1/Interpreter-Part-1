
package interpreterPart1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GeE extends FunExp
{
    public GeE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return ">=";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList instanceof EmptyExpList) return new BoolVal(true);
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
            List<Val> geList = new LinkedList<>();

            while (nonEmptyExpList.expList != null)
            {
                geList.add(nonEmptyExpList.exp.Eval(valMap));
                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }

            if(geList.size() <= 1) return new BoolVal(true);
            else
            {
                FloatVal ge1;
                if(geList.get(0) instanceof IntVal) ge1 = new FloatVal(((IntVal)geList.get(0)).val);
                else if(geList.get(0) instanceof FloatVal) ge1 = (FloatVal) geList.get(0);
                else
                {
                    System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + geList.get(0));
                    return null;
                }

                boolean geFlag = true;

                for(int i = 1; i < geList.size(); i++)
                {
                    if(geList.get(i) instanceof IntVal || geList.get(i) instanceof FloatVal)
                    {
                        FloatVal next;

                        if(geList.get(i) instanceof FloatVal) next = new FloatVal(((FloatVal)geList.get(i)).val);
                        else next = new FloatVal(((IntVal)geList.get(i)).val);

                        if(ge1.val >= next.val)
                        {
                            ge1 = next;
                            geFlag = true;
                        }
                        else
                        {
                            ge1 = next;
                            geFlag = false;
                        }
                    }
                    else
                    {
                        System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + geList.get(i));
                        return null;
                    }
                }
                if(geFlag) return new BoolVal(true);
                else return new BoolVal(false);
            }
        }
    }
}