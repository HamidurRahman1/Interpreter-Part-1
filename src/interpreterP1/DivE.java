
package interpreterP1;

import java.util.Map;

public class DivE extends FunExp
{
    DivE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "/";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        if(expList instanceof EmptyExpList)
        {
            return new IntVal(1); // when n = 0
        }

        NonEmptyExpList ne = (NonEmptyExpList) expList;

        boolean isInt = true;

        float a = 1;

        while(ne.expList != null)
        {
            Class cls = ne.exp.getClass();
            if(cls == Int.class)
            {
                a = ((Int) ne.exp).intElem/a;
            }
            else if(cls == Floatp.class)
            {
                isInt = false;
                a = ((Floatp) ne.exp).floatElem/a;
            }
            else
            {
                Val val = ne.exp.Eval(map);
                if(val.getClass() == IntVal.class)
                {
                    a = ((IntVal)ne.exp.Eval(map)).val/a;
                }
                else if(val.getClass() == FloatVal.class)
                {
                    isInt = false;
                    a = ((FloatVal)ne.exp.Eval(map)).val/a;
                }
                // implement for not Nil, Bool, Comp and others
            }

            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return isInt ? new IntVal((int)a) : new FloatVal(a);
    }
}