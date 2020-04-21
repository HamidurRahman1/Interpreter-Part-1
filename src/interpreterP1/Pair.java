
package interpreterP1;

import java.util.ArrayList;
import java.util.HashMap;

class Pair extends FunExp
{
    Pair(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "pair";
    }

    @Override
    Val Eval(HashMap<String, Val> state)
    {
        ArrayList<Val> pv = new ArrayList<>();
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: pair operator missing arguments");
            return null;
        }
        NonEmptyExpList ne = (NonEmptyExpList)expList;
        while (ne.expList != null)
        {
            Val v = ne.exp.Eval(state);
            if(v != null)
            {
                pv.add(v);
            }
            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        for(int i = 0; i < pv.size(); i+=2)
        {
            try
            {
                state.put(getFunOp(), new PairVal(pv.get(i), pv.get(i+1)));
            }
            catch (Exception e)
            {
                System.out.println("Error: pair operator missing 2nd argument");
                return null;
            }
        }
        return state.get(getFunOp());
    }
}