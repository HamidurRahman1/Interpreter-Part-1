
package interpreterP1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class DivE extends FunExp
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
        map.put(getFunOp(), new IntVal(1));
        List<Val> l = new LinkedList<>();

        while(ne.expList != null)
        {
            l.add(ne.exp.Eval(map));
            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        Collections.reverse(l);

        for(int i = 0; i < l.size(); i++)
        {
            Class cls = l.get(i).getClass();
            Val v = map.get(getFunOp());
            if(cls == FloatVal.class || v.getClass() == FloatVal.class) // one of them is float
            {
                if(v.getClass() == FloatVal.class) // denom float
                {
                    if(l.get(i).getClass() == IntVal.class) // nume int
                    {
                        int f1 = (((IntVal) l.get(i))).val;
                        FloatVal f2 = (FloatVal)v;
                        map.put(getFunOp(), new FloatVal(f1/f2.val));
                    }
                    else                // nume float
                    {
                        float f1 = (((FloatVal) l.get(i))).val;
                        FloatVal f2 = (FloatVal)v;
                        map.put(getFunOp(), new FloatVal(f1/f2.val));
                    }
                }
                else // denom int
                {
                    if(l.get(i).getClass() == IntVal.class) // nume int
                    {
                        int f1 = (((IntVal) l.get(i))).val;
                        IntVal f2 = (IntVal)v;
                        map.put(getFunOp(), new IntVal(f1/f2.val));
                    }
                    else                // nume float
                    {
                        float f1 = (((FloatVal) l.get(i))).val;
                        IntVal f2 = (IntVal)v;
                        map.put(getFunOp(), new FloatVal(f1/f2.val));
                    }
                }
            }
            else
            {
                try
                {
                    if(l.get(i).getClass() == IntVal.class)
                    {
                        int f1 = (((IntVal) l.get(i))).val;
                        IntVal f2 = (IntVal)v;
                        map.put(getFunOp(), new IntVal(f1/f2.val));
                    }
                    else
                    {
                        int f1 = (int)(((FloatVal) l.get(i))).val;
                        IntVal f2 = (IntVal)v;
                        map.put(getFunOp(), new IntVal(f1/f2.val));
                    }
                }
                catch (ArithmeticException ex)
                {
                    System.out.println("Error: integer division by 0");
                    return null;
                }
            }
        }

        return map.get(getFunOp());
    }
}