
package interpreterP1;

import java.util.Map;

class AddE extends FunExp
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
    Val Eval(Map<String, Val> valMap)
    {
        if(expList.getClass() == EmptyExpList.class) return new IntVal(0);

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
                Val val = ne.exp.Eval(valMap);
                if(val == null) return null;
                if(val.getClass() == IntVal.class)
                {
                    t += ((IntVal)ne.exp.Eval(valMap)).val;
                }
                else if(val.getClass() == FloatVal.class)
                {
                    isInt = false;
                    t += ((FloatVal)ne.exp.Eval(valMap)).val;
                }
            }

            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        if(isInt) return new IntVal((int)t);
        else return new FloatVal(t);
    }
}