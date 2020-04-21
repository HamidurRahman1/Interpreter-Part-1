
package interpreterP1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
    Val Eval(HashMap<String, Val> state)
    {
        if(expList.getClass() == EmptyExpList.class) return new IntVal(1);

        NonEmptyExpList ne = (NonEmptyExpList) expList;
        state.put(getFunOp(), new IntVal(1));
        ArrayList<Val> l = new ArrayList<>();

        while(ne.expList != null)
        {
            l.add(ne.exp.Eval(state));
            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        ArrayList<Val> rArray = new ArrayList<>();
        for(int t = l.size()-1; t >= 0; t--) rArray.add(l.get(t));

        l = rArray;

        for(int i = 0; i < l.size(); i++)
        {
            Class cls = l.get(i).getClass();
            Val v = state.get(getFunOp());
            if(cls == FloatVal.class || v.getClass() == FloatVal.class)
            {
                if(v.getClass() == FloatVal.class)
                {
                    if(l.get(i).getClass() == IntVal.class)
                    {
                        int f1 = (((IntVal) l.get(i))).val;
                        FloatVal f2 = (FloatVal)v;
                        state.put(getFunOp(), new FloatVal(f1/f2.val));
                    }
                    else
                    {
                        float f1 = (((FloatVal) l.get(i))).val;
                        FloatVal f2 = (FloatVal)v;
                        state.put(getFunOp(), new FloatVal(f1/f2.val));
                    }
                }
                else
                {
                    if(l.get(i).getClass() == IntVal.class)
                    {
                        int f1 = (((IntVal) l.get(i))).val;
                        IntVal f2 = (IntVal)v;
                        state.put(getFunOp(), new IntVal(f1/f2.val));
                    }
                    else
                    {
                        float f1 = (((FloatVal) l.get(i))).val;
                        IntVal f2 = (IntVal)v;
                        state.put(getFunOp(), new FloatVal(f1/f2.val));
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
                        state.put(getFunOp(), new IntVal(f1/f2.val));
                    }
                    else
                    {
                        int f1 = (int)(((FloatVal) l.get(i))).val;
                        IntVal f2 = (IntVal)v;
                        state.put(getFunOp(), new IntVal(f1/f2.val));
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("Error: integer division by 0");
                    return null;
                }
            }
        }

        return state.get(getFunOp());
    }
}