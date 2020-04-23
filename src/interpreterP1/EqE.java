
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
        valMap.put(getFunOp(), new BoolVal(true));

        if(expList instanceof EmptyExpList) return valMap.get(getFunOp());
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList)expList;
            List<Val> eVal = new LinkedList<>();

            while (nonEmptyExpList.expList != null)
            {
                eVal.add(nonEmptyExpList.exp.Eval(valMap));
                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                else break;
            }

            if(eVal.size() <= 1) return valMap.get(getFunOp());
            else
            {
                FloatVal eq1 = null;
                if(eVal.get(0) instanceof IntVal) eq1 = new FloatVal(((IntVal)eVal.get(0)).val);
                else if(eVal.get(0) instanceof FloatVal) eq1 = (FloatVal) eVal.get(0);
                else if(eVal.get(0) instanceof PairVal)
                {
                    Val first = ((PairVal)eVal.get(0)).first;
                    Val second = ((PairVal)eVal.get(0)).second;
                    for(int i = 1; i < eVal.size(); i++)
                    {
                        PairVal pairVal = (PairVal) eVal.get(i);
                        if(!(first.toString().equals(pairVal.first.toString()) && second.toString().equals(pairVal.second.toString())))
                        {
                            valMap.replace(getFunOp(), new BoolVal(false));
                            return valMap.get(getFunOp());
                        }
                    }
                    valMap.replace(getFunOp(), new BoolVal(true));
                    return valMap.get(getFunOp());
                }
                else
                {
                    System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + eVal.get(0));
                    return null;
                }
                boolean eqFlag = true;
                for(int i = 1; i < eVal.size(); i++)
                {
                    if(eVal.get(i) instanceof IntVal || eVal.get(i) instanceof FloatVal)
                    {
                        FloatVal next = null;
                        if(eVal.get(i) instanceof FloatVal) next = new FloatVal(((FloatVal)eVal.get(i)).val);
                        else if(eVal.get(i) instanceof IntVal) next = new FloatVal(((IntVal)eVal.get(i)).val);
                        else
                        {
                            System.out.println("Error: "+getFunOp()+" operator cannot be applied to " + eVal.get(i));
                            return null;
                        }
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
                }
                if(eqFlag) return valMap.replace(getFunOp(), new BoolVal(true));
                return valMap.replace(getFunOp(), new BoolVal(false));
            }
        }
    }
}