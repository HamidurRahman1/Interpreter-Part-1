
package interpreterP1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EqE extends FunExp
{
    public EqE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "=";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        if(expList instanceof EmptyExpList) return new BoolVal(true);
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
            List<Val> eqList = new LinkedList<>();

            while (nonEmptyExpList.expList != null)
            {
                eqList.add(nonEmptyExpList.exp.Eval(valMap));
                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }

            if(eqList.size() <= 1) return new BoolVal(true);
            else
            {
                FloatVal eq1;

                if(eqList.get(0) instanceof IntVal) eq1 = new FloatVal(((IntVal)eqList.get(0)).val);
                else if(eqList.get(0) instanceof FloatVal) eq1 = (FloatVal) eqList.get(0);
                else if(eqList.get(0) instanceof PairVal)
                {
                    Val f = ((PairVal)eqList.get(0)).first;
                    Val s = ((PairVal)eqList.get(0)).second;
                    for(int i = 1; i < eqList.size(); i++)
                    {
                        PairVal p = (PairVal) eqList.get(i);
                        if(!(f.toString().equals(p.first.toString()) && s.toString().equals(p.second.toString())))
                            return new BoolVal(false);
                    }
                    return new BoolVal(true);
                }
                else
                {
                    System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + eqList.get(0));
                    return null;
                }

                boolean eqFlag = true;

                for(int i = 1; i < eqList.size(); i++)
                {
                    if(eqList.get(i) instanceof IntVal || eqList.get(i) instanceof FloatVal)
                    {
                        FloatVal next;

                        if(eqList.get(i) instanceof FloatVal) next = new FloatVal(((FloatVal)eqList.get(i)).val);
                        else next = new FloatVal(((IntVal)eqList.get(i)).val);

                        if(eq1.val == next.val)
                        {
                            eq1 = next;
                            eqFlag = true;
                        }
                        else
                        {
                            eq1 = next;
                            eqFlag = false;
                        }
                    }
                    else
                    {
                        System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + eqList.get(i));
                        return null;
                    }
                }
                if(eqFlag) return new BoolVal(true);
                else return new BoolVal(false);
            }
        }
    }
}