
package interpreterP1;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LtE extends FunExp
{
    LtE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "<";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        if(expList instanceof EmptyExpList)
        {
            return new BoolVal(true);
        }

        NonEmptyExpList ne = (NonEmptyExpList)expList;
        List<Val> e = new LinkedList<>();

        while (ne.expList != null)
        {
            Val v = ne.exp.Eval(map);
            e.add(v);
            if(ne.expList instanceof NonEmptyExpList) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        if(e.size() <= 1)
        {
            return new BoolVal(true);
        }
        else
        {
            FloatVal v;
            if(e.get(0).getClass() == IntVal.class)
            {
                v = new FloatVal(((IntVal)e.get(0)).val);
            }
            else v = (FloatVal) e.get(0);
            boolean q = true;
            for(int i = 1; i < e.size(); i++)
            {
                Class c = e.get(i).getClass();
                if(c == IntVal.class || c == FloatVal.class)
                {
                    FloatVal o;
                    if(e.get(i).getClass() ==  FloatVal.class)
                        o = new FloatVal(((FloatVal)e.get(i)).val);
                    else o = new FloatVal(((IntVal)e.get(i)).val);
                    if(v.val < o.val)
                    {
                        v = o;
                        q = true;
                        continue;
                    }
                    else
                    {
                        v = o;
                        q = false;
                    }
                }
            }
            if(q)
            {
                return new BoolVal(true);
            }
            return new BoolVal(false);
        }
    }
}