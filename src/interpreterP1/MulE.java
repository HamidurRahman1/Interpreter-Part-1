
package interpreterP1;

import java.util.Map;

class MulE extends FunExp
{
    MulE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "*";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        if(expList instanceof EmptyExpList)
        {
            return new IntVal(1);
        }

        NonEmptyExpList ne = (NonEmptyExpList) expList;

        boolean isInt = true;

        float t = 1;

        while(ne.expList != null)
        {
            Class cls = ne.exp.getClass();
            if(cls == Int.class)
            {
                t *= ((Int) ne.exp).intElem;
            }
            else if(cls == Floatp.class)
            {
                isInt = false;
                t *= ((Floatp) ne.exp).floatElem;
            }
            else
            {
                Val val = ne.exp.Eval(map);
                if(val.getClass() == IntVal.class)
                {
                    t = ((IntVal)ne.exp.Eval(map)).val * t;
                }
                else if(val.getClass() == FloatVal.class)
                {
                    isInt = false;
                    t = ((FloatVal)ne.exp.Eval(map)).val * t;
                }
                else if(val.getClass() == BoolVal.class || val.getClass() == PairVal.class || val.getClass() == NilVal.class)
                {
                    System.out.println("operator * cannot be applied to "+val);
                    return null;
                }
            }

            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return isInt ? new IntVal((int)t) : new FloatVal(t);
    }
}