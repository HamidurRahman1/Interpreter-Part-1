
package interpreterP1;

import java.util.Map;

public class AddE extends FunExp
{
    AddE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "+";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        if(expList instanceof EmptyExpList)
        {
            return new IntVal(0); // when n = 0
        }

        NonEmptyExpList ne = (NonEmptyExpList) expList;

        float t = 0;
        boolean isInt = true;

        while(ne.expList != null)
        {
            Class cls = ne.exp.getClass();
            if(cls == Int.class)
            {
                Int v = (Int) ne.exp;
                t += v.intElem;
            }
            else if(cls == Floatp.class)
            {
                isInt = false;
                Floatp v = (Floatp) ne.exp;
                t += v.floatElem;
            }
            else
            {
                Val val = ne.exp.Eval(map);
                if(val.getClass() == IntVal.class)
                {
                    t += ((IntVal)ne.exp.Eval(map)).val;
                }
                else if(val.getClass() == FloatVal.class)
                {
                    isInt = false;
                    t += ((FloatVal)ne.exp.Eval(map)).val;
                }
                // implement for not Nil, Bool, Comp and others
            }

            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return isInt ? new IntVal((int)t) : new FloatVal(t);
    }
}