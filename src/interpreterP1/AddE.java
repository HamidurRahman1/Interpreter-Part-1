
package interpreterP1;

import java.util.Map;

public class AddE extends FunExp
{
    public AddE(ExpList e)
    {
        expList = e;
    }

    public String getFunOp()
    {
        return "+";
    }

    @Override
    public Val Eval(Map<String, Val> valMap)
    {
        valMap.put(getFunOp(), new IntVal(0));

        if(expList instanceof EmptyExpList)
        {
            return valMap.get(getFunOp());
        }
        else
        {
            NonEmptyExpList nonEmptyExpList = (NonEmptyExpList) expList;

            while(nonEmptyExpList.expList != null)
            {
                if(nonEmptyExpList.exp instanceof Int)
                {
                    IntVal intVal = new IntVal(((Int) nonEmptyExpList.exp).intElem);
                    Val old = valMap.get(getFunOp());
                    valMap.replace(getFunOp(), old instanceof IntVal ? new IntVal(intVal.val+((IntVal) old).val)
                            : new FloatVal(intVal.val+((FloatVal) old).val));
                }
                else if(nonEmptyExpList.exp instanceof Floatp)
                {
                    FloatVal floatVal = new FloatVal(((Floatp) nonEmptyExpList.exp).floatElem);
                    Val old = valMap.get(getFunOp());
                    valMap.replace(getFunOp(), old instanceof FloatVal ? new FloatVal(floatVal.val+((FloatVal) old).val)
                            : new FloatVal(floatVal.val+((IntVal) old).val));
                }
                else
                {
                    Val old = valMap.get(getFunOp());
                    Val val = nonEmptyExpList.exp.Eval(valMap);
                    if(val == null)
                    {
                        return null;
                    }
                    else if(val instanceof IntVal)
                    {
                        valMap.replace(getFunOp(), old instanceof IntVal ? new IntVal(((IntVal) val).val+((IntVal) old).val)
                                : new FloatVal(((IntVal) val).val+((FloatVal) old).val));
                    }
                    else if(val instanceof FloatVal)
                    {
                        valMap.replace(getFunOp(), old instanceof FloatVal ? new FloatVal(((FloatVal) val).val+((FloatVal) old).val)
                                : new FloatVal(((FloatVal) val).val+((IntVal) old).val));
                    }
                    else
                    {
                        System.out.println("Error: " + getFunOp() + " operator cannot be applied to " + val);
                        return null;
                    }
                }

                if(nonEmptyExpList.expList instanceof NonEmptyExpList)
                {
                    nonEmptyExpList = (NonEmptyExpList)nonEmptyExpList.expList;
                }
                else
                {
                    break;
                }
            }

            return valMap.get(getFunOp());
        }
    }
}