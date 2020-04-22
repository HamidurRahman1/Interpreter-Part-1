
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
    Val Eval(Map<String, Val> valMap)
    {
        if(expList.getClass() == EmptyExpList.class) return new IntVal(1);

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
                Val val = ne.exp.Eval(valMap);
                if(val.getClass() == IntVal.class)
                {
                    t = ((IntVal)ne.exp.Eval(valMap)).val * t;
                }
                else if(val.getClass() == FloatVal.class)
                {
                    isInt = false;
                    t = ((FloatVal)ne.exp.Eval(valMap)).val * t;
                }
                else if(val.getClass() == BoolVal.class || val.getClass() == PairVal.class || val.getClass() == NilVal.class)
                {
                    System.out.println("Error: operator * cannot be applied to "+val);
                    return null;
                }
            }

            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        if(isInt) return new IntVal((int)t);
        else return new FloatVal(t);
    }
}