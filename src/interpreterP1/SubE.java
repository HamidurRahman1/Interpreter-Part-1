
package interpreterP1;

import java.util.Map;

class SubE extends FunExp
{
    SubE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "-";
    }

    @Override
    Val Eval(Map<String, Val> valMap)
    {
        if(expList.getClass() == EmptyExpList.class) return new IntVal(0);

        float t = 0;

        NonEmptyExpList ne = (NonEmptyExpList) expList;

        boolean isInt = true;

        while(ne.expList != null)
        {
            Class cls = ne.exp.getClass();
            if(cls == Int.class)
            {
                t = ((Int) ne.exp).intElem - t;
            }
            else if(cls == Floatp.class)
            {
                isInt = false;
                t = ((Floatp) ne.exp).floatElem - t;
            }
            else
            {
                Val val = ne.exp.Eval(valMap);
                if(val.getClass() == IntVal.class)
                {
                    t = ((IntVal)ne.exp.Eval(valMap)).val - t;
                }
                else if(val.getClass() == FloatVal.class)
                {
                    isInt = false;
                    t = ((FloatVal)ne.exp.Eval(valMap)).val - t;
                }
            }

            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }
        if(isInt) return new IntVal((int)t);
        else return new FloatVal(t);
    }
}