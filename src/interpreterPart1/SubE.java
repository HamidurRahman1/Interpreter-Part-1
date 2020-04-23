
package interpreterPart1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SubE extends FunExp
{
    public SubE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "-";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        valMap.put(getFunOp(), new IntVal(0));

        if(expList instanceof EmptyExpList) return valMap.get(getFunOp());
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;

            List<Val> subVal = new LinkedList<>();

            while(nonEmptyExpList.expList != null)
            {
                subVal.add(nonEmptyExpList.exp.Eval(valMap));

                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                {
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                }
                else
                {
                    break;
                }
            }

            for(int i = subVal.size()-1; i >= 0; i--)
            {
                if(subVal.get(i) instanceof IntVal)
                {
                    Val old = valMap.get(getFunOp());
                    valMap.replace(getFunOp(), old instanceof IntVal ? new IntVal(((IntVal)subVal.get(i)).val-((IntVal) old).val)
                            : new FloatVal(((IntVal)subVal.get(i)).val-((FloatVal) old).val));
                }
                else if(subVal.get(i) instanceof FloatVal)
                {
                    Val old = valMap.get(getFunOp());
                    valMap.replace(getFunOp(), old instanceof IntVal ? new FloatVal(((FloatVal)subVal.get(i)).val-((IntVal) old).val)
                            : new FloatVal(((FloatVal)subVal.get(i)).val-((FloatVal) old).val));
                }
                else
                {
                    Val old = valMap.get(getFunOp());
                    Val val = nonEmptyExpList.exp.Eval(valMap);
                    System.out.println(old+" - "+val);
                    if(val == null)
                    {
                        return null;
                    }
                    else if(val instanceof IntVal)
                    {
                        valMap.replace(getFunOp(), old instanceof IntVal ? new IntVal(((IntVal) val).val-((IntVal) old).val)
                                : new FloatVal(((IntVal) val).val-((FloatVal) old).val));
                    }
                    else if(val instanceof FloatVal)
                    {
                        valMap.replace(getFunOp(), old instanceof FloatVal ? new FloatVal(((FloatVal) val).val-((FloatVal) old).val)
                                : new FloatVal(((FloatVal) val).val-((IntVal) old).val));
                    }
                    else
                    {
                        System.out.println("Error: " + getFunOp() + " operator cannot be applied to " + val);
                        return null;
                    }
                }
            }

            return valMap.get(getFunOp());
        }
    }
}