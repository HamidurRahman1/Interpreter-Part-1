
package interpreterP1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LtE extends FunExp
{
    public LtE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "<";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList instanceof EmptyExpList) return new BoolVal(true);
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
            List<Val> ltList = new LinkedList<>();

            while (nonEmptyExpList.expList != null)
            {
                ltList.add(nonEmptyExpList.exp.Eval(valMap));
                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }

            if(ltList.size() <= 1) return new BoolVal(true);
            else
            {
                FloatVal lt1;

                if(ltList.get(0) instanceof IntVal) lt1 = new FloatVal(((IntVal)ltList.get(0)).val);
                else if(ltList.get(0) instanceof FloatVal) lt1 = (FloatVal) ltList.get(0);
                else
                {
                    System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + ltList.get(0));
                    return null;
                }

                boolean ltFlag = true;

                for(int i = 1; i < ltList.size(); i++)
                {
                    if(ltList.get(i) instanceof IntVal || ltList.get(i) instanceof FloatVal)
                    {
                        FloatVal next;

                        if(ltList.get(i) instanceof FloatVal) next = new FloatVal(((FloatVal)ltList.get(i)).val);
                        else next = new FloatVal(((IntVal)ltList.get(i)).val);

                        if(lt1.val < next.val)
                        {
                            lt1 = next;
                            ltFlag = true;
                        }
                        else
                        {
                            lt1 = next;
                            ltFlag = false;
                        }
                    }
                    else
                    {
                        System.out.println("Error: "+getFunOp()+" operator cannot be applied to "+ltList.get(i));
                        return null;
                    }
                }
                if(ltFlag) return new BoolVal(true);
                else return new BoolVal(false);
            }
        }
    }
}