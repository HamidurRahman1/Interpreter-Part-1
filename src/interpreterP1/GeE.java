
package interpreterP1;

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
        valMap.put(getFunOp(), new BoolVal(true));

        if(expList instanceof EmptyExpList) return valMap.get(getFunOp());
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

            if(geList.size() <= 1) return valMap.get(getFunOp());
            else
            {
                FloatVal fGe;
                if(geList.get(0) instanceof IntVal) fGe = new FloatVal(((IntVal)geList.get(0)).val);
                else if(geList.get(0) instanceof FloatVal) fGe = (FloatVal) geList.get(0);
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
                        else if(geList.get(i) instanceof IntVal) next = new FloatVal(((IntVal)geList.get(i)).val);
                        else
                        {
                            System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + geList.get(0));
                            return null;
                        }
                        if(fGe.val >= next.val)
                        {
                            fGe = next;
                            geFlag = true;
                        }
                        else
                        {
                            fGe = next;
                            geFlag = false;
                        }
                    }
                }
                if(geFlag) return valMap.replace(getFunOp(), new BoolVal(true));
                return valMap.replace(getFunOp(), new BoolVal(false));
            }
        }
    }
}